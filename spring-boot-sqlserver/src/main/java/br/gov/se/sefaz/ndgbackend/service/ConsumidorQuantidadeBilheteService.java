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
import br.gov.se.sefaz.ndgbackend.model.ConsumidorQuantidadeBilheteModel;
import br.gov.se.sefaz.ndgbackend.repository.ConsumidorQuantidadeBilheteRepository;

@Service
@Validated
public class ConsumidorQuantidadeBilheteService {

  @Autowired
  private ConsumidorQuantidadeBilheteRepository consumidorQuantidadeBilheteRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;
  
  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorSorteio(@Positive int codSorteio, @PositiveOrZero int pagina) {
    Slice<ConsumidorQuantidadeBilheteModel> slice = consumidorQuantidadeBilheteRepository.buscaPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

}