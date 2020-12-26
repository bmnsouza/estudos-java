package	bmnsouza.database.nota.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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

import bmnsouza.database.nota.entity.SorteioPremio;
import bmnsouza.database.nota.entity.dto.sorteio.SorteioDTO;
import bmnsouza.database.nota.service.SorteioPremioService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/sorteioPremio")
public class SorteioPremioController {

	@Autowired
	private SorteioPremioService sorteioPremioService;

	@GetMapping("buscarPorSorteio")
	public ResponseEntity<EntidadeResult> buscarPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return sorteioPremioService.buscarPorSorteio(codSorteio, pagina);
	}

	@GetMapping("buscarBilhetesPorSorteio")
	public ResponseEntity<EntidadeResult> buscarBilhetesPorSorteio(@RequestParam @Positive Integer codSorteio, @RequestParam @PositiveOrZero int pagina) {
		return sorteioPremioService.buscarBilhetesPorSorteio(codSorteio, pagina);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid List<SorteioPremio> sorteioPremio) {
		return sorteioPremioService.cadastrar(sorteioPremio);
	}

	@PutMapping("transferir")
	public ResponseEntity<EntidadeResult> transferir(@RequestBody @Valid SorteioDTO sorteioDTO) {
		return sorteioPremioService.transferir(sorteioDTO);
	}

}