package bmnsouza.database.fazendario.entity;

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
public class SituacaoDocumento {
	
	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "SID_nrSituacao")
	private Integer nrSituacao;
	
	@Size(min = 5, max = 30)
	@NotNull
	@Column(name = "SID_dsSituacao")
	private String dsSituacao;
	
	@FlagAtivo
	@NotNull
	@Column(name = "SID_flAtivo")
	private String flAtivo;
	
}