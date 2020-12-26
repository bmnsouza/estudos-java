package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.ObjetoReferencia;
import bmnsouza.database.fazendario.entity.dto.objetoReferencia.ObjetoReferenciaDTO;
import bmnsouza.database.fazendario.repository.ObjetoReferenciaRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ObjetoReferenciaService {

	@Autowired
	private ObjetoReferenciaRepository objetoReferenciaRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<ObjetoReferencia> dados = objetoReferenciaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrObjetoReferencia) {
		ObjetoReferencia dados = objetoReferenciaRepository.buscarPorId(nrObjetoReferencia);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(ObjetoReferenciaDTO objetoReferenciaDTO) {
		objetoReferenciaRepository.cadastrar(objetoReferenciaDTO.getDtInicioVigencia(), objetoReferenciaDTO.getDtFimVigencia(),
											 objetoReferenciaDTO.getDsObjetoReferencia());				
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(Integer nrObjetoReferencia) {
		objetoReferenciaRepository.remover(nrObjetoReferencia);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(ObjetoReferencia objetoReferencia){
		objetoReferenciaRepository.atualizar(objetoReferencia.getNrObjetoReferencia(),
				objetoReferencia.getDtInicioVigencia(), objetoReferencia.getDtFimVigencia(),
				objetoReferencia.getDsObjetoReferencia());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}
