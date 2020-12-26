package bmnsouza.model;

import lombok.Getter;

@Getter
public class DebitoContribuinteModel {

	private Result result;

	@Getter
	public class Result {

		private int cdRetorno;
	
		private String msgTecnica;
	
		private String msgUsuario;
	
		private boolean dados;

	}

}