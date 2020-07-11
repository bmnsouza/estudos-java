package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SorteioModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "sor_codsorteio")
  private Integer codSorteio;

  @Column(name = "sor_dtinicio")
  private String dataInicio;

  @Column(name = "sor_dtfim")
  private String dataFim;

  @Column(name = "sor_dtrealizacao")
  private String dataRealizacao;

  @Column(name = "sor_numsorteioloteria")
  private Integer concursoLoteria;

  @Column(name = "sor_dscresultadoloteria")
  private String premiosLoteria;

  @Column(name = "sor_numfaixaini")
  private Integer faixaInicio;

  @Column(name = "sor_numfaixafim")
  private Integer faixaFim;

  @Column(name = "sor_numsemente")
  private String sementeBilhete;

  @Column(name = "sor_hashcodesementebilhete")
  private Integer hashCodeSementeBilhete;

  @Column(name = "sor_numposimpares")
  private String sementeSorteio;

  @Column(name = "sor_numsementesorteio")
  private Integer hashCodeSementeSorteio;

  @Column(name = "sor_dscobservacao")
  private String observacao;

  @Column(name = "sor_flsorteio")
  private int status;

}