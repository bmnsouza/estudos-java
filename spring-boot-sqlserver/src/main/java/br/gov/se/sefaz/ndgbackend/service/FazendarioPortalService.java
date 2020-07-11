package br.gov.se.sefaz.ndgbackend.service;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.FazendarioPortalModel;
import br.gov.se.sefaz.ndgbackend.repository.FazendarioPortalRepository;

@Service
@Validated
public class FazendarioPortalService {

  @Autowired
  private FazendarioPortalRepository fazendarioPortalRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;
  
  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf) {
    FazendarioPortalModel fazendarioPortalModel = fazendarioPortalRepository.buscaPorCpf(cpf);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, fazendarioPortalModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorNome(String nome) {
    List<FazendarioPortalModel> list = fazendarioPortalRepository.buscaPorNome(nome);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, list);
  }

}