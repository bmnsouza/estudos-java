package bmnsouza.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailModel {
	@Email
	@NotEmpty
	private String emailRemetente;
	
	@Email
	@NotEmpty
	private String emailDestinatario;
	
	@NotEmpty
	@Size(min = 5)
	private String assunto;
	
	@NotEmpty
	@Size(min = 5)
	private String mensagem;	

}