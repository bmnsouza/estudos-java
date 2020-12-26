package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.FormaRecolhimentoIcms;
import bmnsouza.database.fazendario.entity.dto.formaRecolhimentoIcms.FormaRecolhimentoIcmsDTO;
import bmnsouza.database.fazendario.repository.FormaRecolhimentoIcmsRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class FormaRecolhimentoIcmsService {

	@Autowired
	private FormaRecolhimentoIcmsRepository formaRecolhimentoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina){
		Slice<FormaRecolhimentoIcms> dados = formaRecolhimentoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrFormaRecolhimentoIcms){
		FormaRecolhimentoIcms dados = formaRecolhimentoRepository.buscarPorId(nrFormaRecolhimentoIcms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(FormaRecolhimentoIcmsDTO formaRecolhimentoIcmsDTO){
		formaRecolhimentoRepository.cadastrar(formaRecolhimentoIcmsDTO.getDsFormaRecolhimentoIcms(), 
				formaRecolhimentoIcmsDTO.getDtInicioVigencia(), formaRecolhimentoIcmsDTO.getNrGrupoRecolhimento(), formaRecolhimentoIcmsDTO.getDtFimVigencia());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer nrFormaRecolhimentoIcms){
		formaRecolhimentoRepository.remover(nrFormaRecolhimentoIcms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> atualizar(FormaRecolhimentoIcms formaRecolhimentoIcms){
		formaRecolhimentoRepository.atualizar(formaRecolhimentoIcms.getNrFormaRecolhimentoIcms(), formaRecolhimentoIcms.getDsFormaRecolhimentoIcms(), 
				formaRecolhimentoIcms.getDtInicioVigencia(), formaRecolhimentoIcms.getNrGrupoRecolhimento(), formaRecolhimentoIcms.getDtFimVigencia());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}