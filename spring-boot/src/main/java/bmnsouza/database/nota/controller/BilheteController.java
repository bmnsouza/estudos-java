package	bmnsouza.database.nota.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
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

import com.microsoft.sqlserver.jdbc.SQLServerException;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.nota.entity.dto.bilhete.BilheteSortearDTO;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioDTO;
import bmnsouza.database.nota.service.BilheteService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/bilhete")
public class BilheteController {

	@Autowired
	private BilheteService bilheteService;

	@GetMapping("buscarPremiadosPorSorteio")
	public ResponseEntity<EntidadeResult> buscarPremiadosPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarPremiadosPorSorteio(codSorteio, pagina);
	}

	@GetMapping("buscarConsumidorPorSorteio")
	public ResponseEntity<EntidadeResult> buscarConsumidorPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @CPF String cpfConsumidor,
	@RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarConsumidorPorSorteio(codSorteio, cpfConsumidor, pagina);
	}

	@GetMapping("buscarEntidadePorSorteio")
	public ResponseEntity<EntidadeResult> buscarEntidadePorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @CNPJ String cnpjEntidade,
	@RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarEntidadePorSorteio(codSorteio, cnpjEntidade, pagina);
	}

	@GetMapping("buscarBilhetesPorSorteio")
	public ResponseEntity<EntidadeResult> buscarBilhetesPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarBilhetesPorSorteio(codSorteio, pagina);
	}

	@GetMapping("buscarQtdeBilhetesPorDatas")
	public ResponseEntity<EntidadeResult> buscarQtdeBilhetesPorDatas(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) throws ServiceException {
		return bilheteService.buscarQtdeBilhetesPorDatas(dataInicio, dataFim);
	}

	@GetMapping("buscarQtdeConsumidorPorSorteio")
	public ResponseEntity<EntidadeResult> buscarQtdeConsumidorPorSorteio(@RequestParam @Positive int codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarQtdeConsumidorPorSorteio(codSorteio, pagina);
	}

	@GetMapping("buscarPercentualEntidadeBilhete")
	public ResponseEntity<EntidadeResult> buscarPercentualEntidadeBilhete(@RequestParam @Positive int codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarPercentualEntidadeBilhete(codSorteio, pagina);
	}

	@GetMapping("buscarPercentualEntidadeIndicacao")
	public ResponseEntity<EntidadeResult> buscarPercentualEntidadeIndicacao(@RequestParam @Positive int codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return bilheteService.buscarPercentualEntidadeIndicacao(codSorteio, pagina);
	}

	@PostMapping("gerar")
	public ResponseEntity<EntidadeResult> gerar(@RequestBody @Valid SorteioDTO sorteioDTO) throws SQLException, SQLServerException, ServiceException {
		return bilheteService.gerar(sorteioDTO);
	}

	@PutMapping("sortear")
	public ResponseEntity<EntidadeResult> sortear(@RequestBody @Valid BilheteSortearDTO bilheteSorteioDTO) throws ServiceException {
		return bilheteService.sortear(bilheteSorteioDTO);
	}

}