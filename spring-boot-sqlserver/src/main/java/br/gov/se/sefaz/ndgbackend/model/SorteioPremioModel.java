package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import br.gov.se.sefaz.ndgbackend.model.id.SorteioPremioModelId;
import lombok.Data;

@Data
@Entity
@IdClass(SorteioPremioModelId.class)
public class SorteioPremioModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "sor_codsorteio")
  private Integer codSorteio;

  @Id
  @Column(name = "sop_codpremio")
  private Integer codPremio;

  @Column(name = "sop_valpremio")
  private BigDecimal valorPremio;

  @Column(name = "sop_flpremiado")
  private Integer flagPremiado;

  @Column(name = "sop_tppremio")
  private char tipoPremio;

}