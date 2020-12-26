package bmnsouza.database.fazendario.entity;

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
public class Receita {

	@Id
	@Max(999999)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdReceita")
	private Integer cdReceita;

	@Size(min = 3, max = 60)
	@NotNull
	@Column(name = "REC_dsReceita")
	private String dsReceita;

	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "GRE_nrGrupoReceita")
	private Integer nrGrupoReceita;

	@Max(9)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdOrigemReceita")
	private Integer cdOrigemReceita;

	@Max(99999)
	@Min(1)
	@NotNull
	@Column(name = "ORG_cdOrgao")
	private Integer cdOrgao;

	@NotNull
	@Column(name = "REC_dtInicioVigencia")
	private LocalDate dtInicioVigencia;

	@Column(name = "REC_dtFimVigencia")
	private LocalDate dtFimVigencia;

	@Max(999999)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdReceitaPai")
	private Integer cdReceitaPai;

	@Max(9)
	@Min(1)
	@NotNull
	@Column(name = "REC_tpReceita")
	private Integer tpReceita;

}