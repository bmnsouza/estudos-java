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
import br.gov.se.sefaz.ndgbackend.model.ContaCorrenteBloqueioModel;
import br.gov.se.sefaz.ndgbackend.repository.ContaCorrenteBloqueioRepository;

@Service
@Validated
public class ContaCorrenteBloqueioService {

  @Autowired
  private ContaCorrenteBloqueioRepository contaCorrenteBloqueioRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;
  
  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorId(@Positive Integer id) {
    ContaCorrenteBloqueioModel contaCorrenteBloqueioModel = contaCorrenteBloqueioRepository.buscaPorId(id);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, contaCorrenteBloqueioModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorContaCorrente(@Positive Integer idContaCorrente, @PositiveOrZero int pagina) {
    Slice<ContaCorrenteBloqueioModel> slice = contaCorrenteBloqueioRepository.buscaPorContaCorrente(idContaCorrente, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf, @PositiveOrZero int pagina) {
    Slice<ContaCorrenteBloqueioModel> slice = contaCorrenteBloqueioRepository.buscaPorCpf(cpf, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<ContaCorrenteBloqueioModel> slice = contaCorrenteBloqueioRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(ContaCorrenteBloqueioModel contaCorrenteBloqueioModel) {
    int linhasAfetadas = contaCorrenteBloqueioRepository.cadastra(contaCorrenteBloqueioModel.getCpf(), contaCorrenteBloqueioModel.getContaBloqueada(),
      contaCorrenteBloqueioModel.getCpfResponsavel(), contaCorrenteBloqueioModel.getJustificativa());

    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}