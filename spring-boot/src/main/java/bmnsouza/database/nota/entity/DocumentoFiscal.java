package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import bmnsouza.annotation.TipoNotaFiscal;
import lombok.Data;

@Data
@Entity
@Table(catalog = "NFA_Consolidado", schema = "dbo", name = "NFA_CREDITOS")
public class DocumentoFiscal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_notafiscal")
	private String numeroNotaFiscal;

	@TipoNotaFiscal
	@Column(name = "tip_notafiscal")
	private String tipoNotaFiscal;

	@Column(name = "num_emitente")
	private String cnpjEmitente;

	@Column(name = "dsc_contribuinte")
	private String razaoEmitente;

	@Column(name = "num_destinatario")
	private String cpfDestinatario;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 16, fraction = 2)
	@Column(name = "val_total")
	private BigDecimal valorTotal;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 16, fraction = 2)
	@Column(name = "val_credito")
	private BigDecimal valorCredito;

	@Column(name = "dat_emissao")
	private LocalDateTime dataEmissao;

	@Column(name = "dat_processamento")
	private LocalDateTime dataProcessamento;

	@Column(name = "ind_situacao")
	private int situacao;

	@Column(name = "fl_devolucao")
	private int devolucao;

}