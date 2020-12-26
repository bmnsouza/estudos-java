/**
 * Classe de entidade que mapeia a tabela tb_pcr_percent_calculo_receita do schema SAE.
 * 
 *  @author wtnsantos
 */

package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

import bmnsouza.database.fazendario.entity.id.PercentualCalculoReceitaId;
import lombok.Data;

@Data
@Entity
@IdClass(PercentualCalculoReceitaId.class)
public class PercentualCalculoReceita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Max(999999)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdReceita")
	private Integer cdReceita;
	
	@Id
	@Max(99999)
	@Min(1)
	@NotNull
	@Column(name = "PCR_nrSequencial")
	private Integer nrSequencial;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	@Column(name = "PCR_vlPercentMultaMora")
	private BigDecimal vlPercentualMultaMora;
	
	@Max(99)
	@Min(1)
	@Column(name = "PCR_qtMesLimiteMultaMora")
	private Integer qtMesLimiteMultaMora;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	@Column(name = "PCR_vlPercentJurosMora")
	private BigDecimal vlPercentualJurosMora;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")	
	@Digits(integer = 3, fraction = 2)
	@Column(name = "PCR_vlPercentAgregacao")
	private BigDecimal vlPercentualAgregacao;
	
	@DecimalMax("999.99")
	@DecimalMin("0.00")
	@Digits(integer = 3, fraction = 2)
	@Column(name = "PCR_vlPercentAliquota")
	private BigDecimal vlPercentualAliquota;
	
	@DecimalMax("999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 6, fraction = 2)
	@Column(name = "PCR_vlUfp")
	private BigDecimal vlUfp;
	
	@NotNull
	@Column(name = "PCR_dtInicioVigencia")
	private LocalDate dtInicioVigencia;
	
	@Column(name = "PCR_dtFinalVigencia")
	private LocalDate dtFinalVigencia;
	
	@Max(99)
	@Min(1)
	@Column(name = "MRE_nrMotivoReceita")
	private Integer nrMotivoReceita;
	
	@Max(2)
	@Min(1)
	@Column(name = "PCR_tpTransmissao")
	private Integer tpTransmissao;
	
	@DecimalMax("999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 6, fraction = 2)
	@Column(name = "PCR_vlUfpFinal")
	private BigDecimal vlUfpFinal;	
	
}