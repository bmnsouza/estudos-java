package br.gov.se.sefaz.ndgbackend.service;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
import br.gov.se.sefaz.ndgbackend.model.EntidadeTotalBilheteModel;
import br.gov.se.sefaz.ndgbackend.repository.EntidadeTotalBilheteRepository;

@Service
@Validated
public class EntidadeTotalBilheteService {

  @Autowired
  private EntidadeTotalBilheteRepository entidadeTotalBilheteRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;
  
  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPercentualBilhete(@Positive int codSorteio, @PositiveOrZero int pagina) {
    Slice<EntidadeTotalBilheteModel> slice = entidadeTotalBilheteRepository.buscaPercentualBilhete(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPercentualIndicacao(@Positive int codSorteio, @PositiveOrZero int pagina) {
    Slice<EntidadeTotalBilheteModel> slice = entidadeTotalBilheteRepository.buscaPercentualIndicacao(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

}