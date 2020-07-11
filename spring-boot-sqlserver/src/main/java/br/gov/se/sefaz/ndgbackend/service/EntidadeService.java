package br.gov.se.sefaz.ndgbackend.service;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;
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
import br.gov.se.sefaz.ndgbackend.model.EntidadeModel;
import br.gov.se.sefaz.ndgbackend.repository.EntidadeRepository;

@Service
@Validated
public class EntidadeService {

  @Autowired
  private EntidadeRepository entidadeRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    EntidadeModel entidadeModel = entidadeRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, entidadeModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorCnpj(@CNPJ String cnpj) {
    EntidadeModel entidadeModel = entidadeRepository.buscaPorCnpj(cnpj);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, entidadeModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorRazao(String razao, @PositiveOrZero int pagina) {
    Slice<EntidadeModel> slice = entidadeRepository.buscaPorRazao(razao, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<EntidadeModel> slice = entidadeRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(EntidadeModel entidadeModel) {
    int linhasAfetadas = entidadeRepository.cadastra(entidadeModel.getCnpj(), entidadeModel.getRazao(), entidadeModel.getLogradouro(),
      entidadeModel.getNumero(), entidadeModel.getComplemento(), entidadeModel.getBairro(), entidadeModel.getCep(), entidadeModel.getMunicipio(),
      entidadeModel.getUf(), entidadeModel.getDdd(), entidadeModel.getTelefone(), entidadeModel.getEmail(), entidadeModel.getFrase(),
      entidadeModel.getFraseEmail(), entidadeModel.getCpfRepresentanteLegal(), entidadeModel.getNomeRepresentanteLegal(), entidadeModel.getSenha(),
      entidadeModel.getCpfResponsavel());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualiza(EntidadeModel entidadeModel) {
    int linhasAfetadas = entidadeRepository.atualiza(entidadeModel.getId(), entidadeModel.getCnpj(), entidadeModel.getRazao(), entidadeModel.getLogradouro(),
      entidadeModel.getNumero(), entidadeModel.getComplemento(), entidadeModel.getBairro(), entidadeModel.getCep(), entidadeModel.getMunicipio(),
      entidadeModel.getUf(), entidadeModel.getDdd(), entidadeModel.getTelefone(), entidadeModel.getEmail(), entidadeModel.getFrase(),
      entidadeModel.getFraseEmail(), entidadeModel.getCpfRepresentanteLegal(), entidadeModel.getNomeRepresentanteLegal(), entidadeModel.getEstado(),
      entidadeModel.getSituacao(), entidadeModel.getCpfResponsavel(), entidadeModel.getJustificativa());
    
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}