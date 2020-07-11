package br.gov.se.sefaz.ndgbackend.model.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OmissaoModelId implements Serializable {

  private static final long serialVersionUID = 1L;

  private String ie;

  private String cnpj;

  private Integer ano;

  private Integer mes;

}