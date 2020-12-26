package bmnsouza.database.fazendario.entity.dto.documentoArrecadacao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Interface utilizada para mapear os campos da consulta buscarDAEPorDocumento.
 * 
 * @author Wendhio Thalison Neres dos Santos - 16/12/2020
 *
 */
public interface DocumentoArrecadacaoBuscarDAEPorDocumentoDTO {
	
	BigInteger getNrDAE();
	
    String getDsSituacao();
	
    String getDsTipoDocumento();
	
    LocalDateTime getDtEmissao();
	
    LocalDateTime getDtValidade();
    
    LocalDateTime getDtArrecadacao();
    
    LocalDateTime getDtEnvioonline();
    
    LocalDateTime getDtEnvioConsolidado();
    
    LocalDateTime getDtRepasse();
    
    String getCdAutenticacao();
    
    String getDsBanco();
    
    String getDsAgencia();
    
    String getCdPessoa();
    
    String getNmPessoa();
    
    String getCdPessoaEmissor();
    
    String getCdPessoaDestinatario();
    
    BigDecimal getVlDescontoTotal();
    
    BigDecimal getVlTotal();
    
	Integer getNrItem();
	
	String getMesAnoReferencia();
	
	LocalDateTime getDtVencimentoReceita();
	
	String getDsReceita();
	
	String getDsFormaRecolhimentoIcms();
	
	BigInteger getCdDocumentoOrigem();
	
	BigDecimal getVlPrincipalReceita();
	
	BigDecimal getVlDesconto();
	
	BigDecimal getVlAtualizMonetaria();
	
	BigDecimal getVlMultaMora();
	
	BigDecimal getVlJuros();
	
	BigDecimal getVlTotalReceita();
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class DocumentoArrecadacaoDTO {
		
		private BigInteger nrDAE;
		
	    private String dsSituacao;
		
	    private String dsTipoDocumento;
		
	    private LocalDateTime dtEmissao;
		
	    private LocalDate dtValidade;
	    
	    private LocalDate dtArrecadacao;
	    
	    private LocalDateTime dtEnvioonline;
	    
	    private LocalDateTime dtEnvioConsolidado;
	    
	    private LocalDate dtRepasse;
	    
	    private String cdAutenticacao;
	    
	    private String dsBanco;
	    
	    private String dsAgencia;
	    
	    private String cdPessoa;
	    
	    private String nmPessoa;
	    
	    private String cdPessoaEmissor;
	    
	    private String cdPessoaDestinatario;
	    
	    private BigDecimal vlDescontoTotal;
	    
	    private BigDecimal vlTotal;

	    private List<DetalheDocumentoArrecadacaoDTO> detalheDocumentoArrecadacao;
	    
	    /**
	     * Subclasse que representa um DED - Documento Detalhe para a Classe {@link DocumentoArrecadacaoDetalheBuscarDAEPorDocumentoDTO}.
	     * 
	     * @author Wendhio Thalison Neres dos Santos - 16/12/2020
	     *
	     */
	    @Data
	    @NoArgsConstructor
	    @AllArgsConstructor
	    public class DetalheDocumentoArrecadacaoDTO {
	    	
	    	private Integer nrItem;
	    	
	    	private String mesAnoReferencia;
	    	
	    	private LocalDate dtVencimentoReceita;
	    	
	    	private String dsReceita;
	    	
	    	private String dsFormaRecolhimentoIcms;
	    	
	    	private BigInteger cdDocumentoOrigem;
	    	
	    	private BigDecimal vlPrincipalReceita;
	    	
	    	private BigDecimal vlDesconto;
	    	
	    	private BigDecimal vlAtualizMonetaria;
	    	
	    	private BigDecimal vlMultaMora;
	    	
	    	private BigDecimal vlJuros;
	    	
	    	private BigDecimal vlTotalReceita;
	    
	    }
	    
	}

}