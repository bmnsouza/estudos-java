package bmnsouza.database.fazendario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.exception.ServiceException;
import bmnsouza.constant.LoginConstant;
import bmnsouza.database.fazendario.entity.Login;
import bmnsouza.database.fazendario.repository.LoginRepository;
import bmnsouza.database.nota.entity.Consumidor;
import bmnsouza.database.nota.entity.Entidade;
import bmnsouza.database.nota.entity.Procon;
import bmnsouza.database.nota.entity.Usuario;
import bmnsouza.database.nota.repository.ConsumidorRepository;
import bmnsouza.database.nota.repository.EntidadeRepository;
import bmnsouza.database.nota.repository.ProconRepository;
import bmnsouza.database.nota.repository.UsuarioRepository;
import bmnsouza.util.SenhaUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class LoginService {

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EntidadeRepository entidadeRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private ProconRepository proconRepository;

	@Autowired
	private ResultUtil resultUtil;

	public ResponseEntity<EntidadeResult> autenticar(String usuario, String senha, String perfil) throws ServiceException {
		boolean dados = false;

		// Consumidor
		if (perfil.equals(LoginConstant.CONSUMIDOR)) {
			Consumidor consumidor = consumidorRepository.buscarPorCpf(usuario);
			if (consumidor == null) {
				throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
			}

			if (consumidor.getEstado().equals(LoginConstant.ATIVO)) {
				if (consumidor.getSenha().equals(SenhaUtil.criptografar(senha))) {
					consumidorRepository.atualizarTentativaLoginSucesso(usuario);
					dados = true;
				} else {
					consumidorRepository.atualizarTentativaLoginErro(usuario);
					throw new ServiceException(LoginConstant.TENTATIVAS_ERRADAS);
				}
			} else {
				if (consumidor.getEstado().equals(LoginConstant.BLOQUEADO)) {
					throw new ServiceException(LoginConstant.SENHA_BLOQUEADA);
				} else if (consumidor.getEstado().equals(LoginConstant.INATIVO)) {
					throw new ServiceException(LoginConstant.USUARIO_INATIVO);
				}
			}

			// Fazendário, Contribuinte ou Contador
		} else if (perfil.equals(LoginConstant.FAZENDARIO) || perfil.equals(LoginConstant.CONTRIBUINTE) || perfil.equals(LoginConstant.CONTADOR)) {
			Login login = loginRepository.buscarPorUsuario(usuario);
			if (login == null) {
				throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
			}

			if (perfil.equals(LoginConstant.FAZENDARIO)) {
				Usuario fazendario = usuarioRepository.buscarPorCpf(usuario);
				if (fazendario == null) {
					throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
				} else if (fazendario.getStatus() == 0) {
					throw new ServiceException(LoginConstant.USUARIO_INATIVO);
				}
				// Verifica se o contribuinte tem permissão para acessar o sistema
			} else if (perfil.equals(LoginConstant.CONTRIBUINTE)) {
				if (usuario.length() != 9 || !usuario.startsWith("27")) {
					throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
				}

				if (!loginRepository.isAcessoPermitido(usuario) && !loginRepository.isObrigatoriedadePassada(usuario)) {
					throw new ServiceException("Emitente não possui obrigatoriedade de envio de Nota da Gente");
				}
			}

			if (loginRepository.isBloqueado(usuario)) {
				String SENHA_BLOQUEADA = perfil.equals(LoginConstant.FAZENDARIO) ? "Senha bloqueada. Por favor, siga os procedimentos do Sistema Fazendário"
					: "Senha bloqueada. Por favor, compareça ao CEAC com documento de identificação com foto para desbloqueá-la";
				throw new ServiceException(SENHA_BLOQUEADA);
			} else if (login.getSenha().equals(SenhaUtil.criptografar(usuario, senha))) {
				loginRepository.atualizarTentativaLoginSucesso(usuario);
				dados = true;
			} else {
				loginRepository.atualizarTentativaLoginErro(usuario);
				throw new ServiceException(LoginConstant.TENTATIVAS_ERRADAS);
			}

			// Entidade
		} else if (perfil.equals(LoginConstant.ENTIDADE)) {
			Entidade entidade = entidadeRepository.buscarPorCnpj(usuario);
			if (entidade == null) {
				throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
			}

			if (entidade.getEstado().equals(LoginConstant.ATIVO)) {
				if (entidade.getSenha().equals(SenhaUtil.criptografar(senha))) {
					entidadeRepository.atualizarTentativaLoginSucesso(usuario);
					dados = true;
				} else {
					entidadeRepository.atualizarTentativaLoginErro(usuario);
					throw new ServiceException(LoginConstant.TENTATIVAS_ERRADAS);
				}
			} else {
				if (entidade.getEstado().equals(LoginConstant.BLOQUEADO)) {
					throw new ServiceException(LoginConstant.SENHA_BLOQUEADA);
				} else if (entidade.getEstado().equals(LoginConstant.INATIVO)) {
					throw new ServiceException(LoginConstant.USUARIO_INATIVO);
				}
			}

			// Procon
		} else if (perfil.equals(LoginConstant.PROCON)) {
			Procon procon = proconRepository.buscarPorCpf(usuario);
			if (procon == null) {
				throw new ServiceException(LoginConstant.USUARIO_NAO_CADASTRADO);
			}

			if (procon.getSituacao().equals(LoginConstant.ATIVO) || procon.getSituacao().equals(LoginConstant.PENDENTE)) {
				if (procon.getSenha().equals(SenhaUtil.criptografar(new StringBuilder(usuario).append(senha).toString()))) {
					proconRepository.atualizarTentativaLoginSucesso(usuario);
					dados = true;
				} else {
					proconRepository.atualizarTentativaLoginErro(usuario);
					throw new ServiceException(LoginConstant.TENTATIVAS_ERRADAS);
				}
			} else {
				if (procon.getSituacao().equals(LoginConstant.BLOQUEADO)) {
					throw new ServiceException(LoginConstant.SENHA_BLOQUEADA);
				} else if (procon.getSituacao().equals(LoginConstant.INATIVO)) {
					throw new ServiceException(LoginConstant.USUARIO_INATIVO);
				}
			}
		}

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

}