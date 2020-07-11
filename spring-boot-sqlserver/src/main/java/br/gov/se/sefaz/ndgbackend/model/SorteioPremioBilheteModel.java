package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SorteioPremioBilheteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "sop_codpremio")
  private Integer codPremio;

  @Column(name = "sop_valpremio")
  private BigDecimal valPremio;

  @Column(name = "sop_tppremio")
  private char tpPremio;

  @Column(name = "bil_numbilhete")
  private Integer numBilhete;

  @Column(name = "bil_cpfcnpj")
  private String cpfCnpjGanhador;

  @Column(name = "nome")
  private String nomeRazaoGanhador;

  private String municipio;

  private String uf;

}