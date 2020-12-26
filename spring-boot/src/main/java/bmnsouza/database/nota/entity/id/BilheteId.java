package bmnsouza.database.nota.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BilheteId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numBilhete;

	private Integer codSorteio;

}