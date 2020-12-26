package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.IcmsContribuinteInscrito;
import bmnsouza.database.fazendario.entity.dto.icmsContribuinteInscrito.IcmsContribuinteInscritoDTO;
import bmnsouza.database.fazendario.repository.IcmsContribuinteInscritoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class IcmsContribuinteInscritoService {

	@Autowired
	private IcmsContribuinteInscritoRepository icmsContribuinteInscritoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<IcmsContribuinteInscrito> dados = icmsContribuinteInscritoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoIcms) {
		IcmsContribuinteInscrito dados = icmsContribuinteInscritoRepository.buscarPorId(tpContribuinte, nrRegimeTributacaoIcms, nrFormaRecolhimentoIcms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(IcmsContribuinteInscritoDTO icmsContribuinteInscritoDTO) {
		icmsContribuinteInscritoRepository.cadastrar(icmsContribuinteInscritoDTO.getTpContribuinte(),
			icmsContribuinteInscritoDTO.getNrRegimeTributacaoIcms(), icmsContribuinteInscritoDTO.getNrFormaRecolhimentoICms());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(IcmsContribuinteInscrito icmsContribuinteInscrito) {
		icmsContribuinteInscritoRepository.atualizar(icmsContribuinteInscrito.getTpContribuinte(), icmsContribuinteInscrito.getNrRegimeTributacaoIcms(),
			icmsContribuinteInscrito.getNrFormaRecolhimentoICms(), icmsContribuinteInscrito.getFlVisivelContribuinte());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoICms) {
		icmsContribuinteInscritoRepository.remover(tpContribuinte, nrRegimeTributacaoIcms, nrFormaRecolhimentoICms);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}