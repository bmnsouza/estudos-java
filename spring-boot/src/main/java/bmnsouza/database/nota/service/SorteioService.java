package bmnsouza.database.nota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.Sorteio;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioExpiradosDTO;
import bmnsouza.database.nota.repository.SorteioRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class SorteioService {

	@Autowired
	private SorteioRepository sorteioRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorCodigo(Integer codSorteio) {
		Sorteio dados = sorteioRepository.buscarPorCodigo(codSorteio);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarExpirados(int pagina) {
		Slice<SorteioExpiradosDTO> dados = sorteioRepository.buscarExpirados(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarNaoExpirados() {
		List<SorteioExpiradosDTO> dados = sorteioRepository.buscarNaoExpirados();
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Sorteio> dados = sorteioRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(Sorteio sorteio) {
		sorteioRepository.cadastrar(sorteio.getCodSorteio(), sorteio.getDataInicio(), sorteio.getDataFim(), sorteio.getDataRealizacao(), sorteio.getObservacao());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

}