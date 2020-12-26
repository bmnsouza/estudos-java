package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bmnsouza.annotation.UF;
import bmnsouza.annotation.Estado;
import bmnsouza.annotation.Situacao;
import lombok.Data;

@Data
@Entity
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "tb_UsuarioCat_CNPJ")
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcnpj")
	private Integer id;

	@CNPJ
	@NotEmpty
	private String cnpj;

	@NotEmpty
	@Size(min = 10, max = 150)
	private String razao;

	@Size(max = 60)
	private String logradouro;

	@Size(max = 6)
	private String numero;

	@Size(max = 50)
	private String complemento;

	@NotEmpty
	@Size(min = 5, max = 50)
	private String bairro;

	@NotEmpty
	@Size(min = 8, max = 8)
	private String cep;

	@NotEmpty
	@Size(min = 5, max = 50)
	private String municipio;

	@UF
	@NotEmpty
	@Size(min = 2, max = 2)
	private String uf;

	@NotEmpty
	@Size(min = 2, max = 2)
	@Column(name = "ddd1")
	private String ddd;

	@NotEmpty
	@Size(min = 8, max = 9)
	@Column(name = "tel1")
	private String telefone;

	@Email
	@NotEmpty
	@Size(max = 150)
	private String email;

	@Size(max = 128)
	private String frase;

	@Size(max = 128)
	private String fraseEmail;

	@CPF
	@NotEmpty
	private String cpfRepresentanteLegal;
	
	@NotEmpty
	@Size(min = 10, max = 70)
	private String nomeRepresentanteLegal;

	@Estado
	@NotEmpty
	private String estado;

	@Situacao
	@NotEmpty
	private String situacao;

	@CPF
	@NotEmpty
	@Column(name = "resp")
	private String cpfResponsavel;

	@Size(max = 255)
	private String justificativa;

	@Column(name = "dataalt")
	private LocalDateTime dataAlteracao;

	@JsonIgnore
	@Size(min = 6, max = 60)
	private String senha;

}