package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
public class ConsumidorModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idcpf")
  private Integer id;

  @CPF
  @NotEmpty
  private String cpf;

  @Size(min = 6, max = 60)
  @NotEmpty
  private String nome;

  @NotNull
  @Column(name = "datanasc")
  private String dataNascimento;

  @Size(min = 5, max = 40)
  @NotNull
  private String logradouro;

  @Size(max = 6)
  @NotNull
  private String numero;

  @Size(max = 21)
  @NotNull
  private String complemento;

  @Size(min = 5, max = 20)
  @NotNull
  private String bairro;

  @Size(min = 8, max = 8)
  @NotEmpty
  private String cep;

  @Size(min = 5, max = 50)
  @NotNull
  private String municipio;

  @NotEmpty
  private String uf;

  @NotEmpty
  private String ddd;

  @Column(name = "fone")
  private String telefone;

  @Size(max = 150)
  @NotEmpty
  @Email
  private String email;

  private String frase;
  @Column(name = "fraseemail")

  private String fraseEmail;
  @Column(name = "idcnpj")

  private Integer idEntidade;

  private String estado;

  private String situacao;

  @Size(min = 6, max = 60)
  @NotEmpty
  private String senha;

  @CPF
  @Column(name = "resp")
  private String cpfResponsavel;

  private String justificativa;

  @Column(name = "dataalt")
  private String dataAlteracao;

}