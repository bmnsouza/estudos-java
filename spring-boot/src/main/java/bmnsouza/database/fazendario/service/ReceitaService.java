package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.Receita;
import bmnsouza.database.fazendario.repository.ReceitaRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private ResultUtil resultUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Receita> dados = receitaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(int cdReceita) {
		Receita dados = receitaRepository.buscarPorId(cdReceita);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(Receita receita) {
		receitaRepository.cadastrar(receita.getCdReceita(), receita.getDsReceita(), receita.getNrGrupoReceita(), receita.getCdOrigemReceita(),
			receita.getCdOrgao(), receita.getDtInicioVigencia(), receita.getDtFimVigencia(), receita.getCdReceitaPai(), receita.getTpReceita());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(Receita receita) {
		receitaRepository.atualizar(receita.getCdReceita(), receita.getDsReceita(), receita.getNrGrupoReceita(), receita.getCdOrigemReceita(),
			receita.getCdOrgao(), receita.getDtInicioVigencia(), receita.getDtFimVigencia(), receita.getCdReceitaPai(), receita.getTpReceita());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(int cdReceita) {
		receitaRepository.remover(cdReceita);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}