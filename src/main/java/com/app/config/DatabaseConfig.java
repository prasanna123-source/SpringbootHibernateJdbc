package com.app.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfig {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USERNAME;
	
	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;

	@Bean
	public DataSource datasource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);		
		return dataSource;		
	}
	
	@Bean
	@Primary
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(datasource());
		sessionFactoryBean.setPackagesToScan("com.app.model");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		// TODO Auto-generated method stub
		return sessionFactoryBean;
	}
	
	@Bean
	PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
		
	}
	

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(datasource());
		emf.setPackagesToScan("com.app.model");
		JpaVendorAdapter hibernateAdapter=new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(hibernateAdapter);
		emf.setJpaProperties(hibernateProperties());
		return emf.getObject();
		
	}


	public Properties hibernateProperties() {
		Properties hibernateProperties=new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return hibernateProperties;
	}
	
	
	
}
