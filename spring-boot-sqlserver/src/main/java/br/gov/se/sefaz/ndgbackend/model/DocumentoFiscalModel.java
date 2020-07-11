package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DocumentoFiscalModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "num_notafiscal")
  private String numeroNotaFiscal;

  @Column(name = "tip_notafiscal")
  private String tipoNotaFiscal;

  @Column(name = "num_emitente")
  private String cnpjEmitente;

  @Column(name = "dsc_contribuinte")
  private String razaoEmitente;

  @Column(name = "num_destinatario")
  private String cpfDestinatario;

  @Column(name = "val_total")
  private BigDecimal valorTotal;

  @Column(name = "val_credito")
  private BigDecimal valorCredito;

  @Column(name = "dat_emissao")
  private String dataEmissao;

  @Column(name = "dat_processamento")
  private String dataProcessamento;

  @Column(name = "ind_situacao")
  private int situacao;

  @Column(name = "fl_devolucao")
  private int devolucao;

}