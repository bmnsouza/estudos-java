package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
public class ProconModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idcpf")
  private Integer id;

  @CPF
  private String cpf;

  private String nome;

  @Column(name = "data_nascimento")
  private String dataNascimento;

  private String logradouro;

  private String numero;

  private String complemento;

  private String bairro;

  private String cep;

  private String municipio;

  private String uf;

  private String ddd;

  @Column(name = "fone")
  private String telefone;

  @Email
  private String email;

  private String frase;

  @Column(name = "frase_email")
  private String fraseEmail;

  private String situacao;

  private String senha;

  @CPF
  @Column(name = "resp")
  private String cpfResponsavel;

  @Column(name = "data_alteracao")
  private String data_alteracao;

}