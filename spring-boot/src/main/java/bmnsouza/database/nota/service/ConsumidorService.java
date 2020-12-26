package bmnsouza.database.nota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.nota.entity.Consumidor;
import bmnsouza.database.nota.entity.dto.consumidor.ConsumidorAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.consumidor.ConsumidorCadastrarDTO;
import bmnsouza.database.nota.repository.ConsumidorRepository;
import bmnsouza.util.SenhaUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class ConsumidorService {

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorId(Integer id) {
		Consumidor dados = consumidorRepository.buscarPorId(id);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorCpf(String cpf) {
		Consumidor dados = consumidorRepository.buscarPorCpf(cpf);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorNome(String nome, int pagina) {
		Slice<Consumidor> dados = consumidorRepository.buscarPorNome(nome, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Consumidor> dados = consumidorRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(ConsumidorCadastrarDTO consumidorCadastrarDTO) throws ServiceException {
		// Verifica se o consumidor está cadastrado
		Consumidor consumidor = consumidorRepository.buscarPorCpf(consumidorCadastrarDTO.getCpf());
		if (consumidor != null) {
			throw new ServiceException("cpf", "Consumidor já cadastrado");
		}

		// Criptografa a senha
		consumidorCadastrarDTO.setSenha(SenhaUtil.criptografar(consumidorCadastrarDTO.getSenha()));

		consumidorRepository.cadastrar(consumidorCadastrarDTO.getCpf(), consumidorCadastrarDTO.getNome(), consumidorCadastrarDTO.getDataNascimento(),
			consumidorCadastrarDTO.getLogradouro(), consumidorCadastrarDTO.getNumero(), consumidorCadastrarDTO.getComplemento(), consumidorCadastrarDTO.getBairro(),
			consumidorCadastrarDTO.getCep(), consumidorCadastrarDTO.getMunicipio(), consumidorCadastrarDTO.getUf(), consumidorCadastrarDTO.getDdd(),
			consumidorCadastrarDTO.getTelefone(), consumidorCadastrarDTO.getEmail(), consumidorCadastrarDTO.getFrase(), consumidorCadastrarDTO.getFraseEmail(),
			consumidorCadastrarDTO.getIdEntidade(), consumidorCadastrarDTO.getSenha());

		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(Consumidor consumidor) {
		consumidorRepository.atualizar(consumidor.getId(), consumidor.getCpf(), consumidor.getNome(), consumidor.getDataNascimento(),
			consumidor.getLogradouro(), consumidor.getNumero(), consumidor.getComplemento(), consumidor.getBairro(), consumidor.getCep(), consumidor.getMunicipio(),
			consumidor.getUf(), consumidor.getDdd(), consumidor.getTelefone(), consumidor.getEmail(), consumidor.getFrase(), consumidor.getFraseEmail(),
			consumidor.getIdEntidade(), consumidor.getEstado(), consumidor.getSituacao(), consumidor.getCpfResponsavel(), consumidor.getJustificativa());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizarSenha(ConsumidorAtualizarSenhaDTO consumidorAtualizarSenhaDTO) throws ServiceException {
		// Verifica se o consumidor existe
		Consumidor consumidor = consumidorRepository.buscarPorId(consumidorAtualizarSenhaDTO.getId());
		if (consumidor == null) {
			throw new ServiceException("cpf", "Consumidor não encontrado");
		}

		// Valida a atualização da senha e obtém a senha nova criptografada
		consumidorAtualizarSenhaDTO.setSenhaNova(SenhaUtil.validarAtualizacaoSenha(consumidor.getSenha(), consumidorAtualizarSenhaDTO.getSenhaAntiga(), consumidorAtualizarSenhaDTO.getSenhaNova(),
				consumidorAtualizarSenhaDTO.getConfirmaSenhaNova()));

		consumidorRepository.atualizarSenha(consumidorAtualizarSenhaDTO.getId(), consumidorAtualizarSenhaDTO.getSenhaNova(), consumidorAtualizarSenhaDTO.getFraseEmail(), consumidorAtualizarSenhaDTO.getCpfResponsavel());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}