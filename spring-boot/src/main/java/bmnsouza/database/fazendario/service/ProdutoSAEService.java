package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.ProdutoSAE;
import bmnsouza.database.fazendario.entity.dto.produtoSAE.ProdutoSAEDTO;
import bmnsouza.database.fazendario.repository.ProdutoSAERepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ProdutoSAEService {
	
	@Autowired
	private ProdutoSAERepository produtoSAERepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<ProdutoSAE> dados = produtoSAERepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(Integer cdProduto, Integer tpProduto) {
		ProdutoSAE dados = produtoSAERepository.buscarPorId(cdProduto, tpProduto);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(ProdutoSAEDTO produtoSAEDTO) {
		produtoSAERepository.cadastrar(produtoSAEDTO.getTpProduto(), produtoSAEDTO.getDsProduto(), produtoSAEDTO.getCdGrupo(), produtoSAEDTO.getCdSubGrupo(),
			produtoSAEDTO.getCdAtvMacro(),	produtoSAEDTO.getCdCnae(), produtoSAEDTO.getCdCnaef(), produtoSAEDTO.getCdProdutoGnre());				
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(ProdutoSAE produtoSAE) {
		produtoSAERepository.atualizar(produtoSAE.getCdProduto(),produtoSAE.getTpProduto(), produtoSAE.getDsProduto(), produtoSAE.getFlAtivo(), produtoSAE.getCdGrupo(),
			produtoSAE.getCdSubGrupo(), produtoSAE.getCdAtvMacro(), produtoSAE.getCdCnae(),  produtoSAE.getCdCnaef(), produtoSAE.getCdProdutoGnre());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(Integer cdProduto, Integer tpProduto) {
		produtoSAERepository.remover(cdProduto, tpProduto);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}