package bmnsouza.database.nota.entity.dto.consumidor;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.UF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumidorCadastrarDTO {

	@CPF
	@NotEmpty
	private String cpf;

	@NotEmpty
	@Size(min = 10, max = 60)
	private String nome;

	private LocalDate dataNascimento;

	@Size(max = 40)
	private String logradouro;

	@Size(max = 6)
	private String numero;

	@Size(max = 21)
	private String complemento;

	@Size(max = 20)
	private String bairro;

	@Size(max = 8)
	private String cep;

	@Size(max = 50)
	private String municipio;

	@UF
	@NotEmpty
	private String uf;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String ddd;

	@NotEmpty
	@Size(min = 8, max = 9)
	private String telefone;

	@Email
	@NotEmpty
	@Size(max = 150)
	private String email;

	@Size(max = 128)
	private String frase;

	@Size(max = 128)
	private String fraseEmail;

	private Integer idEntidade;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String senha;

}