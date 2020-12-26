package bmnsouza.database.fazendario.entity.dto.produtoSAE;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProdutoSAEDTO {

	@Max(2)
	@Min(1)
	@NotNull
	private Integer tpProduto;
	
	@Size(min = 5, max = 200)
	@NotNull
	private String dsProduto;
	
	@Max(99)
	@Min(1)
	private Integer cdGrupo;	
	
	@Max(99)
	@Min(1)
	private Integer cdSubGrupo;
	
	@Max(9)
	@Min(1)
	private Integer cdAtvMacro;
	
	@Max(99)
	@Min(1)
	private Integer cdCnae;
	
	@Max(99)
	@Min(1)
	private Integer cdCnaef;
	
	@Max(999)
	@Min(1)
	private Integer cdProdutoGnre;
		
}