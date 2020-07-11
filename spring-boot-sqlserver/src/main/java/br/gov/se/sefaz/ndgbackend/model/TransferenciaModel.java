package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TransferenciaModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "pktransf")
  private Integer id;

  @Column(name = "codmovimentacao")
  private int codMovimentacao;

  @Column(name = "strcpfcnpjuser")
  private String cpf;

  @Column(name = "codbanco")
  private String banco;

  @Column(name = "stragencia")
  private String agencia;

  @Column(name = "strdigagencia")
  private char digitoAgencia;

  @Column(name = "strnumconta")
  private String conta;

  @Column(name = "strdigconta")
  private char digitoConta;

  private String operacao;

  @Column(name = "valvalorcredito")
  private BigDecimal valorCredito;

  @Column(name = "valvalorcreditobruto")
  private BigDecimal valorCreditoBruto;

  @Column(name = "valvalordescontoir")
  private BigDecimal valorDescontoIR;

  @Column(name = "tra_status")
  private int status;

  @Column(name = "dtgeracaorelatorio")
  private String dataGeracaoRelatorio;

  @Column(name = "tra_dtcargaigesp")
  private String dataCargaIgesp;

}