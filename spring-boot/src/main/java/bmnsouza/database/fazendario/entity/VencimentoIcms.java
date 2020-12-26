package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.FlagSN;
import bmnsouza.annotation.Mes;
import bmnsouza.database.fazendario.entity.id.VencimentoIcmsId;
import lombok.Data;

@Data
@Entity
@IdClass(VencimentoIcmsId.class)
public class VencimentoIcms implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Max(9999)
	@Min(1)
	@NotNull
	@Column(name = "VEI_nrSequencialVencimentoIcms")
	private Integer nrSequencialVencimentoIcms;
	
	@Id
	@Ano
	@NotNull
	@Column(name = "VEI_nrAno")
	private Integer nrAno;
	
	@Max(99)
	@Min(1)
	@Column(name = "TPC_tpContribuinte")
	private Integer tpContribuinte;
	
	@Max(99)
	@Min(1)
	@Column(name = "FRC_nrFormaRecolhimentoIcms")
	private Integer nrFormaRecolhimentoIcms;
	
	@Max(99)
	@Min(1)
	@Column(name = "OBR_nrObjetoReferencia")
	private Integer nrObjetoReferencia;

	@Max(99)
	@Min(1)
	@Column(name = "CAE_cdCategoriaAtvEcon")
	private Integer cdCategoriaAtvEcon;
	
	@Max(99)
	@Min(1)
	@Column(name = "SGA_cdSubgrupo")
	private Integer cdSubgrupo;
	
	@Max(9)
	@Min(0)
	@Column(name = "ATM_cdAtvMacro")
	private Integer cdAtvMacro;
	
	@Max(99)
	@Min(0)
	@Column(name = "CNA_cdCnae")
	private Integer cdCnae;
	
	@Max(99)
	@Min(0)
	@Column(name = "CNF_cdCnaef")
	private Integer cdCnaef;
	
	@Max(9)
	@Min(1)
	@NotNull
	@Column(name = "VEI_nrParcelaApuracao")
	private Integer nrParcelaApuracao;
	
	@FlagSN
	@NotNull
	@Column(name = "VEI_flMesVencimentoSubsequente")
	private String flMesVencimentoSubsequente;
	
	@Max(31)
	@Min(1)
	@NotNull
	@Column(name = "VEI_nrDiaVencimento")
	private Integer nrDiaVencimento;
	
	@Max(99999)
	@Min(1)
	@Column(name = "VEI_nrPortaria")
	private Integer nrPortaria;
	
	@Ano
	@Column(name = "VEI_nrAnoPortaria")
	private Integer nrAnoPortaria;
	
	@Max(9)
	@Min(1)
	@NotNull
	@Column(name = "VEI_nrPrioridadePortaria")
	private Integer nrPrioridadePortaria;
	
	@Mes
	@Column(name = "VEI_nrMesRefinicial")
	private Integer nrMesRefInicial;

	@Ano
	@Column(name = "VEI_nrAnoRefInicial")
	private Integer nrAnoRefInicial;
	
	@Mes
	@Column(name = "VEI_nrMesRefFinal")
	private Integer nrMesRefFinal;
	
	@Ano
	@Column(name = "VEI_nrAnoRefFinal")
	private Integer nrAnoRefFinal;
	
	@Column(name = "VEI_dtOperacao")
	private LocalDateTime dtOperacao;
	
	@CPF
	@Column(name = "PES_cdPessoa")
	private String cdPessoa;
	
	@Max(999)
	@Min(1)
	@Column(name = "PRS_cdProduto")
	private Integer cdProduto;
	
	@Max(2)
	@Min(1)
	@Column(name = "PRS_tpProduto")
	private Integer tpProduto;
	
	@Max(99)
	@Min(1)
	@Column(name = "VEI_nrQtdMesSubsequente")
	private Integer nrQtdMesSubsequente;

}