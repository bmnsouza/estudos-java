package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import bmnsouza.database.fazendario.entity.id.PercentualRepassereceitaId;
import lombok.Data;

@Data
@Entity
@IdClass(PercentualRepassereceitaId.class)
public class PercentualRepasseReceita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Max(999999)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdReceita")
	private Integer cdReceita;
	
	@Id
	@Max(9999)
	@Min(1)
	@NotNull
	@Column(name = "ENC_cdEntidade")
	private Integer cdEntidade;
	
	@Id
	@Max(2)
	@Min(1)	
	@NotNull
	@Column(name = "PRR_tpProducao")
	private Integer tpProducao;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	@NotNull
	@Column(name = "PRR_vlPercentual")
	private BigDecimal vlPercentual;
	
}