package bmnsouza.database.nota.entity.dto.bilhete;

import java.math.BigDecimal;

public interface BilheteBuscarPercentualEntidadeDTO {

	String getCnpj();

	String getRazao();

	int getTotal();

	BigDecimal getPercentual();

}