package bmnsouza.database.fazendario.entity.dto.objetoReferencia;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ObjetoReferenciaDTO {
	
	@NotNull
	private LocalDate dtInicioVigencia;
	
	private LocalDate dtFimVigencia;	
	
	@Size(max = 60, min = 3)
	@NotNull
	private String dsObjetoReferencia;
	
}
