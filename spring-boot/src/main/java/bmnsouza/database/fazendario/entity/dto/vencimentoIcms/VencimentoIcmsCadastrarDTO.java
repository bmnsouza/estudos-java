package bmnsouza.database.fazendario.entity.dto.vencimentoIcms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import bmnsouza.annotation.Ano;
import bmnsouza.annotation.FlagSN;
import bmnsouza.annotation.Mes;
import lombok.Data;

@Data
public class VencimentoIcmsCadastrarDTO {

	@Ano
	@NotNull
	private Integer nrAno;
	
	@Max(99)
	@Min(1)
	private Integer tpContribuinte;
	
	@Max(99)
	@Min(1)
	private Integer nrFormaRecolhimentoIcms;
	
	@Max(99)
	@Min(1)
	private Integer nrObjetoReferencia;

	@Max(99)
	@Min(1)
	private Integer cdCategoriaAtvEcon;
	
	@Max(99)
	@Min(1)
	private Integer cdSubgrupo;
	
	@Max(9)
	@Min(0)
	private Integer cdAtvMacro;
	
	@Max(99)
	@Min(0)
	private Integer cdCnae;
	
	@Max(99)
	@Min(0)
	private Integer cdCnaef;

	@Max(9)
	@Min(1)
	@NotNull
	private Integer nrParcelaApuracao;
	
	@FlagSN
	@NotNull
	private String flMesVencimentoSubsequente;
	
	@Max(31)
	@Min(1)
	@NotNull
	private Integer nrDiaVencimento;
	
	@Max(99999)
	@Min(1)
	private Integer nrPortaria;
	
	@Ano
	private Integer nrAnoPortaria;
	
	@Max(9)
	@Min(1)
	@NotNull
	private Integer nrPrioridadePortaria;
	
	@Mes
	private Integer nrMesRefInicial;

	@Ano
	private Integer nrAnoRefInicial;
	
	@Mes
	private Integer nrMesRefFinal;
	
	@Ano
	private Integer nrAnoRefFinal;
	
	@CPF
	private String cdPessoa;
	
	@Max(999)
	@Min(1)
	private Integer cdProduto;
	
	@Max(2)
	@Min(1)
	private Integer tpProduto;
	
	@Max(99)
	@Min(1)
	private Integer nrQtdMesSubsequente;

}