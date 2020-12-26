package bmnsouza.database.nota.entity.dto.sorteioPremio;

import java.math.BigDecimal;

public interface SorteioBuscarBilhetesPorSorteioDTO {

	Integer getCodPremio();

	BigDecimal getValPremio();

	String getTpPremio();

	Integer getNumBilhete();

	String getCpfCnpjGanhador();

	String getNomeRazaoGanhador();

	String getMunicipio();

	String getUf();

}