package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ResgateModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String cpf;

  private String nome;

  private String logradouro;

  private String numero;

  private String bairro;

  private String cep;

  private String municipio;

  private String uf;

  @Column(name = "codbanco")
  private String codBanco;

  @Column(name = "nomebanco")
  private String nomeBanco;

  @Column(name = "stragencia")
  private String agencia;

  @Column(name = "strdigagencia")
  private char digitoAgencia;

  @Column(name = "strnumconta")
  private String conta;

  @Column(name = "strdigconta")
  private char digitoConta;

  @Column(name = "codtipomov")
  private char tipoConta;

  @Column(name = "valvalorcredito")
  private BigDecimal valorCredito;

  @Column(name = "valvalorcreditobruto")
  private BigDecimal valorCreditoBruto;

  @Column(name = "valvalordescontoir")
  private BigDecimal valorDescontoIR;

  private String operacao;

  @Column(name = "tra_status")
  private int status;

}