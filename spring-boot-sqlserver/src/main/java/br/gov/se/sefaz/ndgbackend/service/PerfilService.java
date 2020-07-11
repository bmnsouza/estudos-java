package br.gov.se.sefaz.ndgbackend.service;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.gov.se.sefaz.ndgbackend.core.result.EntidadeResult;
import br.gov.se.sefaz.ndgbackend.core.result.UtilResult;
import br.gov.se.sefaz.ndgbackend.model.PerfilModel;
import br.gov.se.sefaz.ndgbackend.repository.PerfilRepository;

@Service
@Validated
public class PerfilService {

  @Autowired
  private PerfilRepository perfilRepository;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    PerfilModel perfilModel = perfilRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, perfilModel);
  }

  public ResponseEntity<EntidadeResult> buscaTodos() {
    List<PerfilModel> list = perfilRepository.buscaTodos();
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, list);
  }

}