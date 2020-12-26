package bmnsouza.database.fazendario.entity.dto.tipoDocumento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TipoDocumentoDTO {

	@Max(99)
	@Min(1)
	@NotNull
	private Integer cdTipoDocumento;
	
	@Size(min = 3, max = 30)
	@NotNull
	private String dsTipoDocumento;
	
}