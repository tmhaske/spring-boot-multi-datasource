# Spring Boot Application with Multiple DataSources

This project connects a Spring Boot application to two different databases:
- `video_games`
- `universities`

Each database has its own:
- `DataSource`
- `EntityManagerFactory`
- `TransactionManager`

---

## Database Configuration Steps

### 1. Create Separate Configuration Classes
Create two configuration classes under `com.tm.config`:
- `GameDbConfig` (for `video_games` database)
- `UniversityDbConfig` (for `universities` database)

Each configuration class should define the following beans:

#### a. DataSource Bean
- Use `@ConfigurationProperties` to bind properties from `application.properties`.
- Use `DataSourceBuilder` to create a `DataSource`.

**Example for GameDbConfig:**
```java
@Primary
@Bean(name = "gameDatasource")
@ConfigurationProperties(prefix = "spring.games.datasource")
public DataSource datasource() {
    return DataSourceBuilder.create()
            .type(com.zaxxer.hikari.HikariDataSource.class)
            .build();
}

@Bean(name = "universityDatasource")
@ConfigurationProperties(prefix = "spring.universites.datasource")
public DataSource datasource() {
    return DataSourceBuilder.create()
            .type(com.zaxxer.hikari.HikariDataSource.class)
            .build();
}
```  

### b. EntityManagerFactory Bean
Create a LocalContainerEntityManagerFactoryBean.
Bind the correct DataSource.
Set the package where entity classes are located.

Example for GameDbConfig:
```java
@Primary
@Bean(name = "gameEntityManager")
public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("gameDatasource") DataSource datasource) {
return builder.dataSource(datasource)
              .packages("com.tm.game.model").build();
}
```
Example for UniversityDbConfig:
```java
@Bean(name = "universityEntityManager")
public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("universityDatasource") DataSource datasource) {
    return builder.dataSource(datasource)
                  .packages("com.tm.university.model").build();
}
```
### c. TransactionManager Bean
Create a PlatformTransactionManager.
Bind it to the corresponding EntityManagerFactory.
Example for GameDbConfig:

```java
@Primary
@Bean(name = "gameTransactionManger")
public PlatformTransactionManager transactionManager(@Qualifier("gameEntityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
}
```
Example for UniversityDbConfig:
```java
@Bean(name = "universityTransactionManger")
public PlatformTransactionManager transactionManager(@Qualifier("universityEntityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
}
``` 

### 2. Enable JPA Repositories
Use @EnableJpaRepositories to specify:
- basePackages where repository interfaces are located.
- entityManagerFactoryRef name.
- transactionManagerRef name.

Example for GameDbConfig:
```java
@EnableJpaRepositories(
entityManagerFactoryRef = "gameEntityManager",
basePackages = "com.tm.game.repository",
transactionManagerRef = "gameTransactionManger"
)
```
Example for UniversityDbConfig:
```java
@EnableJpaRepositories(
entityManagerFactoryRef = "universityEntityManager",
basePackages = "com.tm.university.repository",
transactionManagerRef = "universityTransactionManger"
)
```
### 3. Configure Properties
Add the following entries in application.properties:
# Game Database Configuration
```java
spring.games.datasource.url=jdbc:mysql://localhost:3306/video_games
spring.games.datasource.username=root
spring.games.datasource.password=password
spring.games.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
```java 
# University Database Configuration
spring.universites.datasource.url=jdbc:mysql://localhost:3306/universities
spring.universites.datasource.username=root
spring.universites.datasource.password=password
spring.universites.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
