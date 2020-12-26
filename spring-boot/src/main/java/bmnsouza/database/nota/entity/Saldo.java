package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "Saldo")
public class Saldo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pksaldo")
	private Integer id;

	@NotNull
	@Digits(integer = 16, fraction = 2)
	@Column(name = "valvalorsaldo")
	private BigDecimal saldo;

	@Column(name = "dtmtimestamp")
	private LocalDateTime dataCriacao;

}