package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SaldoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "pksaldo")
  private Integer id;

  @Column(name = "valvalorsaldo")
  private BigDecimal saldo;

  @Column(name = "dtmtimestamp")
  private String dataCriacao;

}