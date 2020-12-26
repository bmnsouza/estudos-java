package bmnsouza.database.nota.entity.dto.sorteio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class SorteioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Positive
	@Column(name = "sor_codsorteio")
	private int codSorteio;
	
}
