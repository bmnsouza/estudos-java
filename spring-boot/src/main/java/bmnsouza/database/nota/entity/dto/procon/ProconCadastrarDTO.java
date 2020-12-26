package bmnsouza.database.nota.entity.dto.procon;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProconCadastrarDTO {

	@CPF
	@NotEmpty
	private String cpf;

	@NotEmpty
	@Size(min = 10, max = 60)
	private String nome;

	@Email
	@NotEmpty
	@Size(max = 60)
	private String email;

	@CPF
	@NotEmpty
	private String cpfResponsavel;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String senha;

}