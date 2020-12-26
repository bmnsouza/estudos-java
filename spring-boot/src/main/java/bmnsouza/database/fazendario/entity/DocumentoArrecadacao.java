package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.FlagAtivo;
import lombok.Data;

@Data
@Entity
public class DocumentoArrecadacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@DecimalMax("9999999999999999999")
	@DecimalMin("10000000000000")
	@NotNull
	@Column(name = "DBA_nrDAE")
	private BigInteger nrDAE;

	@Column(name = "DAE_dtArrecadacao")
	private LocalDate dtArrecadacao;

	@Column(name = "DAE_dtEmissao")
	private LocalDateTime dtEmissao;

	@Column(name = "DAE_dtValidade")
	private LocalDate dtValidade;

	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@NotNull
	@Column(name = "DAE_vlTotal")
	private BigDecimal vlTotal;

	@Column(name = "DAE_dtSolicitacaoPagamento")
	private LocalDate dtSolicitacaoPagamento;

	@Size(max = 1)
	@NotNull
	@Column(name = "DAE_flRestricaoFormaPagamento")
	private String flRestricaoFormaPagamento;

	@Size(min = 5, max = 500)
	@Column(name = "DAE_dsMensagem")
	private String dsMensagem;

	@Column(name = "DAE_dtEnvioOnLine")
	private LocalDate dtEnvioOnLine;

	@Max(9999999999999L)
	@Min(0)
	@Column(name = "DAE_nrDocumentoRecebimento")
	private Long nrDocumentoRecebimento;

	@Size(max = 150)
	@NotNull
	@Column(name = "DAE_nmPessoa")
	private String nmPessoa;

	@Size(max = 14)
	@Column(name = "DAE_cdPessoaEmissor")
	private String cdPessoaEmissor;

	@Column(name = "DAE_dtOperacao")
	private LocalDateTime dtOperacao;

	@Column(name = "DAE_dtGeracao")
	private LocalDateTime dtGeracao;

	@Size(max = 48)
	@Column(name = "DAE_cdCodigoBarras")
	private String cdCodigoBarras;

	@Max(9)
	@Min(0)
	@Column(name = "DAE_cdFormaArrecadacao")
	private Integer cdFormaArrecadacao;

	@Size(max = 23)
	@Column(name = "DAE_cdAutenticacao")
	private String cdAutenticacao;

	@Max(99)
	@Min(1)
	@Column(name = "DAE_cdLocalPagamento")
	private Integer cdLocalPagamento;

	@Max(2)
	@Min(1)
	@Column(name = "DAE_cdControleEmissaoMensal")
	private Integer cdControleEmissaoMensal;

	@Column(name = "DAE_dtEnvioConsolidado")
	private LocalDateTime dtEnvioConsolidado;

	@Max(999)
	@Min(1)
	@Column(name = "BCO_cdBancoArrecadador")
	private Integer cdBancoArrecadador;

	@Max(999999)
	@Min(1)
	@Column(name = "AGC_cdAgenciaArrecadadora")
	private Integer cdAgenciaArrecadadora;

	@Size(max = 14)
	@NotNull
	@Column(name = "PES_cdPessoa")
	private String cdPessoa;

	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "TDO_cdTipoDocumento")
	private Integer cdTipoDocumento;

	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "SID_nrSituacao")
	private Integer nrSituacao;

	@Column(name = "DAE_dtRepasse")
	private LocalDate dtRepasse;

	@Column(name = "DAE_dtDepositoRepasse")
	private LocalDate dtDepositoRepasse;

	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@Column(name = "DAE_vlDescontoTotal")
	private BigDecimal vlDescontoTotal;

	@Max(99999999999L)
	@Min(1)
	@Column(name = "VEI_cdRenavam")
	private Long cdRenavam;

	@Max(99)
	@Min(1)
	@Column(name = "TSI_cdSituacaoContrib")
	private Integer cdSituacaoContrib;

	@Max(99)
	@Min(1)
	@Column(name = "TPC_tpContribuinte")
	private Integer tpContribuinte;

	@Max(2)
	@Min(-1)
	@Column(name = "DAE_flGeracaoValorAdicionado")
	private Integer flGeracaoValorAdicionado;

	@Size(max = 14)
	@Column(name = "PES_cdPessoaDestinatario")
	private String cdPessoaDestinatario;

	@Max(999999)
	@Min(1)
	@Column(name = "LOC_cdLocalidadeEmitente")
	private Integer cdLocalidadeEmitente;

	@Size(max = 20, min = 3)
	@Column(name = "DAE_cdSistemaEmissor")
	private String cdSistemaEmissor;

	@FlagAtivo
	@Column(name = "DAE_flCedido")
	private String flCedido;

}
