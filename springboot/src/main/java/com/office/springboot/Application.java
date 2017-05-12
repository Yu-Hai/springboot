package com.office.springboot;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.office.springboot.common.util.PropertiesUtil;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan({"com.office.springboot.common.mapper","com.office.springboot.*.mapper"})
public class Application {

	private static Logger logger=LoggerFactory.getLogger(Application.class);  
    

	
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
    	DataSource dataSource = new DataSource();
    	dataSource.setUrl(PropertiesUtil.getProperty("spring.datasource.url"));
    	dataSource.setUsername(PropertiesUtil.getProperty("spring.datasource.username"));
    	dataSource.setPassword(PropertiesUtil.getProperty("spring.datasource.password"));
    	dataSource.setDriverClassName(PropertiesUtil.getProperty("spring.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/com/office/springboot/sqlmap/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.trace("Start Success");
	}
		
	
	
}
