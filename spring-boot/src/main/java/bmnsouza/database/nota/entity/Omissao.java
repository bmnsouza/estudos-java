package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.Mes;
import bmnsouza.database.nota.entity.id.OmissaoId;
import lombok.Data;

@Data
@Entity
@IdClass(OmissaoId.class)
@Table(catalog = "USUARIOCAT", schema = "dbo", name = "tb_ome_omissao_empresa")
public class Omissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Size(min = 9, max = 9)
	@Column(name = "ome_ie")
	private String ie;

	@Id
	@CNPJ
	@Column(name = "ome_cnpj")
	private String cnpj;

	@Id
	@Ano
	@Column(name = "ome_ano")
	private Integer ano;

	@Id
	@Mes
	@Column(name = "ome_mes")
	private Integer mes;

	@Digits(integer = 16, fraction = 2)
	@Column(name = "ome_rba")
	private BigDecimal rba;

	@Column(name = "ome_dtentrada")
	private LocalDateTime dataEntrada;

}