package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.Banco;
import bmnsouza.annotation.StatusTransferencia;
import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "Transferencia")
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pktransf")
	private Integer id;

	@NotNull
	@Positive
	private Integer codMovimentacao;

	@CPF
	@NotEmpty
	@Column(name = "strcpfcnpjuser")
	private String cpf;

	@Banco
	@NotEmpty
	@Size(min = 3, max = 3)
	@Column(name = "codbanco")
	private String banco;

	@NotEmpty
	@Size(min = 1, max = 5)
	@Column(name = "stragencia")
	private String agencia;

	@NotEmpty
	@Size(min = 1, max = 1)
	@Column(name = "strdigagencia")
	private String digitoAgencia;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String operacao;

	@NotEmpty
	@Size(min = 4, max = 12)
	@Column(name = "strnumconta")
	private String conta;

	@NotEmpty
	@Size(min = 1, max = 1)
	@Column(name = "strdigconta")
	private String digitoConta;

	@NotNull
	@Digits(integer = 16, fraction = 2)
	@Column(name = "valvalorcredito")
	private BigDecimal valorCredito;

	@NotNull
	@Digits(integer = 16, fraction = 2)
	@Column(name = "valvalorcreditobruto")
	private BigDecimal valorCreditoBruto;

	@NotNull
	@Digits(integer = 16, fraction = 2)
	@Column(name = "valvalordescontoir")
	private BigDecimal valorDescontoIR;

	@StatusTransferencia
	@NotNull
	@Column(name = "tra_status")
	private int status;

	@Column(name = "dtgeracaorelatorio")
	private LocalDateTime dataGeracaoRelatorio;

	@Column(name = "tra_dtcargaigesp")
	private LocalDateTime dataCargaIgesp;

}