package bmnsouza.database.nota.service;

import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.exception.ServiceException;
import bmnsouza.database.nota.entity.Entidade;
import bmnsouza.database.nota.entity.dto.entidade.EntidadeAtualizarSenhaDTO;
import bmnsouza.database.nota.entity.dto.entidade.EntidadeCadastrarDTO;
import bmnsouza.database.nota.repository.EntidadeRepository;
import bmnsouza.util.SenhaUtil;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepository entidadeRepository;

	@Autowired
	private ResultUtil resultUtil;

	// Tamanho da página
	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarPorId(@Positive Integer id) {
		Entidade dados = entidadeRepository.buscarPorId(id);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorCnpj(@CNPJ String cnpj) {
		Entidade dados = entidadeRepository.buscarPorCnpj(cnpj);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorRazao(String razao, int pagina) {
		Slice<Entidade> dados = entidadeRepository.buscarPorRazao(razao, PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<Entidade> dados = entidadeRepository.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(EntidadeCadastrarDTO entidadeCadastrarDTO) throws ServiceException {
		// Verifica se a Entidade está cadastrada
		Entidade entidade = entidadeRepository.buscarPorCnpj(entidadeCadastrarDTO.getCnpj());
		if (entidade != null) {
			throw new ServiceException("cnpj", "Entidade já cadastrada");
		}

		// Criptografa a senha
		entidadeCadastrarDTO.setSenha(SenhaUtil.criptografar(entidadeCadastrarDTO.getSenha()));

		entidadeRepository.cadastrar(entidadeCadastrarDTO.getCnpj(), entidadeCadastrarDTO.getRazao(), entidadeCadastrarDTO.getLogradouro(),
			entidadeCadastrarDTO.getNumero(), entidadeCadastrarDTO.getComplemento(), entidadeCadastrarDTO.getBairro(), entidadeCadastrarDTO.getCep(),
			entidadeCadastrarDTO.getMunicipio(), entidadeCadastrarDTO.getUf(), entidadeCadastrarDTO.getDdd(), entidadeCadastrarDTO.getTelefone(),
			entidadeCadastrarDTO.getEmail(), entidadeCadastrarDTO.getFrase(), entidadeCadastrarDTO.getFraseEmail(), entidadeCadastrarDTO.getCpfRepresentanteLegal(),
			entidadeCadastrarDTO.getNomeRepresentanteLegal(), entidadeCadastrarDTO.getSenha(),
			entidadeCadastrarDTO.getCpfResponsavel());

		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizar(Entidade entidade) {
		entidadeRepository.atualizar(entidade.getId(), entidade.getCnpj(), entidade.getRazao(), entidade.getLogradouro(),
			entidade.getNumero(), entidade.getComplemento(), entidade.getBairro(), entidade.getCep(), entidade.getMunicipio(), entidade.getUf(),
			entidade.getDdd(), entidade.getTelefone(), entidade.getEmail(), entidade.getFrase(), entidade.getFraseEmail(),
			entidade.getCpfRepresentanteLegal(), entidade.getNomeRepresentanteLegal(), entidade.getEstado(), entidade.getSituacao(),
			entidade.getCpfResponsavel(), entidade.getJustificativa());

		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> atualizarSenha(EntidadeAtualizarSenhaDTO entidadeAtualizarSenhaDTO) throws ServiceException {
		// Verifica se a Entidade existe
		Entidade entidade = entidadeRepository.buscarPorId(entidadeAtualizarSenhaDTO.getId());
		if (entidade == null) {
			throw new ServiceException("cnpj", "Entidade não encontrada");
		}

		// Valida a atualização da senha e obtém a senha nova criptografada
		entidadeAtualizarSenhaDTO.setSenhaNova(SenhaUtil.validarAtualizacaoSenha(entidade.getSenha(), entidadeAtualizarSenhaDTO.getSenhaAntiga(), entidadeAtualizarSenhaDTO.getSenhaNova(),
				entidadeAtualizarSenhaDTO.getConfirmaSenhaNova()));

		entidadeRepository.atualizarSenha(entidadeAtualizarSenhaDTO.getId(), entidadeAtualizarSenhaDTO.getSenhaNova(), entidadeAtualizarSenhaDTO.getFraseEmail(), entidadeAtualizarSenhaDTO.getCpfResponsavel());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}