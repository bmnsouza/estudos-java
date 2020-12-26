package bmnsouza.util.result;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeResult {

	private Result result;

	@Data
	public class Result {

		private int cdRetorno;

		private String msgTecnica;

		private String msgUsuario = new String();

		private Object dados = new HashMap<>();

	}

	@Data
	public class Paginacao {

		private int pagina;

		private int elementos;

		private int tamanho;

		private boolean conteudo;

		private boolean anterior;

		private boolean proxima;

		private boolean primeira;

		private boolean ultima;

	}

}