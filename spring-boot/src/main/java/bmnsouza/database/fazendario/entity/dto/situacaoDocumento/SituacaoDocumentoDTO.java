package bmnsouza.database.fazendario.entity.dto.situacaoDocumento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SituacaoDocumentoDTO {
	
	@NotNull
	@Size(min = 5, max = 30)
	private String dsSituacao;

}