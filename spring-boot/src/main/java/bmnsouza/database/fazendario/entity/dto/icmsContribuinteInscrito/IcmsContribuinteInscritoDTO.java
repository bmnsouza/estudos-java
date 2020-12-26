package bmnsouza.database.fazendario.entity.dto.icmsContribuinteInscrito;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class IcmsContribuinteInscritoDTO {

	@Max(99)
	@Min(1)
	@NotNull
	private Integer tpContribuinte;
	
	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrRegimeTributacaoIcms;
	
	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrFormaRecolhimentoICms;
	
}