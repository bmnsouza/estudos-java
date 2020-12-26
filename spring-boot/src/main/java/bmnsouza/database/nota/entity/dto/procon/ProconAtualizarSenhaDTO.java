package bmnsouza.database.nota.entity.dto.procon;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProconAtualizarSenhaDTO {

	@NotNull
	@Positive
	private Integer id;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String senhaAntiga;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String senhaNova;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String confirmaSenhaNova;

	@NotEmpty
	@Size(min = 10, max = 128)
	private String fraseEmail;

	@CPF
	@NotEmpty
	private String cpfResponsavel;
	
}
