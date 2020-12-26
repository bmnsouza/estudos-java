package bmnsouza.database.fazendario.entity.dto.documentoArrecadacao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.Flag;
import bmnsouza.annotation.FlagAtivo;
import bmnsouza.annotation.Mes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoArrecadacaoCadastrarDTO {

	private LocalDate dtArrecadacao;

	@Max(999)
	@Min(1)
	private Integer cdBancoArrecadador;

	@Max(999999)
	@Min(1)
	private Integer cdAgenciaArrecadadora;

	private LocalDate dtValidade;

	@Size(max = 14)
	@NotNull
	private String cdPessoa;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.01")
	@Digits(integer = 13, fraction = 2)
	@NotNull
	private BigDecimal vlTotal;

	private LocalDate dtSolicitacaoPagamento;

	@Max(9)
	@Min(0)
	private Integer cdFormaArrecadacao;

	@Size(max = 1)
	@NotNull
	private String flRestricaoFormaPagamento;

	@Size(min = 5, max = 500)
	private String dsMensagem;

	private LocalDateTime dtEnvioOnLine;

	@Max(9999999999999L)
	@Min(0)
	private Long nrDocumentoRecebimento;

	@Size(min = 5, max = 150)
	@NotNull
	private String nmPessoa;

	private LocalDate dtRepasse;
	
	@Size(min = 9, max = 14)
	private String cdPessoaEmissor;

	@Size(min = 44, max = 48)
	private String cdCodigoBarras;

	@Size(min = 1, max = 23)
	private String cdAutenticacao;

	@Max(99)
	@Min(1)
	private Integer cdLocalPagamento;

	private LocalDateTime dtEnvioConsolidado;

	@Max(99)
	@Min(1)
	@NotNull
	private Integer cdTipoDocumento;

	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrSituacao;
	
	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vlDescontoTotal;

	@Max(99999999999L)
	@Min(1)
	private Long cdRenavam;
	
	private LocalDate dtDepositoRepasse;

	@Max(99)
	@Min(1)
	private Integer cdSituacaoContrib;

	@Max(99)
	@Min(1)
	private Integer tpContribuinte;

	@Size(min = 9, max = 14)
	private String cdPessoaDestinatario;

	@Max(999999)
	@Min(1)
	private Integer cdLocalidadeEmitente;

	@Size(min = 3, max = 20)
	private String cdSistemaEmissor;

	@FlagAtivo
	private String flCedido;
	
	@Size(min = 1, max = 1000)
	@NotNull
	private List<DetalheDocumentoArrecadacaoCadastrarDTO> detalheDocumentoArrecadacao;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DetalheDocumentoArrecadacaoCadastrarDTO {

		@Max(999)
		@Min(1)
		@NotNull
		private Integer nrItem;
		
		@Ano
		@NotNull
		private Integer nrAnoReferencia;
		
		@Mes
		@NotNull
		private Integer nrMesReferencia;
		
		@NotNull
		private LocalDate dtVencimentoReceita;
		
		@DecimalMax("9999999999999.99")
		@DecimalMin("0.01")
		@Digits(integer = 13, fraction = 2)
		@NotNull
		private BigDecimal vlPrincipalReceita;
		
		@DecimalMax("9999999999999.99")
		@DecimalMin("0.01")
		@Digits(integer = 13, fraction = 2)
		@NotNull
		private BigDecimal vlTotalReceita;

		@DecimalMax("9999999999999.99")
		@DecimalMin("0.00")
		@Digits(integer = 13, fraction = 2)
		private BigDecimal vlDesconto;
		
		@DecimalMax("99999999999999999999")
		@DecimalMin("1")
		private BigInteger cdDocumentoOrigem;

		@Max(999999)
		@Min(1)
		@NotNull
		private Integer cdReceita;

		@Max(999)
		@Min(1)
		private Integer nrTipoDesconto;
		
		@Max(99)
		@Min(1)
		@NotNull
		private Integer nrObjetoReferencia;

		@Max(999)
		@Min(0)
		private Integer nrParcelaApuracao;
		
		@Max(99)
		@Min(1)
		private Integer cdTipoDocumentoOrigem;

		@Max(999)
		@Min(1)
		private Integer nrMotivoReceita;
		
		@Max(999)
		@Min(1)
		private Integer cdProduto;

		@Max(9999)
		@Min(0)
		private Integer qtTaxa;
		
		@Max(9)
		@Min(1)
		private Integer tpProduto;

		@Max(9)
		@Min(1)
		private Integer cdMotivoItd;

		@Max(99)
		@Min(1)
		private Integer tpProducao;
		
		@Max(99)
		@Min(1)
		private Integer nrItemProcesso;
		
		@Max(999999)
		@Min(1)
		private Integer cdLocalidade;
		
		@Max(99)
		@Min(1)
		private Integer nrTipoOperacao;

		@DecimalMax("9999999999999.99")
		@DecimalMin("0.00")
		@Digits(integer = 13, fraction = 2)
		private BigDecimal vlAtualizMonetaria;
		
		@DecimalMax("9999999999999.99")
		@DecimalMin("0.00")
		@Digits(integer = 13, fraction = 2)
		private BigDecimal vlMultaMora;
		
		@DecimalMax("9999999999999.99")
		@DecimalMin("0.00")
		@Digits(integer = 13, fraction = 2)
		private BigDecimal vlJuros;
		
		@Max(99)
		@Min(0)
		private Integer nrRegimeTributacaoIcms;
		
		@Max(99)
		@Min(1)
		private Integer nrFormaRecolhimentoIcms;
		
		@Max(99)
		@Min(0)
		private Integer cdCategoriaAtvEcon;
		
		@Max(99)
		@Min(1)
		private Integer cdSubgrupo;
		
		@Max(9)
		@Min(0)
		private Integer cdAtvMacro;
		
		@Max(99)
		@Min(0)
		private Integer cdCnae;
		
		@Max(99)
		@Min(0)
		private Integer cdCnaef;
		
		@Max(9999999)
		@Min(0)
		private Integer cdAtvdEconEstadual;
		
		@Flag(value = {"C", "G", "H", "O", "P", "R", "T"})
		private String tpPagamentoProcesso;
		
		private LocalDate dtDesembaracoAduaneiro;
		
		@Max(99999)
		@Min(1)
		private Integer cdOrgao;
		
		@Size(min = 44, max = 44)
		private String nrChaveAcesso;
		
		@Size(min = 9, max = 14)
		private String cdPessoaDestinatario;	
		
	}
	
}
