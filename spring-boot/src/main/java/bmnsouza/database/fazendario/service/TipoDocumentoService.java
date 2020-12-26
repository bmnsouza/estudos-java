package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.TipoDocumento;
import bmnsouza.database.fazendario.entity.dto.tipoDocumento.TipoDocumentoDTO;
import bmnsouza.database.fazendario.repository.TipoDocumentoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class TipoDocumentoService {
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<TipoDocumento> dados = tipoDocumentoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(Integer cdTipoDocumento) {
		TipoDocumento dados = tipoDocumentoRepository.buscarPorId(cdTipoDocumento);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(TipoDocumentoDTO tipoDocumentoDTO) {
		tipoDocumentoRepository.cadastrar(tipoDocumentoDTO.getCdTipoDocumento(), tipoDocumentoDTO.getDsTipoDocumento());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> atualizar(TipoDocumento tipoDocumento) {
		tipoDocumentoRepository.atualizar(tipoDocumento.getCdTipoDocumento(), tipoDocumento.getDsTipoDocumento(), tipoDocumento.getFlAtivo());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer cdTipoDocumento) {
		tipoDocumentoRepository.remover(cdTipoDocumento);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
}
