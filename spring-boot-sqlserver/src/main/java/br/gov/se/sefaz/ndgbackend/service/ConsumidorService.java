package br.gov.se.sefaz.ndgbackend.service;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.se.sefaz.ndgbackend.core.Data;
import br.gov.se.sefaz.ndgbackend.core.exception.TransactionAbortException;
import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.ConsumidorModel;
import br.gov.se.sefaz.ndgbackend.repository.ConsumidorRepository;

@Service
@Validated
public class ConsumidorService {

  @Autowired
  private ConsumidorRepository consumidorRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  private final String DATA_NASCIMENTO = "dataNascimento";
  private final String INVALIDA = "inválida";

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    ConsumidorModel consumidorModel = consumidorRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, consumidorModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf) {
    ConsumidorModel consumidorModel = consumidorRepository.buscaPorCpf(cpf);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, consumidorModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorNome(String nome, @PositiveOrZero int pagina) {
    Slice<ConsumidorModel> slice = consumidorRepository.buscaPorNome(nome, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<ConsumidorModel> slice = consumidorRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(ConsumidorModel consumidorModel) throws TransactionAbortException {
    if (!Data.isValida(consumidorModel.getDataNascimento())) {
      throw new TransactionAbortException(DATA_NASCIMENTO, INVALIDA);
    }

    int linhasAfetadas = consumidorRepository.cadastra(consumidorModel.getCpf(), consumidorModel.getNome(), consumidorModel.getDataNascimento(),
      consumidorModel.getLogradouro(), consumidorModel.getNumero(), consumidorModel.getComplemento(), consumidorModel.getBairro(), consumidorModel.getCep(),
      consumidorModel.getMunicipio(), consumidorModel.getUf(), consumidorModel.getDdd(), consumidorModel.getTelefone(), consumidorModel.getEmail(),
      consumidorModel.getFrase(), consumidorModel.getFraseEmail(), consumidorModel.getIdEntidade(), consumidorModel.getSenha());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualiza(ConsumidorModel consumidorModel) throws TransactionAbortException {
    if (!Data.isValida(consumidorModel.getDataNascimento())) {
      throw new TransactionAbortException(DATA_NASCIMENTO, INVALIDA);
    }

    int linhasAfetadas = consumidorRepository.atualiza(consumidorModel.getId(), consumidorModel.getCpf(), consumidorModel.getNome(),
      consumidorModel.getDataNascimento(), consumidorModel.getLogradouro(), consumidorModel.getNumero(), consumidorModel.getComplemento(),
      consumidorModel.getBairro(), consumidorModel.getCep(), consumidorModel.getMunicipio(), consumidorModel.getUf(), consumidorModel.getDdd(),
      consumidorModel.getTelefone(), consumidorModel.getEmail(), consumidorModel.getFrase(), consumidorModel.getFraseEmail(), consumidorModel.getIdEntidade(),
      consumidorModel.getEstado(), consumidorModel.getSituacao(), consumidorModel.getCpfResponsavel(), consumidorModel.getJustificativa());

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}