package bmnsouza.database.nota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.ContaCorrenteBloqueio;
import bmnsouza.database.nota.repository.ContaCorrenteBloqueioRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ContaCorrenteBloqueioService {

	@Autowired
	private ContaCorrenteBloqueioRepository contaCorrenteBloqueioRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorCpf(String cpf, int pagina) {
		Slice<ContaCorrenteBloqueio> dados = contaCorrenteBloqueioRepository.buscarPorCpf(cpf, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<ContaCorrenteBloqueio> dados = contaCorrenteBloqueioRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(ContaCorrenteBloqueio contaCorrenteBloqueio) {
		contaCorrenteBloqueioRepository.cadastrar(contaCorrenteBloqueio.getCpf(), contaCorrenteBloqueio.getContaBloqueada(),
			contaCorrenteBloqueio.getCpfResponsavel(), contaCorrenteBloqueio.getJustificativa());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

}