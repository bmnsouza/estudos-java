package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import bmnsouza.database.fazendario.entity.id.TipoDocumentoSituacaoId;
import lombok.Data;

@Data
@Entity
@IdClass(TipoDocumentoSituacaoId.class)
public class TipoDocumentoSituacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "TDO_cdTipoDocumento")
	private Integer cdTipoDocumento;

	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "SID_nrSituacao")
	private Integer nrSituacao;

}