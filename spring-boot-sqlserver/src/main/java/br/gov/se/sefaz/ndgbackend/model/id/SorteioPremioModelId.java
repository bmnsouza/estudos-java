package br.gov.se.sefaz.ndgbackend.model.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SorteioPremioModelId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer codSorteio;

  private Integer codPremio;

}