package com.tm.config;

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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "universityEntityManager", basePackages = {
		"com.tm.university.repository" }, transactionManagerRef = "universityTransactionManger")
public class UniversityDbConfig {

	@Bean(name = "universityDatasource")
	@ConfigurationProperties(prefix = "spring.universites.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create()
				.type(com.zaxxer.hikari.HikariDataSource.class)
				.build();
	}

	@Bean(name = "universityEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("universityDatasource") DataSource datasource) {
		return builder.dataSource(datasource).packages("com.tm.university.model").build();
	}

	@Bean(name = "universityTransactionManger")
	public PlatformTransactionManager transactionManager(
			@Qualifier("universityEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
