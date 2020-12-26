
package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import bmnsouza.annotation.FlagAtivo;
import bmnsouza.database.fazendario.entity.id.IcmsContribuinteInscritoId;
import lombok.Data;

@Data
@Entity
@IdClass(IcmsContribuinteInscritoId.class)
public class IcmsContribuinteInscrito implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Max(99)
	@Min(1)	
	@NotNull
	@Column(name = "TPC_tpContribuinte")
	private Integer tpContribuinte;
	
	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "RET_nrRegimeTributacaoIcms")
	private Integer nrRegimeTributacaoIcms;
	
	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "FRC_nrFormaRecolhimentoIcms")
	private Integer nrFormaRecolhimentoICms;
	
	@FlagAtivo
	@NotNull
	@Column(name = "ICI_flVisivelContribuinte")
	private String flVisivelContribuinte;
	
}