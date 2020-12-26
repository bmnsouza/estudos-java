package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class FormaRecolhimentoIcms implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "FRC_nrFormaRecolhimentoIcms")
	private Integer nrFormaRecolhimentoIcms;
	
	@Size(max = 60, min = 5)
	@NotNull
	@Column(name = "FRC_dsFormaRecolhimentoIcms")
	private String dsFormaRecolhimentoIcms;
	
	@NotNull
	@Column(name = "FRC_dtInicioVigencia")
	private LocalDate dtInicioVigencia;
	
	@Column(name = "FRC_dtFimVigencia")
	private LocalDate dtFimVigencia;
	
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "GRR_nrGrupoRecolhimento")
	private Integer nrGrupoRecolhimento;

}