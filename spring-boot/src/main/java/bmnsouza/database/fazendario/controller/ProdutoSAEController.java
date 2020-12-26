package bmnsouza.database.fazendario.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.database.fazendario.entity.ProdutoSAE;
import bmnsouza.database.fazendario.entity.dto.produtoSAE.ProdutoSAEDTO;
import bmnsouza.database.fazendario.service.ProdutoSAEService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/produtoSAE")
public class ProdutoSAEController {
	
	@Autowired
	private ProdutoSAEService produtoSAEService;
	
	@GetMapping("buscarTodos")
	public ResponseEntity<EntidadeResult> buscarTodos(@RequestParam @PositiveOrZero int pagina) {
		return produtoSAEService.buscarTodos(pagina);
	}
	
	@GetMapping("buscarPorId")
	public ResponseEntity<EntidadeResult> buscarPorId(@RequestParam @Min(1) @Max(999) @NotNull Integer cdProduto,
			@RequestParam @Min(1) @Max(2) @NotNull Integer tpProduto) {
		return produtoSAEService.buscarPorId(cdProduto, tpProduto);
	}

	@PostMapping("cadastrar")
	public ResponseEntity<EntidadeResult> cadastrar(@RequestBody @Valid ProdutoSAEDTO produtoSAEDTO) {		
		return produtoSAEService.cadastrar(produtoSAEDTO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<EntidadeResult> atualizar(@RequestBody @Valid ProdutoSAE produtoSAE){		
		return produtoSAEService.atualizar(produtoSAE);
	}
	
	@DeleteMapping("remover")
	public ResponseEntity<EntidadeResult> remover(@RequestParam @Min(1) @Max(999) @NotNull Integer cdProduto,
			@RequestParam @Min(1) @Max(2) @NotNull Integer tpProduto) {		
		return produtoSAEService.remover(cdProduto, tpProduto);
	}
	
}