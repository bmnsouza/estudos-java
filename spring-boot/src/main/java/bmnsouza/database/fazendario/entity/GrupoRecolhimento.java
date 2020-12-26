package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.FlagAtivo;
import lombok.Data;

@Data
@Entity
public class GrupoRecolhimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "GRR_nrGrupoRecolhimento")
	private Integer nrGrupoRecolhimento;
	
	@Size(max = 30, min = 5)
	@NotNull
	@Column(name = "GRR_dsGrupoRecolhimento")
	private String dsGrupoRecolhimento;
	
	@FlagAtivo
	@NotNull
	@Column(name = "GRR_flAtivo")
	private String flAtivo;
	
}
