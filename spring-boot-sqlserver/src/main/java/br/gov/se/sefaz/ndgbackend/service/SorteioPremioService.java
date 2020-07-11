package br.gov.se.sefaz.ndgbackend.service;

import java.util.List;

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
import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;
import br.gov.se.sefaz.ndgbackend.repository.SorteioPremioRepository;

@Service
@Validated
public class SorteioPremioService {

  @Autowired
  private SorteioPremioRepository sorteioPremioRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorSorteio(@Positive Integer codSorteio, @PositiveOrZero int pagina) {
    Slice<SorteioPremioModel> slice = sorteioPremioRepository.buscaPorSorteio(codSorteio, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<SorteioPremioModel> slice = sorteioPremioRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(List<SorteioPremioModel> sorteioPremioModel) {
    int linhasAfetadas = sorteioPremioRepository.cadastra(sorteioPremioModel);
    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> transfere(@Positive Integer codSorteio) {
    int linhasAfetadas = sorteioPremioRepository.transfere(codSorteio);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}