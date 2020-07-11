package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BloqueioResgateModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "blr_dtiniciobloqueio")
  private String dataBloqueio;

  @Column(name = "blr_msgbloqueio")
  private String mensagemBloqueio;

}