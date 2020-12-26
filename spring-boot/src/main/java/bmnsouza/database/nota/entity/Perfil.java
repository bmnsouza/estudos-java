package bmnsouza.database.nota.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "TB_PER_PERFIL")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "per_codperfil")
	private Integer codPerfil;

	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "per_dscperfil")
	private String dscPerfil;

}