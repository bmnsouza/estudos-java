package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import bmnsouza.annotation.TipoPremio;
import bmnsouza.database.nota.entity.id.SorteioPremioId;
import lombok.Data;

@Data
@Entity
@IdClass(SorteioPremioId.class)
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "TB_SOP_SORTEIO_PREMIO")
public class SorteioPremio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sor_codsorteio")
	private Integer codSorteio;

	@Id
	@Column(name = "sop_codpremio")
	private Integer codPremio;

	@NotNull
	@Digits(integer = 16, fraction = 2)
	@Column(name = "sop_valpremio")
	private BigDecimal valorPremio;

	@NotNull
	@Column(name = "sop_flpremiado")
	private Integer flagPremiado;

	@NotNull
	@TipoPremio
	@Column(name = "sop_tppremio")
	private String tipoPremio;

}