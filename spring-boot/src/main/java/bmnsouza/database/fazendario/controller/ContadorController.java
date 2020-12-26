package	bmnsouza.database.fazendario.controller;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.fazendario.service.ContadorService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/contador")
public class ContadorController {

	@Autowired
	private ContadorService contadorService;

	@GetMapping("buscarContribuintes")
	public ResponseEntity<EntidadeResult> buscarContribuintes(@RequestParam @Size(min = 3, max = 13) String crc, @RequestParam @PositiveOrZero int pagina) {
		return contadorService.buscarContribuintes(crc, pagina);
	}

}