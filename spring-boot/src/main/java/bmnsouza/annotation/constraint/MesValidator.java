package bmnsouza.annotation.constraint;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bmnsouza.annotation.Mes;

public class MesValidator implements ConstraintValidator<Mes, Integer> {

	@Override
	public boolean isValid(Integer mes, ConstraintValidatorContext constraintValidatorContext) {
		try {
			if(mes != null) {				
				LocalDate.of(LocalDate.now().getYear(), mes, 1);
			}
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}