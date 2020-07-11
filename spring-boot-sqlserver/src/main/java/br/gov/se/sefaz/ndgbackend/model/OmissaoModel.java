package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import br.gov.se.sefaz.ndgbackend.model.id.OmissaoModelId;
import lombok.Data;

@Data
@Entity
@IdClass(OmissaoModelId.class)
public class OmissaoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ome_ie")
  private String ie;

  @Id
  @Column(name = "ome_cnpj")
  private String cnpj;

  @Id
  @Column(name = "ome_ano")
  private Integer ano;

  @Id
  @Column(name = "ome_mes")
  private Integer mes;

  @Column(name = "ome_rba")
  private BigDecimal rba;

  @Column(name = "ome_dtentrada")
  private String dataEntrada;

  // @Column(name = "cpfresponsavel")
  // private String cpfResponsavel;

  // private String protocolo;

}