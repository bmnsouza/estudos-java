package bmnsouza.database.nota.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bmnsouza.exception.ServiceException;
import bmnsouza.constant.SorteioConstant;
import bmnsouza.constant.SorteioPremioConstant;
import bmnsouza.database.nota.entity.Bilhete;
import bmnsouza.database.nota.entity.Sorteio;
import bmnsouza.database.nota.entity.SorteioPremio;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarPercentualEntidadeDTO;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarQtdeConsumidorPorSorteioDTO;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteBuscarQtdeEntidadeDTO;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteSortearDTO;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioDTO;
import bmnsouza.database.nota.repository.BilheteRepository;
import bmnsouza.database.nota.repository.SorteioPremioRepository;
import bmnsouza.database.nota.repository.SorteioRepository;
import bmnsouza.util.DataUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class BilheteService {

	@Autowired
	private BilheteRepository bilheteRepository;

	@Autowired
	private SorteioRepository sorteioRepository;

	@Autowired
	private SorteioPremioRepository sorteioPremioRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPremiadosPorSorteio(Integer codSorteio, int pagina) {
		Slice<Bilhete> dados = bilheteRepository.buscarPremiadosPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarConsumidorPorSorteio(Integer codSorteio, String cpfConsumidor, int pagina) {
		Slice<Bilhete> dados = bilheteRepository.buscarConsumidorPorSorteio(codSorteio, cpfConsumidor, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarEntidadePorSorteio(Integer codSorteio, @CNPJ String cnpjEntidade, int pagina) {
		Slice<Bilhete> dados = bilheteRepository.buscarEntidadePorSorteio(codSorteio, cnpjEntidade, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarBilhetesPorSorteio(Integer codSorteio, int pagina) {
		Slice<Bilhete> dados = bilheteRepository.buscarBilhetesPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarQtdeBilhetesPorDatas(LocalDate dataInicio, LocalDate dataFim) throws ServiceException {
		if (dataInicio.isAfter(dataFim)) {
			throw new ServiceException("Data início não pode ser depois da data fim");
		}
				
		int quantidadePorDatas = bilheteRepository.buscarQtdeBilhetesPorDatas(dataInicio, dataFim);
		Map<String, Integer> dados = new HashMap<>();
		dados.put("quantidade", quantidadePorDatas);

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarQtdeConsumidorPorSorteio(int codSorteio, int pagina) {
		Slice<BilheteBuscarQtdeConsumidorPorSorteioDTO> dados = bilheteRepository.buscarQtdeConsumidorPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPercentualEntidadeBilhete(int codSorteio, int pagina) {
		Slice<BilheteBuscarPercentualEntidadeDTO> dados = bilheteRepository.buscarPercentualEntidadeBilhete(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPercentualEntidadeIndicacao(int codSorteio, int pagina) {
		Slice<BilheteBuscarPercentualEntidadeDTO> dados = bilheteRepository.buscarPercentualEntidadeIndicacao(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> gerar(SorteioDTO sorteioDTO) throws SQLException, SQLServerException, ServiceException {
		final String COD_SORTEIO = "codSorteio";
		
		// Verifica se o sorteio está cadastrado
		Sorteio sorteio = sorteioRepository.buscarPorCodigo(sorteioDTO.getCodSorteio());
		if (sorteio == null) {
			throw new ServiceException(COD_SORTEIO, "Sorteio não cadastrado");
		}

		// Verifica se os prêmios do sorteios estão cadastrados
		if (sorteioPremioRepository.buscarQtdePremiosPorSorteio(sorteioDTO.getCodSorteio()) <= 0) {
			throw new ServiceException(COD_SORTEIO, "Sorteio não possui prêmios cadastrados");
		}

		// Verifica se o sorteio possui bilhetes gerados
		if (bilheteRepository.buscarQtdeBilhetesPorSorteio(sorteioDTO.getCodSorteio()) > 0) {
			throw new ServiceException(COD_SORTEIO, "Sorteio já possui bilhetes gerados");
		}

		// Verifica se o sorteio possui bilhetes disponíveis para geração
		final int quantidadePorDatas = bilheteRepository.buscarQtdeBilhetesPorDatas(sorteio.getDataInicio(), sorteio.getDataFim());
		if (quantidadePorDatas <= 0) {
			throw new ServiceException(COD_SORTEIO, new StringBuilder("No período de ").append(sorteio.getDataInicio()).append(" a ")
				.append(sorteio.getDataFim()).append(" não há notas disponíveis para geração de bilhetes").toString());
		}

		// String formada pelo código do sorteio + data no formato DDMMAAAA
		final String sementeBilhete = new StringBuilder(StringUtils.leftPad(String.valueOf(sorteioDTO.getCodSorteio()), 3, '0')).append(DataUtil.formatar(LocalDate.now(), 
			DataUtil.DDMMAAAA_BASICO)).toString();

		// Código hash da string semente que será utilizado na geração dos números randômicos para embaralhamento dos bilhetes
		final int hashCodeSementeBilhete = sementeBilhete.hashCode();

		// Gera os números dos bilhetes
		int [] numeros = new int[quantidadePorDatas];
		Arrays.setAll(numeros, i -> i + 1);

		// Embaralha os números dos bilhetes a partir de números randômicos baseados no código hash da semente
		Random random = new Random(hashCodeSementeBilhete);
		for (int i = 0; i < quantidadePorDatas; i++) {
			int next = random.nextInt(quantidadePorDatas);
			int temp = numeros[i];
			numeros[i] = numeros[next];
			numeros[next] = temp;
		}

		// Atribui os bilhetes embaralhados à lista de bilhetes
		List<Bilhete> listaBilhetes = new ArrayList<>();
		for (int i = 0; i < quantidadePorDatas; i++) {
			listaBilhetes.add(new Bilhete(numeros[i], sorteioDTO.getCodSorteio(), null, null, null));
		}
		
		// Obtém a quantidade de bilhetes e entidade de cada consumidor
		List<BilheteBuscarQtdeEntidadeDTO> listaBilhetesConsumidor = bilheteRepository.buscarQtdeEntidade(sorteio.getDataInicio(), sorteio.getDataFim());

		// Distribui a quantidade de bilhetes e entidade de cada consumidor na lista de bilhetes
		int j = 0;
		for (BilheteBuscarQtdeEntidadeDTO consumidorQuantidadeBilheteEntidade : listaBilhetesConsumidor) {
			for (int i = 0; i < consumidorQuantidadeBilheteEntidade.getQuantidade(); i++) {
				listaBilhetes.get(j).setCpfConsumidor(consumidorQuantidadeBilheteEntidade.getCpfConsumidor());
				listaBilhetes.get(j).setCnpjEntidade(consumidorQuantidadeBilheteEntidade.getCnpjEntidade());
				j++;
			}
		}

		// Verifica a quantidade de bilhetes gerados com a quantidade de bilhetes distribuídos
		if (j != listaBilhetes.size()) {
			throw new ServiceException("Quantidade de bilhetes gerados é diferente da quantidade a ser distribuída");
		}

		// Cadastra os bilhetes
		bilheteRepository.cadastrar(listaBilhetes);

		// Atualiza o sorteio com a quantidade de bilhetes, semente dos bilhetes, código hash da semente e status
		sorteioRepository.atualizar(sorteio.getCodSorteio(), 1, quantidadePorDatas, sementeBilhete, hashCodeSementeBilhete, SorteioConstant.BILHETES_GERADOS,
			sorteio.getDataRealizacao(), sorteio.getConcursoLoteria(), sorteio.getSementeSorteio(), sorteio.getPremiosLoteria(),
			sorteio.getHashCodeSementeSorteio());

		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> sortear(BilheteSortearDTO bilheteSortearDTO) throws ServiceException {
		final String COD_SORTEIO = "codSorteio";

		// Verifica se o sorteio está cadastrado
		Sorteio sorteio = sorteioRepository.buscarPorCodigo(bilheteSortearDTO.getCodSorteio());
		if (sorteio == null) {
			throw new ServiceException(COD_SORTEIO, "Sorteio não cadastrado");
		}

		// Verifica se os prêmios do sorteios estão cadastrados
		final int quantidadePremiosPorSorteio = sorteioPremioRepository.buscarQtdePremiosPorSorteio(bilheteSortearDTO.getCodSorteio());
		if (quantidadePremiosPorSorteio <= 0) {
			throw new ServiceException(COD_SORTEIO, "Sorteio não possui prêmios cadastrados");
		}

		// Verifica se o sorteio não possui bilhetes gerados
		final int quantidadePorSorteio = bilheteRepository.buscarQtdeBilhetesPorSorteio(bilheteSortearDTO.getCodSorteio());
		if (quantidadePorSorteio <= 0) {
			throw new ServiceException(COD_SORTEIO, "Sorteio não possui bilhetes gerados");
		}

		final int quantidadePremiadosPorSorteio = bilheteRepository.buscarQtdePremiadosPorSorteio(bilheteSortearDTO.getCodSorteio());
		if (quantidadePremiadosPorSorteio > 0) {
			throw new ServiceException(COD_SORTEIO, "Sorteio já possui bilhetes premiados");
		}

		// String formada pelos 5 prêmios do concurso
		String premiosLoteria = new StringBuilder(bilheteSortearDTO.getPremioLoteria1()).append(bilheteSortearDTO.getPremioLoteria2())
			.append(bilheteSortearDTO.getPremioLoteria3()).append(bilheteSortearDTO.getPremioLoteria4()).append(bilheteSortearDTO.getPremioLoteria5()).toString();
		String sementeSorteio = "";

		// Extrai da String premiosLoteria os dígitos das posições ímpares
		for (int i = 0; i < premiosLoteria.length(); i++) {
			if (i % 2 == 0) {
				sementeSorteio += premiosLoteria.charAt(i);
			}
		}

		// Código hash da string semente que será utilizado na geração dos números randômicos para embaralhamento dos bilhetes
		final int hashCodeSementeSorteio = sementeSorteio.hashCode();

		// Verifica se existe prêmios para Consumidor e realiza o sorteio
		List<SorteioPremio> sorteioPremioConsumidor = sorteioPremioRepository.buscarPorSorteioTipoPremio(bilheteSortearDTO.getCodSorteio(), SorteioPremioConstant.TIPO_PREMIO_CONSUMIDOR);
		if (sorteioPremioConsumidor.size() > 0) {
			sortearBilhetes(hashCodeSementeSorteio, bilheteRepository.buscarConsumidorSemPremioPorSorteio(bilheteSortearDTO.getCodSorteio()), sorteioPremioConsumidor);
		}

		// Verifica se existe prêmios para Entidade e realiza o sorteio
		List<SorteioPremio> sorteioPremioEntidade = sorteioPremioRepository.buscarPorSorteioTipoPremio(bilheteSortearDTO.getCodSorteio(), SorteioPremioConstant.TIPO_PREMIO_ENTIDADE);
		if (sorteioPremioEntidade.size() > 0) {
			sortearBilhetes(hashCodeSementeSorteio, bilheteRepository.buscarEntidadeSemPremioPorSorteio(bilheteSortearDTO.getCodSorteio()), sorteioPremioEntidade);
		}

		// Atualiza o sorteio com status, data da realização, concurso da loteria, prêmios da loteria, semente do sorteio e código hash da semente
		sorteioRepository.atualizar(sorteio.getCodSorteio(), sorteio.getFaixaInicio(), sorteio.getFaixaFim(), sorteio.getSementeBilhete(),
			sorteio.getHashCodeSementeBilhete(), SorteioConstant.SORTEIO_REALIZADO, LocalDate.now(), bilheteSortearDTO.getConcursoLoteria(),
			premiosLoteria, sementeSorteio, hashCodeSementeSorteio);

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	// Sortea os bilhetes
	private void sortearBilhetes(int hashCodeSemente, List<Bilhete> listaBilhetesSemPremio, List<SorteioPremio> sorteioPremio) throws ServiceException {
		Random random = new Random(hashCodeSemente);
		List<String> listaCpfCnpjGanhadores = new ArrayList<>();
		List<Bilhete> listaBilhetesComPremio = new ArrayList<>();

		int pos = 0;
		while (pos < sorteioPremio.size()) {
			int nextInt = random.nextInt(listaBilhetesSemPremio.size());
			String cpfCnpjGanhador = null;

			if (sorteioPremio.get(pos).getTipoPremio() == SorteioPremioConstant.TIPO_PREMIO_CONSUMIDOR) {
				cpfCnpjGanhador = listaBilhetesSemPremio.get(nextInt).getCpfConsumidor();
			} else {
				cpfCnpjGanhador = listaBilhetesSemPremio.get(nextInt).getCnpjEntidade();
			}

			// Verifica se o cpfCnpjGanhador não foi sorteado para adicioná-lo à lista
			if (!listaCpfCnpjGanhadores.contains(cpfCnpjGanhador)) {
				listaCpfCnpjGanhadores.add(cpfCnpjGanhador);

				Bilhete bilhete = new Bilhete(
					listaBilhetesSemPremio.get(nextInt).getNumBilhete(), 
					sorteioPremio.get(pos).getCodSorteio(),
					sorteioPremio.get(pos).getCodPremio(), 
					listaBilhetesSemPremio.get(nextInt).getCpfConsumidor(), 
					listaBilhetesSemPremio.get(nextInt).getCnpjEntidade());

				listaBilhetesComPremio.add(bilhete);
				pos++;
			}
		}

		// Atualiza os sorteio
		bilheteRepository.atualizar(listaBilhetesComPremio);
	}

}