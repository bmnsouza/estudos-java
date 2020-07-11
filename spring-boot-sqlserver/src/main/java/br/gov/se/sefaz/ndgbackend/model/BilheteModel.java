package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import br.gov.se.sefaz.ndgbackend.model.id.BilheteModelId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BilheteModelId.class)
public class BilheteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "bil_numbilhete")
  private Integer numBilhete;

  @Id
  @Column(name = "sor_codsorteio")
  private Integer codSorteio;

  @Column(name = "sop_codpremio")
  private Integer codPremio;

  @Column(name = "bil_cpfcnpj")
  private String cpfConsumidor;

  @Column(name = "bil_cnpjentidade")
  private String cnpjEntidade;

}