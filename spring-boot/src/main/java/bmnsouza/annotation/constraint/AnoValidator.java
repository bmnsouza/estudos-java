package bmnsouza.annotation.constraint;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bmnsouza.annotation.Ano;

public class AnoValidator implements ConstraintValidator<Ano, Integer> {

	@Override
	public boolean isValid(Integer ano, ConstraintValidatorContext constraintValidatorContext) {
		try {
			if (ano != null) {
				// Verifica se o ano é menor que 1 ou maior que 9999
				if (ano < 1 || ano > 9999) {
					return false;
				}
				LocalDate.of(ano, LocalDate.now().getMonthValue(), 1);
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}