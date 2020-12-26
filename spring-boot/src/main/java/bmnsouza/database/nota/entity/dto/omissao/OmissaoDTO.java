package bmnsouza.database.nota.entity.dto.omissao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.Mes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmissaoDTO {

	@NotEmpty
	@Size(min = 9, max = 9)
	private String ie;

	@CNPJ
	@NotEmpty
	private String cnpj;

	@Ano
	@NotNull
	private Integer ano;

	@Mes
	@NotNull
	private Integer mes;

	@CPF
	@NotEmpty
	private String cpfResponsavel;

	@NotEmpty
	@Size(min = 1, max = 16)
	private String protocolo;

}