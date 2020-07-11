package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SorteioPremioResgateModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "sor_codsorteio")
  private Integer codSorteio;

  @Column(name = "sor_dtrealizacao")
  private String dataRealizacao;

  @Column(name = "sop_vlpremiacao")
  private BigDecimal valorPremiacao;

  @Column(name = "mov_vlresgate")
  private BigDecimal valorResgatado;

  @Column(name = "mov_vlmovimentacao")
  private BigDecimal valorDiferenca;

}