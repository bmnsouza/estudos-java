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
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "Movimentacao")
public class Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codMovimentacao;

	@NotNull
	@Column(name = "fkcontacorrente")
	private Long idContaCorrente;

	@NotNull
	@Column(name = "codtipomov")
	private Short tipoMovimentacao;

	@NotEmpty
	@Size(min = 10, max = 500)
	@Column(name = "strdescmov")
	private String descricaoMovimentacao;

	@Digits(integer = 16, fraction = 2)
	@Column(name = "valsaldoanterior")
	private BigDecimal saldoAnterior;

	@Digits(integer = 16, fraction = 2)
	@Column(name = "valvalormov")
	private BigDecimal valorMovimentacao;

	@Digits(integer = 16, fraction = 2)
	@Column(name = "valsaldoatual")
	private BigDecimal saldoAtual;

	@Column(name = "bil_numbilhete")
	private Integer numBilhete;

	@Column(name = "sor_codsorteio")
	private Integer codSorteio;

	@NotEmpty
	@Column(name = "dtmtimestamp")
	private LocalDateTime dataMovimentacao;

}