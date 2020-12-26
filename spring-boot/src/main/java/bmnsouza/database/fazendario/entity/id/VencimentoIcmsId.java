package bmnsouza.database.fazendario.entity.id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class VencimentoIcmsId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer nrSequencialVencimentoIcms;
	
	private Integer nrAno;

}