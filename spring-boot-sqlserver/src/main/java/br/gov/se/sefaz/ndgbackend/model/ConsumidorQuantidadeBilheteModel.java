package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ConsumidorQuantidadeBilheteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "bil_cpfcnpj")
  private String cpf;

  private String nome;

  private int quantidade;

}