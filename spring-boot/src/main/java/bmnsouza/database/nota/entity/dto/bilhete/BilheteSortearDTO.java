package bmnsouza.database.nota.entity.dto.bilhete;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BilheteSortearDTO {

	@NotNull
	@Positive
	private Integer codSorteio;

	@NotNull
	@Positive
	private Integer concursoLoteria;

	@NotEmpty
	@Size(min = 5, max = 5)
	private String premioLoteria1;

	@NotEmpty
	@Size(min = 5, max = 5)
	private String premioLoteria2;

	@NotEmpty
	@Size(min = 5, max = 5)
	private String premioLoteria3;

	@NotEmpty
	@Size(min = 5, max = 5)
	private String premioLoteria4;

	@NotEmpty
	@Size(min = 5, max = 5)
	private String premioLoteria5;

}