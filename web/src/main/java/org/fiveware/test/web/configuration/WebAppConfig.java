package org.fiveware.test.web.configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages={"org.fiveware.test.web", "org.fiveware.test.service", })
public class WebAppConfig extends WebMvcConfigurerAdapter {

	private static final String 	JNDI 			= "java:comp/env/jdbc/FivewareDS";
	private static final String 	PU_NAME 		= "fiveware-test";
	private static final Database 	DATABASE 		= Database.MYSQL;
	private static final boolean 	SHOW_SQL 		= false;
	private static final int 		CACHE_PERIOD	= 31556926;
	
	@Bean
	public DataSource dataSource() throws NamingException {
		return (DataSource) new InitialContext().lookup(JNDI);
	}
	
	@Bean
	public PersistenceAnnotationBeanPostProcessor annotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
	
	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(DATABASE);
		adapter.setShowSql(SHOW_SQL);
		
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPersistenceUnitName(PU_NAME);
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		
		return factory;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws NamingException {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
	
	@Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.setCachePeriod(CACHE_PERIOD);
	}
	
}
