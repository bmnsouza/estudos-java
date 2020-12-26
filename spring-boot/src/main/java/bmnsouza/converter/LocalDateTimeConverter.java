package bmnsouza.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import bmnsouza.util.DataUtil;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String dataHora) {
		return LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern(DataUtil.DDMMAAAA_HHMMSS_BARRA).withResolverStyle(ResolverStyle.STRICT));
	}

}