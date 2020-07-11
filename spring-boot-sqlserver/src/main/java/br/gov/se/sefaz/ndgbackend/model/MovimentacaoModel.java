package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MovimentacaoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "codmovimentacao")
  private int codMovimentacao;

  @Column(name = "fkcontacorrente")
  private long idContaCorrente;

  @Column(name = "codtipomov")
  private short tipoMovimentacao;

  @Column(name = "strdescmov")
  private String descricaoMovimentacao;

  @Column(name = "valsaldoanterior")
  private BigDecimal saldoAnterior;

  @Column(name = "valvalormov")
  private BigDecimal valorMovimentacao;

  @Column(name = "valsaldoatual")
  private BigDecimal saldoAtual;

  @Column(name = "bil_numbilhete")
  private Integer numBilhete;

  @Column(name = "sor_codsorteio")
  private Integer codSorteio;

  @Column(name = "dtmtimestamp")
  private String dataMovimentacao;

}