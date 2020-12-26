package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.MotivoAlteracaoDocumento;
import bmnsouza.database.fazendario.entity.dto.motivoAlteracaoDocumento.MotivoAlteracaoDocumentoDTO;
import bmnsouza.database.fazendario.repository.MotivoAlteracaoDocumentoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class MotivoAlteracaoDocumentoService {
	
	@Autowired
	private MotivoAlteracaoDocumentoRepository motivoAlteracaoDocumentoRepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
		
	public ResponseEntity<EntidadeResult> buscarTodos(Integer pagina) {
		Slice<MotivoAlteracaoDocumento> dados = motivoAlteracaoDocumentoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
		
	public ResponseEntity<EntidadeResult> buscarPorId(Integer cdMotivo) {
		MotivoAlteracaoDocumento dados = motivoAlteracaoDocumentoRepository.buscarPorId(cdMotivo);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult>cadastrar(MotivoAlteracaoDocumentoDTO motivoAlteracaoDocumentoDTO) {
		motivoAlteracaoDocumentoRepository.cadastrar(motivoAlteracaoDocumentoDTO.getDsMotivo(), motivoAlteracaoDocumentoDTO.getNrSituacao(), motivoAlteracaoDocumentoDTO.getFlManual());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult>atualizar(MotivoAlteracaoDocumento motivoAlteracaoDocumento) {
		motivoAlteracaoDocumentoRepository.atualizar(motivoAlteracaoDocumento.getCdMotivo(), motivoAlteracaoDocumento.getDsMotivo(), motivoAlteracaoDocumento.getFlAtivo(), 
			motivoAlteracaoDocumento.getNrSituacao(), motivoAlteracaoDocumento.getFlManual());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
		
	}
	
	public ResponseEntity<EntidadeResult>remover(Integer cdMotivo) {
		motivoAlteracaoDocumentoRepository.remover(cdMotivo);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}