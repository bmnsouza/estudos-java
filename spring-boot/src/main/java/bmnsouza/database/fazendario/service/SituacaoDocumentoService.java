package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.SituacaoDocumento;
import bmnsouza.database.fazendario.entity.dto.situacaoDocumento.SituacaoDocumentoDTO;
import bmnsouza.database.fazendario.repository.SituacaoDocumentoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class SituacaoDocumentoService {

	@Autowired
	private SituacaoDocumentoRepository situacaoDocumentoRepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<SituacaoDocumento> dados = situacaoDocumentoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrSituacao){
		SituacaoDocumento dados = situacaoDocumentoRepository.buscarPorId(nrSituacao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(SituacaoDocumentoDTO situacaoDocumento) {
		situacaoDocumentoRepository.cadastrar(situacaoDocumento.getDsSituacao());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);	
	}
	
	public ResponseEntity<EntidadeResult> atualizar(SituacaoDocumento situacaoDocumento) {
		situacaoDocumentoRepository.atualizar(situacaoDocumento.getNrSituacao(), situacaoDocumento.getDsSituacao(), situacaoDocumento.getFlAtivo());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer nrSituacao){
		situacaoDocumentoRepository.remover(nrSituacao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
}