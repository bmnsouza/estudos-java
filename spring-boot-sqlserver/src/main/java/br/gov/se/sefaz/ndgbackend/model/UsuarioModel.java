package br.gov.se.sefaz.ndgbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UsuarioModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "usu_login")
  private String cpf;

  @Column(name = "usu_nome")
  private String nome;

  @Column(name = "per_codperfil")
  private int codPerfil;

  @Column(name = "usu_dtregistro")
  private String dataRegistro;

  @Column(name = "usu_flusuario")
  private int status;

  @Column(name = "usu_loginresp")
  private String cpfResponsavel;

}