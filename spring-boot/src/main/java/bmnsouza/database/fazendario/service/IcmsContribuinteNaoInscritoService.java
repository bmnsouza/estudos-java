package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.IcmsContribuinteNaoInscrito;
import bmnsouza.database.fazendario.repository.IcmsContribuinteNaoInscritoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class IcmsContribuinteNaoInscritoService {

	@Autowired
	private IcmsContribuinteNaoInscritoRepository icmsContribuinteNaoInscritoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina){
		Slice<IcmsContribuinteNaoInscrito> dados = icmsContribuinteNaoInscritoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrObjetoReferencia, Integer nrFormaRecolhimentoIcms) {
		IcmsContribuinteNaoInscrito dados = icmsContribuinteNaoInscritoRepository.buscarPorId(nrObjetoReferencia, nrFormaRecolhimentoIcms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(IcmsContribuinteNaoInscrito icmsContribuinteNaoInscrito) {
		icmsContribuinteNaoInscritoRepository.cadastrar(icmsContribuinteNaoInscrito.getNrObjetoReferencia(), icmsContribuinteNaoInscrito.getNrFormaRecolhimentoIcms());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer nrObjetoReferencia, Integer nrFormaRecolhimentoIcms) {
		icmsContribuinteNaoInscritoRepository.remover(nrObjetoReferencia, nrFormaRecolhimentoIcms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}