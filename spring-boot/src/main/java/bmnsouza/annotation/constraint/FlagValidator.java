package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.Flag;

public class FlagValidator implements ConstraintValidator<Flag, String> {
	
	String[] value;
	
	@Override
	public void initialize(Flag flagAbstrata) {
		ConstraintValidator.super.initialize(flagAbstrata);
		String[] valoresComNull = new String[flagAbstrata.value().length + 1];
		
		for (int i = 0; i < flagAbstrata.value().length; i++) {
			valoresComNull[i] = flagAbstrata.value()[i];
		}
		
		this.value = valoresComNull;
	}

	@Override
	public boolean isValid(String flag, ConstraintValidatorContext constraintValidatorContext) {
		return ArrayUtils.contains(value, flag);
	}

}