package bmnsouza.configuration;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import bmnsouza.util.DataUtil;

@Configuration
public class DataConfiguration {

	private static final String LOCAL_TIME_PATTERN = DataUtil.HHMMSS_DOIS_PONTOS;
	private static final String LOCAL_DATE_PATTERN = DataUtil.DDMMAAAA_BARRA;
	private static final String LOCAL_DATE_TIME_PATTERN = DataUtil.DDMMAAAA_HHMMSS_BARRA;

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			// Serializers
			builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_PATTERN)));
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN).withResolverStyle(ResolverStyle.STRICT)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN).withResolverStyle(ResolverStyle.STRICT)));

			// Deserializers
			builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_TIME_PATTERN)));
			builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN).withResolverStyle(ResolverStyle.STRICT)));
			builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN).withResolverStyle(ResolverStyle.STRICT)));
		};
	}

}