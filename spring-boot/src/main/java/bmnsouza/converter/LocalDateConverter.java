package bmnsouza.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bmnsouza.util.DataUtil;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String data) {
		return LocalDate.parse(data, DateTimeFormatter.ofPattern(DataUtil.DDMMAAAA_BARRA).withResolverStyle(ResolverStyle.STRICT));
	}

}