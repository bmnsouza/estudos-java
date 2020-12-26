package bmnsouza.database.fazendario.entity.dto.grupoRecolhimento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GrupoRecolhimentoDTO {

	@Size(max = 30, min = 5)
	@NotNull
	private String dsGrupoRecolhimento;
	
}
