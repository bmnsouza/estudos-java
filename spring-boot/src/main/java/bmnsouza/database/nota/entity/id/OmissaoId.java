package bmnsouza.database.nota.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OmissaoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ie;

	private String cnpj;

	private Integer ano;

	private Integer mes;

}