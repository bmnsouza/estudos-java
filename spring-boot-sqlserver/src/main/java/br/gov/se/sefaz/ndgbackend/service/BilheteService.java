package br.gov.se.sefaz.ndgbackend.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.se.sefaz.ndgbackend.core.Data;
import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.BilheteModel;
import br.gov.se.sefaz.ndgbackend.model.ConsumidorQuantidadeBilheteEntidadeModel;
import br.gov.se.sefaz.ndgbackend.model.SorteioModel;
import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;
import br.gov.se.sefaz.ndgbackend.repository.BilheteRepository;
import br.gov.se.sefaz.ndgbackend.repository.ConsumidorQuantidadeBilheteEntidadeRepository;
import br.gov.se.sefaz.ndgbackend.repository.SorteioPremioRepository;
import br.gov.se.sefaz.ndgbackend.repository.SorteioRepository;

@Service
@Validated
public class BilheteService {

  @Autowired
  private BilheteRepository bilheteRepository;

  @Autowired
  private SorteioRepository sorteioRepository;

  @Autowired
  private SorteioPremioRepository sorteioPremioRepository;

  @Autowired
  private ConsumidorQuantidadeBilheteEntidadeRepository consumidorQuantidadeBilheteEntidadeRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  // Tipo de prêmios
  private final char TP_PREMIO_CONSUMIDOR = 'C';
  private final char TP_PREMIO_ENTIDADE = 'E';

  public ResponseEntity<EntidadeResult> buscaPremiadosPorSorteio(@Positive Integer codSorteio, @PositiveOrZero int pagina) {
    Slice<BilheteModel> slice = bilheteRepository.buscaPremiadosPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaConsumidorPorSorteio(@Positive Integer codSorteio, @CPF String cpfConsumidor, @PositiveOrZero int pagina) {
    Slice<BilheteModel> slice = bilheteRepository.buscaConsumidorPorSorteio(codSorteio, cpfConsumidor, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaEntidadePorSorteio(@Positive Integer codSorteio, @CNPJ String cnpjEntidade, @PositiveOrZero int pagina) {
    Slice<BilheteModel> slice = bilheteRepository.buscaEntidadePorSorteio(codSorteio, cnpjEntidade, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorSorteio(@Positive Integer codSorteio, @PositiveOrZero int pagina) {
    Slice<BilheteModel> slice = bilheteRepository.buscaPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaQuantidadePorDatas(String dataInicio, String dataFim) throws TransactionAbortException {
    final String INVALIDA = "inválida";
    
    if (!Data.isValida(dataInicio)) {
      throw new TransactionAbortException("dataInicio", INVALIDA);
    }

    if (!Data.isValida(dataFim)) {
      throw new TransactionAbortException("dataFim", INVALIDA);
    }

    final int quantidadePorDatas = bilheteRepository.buscaQuantidadePorDatas(dataInicio, dataFim);
    Map<String, Integer> hashMap = new HashMap<>();
    hashMap.put("quantidade", quantidadePorDatas);

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, hashMap);
  }

  public ResponseEntity<EntidadeResult> gera(@Positive Integer codSorteio) throws SQLException, SQLServerException, TransactionAbortException {
    final String COD_SORTEIO = "codSorteio";

    // Verifica se o sorteio está cadastrado
    SorteioModel sorteioModel = sorteioRepository.buscaPorCodigo(codSorteio);
    if (sorteioModel == null) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio não cadastrado");
    }

    // Verifica se os prêmios do sorteios estão cadastrados
    final int quantidadePremiosPorSorteio = sorteioPremioRepository.buscaQuantidadePremiosPorSorteio(codSorteio);
    if (quantidadePremiosPorSorteio <= 0) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio não possui prêmios cadastrados");
    }

    // Verifica se o sorteio possui bilhetes gerados
    final int quantidadePorSorteio = bilheteRepository.buscaQuantidadePorSorteio(codSorteio);
    if (quantidadePorSorteio > 0) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio já possui bilhetes gerados");
    }

    // Verifica se o sorteio possui bilhetes disponíveis para geração
    final int quantidadePorDatas = bilheteRepository.buscaQuantidadePorDatas(sorteioModel.getDataInicio(), sorteioModel.getDataFim());
    if (quantidadePorDatas <= 0) {
      throw new TransactionAbortException(COD_SORTEIO, new StringBuilder("No período de ").append(sorteioModel.getDataInicio()).append(" a ")
        .append(sorteioModel.getDataFim()).append(" não há notas disponíveis para geração de bilhetes").toString());
    }

    // String formada pelo código do sorteio + data no formato DDMMAAAA
    final String sementeBilhete = new StringBuilder(StringUtils.leftPad(String.valueOf(codSorteio), 3, '0')).append(Data.formata(LocalDate.now(), 
      Data.DDMMAAAA_BASICA)).toString();

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
    List<BilheteModel> listaBilhetes = new ArrayList<>();
    for (int i = 0; i < quantidadePorDatas; i++) {
      BilheteModel bilheteModel = new BilheteModel();
      bilheteModel.setNumBilhete(numeros[i]);
      bilheteModel.setCodSorteio(codSorteio);
      bilheteModel.setCodPremio(null);
      bilheteModel.setCpfConsumidor(null);
      bilheteModel.setCnpjEntidade(null);
      listaBilhetes.add(bilheteModel);
    }

    // Obtém a quantidade de bilhetes e entidade de cada consumidor
    List<ConsumidorQuantidadeBilheteEntidadeModel> listaBilhetesConsumidor = consumidorQuantidadeBilheteEntidadeRepository.buscaQuantidade(
      sorteioModel.getDataInicio(), sorteioModel.getDataFim());

    // Distribui a quantidade de bilhetes e entidade de cada consumidor na lista de bilhetes
    int j = 0;
    for (ConsumidorQuantidadeBilheteEntidadeModel consumidorQuantidadeBilheteEntidadeModel : listaBilhetesConsumidor) {
      for (int i = 0; i < consumidorQuantidadeBilheteEntidadeModel.getQuantidade(); i++) {
        listaBilhetes.get(j).setCpfConsumidor(consumidorQuantidadeBilheteEntidadeModel.getCpfConsumidor());
        listaBilhetes.get(j).setCnpjEntidade(consumidorQuantidadeBilheteEntidadeModel.getCnpjEntidade());
        j++;
      }
    }

    // Verifica a quantidade de bilhetes gerados com a quantidade de bilhetes distribuídos
    if (j != listaBilhetes.size()) {
      throw new TransactionAbortException("Quantidade de bilhetes gerados é diferente da quantidade de bilhetes a serem distribuídos");
    }

    // Cadastra os bilhetes
    int linhasAfetadas = bilheteRepository.cadastra(listaBilhetes);

    // Atualiza o sorteio com a quantidade de bilhetes, semente dos bilhetes, código hash da semente e status
    sorteioRepository.atualiza(sorteioModel.getCodSorteio(), 1, quantidadePorDatas, sementeBilhete, hashCodeSementeBilhete, 1, sorteioModel.getDataRealizacao(),
      sorteioModel.getConcursoLoteria(), sorteioModel.getSementeSorteio(), sorteioModel.getPremiosLoteria(), sorteioModel.getHashCodeSementeSorteio());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> sorteia(@Positive int codSorteio, @Positive Integer concursoLoteria, String premioLoteria1, String premioLoteria2,
    String premioLoteria3, String premioLoteria4, String premioLoteria5) throws TransactionAbortException {
    final String COD_SORTEIO = "codSorteio";

    // Verifica se o sorteio está cadastrado
    SorteioModel sorteioModel = sorteioRepository.buscaPorCodigo(codSorteio);
    if (sorteioModel == null) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio não cadastrado");
    }

    // Verifica se os prêmios do sorteios estão cadastrados
    final int quantidadePremiosPorSorteio = sorteioPremioRepository.buscaQuantidadePremiosPorSorteio(codSorteio);
    if (quantidadePremiosPorSorteio <= 0) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio não possui prêmios cadastrados");
    }

    // Verifica se o sorteio não possui bilhetes gerados
    final int quantidadePorSorteio = bilheteRepository.buscaQuantidadePorSorteio(codSorteio);
    if (quantidadePorSorteio <= 0) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio não possui bilhetes gerados");
    }

    final int quantidadePremiadosPorSorteio = bilheteRepository.buscaQuantidadePremiadosPorSorteio(codSorteio);
    if (quantidadePremiadosPorSorteio > 0) {
      throw new TransactionAbortException(COD_SORTEIO, "Sorteio já possui bilhetes premiados");
    }

    // String formada pelos 5 prêmios do concurso
    String premiosLoteria = new StringBuilder(premioLoteria1).append(premioLoteria2).append(premioLoteria3).append(premioLoteria4)
      .append(premioLoteria5).toString();
    String sementeSorteio = "";

    // Extrai da String premiosLoteria os dígitos das posições ímpares
    for (int i = 0; i < premiosLoteria.length(); i++) {
      if (i % 2 == 0) {
        sementeSorteio += premiosLoteria.charAt(i);
      }
    }

    // Código hash da string semente que será utilizado na geração dos números randômicos para embaralhamento dos bilhetes
    final int hashCodeSementeSorteio = sementeSorteio.hashCode();

    // Armazena a quantidade de linhas afetadas em cada sorteio
    int linhasAfetadas = 0;

    // Realiza o sorteio do Consumidor
    List<SorteioPremioModel> sorteioPremioConsumidor = sorteioPremioRepository.buscaPorSorteioTipoPremio(codSorteio, TP_PREMIO_CONSUMIDOR);
    if (sorteioPremioConsumidor.size() > 0) {
      linhasAfetadas += sorteiaBilhetes(hashCodeSementeSorteio, bilheteRepository.buscaConsumidorSemPremioPorSorteio(codSorteio), sorteioPremioConsumidor);
    }

    // Realiza o sorteio da Entidade
    List<SorteioPremioModel> sorteioPremioEntidade = sorteioPremioRepository.buscaPorSorteioTipoPremio(codSorteio, TP_PREMIO_ENTIDADE);
    if (sorteioPremioEntidade.size() > 0) {
      linhasAfetadas += sorteiaBilhetes(hashCodeSementeSorteio, bilheteRepository.buscaEntidadeSemPremioPorSorteio(codSorteio), sorteioPremioEntidade);
    }

    // Atualiza o sorteio com status, data da realização, concurso da loteria, prêmios da loteria, semente do sorteio e código hash da semente
    sorteioRepository.atualiza(sorteioModel.getCodSorteio(), sorteioModel.getFaixaInicio(), sorteioModel.getFaixaFim(), sorteioModel.getSementeBilhete(),
      sorteioModel.getHashCodeSementeBilhete(), 2, Data.formata(LocalDate.now()), concursoLoteria, premiosLoteria, sementeSorteio, hashCodeSementeSorteio);

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  // Sortea os bilhetes
  private int sorteiaBilhetes(int hashCodeSemente, List<BilheteModel> bilheteModel, List<SorteioPremioModel> sorteioPremioModel)
    throws TransactionAbortException {

    Random random = new Random(hashCodeSemente);
    List<BilheteModel> listaBilhete = new ArrayList<>();
    List<String> listaGanhador = new ArrayList<>();

    int pos = 0;
    while (pos < sorteioPremioModel.size()) {

      int nextInt = random.nextInt(bilheteModel.size());
      String cpfCnpjGanhador = null;

      if (sorteioPremioModel.get(pos).getTipoPremio() == TP_PREMIO_CONSUMIDOR) {
        cpfCnpjGanhador = bilheteModel.get(nextInt).getCpfConsumidor();
      } else {
        cpfCnpjGanhador = bilheteModel.get(nextInt).getCnpjEntidade();
      }

      // Verificar se o cpfCnpjGanhador não foi sorteado para adicioná-lo à lista
      if (!listaGanhador.contains(cpfCnpjGanhador)) {
        listaGanhador.add(cpfCnpjGanhador);

        BilheteModel bilhete = new BilheteModel(bilheteModel.get(nextInt).getNumBilhete(), sorteioPremioModel.get(pos).getCodSorteio(),
          sorteioPremioModel.get(pos).getCodPremio(), bilheteModel.get(nextInt).getCpfConsumidor(), bilheteModel.get(nextInt).getCnpjEntidade());

        listaBilhete.add(bilhete);
        pos++;
      }
    }

    return bilheteRepository.atualiza(listaBilhete);
  }

}