package io.cesa.libraryAPI.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {
  
  @Value("${spring.datasource.url}")
  String url;
  @Value("${spring.datasource.username}")
  String username;
  @Value("${spring.datasource.password}")
  String password;
  @Value("${spring.datasource.driver-class-name}")
  String driver;
  
  
  //@Bean  // não é otimizado para produção
  public DataSource dataSource(){
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    ds.setDriverClassName(driver);
    return ds;
  }
  
  /**
   * Configuração Hikari
   * https://github.com/brettwooldridge/HikariCP
   * @return
   */
  @Bean
  public DataSource hikariDatasource(){
    HikariConfig config = new HikariConfig();
    config.setUsername(username);
    config.setPassword(password);
    config.setDriverClassName(driver);
    config.setJdbcUrl(url);
    
    // config pool de conexoes
    config.setMaximumPoolSize(10); // max de conexões liberadas
    config.setMinimumIdle(1);    // tamanho inicial do pool
    config.setPoolName("library-db-pool");
    config.setMaxLifetime(600000); // 600 mil ms (10 minutos)
    config.setConnectionTimeout(100000);
    config.setConnectionTestQuery("select 1"); //query de test
    
    return  new HikariDataSource(config);
    
  }
  
  
  
}
