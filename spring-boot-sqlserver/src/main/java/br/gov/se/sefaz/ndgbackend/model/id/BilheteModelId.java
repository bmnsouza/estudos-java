package br.gov.se.sefaz.ndgbackend.model.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BilheteModelId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer numBilhete;

  private Integer codSorteio;

}