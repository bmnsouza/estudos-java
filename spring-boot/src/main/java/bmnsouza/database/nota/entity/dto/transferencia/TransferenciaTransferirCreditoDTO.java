package bmnsouza.database.nota.entity.dto.transferencia;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.Banco;
import bmnsouza.annotation.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaTransferirCreditoDTO {

	@CPF
	@NotEmpty
	private String cpf;

	@Banco
	@NotEmpty
	@Size(min = 3, max = 3)
	private String banco;

	@NotEmpty
	@Size(min = 1, max = 5)
	private String agencia;

	@NotEmpty
	@Size(min = 1, max = 1)
	private String digitoAgencia;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String operacao;

	@NotEmpty
	@Size(min = 4, max = 12)
	private String conta;

	@NotEmpty
	@Size(min = 1, max = 1)
	private String digitoConta;

	@TipoConta
	@NotNull
	private Integer tipoConta;

}