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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = FazendarioConfiguration.ENTITY_MANAGER_FACTORY,
	transactionManagerRef = FazendarioConfiguration.TRANSACTION_MANAGER,
	basePackages = { "bmnsouza.database.fazendario.repository" }
)
public class FazendarioConfiguration {

	// Constantes
	public static final String DATA_SOURCE = "fazendarioDataSource";
	public static final String ENTITY_MANAGER_FACTORY = "fazendarioEntityManagerFactory";
	public static final String TRANSACTION_MANAGER = "fazendarioTransactionManager";

	@Bean(FazendarioConfiguration.DATA_SOURCE)
	@ConfigurationProperties(prefix = "fazendario.datasource")
	public DataSource dataSource() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return DataSourceBuilder.create().build();
	}

	@Bean(FazendarioConfiguration.ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
		@Qualifier(FazendarioConfiguration.DATA_SOURCE) DataSource dataSource) {

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		return builder.dataSource(dataSource).properties(properties)
			.packages("bmnsouza.database.fazendario.entity")
			.persistenceUnit("fazendarioPU").build();
	}

	@Bean(FazendarioConfiguration.TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(@Qualifier(FazendarioConfiguration.ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}