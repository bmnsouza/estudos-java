package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ConsumidorQuantidadeBilheteEntidadeModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "bil_cpfcnpj")
  private String cpfConsumidor;

  @Column(name = "bil_cnpjentidade")
  private String cnpjEntidade;

  private int quantidade;

}