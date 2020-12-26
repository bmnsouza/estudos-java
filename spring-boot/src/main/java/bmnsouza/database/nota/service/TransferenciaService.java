package bmnsouza.database.nota.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.exception.ServiceException;
import bmnsouza.constant.TransferenciaConstant;
import bmnsouza.database.nota.entity.BloqueioResgate;
import bmnsouza.database.nota.entity.Consumidor;
import bmnsouza.database.nota.entity.ContaCorrenteBloqueio;
import bmnsouza.database.nota.entity.Saldo;
import bmnsouza.database.nota.entity.Transferencia;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaAtualizarParaInvalidaDTO;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaResgateDTO;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaTransferirCreditoDTO;
import bmnsouza.database.nota.repository.ConsumidorRepository;
import bmnsouza.database.nota.repository.ContaCorrenteBloqueioRepository;
import bmnsouza.database.nota.repository.SaldoRepository;
import bmnsouza.database.nota.repository.TransferenciaRepository;
import bmnsouza.model.BaneseModel;
import bmnsouza.model.DebitoContribuinteModel;
import bmnsouza.model.DebitoContribuinteModel.Result;
import bmnsouza.util.WebClientUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private BloqueioResgateService bloqueioResgateService;

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private ContaCorrenteBloqueioRepository contaCorrenteBloqueioRepository;

	@Autowired
	private SaldoRepository saldoRepository;

	@Autowired
	private ResultUtil resultUtil;

	@Autowired
	private WebClientUtil webClientUtil;
	
	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	// URL base do Gateway
	@Value("${api.base-url.gateway}")
	private String URL_GATEWAY;

	// Chave do Token
	@Value("${api.chave.token}")
	private String CHAVE_TOKEN;

	public ResponseEntity<EntidadeResult> buscarPorCpf(String cpf, int pagina) {
		Slice<Transferencia> dados = transferenciaRepository.buscarPorCpf(cpf, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorDataRelatorio(LocalDate dataRelatorio, int pagina) {
		Slice<Transferencia> dados = transferenciaRepository.buscarPorDataRelatorio(dataRelatorio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorStatus(int status, int pagina) {
		Slice<Transferencia> dados = transferenciaRepository.buscarPorStatus(status, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarResgateConferencia(int pagina) {
		Slice<TransferenciaResgateDTO> dados = transferenciaRepository.buscarResgateConferencia(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarResgateRealizado(LocalDate dataRelatorio, int pagina) {
		Slice<TransferenciaResgateDTO> dados = transferenciaRepository.buscarResgateRealizado(dataRelatorio, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarResgateNaoRealizado(int pagina) {
		Slice<Map<String, String>> dados = transferenciaRepository.buscarResgateNaoRealizado(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTotalCreditoPorAno(String cpf, int ano) {
		Map<String, BigDecimal> dados = transferenciaRepository.buscarTotalCreditoPorAno(cpf, ano);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Transferencia> dados = transferenciaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> atualizarParaInvalida(TransferenciaAtualizarParaInvalidaDTO transferenciaAtualizarParaInvalidaDTO) {
		transferenciaRepository.atualizarParaInvalida(transferenciaAtualizarParaInvalidaDTO.getId());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizarParaConferencia() {
		transferenciaRepository.atualizarParaConferencia();
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizarParaResgate() {
		transferenciaRepository.atualizarParaResgate();
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> transferirCredito(TransferenciaTransferirCreditoDTO transferenciaTransferirCreditoDTO) throws ServiceException {
		// Verifica se a transferência de crédito está bloqueada
		BloqueioResgate bloqueioResgate = bloqueioResgateService.verificar();
		if (bloqueioResgate != null) {
			throw new ServiceException(bloqueioResgate.getMensagemBloqueio());
		}

		// Verifica se o consumidor está cadastrado
		Consumidor consumidor = consumidorRepository.buscarPorCpf(transferenciaTransferirCreditoDTO.getCpf());
		if (consumidor == null) {
			throw new ServiceException("cpf", "Consumidor não cadastrado");
		}

		// Verifica se o consumidor está com a conta corrente bloqueada bloqueada
		ContaCorrenteBloqueio contaCorrenteBloqueio = contaCorrenteBloqueioRepository.buscarBloqueioPorCpf(transferenciaTransferirCreditoDTO.getCpf());
		if (contaCorrenteBloqueio != null) {
			throw new ServiceException(new StringBuilder("Conta Corrente bloqueada. Justificativa: ").append(contaCorrenteBloqueio.getJustificativa()).toString());
		}

		// Verifica se o consumidor possui débitos
		Result result = verificarDebitoContribuinte(transferenciaTransferirCreditoDTO.getCpf()).getResult();
		if (result.getCdRetorno() == HttpStatus.OK.value()) {
			if (result.isDados()) {
				throw new ServiceException(TransferenciaConstant.MENSAGEM_TRANSFERENCIA);
			}
		} else {
			throw new ServiceException(result.getMsgUsuario());
		}

		// Verifica se há saldo suficiente para realizar a transferência
		Saldo saldo = saldoRepository.buscarPorCpf(transferenciaTransferirCreditoDTO.getCpf());
		if (saldo == null || saldo.getSaldo().doubleValue() <= 0) {
			throw new ServiceException("Não há saldo suficiente para realizar a transferência");
		}

		// Verifica se o CPF pertence à conta informada
		BaneseModel banese = validarConta(transferenciaTransferirCreditoDTO.getAgencia(), transferenciaTransferirCreditoDTO.getOperacao(), transferenciaTransferirCreditoDTO.getConta(),
			transferenciaTransferirCreditoDTO.getDigitoConta(), transferenciaTransferirCreditoDTO.getCpf());
		if (!banese.getIsContaCorrente() && !banese.getIsPoupanca()) {
			if (banese.getIsContaSalario()) {
				throw new ServiceException("Não é permitido conta salário. Por gentileza informe uma conta corrente ou poupança");
			} else	{
				throw new ServiceException("CPF não pertence à conta informada");
			}
		}

		// Descrição da transferência do crédito
		String dscTransferencia = new StringBuilder("Transferência para C/C ou Poupança: CPF/CNPJ ").append(consumidor.getCpf()).append(' ').append(consumidor.getNome())
			.append(", Banco ").append(transferenciaTransferirCreditoDTO.getBanco()).append(" Ag ").append(transferenciaTransferirCreditoDTO.getAgencia()).append(" CC ")
			.append(transferenciaTransferirCreditoDTO.getConta()).toString();
		
		// Transferência do crédito
		long nroControle = transferenciaRepository.transferirCredito(saldo.getId(), transferenciaTransferirCreditoDTO.getTipoConta(), dscTransferencia, consumidor.getCpf(),
			saldo.getSaldo(), transferenciaTransferirCreditoDTO.getBanco(), transferenciaTransferirCreditoDTO.getAgencia(), transferenciaTransferirCreditoDTO.getDigitoAgencia(),
			transferenciaTransferirCreditoDTO.getConta(), transferenciaTransferirCreditoDTO.getDigitoConta(), transferenciaTransferirCreditoDTO.getOperacao());
		
		// Dados de retorno
		Map<String, String> dados = new HashMap<>();
		dados.put("msgTransferencia", obterMensagemTransferencia(saldo.getSaldo()));
		dados.put("nroControle", StringUtils.leftPad(String.valueOf(nroControle), 12, '0'));

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	// Serviço verificarDebitoContribuinte do SAP
	private DebitoContribuinteModel verificarDebitoContribuinte(String cdIdentificacao) {
		return webClientUtil.webClientToken(URL_GATEWAY, CHAVE_TOKEN).get()
				.uri(uriBuilder -> uriBuilder.path("/fazendario/v1/SAP/processo/verificarDebitoContribuinte")
					.queryParam("cdIdentificacao", cdIdentificacao)
					.build())
				.retrieve()
				.bodyToMono(DebitoContribuinteModel.class)
				.block();
	}

	// Serviço validarConta do Banese
	private BaneseModel validarConta(String agencia, String operacao, String conta, String digitoConta, String cpfCnpj) {
		return webClientUtil.webClient(URL_GATEWAY).get()
				.uri(uriBuilder -> uriBuilder.path("/externo/banese/v1/validarConta")
					.queryParam("agencia", agencia)
					.queryParam("conta", new StringBuilder(operacao).append(conta).append(digitoConta))
					.queryParam("cpfCnpj", cpfCnpj)
					.build())
				.retrieve()
				.bodyToMono(BaneseModel.class)
				.block();
	}

	// Mensagem da transferência
	private String obterMensagemTransferencia(BigDecimal valor) {
		return new StringBuilder("O valor de ").append(NumberFormat.getCurrencyInstance().format(valor)).append(" será transferido para o banco desde que o consumidor ")
			.append("não esteja inadimplente com o Estado de Sergipe, em relação a obrigações pecuniárias de natureza tributária ou não tributária, ficando impedido de ")
			.append("receber o valor resgatado enquanto permanecer nessa situação, conf. determina o §5º do art. 4º do Decreto nº 28.022/2011.").toString();
	}

}