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

import bmnsouza.annotation.ContaBloqueada;
import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "ContaCorrente_Bloqueio")
public class ContaCorrenteBloqueio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcc")
	private Integer id;

	@CPF
	@NotEmpty
	@Column(name = "strcpfcnpjuser")
	private String cpf;

	@NotEmpty
	@ContaBloqueada
	@Column(name = "conta_bloqueada")
	private String contaBloqueada;

	@NotNull
	@Size(max = 255)
	private String justificativa;

	@CPF
	@Column(name = "resp")
	private String cpfResponsavel;

	@Column(name = "data_alteracao")
	private LocalDateTime dataAlteracao;

}