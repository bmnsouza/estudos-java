package bmnsouza.database.nota.entity.dto.entidade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.UF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeCadastrarDTO {

	@CNPJ
	@NotEmpty
	private String cnpj;

	@NotEmpty
	@Size(min = 10, max = 150)
	private String razao;

	@Size(max = 60)
	private String logradouro;

	@Size(max = 6)
	private String numero;

	@Size(max = 21)
	private String complemento;

	@NotEmpty
	@Size(min = 5, max = 50)
	private String bairro;

	@NotEmpty
	@Size(min = 8, max = 8)
	private String cep;

	@NotEmpty
	@Size(min = 5, max = 50)
	private String municipio;

	@UF
	@NotEmpty
	@Size(min = 2, max = 2)
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

	@CPF
	@NotEmpty
	private String cpfRepresentanteLegal;
	
	@NotEmpty
	@Size(min = 10, max = 70)
	private String nomeRepresentanteLegal;

	@CPF
	@NotEmpty
	private String cpfResponsavel;

	@NotEmpty
	@Size(min = 6, max = 60)
	private String senha;

}