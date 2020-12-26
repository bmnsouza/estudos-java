package bmnsouza.database.fazendario.entity.dto.grupoReceita;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoReceitaDTO {

	@Size(max = 30, min = 5)
	@NotNull
	private String dsGrupoReceita;
	
}
