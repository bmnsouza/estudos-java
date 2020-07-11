package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PerfilModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "per_codperfil")
  private Integer codPerfil;

  @Column(name = "per_dscperfil")
  private String dscPerfil;

}