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
import bmnsouza.annotation.Estado;
import bmnsouza.annotation.Situacao;
import lombok.Data;

@Data
@Entity
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "tb_UsuarioCat_CPF")
public class Consumidor implements Serializable {

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

	@Column(name = "datanasc")
	private LocalDate dataNascimento;

	@Size(max = 40)
	private String logradouro;

	@Size(max = 6)
	private String numero;

	@Size(max = 21)
	private String complemento;

	@Size(max = 20)
	private String bairro;

	@Size(max = 8)
	private String cep;

	@Size(max = 50)
	private String municipio;

	@UF
	@NotEmpty
	@Size(max = 2)
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
	@Size(max = 150)
	private String email;

	@Size(max = 128)
	private String frase;

	@Size(max = 128)
	private String fraseEmail;

	@Column(name = "idcnpj")
	private Integer idEntidade;

	@Estado
	@NotEmpty
	private String estado;

	@Situacao
	@NotEmpty
	private String situacao;

	@Size(max = 255)
	private String justificativa;

	@CPF
	@NotEmpty
	@Column(name = "resp")
	private String cpfResponsavel;

	@Column(name = "dataalt")
	private LocalDateTime dataAlteracao;

	@JsonIgnore
	@NotEmpty
	@Size(min = 6, max = 60)
	private String senha;

}