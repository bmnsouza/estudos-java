package bmnsouza.database.nota.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.Saldo;
import bmnsouza.database.nota.repository.SaldoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class SaldoService {

	@Autowired
	private SaldoRepository saldoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorId(Integer id) {
		Saldo dados = saldoRepository.buscarPorId(id);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorCpf(String cpf) {
		Saldo dados = saldoRepository.buscarPorCpf(cpf);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorSaldoMenorQue(BigDecimal saldo, int pagina) {
		Slice<Saldo> dados = saldoRepository.buscarPorSaldoMenorQue(saldo, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorSaldoIgualA(BigDecimal saldo, int pagina) {
		Slice<Saldo> dados = saldoRepository.buscarPorSaldoIgualA(saldo, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorSaldoMaiorQue(BigDecimal saldo, int pagina) {
		Slice<Saldo> dados = saldoRepository.buscarPorSaldoMaiorQue(saldo, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Saldo> dados = saldoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

}