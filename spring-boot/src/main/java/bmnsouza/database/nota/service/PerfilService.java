package bmnsouza.database.nota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.nota.entity.Perfil;
import bmnsouza.database.nota.repository.PerfilRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private ResultUtil resultUtil;

	public ResponseEntity<EntidadeResult> buscarPorId(Integer id) {
		Perfil dados = perfilRepository.buscarPorId(id);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos() {
		List<Perfil> dados = perfilRepository.buscarTodos();
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

}