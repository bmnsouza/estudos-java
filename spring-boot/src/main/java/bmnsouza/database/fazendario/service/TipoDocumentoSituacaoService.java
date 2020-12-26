package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.TipoDocumentoSituacao;
import bmnsouza.database.fazendario.repository.TipoDocumentoSituacaoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class TipoDocumentoSituacaoService {

	@Autowired
	private TipoDocumentoSituacaoRepository tipoDocumentoSituacaoRepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult>buscarTodos(int pagina) {
		Slice<TipoDocumentoSituacao> dados = tipoDocumentoSituacaoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult>buscarPorId(Integer cdTipoDocumento, Integer nrSituacao) {
		TipoDocumentoSituacao dados = tipoDocumentoSituacaoRepository.buscarPorId(cdTipoDocumento, nrSituacao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult>cadastrar(TipoDocumentoSituacao tipoDocumentoSituacao) {
		tipoDocumentoSituacaoRepository.cadastrar(tipoDocumentoSituacao.getCdTipoDocumento(), tipoDocumentoSituacao.getNrSituacao());				
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult>remover(Integer cdTipoDocumento, Integer nrSituacao) {
		tipoDocumentoSituacaoRepository.remover(cdTipoDocumento, nrSituacao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}