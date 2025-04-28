package com.tm.config;

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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "gameEntityManager", basePackages = {
		"com.tm.game.repository" }, transactionManagerRef = "gameTransactionManger")
public class GameDbConfig {

	@Primary
	@Bean(name = "gameDatasource")
	@ConfigurationProperties(prefix = "spring.games.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create()
				.type(com.zaxxer.hikari.HikariDataSource.class)
				.build();
	}

	@Primary
	@Bean(name = "gameEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("gameDatasource") DataSource datasource) {
		return builder.dataSource(datasource).packages("com.tm.game.model").build();
	}

	@Primary
	@Bean(name = "gameTransactionManger")
	public PlatformTransactionManager transactionManager(
			@Qualifier("gameEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
