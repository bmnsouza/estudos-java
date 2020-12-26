package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class IcmsContribuinteNaoInscritoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nrObjetoReferencia;
	
	private Integer nrFormaRecolhimentoIcms;
	
}