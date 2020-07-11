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

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.ProconModel;
import br.gov.se.sefaz.ndgbackend.repository.ProconRepository;

@Service
@Validated
public class ProconService {

  @Autowired
  private ProconRepository proconRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    ProconModel proconModel = proconRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, proconModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf) {
    ProconModel proconModel = proconRepository.buscaPorCpf(cpf);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, proconModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorNome(String nome, @PositiveOrZero int pagina) {
    Slice<ProconModel> slice = proconRepository.buscaPorNome(nome, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<ProconModel> slice = proconRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(ProconModel proconModel) {
    int linhasAfetadas = proconRepository.cadastra(proconModel.getCpf(), proconModel.getSenha(), proconModel.getNome(), proconModel.getEmail(),
      proconModel.getCpfResponsavel());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualiza(ProconModel proconModel) {
    int linhasAfetadas = proconRepository.atualiza(proconModel.getId(), proconModel.getCpf(), proconModel.getNome(), proconModel.getDataNascimento(),
      proconModel.getLogradouro(), proconModel.getNumero(), proconModel.getComplemento(), proconModel.getBairro(), proconModel.getCep(),
      proconModel.getMunicipio(), proconModel.getUf(), proconModel.getDdd(), proconModel.getTelefone(), proconModel.getEmail(), proconModel.getFrase(),
      proconModel.getFraseEmail(), proconModel.getSituacao(), proconModel.getCpfResponsavel());

    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}