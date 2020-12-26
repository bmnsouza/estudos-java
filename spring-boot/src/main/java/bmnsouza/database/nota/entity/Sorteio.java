package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.StatusSorteio;
import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "TB_SOR_SORTEIO")
public class Sorteio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sor_codsorteio")
	private Integer codSorteio;

	@Column(name = "sor_dtinicio")
	private LocalDate dataInicio;

	@Column(name = "sor_dtfim")
	private LocalDate dataFim;

	@Column(name = "sor_dtrealizacao")
	private LocalDate dataRealizacao;

	@Column(name = "sor_numsorteioloteria")
	private Integer concursoLoteria;

	@Size(max = 25)
	@Column(name = "sor_dscresultadoloteria")
	private String premiosLoteria;

	@Column(name = "sor_numfaixaini")
	private Integer faixaInicio;

	@Column(name = "sor_numfaixafim")
	private Integer faixaFim;

	@Size(max = 11)
	@Column(name = "sor_numsemente")
	private String sementeBilhete;

	@Column(name = "sor_hashcodesementebilhete")
	private Integer hashCodeSementeBilhete;

	@Size(max = 13)
	@Column(name = "sor_numposimpares")
	private String sementeSorteio;

	@Column(name = "sor_numsementesorteio")
	private Integer hashCodeSementeSorteio;

	@Size(max = 255)
	@Column(name = "sor_dscobservacao")
	private String observacao;

	@NotNull
	@StatusSorteio
	@Column(name = "sor_flsorteio")
	private Integer status;

}