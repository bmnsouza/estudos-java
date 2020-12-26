package bmnsouza.database.fazendario.entity.dto.motivoAlteracaoDocumento;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.FlagAtivo;
import lombok.Data;

@Data
public class MotivoAlteracaoDocumentoDTO {

	@Size(min = 5, max = 60)
	@NotNull
	private String dsMotivo;	
		
	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrSituacao;
	
	@FlagAtivo
	@NotNull
	private String flManual;
	
}
