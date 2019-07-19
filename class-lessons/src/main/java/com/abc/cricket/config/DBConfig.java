package com.abc.cricket.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Soham.Chakravarti
 *
 */
@Configuration
@EnableTransactionManagement
public class DBConfig {
	
	@Autowired
    private Environment env;
	
	@Bean({"scorecard-rep_rdbms_emf.bean"})
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		vendorAdapter.setDatabase(env.getProperty("hibernate.database", Database.class));
		vendorAdapter.setGenerateDdl(env.getProperty("hibernate.generateDDL", Boolean.class, false));

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPackagesToScan("com.abc.cricket.model");
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(ds);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		jpaProperties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
		jpaProperties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
		jpaProperties.put("hibernate.connection.autoReconnect", env.getProperty("hibernate.connection.autoReconnect"));
		jpaProperties.put("hibernate.connection.autoReconnectForPools",	env.getProperty("hibernate.connection.autoReconnectForPools"));

		jpaProperties.put("hibernate.connection.release_mode", "after_transaction");
		
		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	
	@Bean
	public EntityManagerFactory entityManagerFactory(@Qualifier("scorecard-rep_rdbms_emf.bean") LocalContainerEntityManagerFactoryBean entityMgrFactory) {
		return entityMgrFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("scorecard-rep_rdbms_emf.bean") LocalContainerEntityManagerFactoryBean entityMgrFactoryBean) {
		return new JpaTransactionManager(entityMgrFactoryBean.getObject());
	}
}
