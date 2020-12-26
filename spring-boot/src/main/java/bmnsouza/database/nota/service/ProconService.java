package bmnsouza.database.nota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.nota.entity.Procon;
import bmnsouza.database.nota.entity.dto.procon.ProconAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.procon.ProconCadastrarDTO;
import bmnsouza.database.nota.repository.ProconRepository;
import bmnsouza.util.SenhaUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ProconService {

	@Autowired
	private ProconRepository proconRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorId(Integer id) {
		Procon dados = proconRepository.buscarPorId(id);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorCpf(String cpf) {
		Procon dados = proconRepository.buscarPorCpf(cpf);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorNome(String nome, int pagina) {
		Slice<Procon> dados = proconRepository.buscarPorNome(nome, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Procon> dados = proconRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(ProconCadastrarDTO proconCadastrarDTO) throws ServiceException {
		// Verifica se o usuário está cadastrado
		Procon procon = proconRepository.buscarPorCpf(proconCadastrarDTO.getCpf());
		if (procon != null) {
			throw new ServiceException("cpf", "Usuário já cadastrado");
		}

		// Substitui a senha pela concatenação do CPF com a senha e depois criptografa
		proconCadastrarDTO.setSenha(SenhaUtil.criptografar(new StringBuilder(proconCadastrarDTO.getCpf()).append(proconCadastrarDTO.getSenha()).toString()));

		proconRepository.cadastrar(proconCadastrarDTO.getCpf(), proconCadastrarDTO.getSenha(), proconCadastrarDTO.getNome(), proconCadastrarDTO.getEmail(), proconCadastrarDTO.getCpfResponsavel());
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(Procon procon) {
		proconRepository.atualizar(procon.getId(), procon.getCpf(), procon.getNome(), procon.getDataNascimento(), procon.getLogradouro(),
			procon.getNumero(), procon.getComplemento(), procon.getBairro(), procon.getCep(), procon.getMunicipio(), procon.getUf(), procon.getDdd(),
			procon.getTelefone(), procon.getEmail(), procon.getFrase(), procon.getFraseEmail(), procon.getSituacao(), procon.getCpfResponsavel());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizarSenha(ProconAtualizarSenhaDTO proconAtualizarSenhaDTO) throws ServiceException {
		// Verifica se o usuário existe
		Procon procon = proconRepository.buscarPorId(proconAtualizarSenhaDTO.getId());
		if (procon == null) {
			throw new ServiceException("cpf", "Usuário não encontrado");
		}

		// Substitui as senhas pela concatenação do CPF com a respectiva senha
		proconAtualizarSenhaDTO.setSenhaAntiga(new StringBuilder(procon.getCpf()).append(proconAtualizarSenhaDTO.getSenhaAntiga()).toString());
		proconAtualizarSenhaDTO.setSenhaNova(new StringBuilder(procon.getCpf()).append(proconAtualizarSenhaDTO.getSenhaNova()).toString());
		proconAtualizarSenhaDTO.setConfirmaSenhaNova(new StringBuilder(procon.getCpf()).append(proconAtualizarSenhaDTO.getConfirmaSenhaNova()).toString());

		// Valida a atualização da senha e obtém a senha nova criptografada
		proconAtualizarSenhaDTO.setSenhaNova(SenhaUtil.validarAtualizacaoSenha(procon.getSenha(), proconAtualizarSenhaDTO.getSenhaAntiga(), proconAtualizarSenhaDTO.getSenhaNova(),
				proconAtualizarSenhaDTO.getConfirmaSenhaNova()));

		proconRepository.atualizarSenha(proconAtualizarSenhaDTO.getId(), proconAtualizarSenhaDTO.getSenhaNova(), proconAtualizarSenhaDTO.getFraseEmail(), proconAtualizarSenhaDTO.getCpfResponsavel());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}