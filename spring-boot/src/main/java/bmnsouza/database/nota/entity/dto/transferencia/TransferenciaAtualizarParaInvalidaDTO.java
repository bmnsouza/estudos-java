package bmnsouza.database.nota.entity.dto.transferencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class TransferenciaAtualizarParaInvalidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pktransf")
	@Positive
	private Integer id;
	
}
