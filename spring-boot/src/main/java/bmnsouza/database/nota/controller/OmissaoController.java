package bmnsouza.database.nota.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.Mes;
import bmnsouza.database.nota.service.OmissaoService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/omissao")
public class OmissaoController {

	@Autowired
	private OmissaoService omissaoService;

	@GetMapping("buscarPorIe")
	public ResponseEntity<EntidadeResult> buscarPorIe(@RequestParam @NotEmpty String ie, @RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorIe(ie, pagina);
	}

	@GetMapping("buscarPorCnpj")
	public ResponseEntity<EntidadeResult> buscarPorCnpj(@RequestParam @CNPJ String cnpj, @RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorCnpj(cnpj, pagina);
	}

	@GetMapping("buscarPorAnoMes")
	public ResponseEntity<EntidadeResult> buscarPorAnoMes(@RequestParam @Ano int ano, @RequestParam @Mes int mes, @RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorAnoMes(ano, mes, pagina);
	}

	@GetMapping("buscarPorRbaMenorQue")
	public ResponseEntity<EntidadeResult> buscarPorRbaMenorQue(@RequestParam @PositiveOrZero BigDecimal rba, @RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorRbaMenorQue(rba, pagina);
	}

	@GetMapping("buscarPorRbaIgualA")
	public ResponseEntity<EntidadeResult> buscarPorRbaIgualA(@RequestParam @PositiveOrZero BigDecimal rba,	@RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorRbaIgualA(rba, pagina);
	}

	@GetMapping("buscarPorRbaMaiorQue")
	public ResponseEntity<EntidadeResult> buscarPorRbaMaiorQue(@RequestParam @PositiveOrZero BigDecimal rba, @RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorRbaMaiorQue(rba, pagina);
	}

	@GetMapping("buscarPorDataEntrada")
	public ResponseEntity<EntidadeResult> buscarPorDataEntrada(@RequestParam LocalDate dataEntrada,	@RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarPorDataEntrada(dataEntrada, pagina);
	}

	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return omissaoService.buscarTodos(pagina);
	}

	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @NotEmpty @Size(min = 9, max = 9) String ie, @RequestParam @CNPJ String cnpj,
			@RequestParam @Ano int ano, @RequestParam @Mes int mes,	@RequestParam @CPF String cpfResponsavel, @RequestParam @Size(min = 1, max = 16) String protocolo) {
		return omissaoService.remover(ie, cnpj, ano, mes, cpfResponsavel, protocolo);
	}

}