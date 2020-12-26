package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "tb_blr_bloqueio_resgate")
public class BloqueioResgate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "blr_dtiniciobloqueio")
	private LocalDate dataBloqueio;

	@NotEmpty
	@Size(min = 10, max = 300)
	@Column(name = "blr_msgbloqueio")
	private String mensagemBloqueio;

}