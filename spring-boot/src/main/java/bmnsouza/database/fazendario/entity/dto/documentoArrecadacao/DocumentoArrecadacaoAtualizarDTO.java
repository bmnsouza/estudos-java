package bmnsouza.database.fazendario.entity.dto.documentoArrecadacao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

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
public class DocumentoArrecadacaoAtualizarDTO {

	@DecimalMax("9999999999999999999")
	@DecimalMin("10000000000000")
	@NotNull
	private BigInteger nrDAE;
	
	private LocalDate dtArrecadacao;

	private LocalDate dtValidade;

	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	@NotNull
	private BigDecimal vlTotal;

	private LocalDate dtSolicitacaoPagamento;

	@Size(max = 1)
	@NotNull
	private String flRestricaoFormaPagamento;

	@Size(min = 5, max = 500)
	private String dsMensagem;

	private LocalDate dtEnvioOnLine;

	@Max(9999999999999L)
	@Min(0)
	private Long nrDocumentoRecebimento;

	@Size(max = 150)
	@NotNull
	private String nmPessoa;

	@Size(max = 14)
	private String cdPessoaEmissor;

	@Size(max = 48)
	private String cdCodigoBarras;

	@Max(9)
	@Min(0)
	private Integer cdFormaArrecadacao;

	@Size(max = 23)
	private String cdAutenticacao;

	@Max(99)
	@Min(1)
	private Integer cdLocalPagamento;

	@Max(2)
	@Min(1)
	private Integer cdControleEmissaoMensal;

	private LocalDate dtEnvioConsolidado;

	@Max(999)
	@Min(1)
	private Integer cdBancoArrecadador;

	@Max(999999)
	@Min(1)
	private Integer cdAgenciaArrecadadora;

	@Size(max = 14)
	@NotNull
	private String cdPessoa;

	@Max(99)
	@Min(1)
	@NotNull
	private Integer cdTipoDocumento;

	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrSituacao;

	private LocalDate dtRepasse;

	private LocalDate dtDepositoRepasse;

	@DecimalMax("9999999999999.99")
	@DecimalMin("0.00")
	@Digits(integer = 13, fraction = 2)
	private BigDecimal vlDescontoTotal;

	@Max(99999999999L)
	@Min(1)
	private Long cdRenavam;

	@Max(99)
	@Min(1)
	private Integer cdSituacaoContrib;

	@Max(99)
	@Min(1)
	private Integer tpContribuinte;

	@Max(2)
	@Min(-1)
	private Integer flGeracaoValorAdicionado;

	@Size(max = 14)
	private String cdPessoaDestinatario;

	@Max(999999)
	@Min(1)
	private Integer cdLocalidadeEmitente;

	@Size(max = 20, min = 3)
	private String cdSistemaEmissor;

	@FlagAtivo
	private String flCedido;

}
