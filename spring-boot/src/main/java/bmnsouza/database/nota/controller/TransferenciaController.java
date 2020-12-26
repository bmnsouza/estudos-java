package	bmnsouza.database.nota.controller;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.annotation.Ano;
import bmnsouza.exception.ServiceException;
import bmnsouza.annotation.StatusTransferencia;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaAtualizarParaInvalidaDTO;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaTransferirCreditoDTO;
import bmnsouza.database.nota.service.TransferenciaService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@GetMapping("buscarPorCpf")
	public ResponseEntity<EntidadeResult> buscarPorCpf(@RequestParam @CPF String cpf, @RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarPorCpf(cpf, pagina);
	}

	@GetMapping("buscarPorDataRelatorio")
	public ResponseEntity<EntidadeResult> buscarPorDataRelatorio(@RequestParam LocalDate dataRelatorio, @RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarPorDataRelatorio(dataRelatorio, pagina);
	}

	@GetMapping("buscarPorStatus")
	public ResponseEntity<EntidadeResult> buscarPorStatus(@RequestParam @StatusTransferencia int status, @RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarPorStatus(status, pagina);
	}

	@GetMapping("buscarResgateConferencia")
	public ResponseEntity<EntidadeResult> buscarResgateConferencia(@RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarResgateConferencia(pagina);
	}

	@GetMapping("buscarResgateRealizado")
	public ResponseEntity<EntidadeResult> buscarResgateRealizado(@RequestParam LocalDate dataRelatorio, @RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarResgateRealizado(dataRelatorio, pagina);
	}

	@GetMapping("buscarResgateNaoRealizado")
	public ResponseEntity<EntidadeResult> buscarResgateNaoRealizado(@RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarResgateNaoRealizado(pagina);
	}

	@GetMapping("buscarTotalCreditoPorAno")
	public ResponseEntity<EntidadeResult> buscarTotalCreditoPorAno(@RequestParam @CPF String cpf, @RequestParam @Ano int ano) {
		return transferenciaService.buscarTotalCreditoPorAno(cpf, ano);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return transferenciaService.buscarTodos(pagina);
	}

	@PutMapping("atualizarParaInvalida")
	public ResponseEntity<EntidadeResult> atualizarParaInvalida(@RequestBody @Valid TransferenciaAtualizarParaInvalidaDTO transferenciaAtualizarParaInvalidaDTO) {
		return transferenciaService.atualizarParaInvalida(transferenciaAtualizarParaInvalidaDTO);
	}

	@PutMapping("atualizarParaConferencia")
	public ResponseEntity<EntidadeResult> atualizarParaConferencia() {
		return transferenciaService.atualizarParaConferencia();
	}

	@PutMapping("atualizarParaResgate")
	public ResponseEntity<EntidadeResult> atualizarParaResgate() {
		return transferenciaService.atualizarParaResgate();
	}

	@PostMapping("transferirCredito")
	public ResponseEntity<EntidadeResult> transferirCredito(@RequestBody @Valid TransferenciaTransferirCreditoDTO transferenciaTransferirCreditoDTO) throws ServiceException {
		return transferenciaService.transferirCredito(transferenciaTransferirCreditoDTO);
	}

}