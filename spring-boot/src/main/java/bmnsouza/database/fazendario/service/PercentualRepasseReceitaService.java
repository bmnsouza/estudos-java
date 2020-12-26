package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.PercentualRepasseReceita;
import bmnsouza.database.fazendario.repository.PercentualRepasseReceitaRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class PercentualRepasseReceitaService {
	
	@Autowired
	private PercentualRepasseReceitaRepository percentualRepasseReceitaRepository;
	
	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<PercentualRepasseReceita> dados = percentualRepasseReceitaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer cdReceita, Integer cdEntidade, Integer tpProducao) {
		PercentualRepasseReceita dados = percentualRepasseReceitaRepository.buscarPorId(cdReceita, cdEntidade, tpProducao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(PercentualRepasseReceita percentualRepasseReceita) {
		percentualRepasseReceitaRepository.cadastrar(percentualRepasseReceita.getCdReceita(),
				percentualRepasseReceita.getCdEntidade(), percentualRepasseReceita.getTpProducao(),
				percentualRepasseReceita.getVlPercentual());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(PercentualRepasseReceita percentualRepasseReceita) {
		percentualRepasseReceitaRepository.atualizar(percentualRepasseReceita.getCdReceita(),
				percentualRepasseReceita.getCdEntidade(), percentualRepasseReceita.getTpProducao(),
				percentualRepasseReceita.getVlPercentual());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer cdReceita, Integer cdEntidade, Integer tpProducao) {
		percentualRepasseReceitaRepository.remover(cdReceita, cdEntidade, tpProducao);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}