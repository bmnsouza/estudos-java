package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DetalheDocumentoArrecadacaoId implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private BigInteger nrDAE;

	private Integer nrItem;
	
	
}
