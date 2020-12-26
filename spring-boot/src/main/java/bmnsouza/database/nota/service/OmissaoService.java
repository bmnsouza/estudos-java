package bmnsouza.database.nota.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.Omissao;
import bmnsouza.database.nota.repository.OmissaoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class OmissaoService {

	@Autowired
	private OmissaoRepository omissaoRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorIe(String ie, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorIe(ie, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorCnpj(String cnpj, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorCnpj(cnpj, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorAnoMes(int ano, int mes, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorAnoMes(ano, mes, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorRbaMenorQue(BigDecimal rba, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorRbaMenorQue(rba, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorRbaIgualA(BigDecimal rba, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorRbaIgualA(rba, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorRbaMaiorQue(BigDecimal rba, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorRbaMaiorQue(rba, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorDataEntrada(LocalDate dataEntrada, int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarPorDataEntrada(dataEntrada, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Omissao> dados = omissaoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> remover(String ie, String cnpj, Integer ano, Integer mes, String cpfResponsavel, String protocolo) {
		omissaoRepository.remover(ie, cnpj, ano, mes, cpfResponsavel, protocolo);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}


}