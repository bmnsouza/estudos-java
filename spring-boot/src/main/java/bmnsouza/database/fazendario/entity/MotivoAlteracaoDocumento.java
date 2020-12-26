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
public class MotivoAlteracaoDocumento implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@Max(999)
	@Min(1)
	@NotNull
	@Column(name = "MAD_cdMotivo")
	private Integer cdMotivo;
	
	@Size(min = 5, max = 60)
	@NotNull
	@Column(name = "MAD_dsMotivo")
	private String dsMotivo;
	
	@FlagAtivo
	@NotNull
	@Column(name = "MAD_flAtivo")
	private String flAtivo;
	
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "SID_nrSituacao")
	private Integer nrSituacao;
	
	@FlagAtivo
	@NotNull
	@Column(name = "MAD_flManual")
	private String flManual;

}