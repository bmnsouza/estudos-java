package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.GrupoRecolhimento;
import bmnsouza.database.fazendario.entity.dto.grupoRecolhimento.GrupoRecolhimentoDTO;
import bmnsouza.database.fazendario.repository.GrupoRecolhimentoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class GrupoRecolhimentoService {
	
	@Autowired
	private GrupoRecolhimentoRepository grupoRecolhimentoRepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<GrupoRecolhimento> dados = grupoRecolhimentoRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrGrupoRecolhimento) {
		GrupoRecolhimento dados = grupoRecolhimentoRepository.buscarPorId(nrGrupoRecolhimento);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(GrupoRecolhimentoDTO grupoRecolhimento) {
		grupoRecolhimentoRepository.cadastrar(grupoRecolhimento.getDsGrupoRecolhimento());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> atualizar(GrupoRecolhimento grupoRecolhimento) {	
		grupoRecolhimentoRepository.atualizar(grupoRecolhimento.getNrGrupoRecolhimento(), grupoRecolhimento.getDsGrupoRecolhimento(), grupoRecolhimento.getFlAtivo());	
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer nrGrupoRecolhimento) {		
		grupoRecolhimentoRepository.remover(nrGrupoRecolhimento);	
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
}
