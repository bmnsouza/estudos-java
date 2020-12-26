package bmnsouza.configuration;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertiesConfiguration {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
		File propertiesFile = new File("properties/application.properties");
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		properties.setLocation(new FileSystemResource(propertiesFile.getAbsoluteFile()));
		properties.setIgnoreResourceNotFound(false);

		return properties;
	}

}