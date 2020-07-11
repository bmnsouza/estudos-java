package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ContaCorrenteBloqueioModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idcc")
  private Integer id;

  @Column(name = "pkconta")
  private Integer idContaCorrente;

  @Column(name = "strcpfcnpjuser")
  private String cpf;

  @Column(name = "conta_bloqueada")
  private char contaBloqueada;

  @Column(name = "resp")
  private String cpfResponsavel;

  private String justificativa;

  @Column(name = "data_alteracao")
  private String dataAlteracao;

}