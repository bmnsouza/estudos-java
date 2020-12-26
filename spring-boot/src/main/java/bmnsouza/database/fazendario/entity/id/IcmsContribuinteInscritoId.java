package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class IcmsContribuinteInscritoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tpContribuinte;
	
	private Integer nrRegimeTributacaoIcms;
	
	private Integer nrFormaRecolhimentoICms;
	
}
