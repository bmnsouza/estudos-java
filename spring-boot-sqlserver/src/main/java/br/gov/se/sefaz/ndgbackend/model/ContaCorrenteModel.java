package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ContaCorrenteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "pkconta")
  private Integer id;

  @Column(name = "strcpfcnpjuser")
  private String cpf;

  @Column(name = "dtmtimestamp")
  private String dataCriacao;

}