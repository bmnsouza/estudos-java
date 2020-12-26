package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bmnsouza.annotation.UF;
import bmnsouza.annotation.SituacaoProcon;
import lombok.Data;

@Data
@Entity
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "tb_UsuarioCat_Procon")
public class Procon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcpf")
	private Integer id;

	@CPF
	@NotEmpty
	private String cpf;

	@NotEmpty
	@Size(min = 10, max = 60)
	private String nome;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Size(max = 60)
	private String logradouro;

	@Size(max = 6)
	private String numero;

	@Size(max = 60)
	private String complemento;

	@Size(max = 19)
	private String bairro;

	@Size(max = 8)
	private String cep;

	private String municipio;

	@UF
	@NotEmpty
	private String uf;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String ddd;

	@NotEmpty
	@Size(min = 8, max = 9)
	@Column(name = "fone")
	private String telefone;

	@Email
	@NotEmpty
	@Size(max = 60)
	private String email;

	@Size(max = 128)
	private String frase;

	@Size(max = 128)
	@Column(name = "frase_email")
	private String fraseEmail;

	@NotEmpty
	@SituacaoProcon
	private String situacao;

	@CPF
	@NotEmpty
	@Column(name = "resp")
	private String cpfResponsavel;

	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;

	@JsonIgnore
	@Size(min = 6, max = 60)
	private String senha;

}