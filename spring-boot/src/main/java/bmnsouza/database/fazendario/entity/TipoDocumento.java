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
public class TipoDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "TDO_cdTipoDocumento")
	private Integer cdTipoDocumento;
	
	@Size(min = 3, max = 30)
	@NotNull
	@Column(name = "TDO_dsTipoDocumento")
	private String dsTipoDocumento;
	
	@FlagAtivo
	@NotNull
	@Column(name = "TDO_flAtivo")
	private String flAtivo;
	
}