package bmnsouza.database.nota.entity.dto.sorteio;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SorteioExpiradosDTO {

	Integer getCodSorteio();

	LocalDate getDataRealizacao();

	BigDecimal getValorPremiacao();

	BigDecimal getValorResgatado();

	BigDecimal getValorDiferenca();

}