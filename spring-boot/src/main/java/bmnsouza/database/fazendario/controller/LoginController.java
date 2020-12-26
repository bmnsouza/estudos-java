package	bmnsouza.database.fazendario.controller;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bmnsouza.exception.ServiceException;
import bmnsouza.annotation.PerfilLogin;
import bmnsouza.database.fazendario.service.LoginService;
import bmnsouza.util.result.EntidadeResult;

@Validated
@RestController
@RequestMapping("api/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("autenticar")
	public ResponseEntity<EntidadeResult> autenticar(@RequestParam @Size(min = 8, max = 14) String usuario, @Size(min = 6, max = 60) String senha,
		@PerfilLogin String perfil) throws ServiceException {
		return loginService.autenticar(usuario, senha, perfil);
	}

}