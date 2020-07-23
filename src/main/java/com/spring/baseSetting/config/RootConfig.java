package com.spring.baseSetting.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration //설정용 클래스
@PropertySource(value = {"classpath:config/jdbc.properties"})
@EnableTransactionManagement //<tx:annotation-driven /> 트랜잭션 설정
@ComponentScan(basePackages = {"com.spring.baseSetting.dao", "com.spring.baseSetting.service"})
public class RootConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public RootConfig(Environment env) {
        logger.info("RootConfig Init...");
        this.env = env;
    }

    // 아래 4개의 빈은 마이바티스 설정
    @Bean
    public DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource();
        //BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriver(env.getProperty("jdbc.mysql.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.mysql.url"));
        dataSource.setUsername(env.getProperty("jdbc.mysql.username"));
        dataSource.setPassword(env.getProperty("jdbc.mysql.password"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/model/modelConfig.xml"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean  // SqlSession구현
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean  //트랜잭션 관리
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}