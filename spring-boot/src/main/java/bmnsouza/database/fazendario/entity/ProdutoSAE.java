package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bmnsouza.annotation.FlagAtivo;
import bmnsouza.database.fazendario.entity.id.ProdutoSAEId;
import lombok.Data;

@Data
@Entity
@IdClass(ProdutoSAEId.class)
public class ProdutoSAE implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Min(1)
	@Max(999)
	@NotNull
	@Column(name = "PRS_cdProduto")
	private Integer cdProduto;

	@Id
	@Max(2)
	@Min(1)
	@NotNull
	@Column(name = "PRS_tpProduto")
	private Integer tpProduto;
	
	@Size(min = 5, max = 200)
	@NotNull
	@Column(name = "PRS_dsProduto")
	private String dsProduto;
	
	@FlagAtivo
	@NotNull
	@Column(name = "PRS_flAtivo")
	private String flAtivo;
	
	@Max(99)
	@Min(1)
	@Column(name = "GPR_cdGrupo")
	private Integer cdGrupo;	
	
	@Max(99)
	@Min(1)
	@Column(name = "SGA_cdSubGrupo")
	private Integer cdSubGrupo;
	
	@Max(9)
	@Min(1)
	@Column(name = "ATM_cdAtvMacro")
	private Integer cdAtvMacro;
	
	@Max(99)
	@Min(1)
	@Column(name = "CNA_cdCnae")
	private Integer cdCnae;
	
	@Max(99)
	@Min(1)
	@Column(name = "CNF_cdCnaef")
	private Integer cdCnaef;
	
	@Max(999)
	@Min(1)
	@Column(name = "PRS_cdProdutoGnre")
	private Integer cdProdutoGnre;

}