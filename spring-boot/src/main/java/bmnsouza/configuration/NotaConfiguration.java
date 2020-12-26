package bmnsouza.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = NotaConfiguration.ENTITY_MANAGER_FACTORY,
	transactionManagerRef = NotaConfiguration.TRANSACTION_MANAGER,
	basePackages = { "bmnsouza.database.nota.repository" }
)
public class NotaConfiguration {

	// Constantes
	public static final String DATA_SOURCE = "notaDataSource";
	public static final String ENTITY_MANAGER_FACTORY = "notaEntityManagerFactory";
	public static final String TRANSACTION_MANAGER = "notaTransactionManager";

	@Primary
	@Bean(NotaConfiguration.DATA_SOURCE)
	@ConfigurationProperties(prefix = "nota.datasource")
	public DataSource dataSource() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(NotaConfiguration.ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
		@Qualifier(NotaConfiguration.DATA_SOURCE) DataSource dataSource) {

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");

		return builder.dataSource(dataSource)
			.properties(properties).packages("bmnsouza.database.nota.entity")
			.persistenceUnit("notaPU").build();
	}

	@Primary
	@Bean(NotaConfiguration.TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(@Qualifier(NotaConfiguration.ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}