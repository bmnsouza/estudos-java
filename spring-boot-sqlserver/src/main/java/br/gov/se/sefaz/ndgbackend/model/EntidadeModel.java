package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
public class EntidadeModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idcnpj")
  private Integer id;

  @CNPJ
  private String cnpj;

  private String razao;

  private String logradouro;

  private String numero;

  private String complemento;

  private String bairro;

  private String cep;

  private String municipio;

  private String uf;

  @Column(name = "ddd1")
  private String ddd;

  @Column(name = "tel1")
  private String telefone;

  @Email
  private String email;

  private String frase;

  @Column(name = "fraseemail")
  private String fraseEmail;

  @CPF
  @Column(name = "cpfrepresentantelegal")
  private String cpfRepresentanteLegal;
  
  @Column(name = "nomerepresentantelegal")
  private String nomeRepresentanteLegal;

  private String estado;

  private String situacao;

  private String senha;

  @CPF
  @Column(name = "resp")
  private String cpfResponsavel;

  private String justificativa;
  @Column(name = "dataalt")

  private String dataAlteracao;

}