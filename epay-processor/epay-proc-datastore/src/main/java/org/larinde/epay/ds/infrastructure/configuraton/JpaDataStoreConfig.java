package org.larinde.epay.ds.infrastructure.configuraton;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
@ComponentScan
@PropertySource("classpath:jdbc.properties")
public class JpaDataStoreConfig {

	private static final String ENTITIES_PACKAGE_NAME = "org.larinde.epay.ds.domain";
	private static final String DB_DRIVER_NAME = "jdbc.driver";
	private static final String DB_URL = "jdbc.url";
	private static final String DB_USERNAME = "jdbc.username";
	private static final String DB_PASSWORD = "jdbc.password";
	private static final String DB_DIALECT = "jdbc.dialect";
	

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
//		 BasicDataSource datasource = new BasicDataSource();
//		 datasource.setDriverClassName(env.getProperty(DB_DRIVER_NAME));
//		 datasource.setUrl(env.getProperty(DB_URL));
//		 datasource.setUsername(env.getProperty(DB_USERNAME));
//		 datasource.setPassword(env.getProperty(DB_PASSWORD));
//		 return datasource;
		
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("org.postgresql.Driver");
		datasource.setUrl("jdbc:postgresql://localhost:5432/epay");
		datasource.setUsername("epayuser");
		datasource.setPassword("epaypass");
		return datasource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabasePlatform(env.getProperty(DB_DIALECT));
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
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
