package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.PercentualCalculoReceita;
import bmnsouza.database.fazendario.entity.dto.percentualCalculoReceita.PercentualCalculoReceitaCadastrarDTO;
import bmnsouza.database.fazendario.repository.PercentualCalculoReceitaRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class PercentualCalculoReceitaService {

	@Autowired
	private PercentualCalculoReceitaRepository percentualCalculoReceitaRepository;
	
	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<PercentualCalculoReceita> dados = percentualCalculoReceitaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer cdReceita, Integer nrSequencial) {
		PercentualCalculoReceita dados = percentualCalculoReceitaRepository.buscarPorId(cdReceita, nrSequencial);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(
			PercentualCalculoReceitaCadastrarDTO percentualCalculoReceitaCadastrarDTO) {
		percentualCalculoReceitaRepository.cadastrar(percentualCalculoReceitaCadastrarDTO.getCdReceita(),
			percentualCalculoReceitaCadastrarDTO.getVlPercentualMultaMora(), percentualCalculoReceitaCadastrarDTO.getQtMesLimiteMultaMora(),
			percentualCalculoReceitaCadastrarDTO.getVlPercentualJurosMora(), percentualCalculoReceitaCadastrarDTO.getVlPercentualAgregacao(),
			percentualCalculoReceitaCadastrarDTO.getVlPercentualAliquota(), percentualCalculoReceitaCadastrarDTO.getVlUfp(),
			percentualCalculoReceitaCadastrarDTO.getDtInicioVigencia(), percentualCalculoReceitaCadastrarDTO.getDtFinalVigencia(),
			percentualCalculoReceitaCadastrarDTO.getNrMotivoReceita(), percentualCalculoReceitaCadastrarDTO.getTpTransmissao(),
			percentualCalculoReceitaCadastrarDTO.getVlUfpFinal());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> atualizar(PercentualCalculoReceita percentualCalculoReceita) {
		percentualCalculoReceitaRepository.atualizar(percentualCalculoReceita.getCdReceita(),
			percentualCalculoReceita.getNrSequencial(), percentualCalculoReceita.getVlPercentualMultaMora(),
			percentualCalculoReceita.getQtMesLimiteMultaMora(), percentualCalculoReceita.getVlPercentualJurosMora(),
			percentualCalculoReceita.getVlPercentualAgregacao(), percentualCalculoReceita.getVlPercentualAliquota(),
			percentualCalculoReceita.getVlUfp(), percentualCalculoReceita.getDtInicioVigencia(),
			percentualCalculoReceita.getDtFinalVigencia(), percentualCalculoReceita.getNrMotivoReceita(),
			percentualCalculoReceita.getTpTransmissao(), percentualCalculoReceita.getVlUfpFinal());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer cdReceita, Integer nrSequencial) {
		percentualCalculoReceitaRepository.remover(cdReceita, nrSequencial);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
}
