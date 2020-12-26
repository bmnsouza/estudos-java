package bmnsouza.database.nota.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.database.nota.entity.id.BilheteId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BilheteId.class)
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "TB_BIL_BILHETE")
public class Bilhete implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bil_numbilhete")
	private Integer numBilhete;

	@Id
	@Column(name = "sor_codsorteio")
	private Integer codSorteio;

	@Column(name = "sop_codpremio")
	private Integer codPremio;

	@CPF
	@NotEmpty
	@Column(name = "bil_cpfcnpj")
	private String cpfConsumidor;

	@CNPJ
	@Column(name = "bil_cnpjentidade")
	private String cnpjEntidade;

}