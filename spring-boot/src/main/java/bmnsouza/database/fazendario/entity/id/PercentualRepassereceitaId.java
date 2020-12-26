package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PercentualRepassereceitaId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cdReceita;
	
	private Integer cdEntidade;
	
	private Integer tpProducao;

}