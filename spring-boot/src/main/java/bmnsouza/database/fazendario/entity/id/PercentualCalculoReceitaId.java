package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PercentualCalculoReceitaId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cdReceita;
	
	private Integer nrSequencial;

}
