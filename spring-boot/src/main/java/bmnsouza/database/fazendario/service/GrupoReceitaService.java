package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.GrupoReceita;
import bmnsouza.database.fazendario.entity.dto.grupoReceita.GrupoReceitaDTO;
import bmnsouza.database.fazendario.repository.GrupoReceitaRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class GrupoReceitaService {
	
	@Autowired
	private GrupoReceitaRepository grupoReceitaRepository;
	
	@Autowired
	private ResultUtil resultUtil;
	
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;
	
	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<GrupoReceita> dados = grupoReceitaRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarPorId(Integer nrGrupoReceita){
		GrupoReceita dados = grupoReceitaRepository.buscarPorId(nrGrupoReceita);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> cadastrar(GrupoReceitaDTO grupoReceitaDTO){
		grupoReceitaRepository.cadastrar(grupoReceitaDTO.getDsGrupoReceita());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);	
	}
	
	public ResponseEntity<EntidadeResult> atualizar(GrupoReceita grupoReceita){
		grupoReceitaRepository.atualizar(grupoReceita.getNrGrupoReceita(), grupoReceita.getDsGrupoReceita(), grupoReceita.getFlAtivo());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> remover(Integer nrGrupoReceita){
		grupoReceitaRepository.remover(nrGrupoReceita);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
}
