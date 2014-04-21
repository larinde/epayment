package org.larinde.epay.ds.infrastructure.configuraton;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.larinde.epay.ds.domain.repository")
@PropertySource("classpath:jdbc.properties")
public class TestJpaDataStoreConfig {

	private static final String ENTITIES_PACKAGE_NAME = "org.larinde.epay.ds.domain";

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(adapter);
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(ENTITIES_PACKAGE_NAME);
		return factoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
}
