package bmnsouza.database.fazendario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class ObjetoReferencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Max(99)
	@Min(1)
	@NotNull
	@Column(name = "OBR_nrObjetoReferencia")
	private Integer nrObjetoReferencia;	
		
	@NotNull
	@Column(name = "OBR_DTInicioVigencia")
	private LocalDate dtInicioVigencia;
	
	@Column(name = "OBR_dtFimVigencia")
	private LocalDate dtFimVigencia;	
	
	@Size(max = 60, min = 3)
	@NotNull
	@Column(name = "OBR_dsObjetoReferencia")
	private String dsObjetoReferencia;
	
}
