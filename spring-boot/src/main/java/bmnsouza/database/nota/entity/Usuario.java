package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "TB_USU_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usu_login")
	private String cpf;

	@NotEmpty
	@Size(min = 10, max = 70)
	@Column(name = "usu_nome")
	private String nome;

	@NotNull
	@Column(name = "per_codperfil")
	private int codPerfil;

	@Column(name = "usu_dtregistro")
	private LocalDateTime dataRegistro;

	@NotNull
	@Column(name = "usu_flusuario")
	private int status;

	@CPF
	@NotEmpty
	@Column(name = "usu_loginresp")
	private String cpfResponsavel;

}