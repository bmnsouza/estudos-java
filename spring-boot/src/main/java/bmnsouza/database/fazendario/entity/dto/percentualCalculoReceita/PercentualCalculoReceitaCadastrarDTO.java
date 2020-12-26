package bmnsouza.database.fazendario.entity.dto.percentualCalculoReceita;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PercentualCalculoReceitaCadastrarDTO {

	@Max(999999)
	@Min(1)
	@NotNull
	private Integer cdReceita;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal vlPercentualMultaMora;
	
	@Max(99)
	@Min(1)
	private Integer qtMesLimiteMultaMora;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal vlPercentualJurosMora;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal vlPercentualAgregacao;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal vlPercentualAliquota;
	
	@DecimalMax("999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 6, fraction = 2)
	private BigDecimal vlUfp;
	
	@NotNull
	private LocalDate dtInicioVigencia;
	
	private LocalDate dtFinalVigencia;
	
	@Max(99)
	@Min(1)
	private Integer nrMotivoReceita;
	
	@Max(2)
	@Min(1)
	private Integer tpTransmissao;
	
	@DecimalMax("999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 6, fraction = 2)
	private BigDecimal vlUfpFinal;	

}