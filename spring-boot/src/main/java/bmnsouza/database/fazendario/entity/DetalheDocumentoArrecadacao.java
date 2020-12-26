package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.Mes;
import bmnsouza.database.fazendario.entity.id.DetalheDocumentoArrecadacaoId;
import lombok.Data;

@Data
@Entity
@IdClass(DetalheDocumentoArrecadacaoId.class)
public class DetalheDocumentoArrecadacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@DecimalMax("9999999999999999999")
	@DecimalMin("10000000000000")
	@NotNull
	@Column(name = "DBA_nrDAE")
	private BigInteger nrDAE;
	
	@Id
	@Max(999)
	@Min(0)
	@NotNull
	@Column(name = "DED_nrItem")
	private Integer nrItem;
	
	@Ano
	@NotNull
	@Column(name = "DED_nrAnoReferencia")
	private Integer nrAnoReferencia;
	
	@Mes
	@NotNull
	@Column(name = "DED_nrMesReferencia")
	private Integer nrMesReferencia;
	
	@NotNull
	@Column(name = "DED_dtVencimentoReceita")
	private LocalDate dtVencimentoReceita;
	
	@Max(999)
	@Min(0)
	@Column(name = "DED_nrParcelaApuracao")
	private Integer nrParcelaApuracao;
	
	@Max(9999)
	@Min(0)
	@Column(name = "DED_qtTaxa")
	private Integer qtTaxa;
	
	@Max(2)
	@Min(1)
	@Column(name = "DED_tpProducao")
	private Integer tpProducao;
	
	@Max(2)
	@Min(1)
	@Column(name = "DED_cdMotivoITD")
	private Integer cdMotivoITD;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.01")
	@Digits(integer = 13, fraction = 2)
	@NotNull
	@Column(name = "DED_vlPrincipalReceita")
	private BigDecimal vlPrincipalReceita;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@Column(name = "DED_vlDesconto")
	private BigDecimal vlDesconto;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@Column(name = "DED_vlAtualizMonetaria")
	private BigDecimal vlAtualizMonetaria;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@Column(name = "DED_vlMultaMora")
	private BigDecimal vlMultaMora;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@Column(name = "DED_vlJuros")
	private BigDecimal vlJuros;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.01")
	@Digits(integer = 13, fraction = 2)
	@NotNull
	@Column(name = "DED_vlTotalReceita")
	private BigDecimal vlTotalReceita;
	
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "OBR_nrObjetoReferencia")
	private Integer nrObjetoReferencia;
	
	@Max(99)
	@Min(0)
	@Column(name = "RET_nrRegimeTributacaoIcms")
	private Integer nrRegimeTributacaoIcms;
	
	@Max(999999)
	@Min(1)
	@NotNull
	@Column(name = "REC_cdReceita")
	private Integer cdReceita;
	
	@Max(999)
	@Min(1)
	@Column(name = "TID_nrTipoDesconto")
	private Integer nrTipoDesconto;
	
	@Max(999)
	@Min(1)
	@Column(name = "MRE_nrMotivoReceita")
	private Integer nrMotivoReceita;
	
	@Max(99)
	@Min(1)
	@Column(name = "TDG_cdTipoDocumentoOrigem")
	private Integer cdTipoDocumentoOrigem;
	
	@Max(999999)
	@Min(1)
	@Column(name = "LOC_cdLocalidade")
	private Integer cdLocalidade;
	
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
	@Column(name = "FRC_nrFormaRecolhimentoIcms")
	private Integer nrFormaRecolhimentoIcms;
	
	@Max(99)
	@Min(0)
	@Column(name = "CAE_cdCategoriaAtvEcon")
	private Integer cdCategoriaAtvEcon;
	
	@Max(99)
	@Min(1)
	@Column(name = "SGA_cdSubGrupo")
	private Integer cdSubGrupo;
	
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
	
	@Max(9999999)
	@Min(0)
	@Column(name = "AEE_cdAtvdEconEstadual")
	private Integer cdAtvdEconEstadual;
	
	@Max(99)
	@Min(1)
	@Column(name = "TOP_nrTipoOperacao")
	private Integer nrTipoOperacao;
	
	@Max(99)
	@Min(1)
	@Column(name = "DED_nrItemProcesso")
	private Integer nrItemProcesso;
	
	@Size(max = 1)
	@Column(name = "DED_tpPagamentoProcesso")
	private String tpPagamentoProcesso;
	
	@Column(name = "DED_dtDesembaracoAduaneiro")
	private LocalDate dtDesembaracoAduaneiro;
	
	@Max(99999)
	@Min(1)
	@Column(name = "ORG_cdOrgao")
	private Integer cdOrgao;
	
	@Size(max = 44, min = 44)
	@Column(name = "DED_nrChaveAcesso")
	private String nrChaveAcesso;
	
	@Size(max = 14, min = 1)
	@Column(name = "PES_cdPessoaDestinatario")
	private String cdPessoaDestinatario;
	
	@DecimalMin("-1")
	@DecimalMax("99999999999999999999")
	@Column(name = "DED_cdDocumentoOrigem")
	private BigInteger cdDocumentoOrigem;
	
}

