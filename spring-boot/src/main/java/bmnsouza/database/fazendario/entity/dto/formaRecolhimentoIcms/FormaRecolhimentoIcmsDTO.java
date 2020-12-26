package bmnsouza.database.fazendario.entity.dto.formaRecolhimentoIcms;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaRecolhimentoIcmsDTO {
	
	@Size(max = 60, min = 5)
	@NotNull
	private String dsFormaRecolhimentoIcms;
	
	@NotNull
	private LocalDate dtInicioVigencia;
	
	private LocalDate dtFimVigencia;
	
	@Max(99)
	@Min(1)
	@NotNull
	private Integer nrGrupoRecolhimento;
	
}
