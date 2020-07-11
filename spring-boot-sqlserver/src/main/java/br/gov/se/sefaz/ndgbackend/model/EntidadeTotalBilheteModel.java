package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EntidadeTotalBilheteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "bil_cnpjentidade")
  private String cnpj;

  private String razao;

  private int total;

  private BigDecimal percentual;

}