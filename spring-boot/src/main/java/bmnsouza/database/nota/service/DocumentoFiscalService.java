package bmnsouza.database.nota.service;

import java.time.LocalDate;

import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.DocumentoFiscal;
import bmnsouza.database.nota.repository.DocumentoFiscalRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class DocumentoFiscalService {

	@Autowired
	private DocumentoFiscalRepository documentoFiscalRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorNotaFiscal(String numeroNotaFiscal) {
		DocumentoFiscal dados = documentoFiscalRepository.buscarPorNotaFiscal(numeroNotaFiscal);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorEmitente(String cnpjEmitente, LocalDate dataEmissaoInicio, LocalDate dataEmissaoFim,
		@PositiveOrZero int pagina) {
		Slice<DocumentoFiscal> dados = documentoFiscalRepository.buscarPorEmitente(cnpjEmitente, dataEmissaoInicio, dataEmissaoFim,
			PageRequest.of(pagina, PAGE_SIZE));

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorDestinatario(String cpfDestinatario, LocalDate dataEmissaoInicio, LocalDate dataEmissaoFim,
		@PositiveOrZero int pagina) {
		Slice<DocumentoFiscal> dados = documentoFiscalRepository.buscarPorDestinatario(cpfDestinatario, dataEmissaoInicio, dataEmissaoFim,
			PageRequest.of(pagina, PAGE_SIZE));

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorEmitenteDestinatario(String cnpjEmitente, String cpfDestinatario, LocalDate dataEmissaoInicio,
		LocalDate dataEmissaoFim, int pagina) {
		Slice<DocumentoFiscal> dados = documentoFiscalRepository.buscarPorEmitenteDestinatario(cnpjEmitente, cpfDestinatario, dataEmissaoInicio, dataEmissaoFim,
			PageRequest.of(pagina, PAGE_SIZE));

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

}