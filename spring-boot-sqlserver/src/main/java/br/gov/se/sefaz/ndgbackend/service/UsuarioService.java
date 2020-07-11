package br.gov.se.sefaz.ndgbackend.service;

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
import br.gov.se.sefaz.ndgbackend.model.UsuarioModel;
import br.gov.se.sefaz.ndgbackend.repository.UsuarioRepository;

@Service
@Validated
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  // Tamanho da página definido no application.properties
  @Value("${spring.data.web.pageable.default-page-size}")
  private int PAGE_SIZE;

  private UtilResult utilResult = UtilResult.getInstance();

  public ResponseEntity<EntidadeResult> buscaPorCpf(@CPF String cpf) {
    UsuarioModel usuarioModel = usuarioRepository.buscaPorCpf(cpf);
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, usuarioModel);
  }

  public ResponseEntity<EntidadeResult> buscaPorNome(String nome, @PositiveOrZero int pagina) {
    Slice<UsuarioModel> slice = usuarioRepository.buscaPorNome(nome, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorPerfil(@PositiveOrZero int codPerfil, @PositiveOrZero int pagina) {
    Slice<UsuarioModel> slice = usuarioRepository.buscaPorPerfil(codPerfil, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaPorStatus(int status, @PositiveOrZero int pagina) {
    Slice<UsuarioModel> slice = usuarioRepository.buscaPorStatus(status, PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> buscaTodos(@PositiveOrZero int pagina) {
    Slice<UsuarioModel> slice = usuarioRepository.buscaTodos(PageRequest.of(pagina, PAGE_SIZE));
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, slice);
  }

  public ResponseEntity<EntidadeResult> cadastra(UsuarioModel usuarioModel) {
    int linhasAfetadas = usuarioRepository.cadastra(usuarioModel.getCpf(), usuarioModel.getNome(), usuarioModel.getCodPerfil(),
      usuarioModel.getCpfResponsavel());
    return utilResult.resultSucesso(HttpStatus.CREATED, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

  public ResponseEntity<EntidadeResult> atualiza(UsuarioModel usuarioModel) {
    int linhasAfetadas = usuarioRepository.atualiza(usuarioModel.getCpf(), usuarioModel.getNome(), usuarioModel.getCodPerfil(), usuarioModel.getStatus(),
      usuarioModel.getCpfResponsavel());
    return utilResult.resultSucesso(HttpStatus.OK, utilResult.MSG_DEFAULT_SUCESSO, utilResult.linhasAfetadas(linhasAfetadas));
  }

}