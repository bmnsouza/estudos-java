package bmnsouza.database.nota.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class SorteioPremioId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codSorteio;

	private Integer codPremio;

}