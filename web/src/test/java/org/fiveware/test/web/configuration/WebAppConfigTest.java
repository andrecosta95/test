package org.fiveware.test.web.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages={"org.fiveware.test.web", "org.fiveware.test.service", })
public class WebAppConfigTest extends WebMvcConfigurerAdapter {

	private static final String 	PU_NAME 		= "fiveware-test";
	private static final Database 	DATABASE 		= Database.H2;
	private static final boolean 	SHOW_SQL 		= false;
	private static final boolean 	GENERATE_DDL 	= true;
	
	@Bean
	public DataSource dataSource() throws NamingException {
		
		EmbeddedDatabaseFactory edf = new EmbeddedDatabaseFactory();
		edf.setDatabaseType(EmbeddedDatabaseType.H2);
		edf.setDatabaseName("FV_FIVEWARE");
		
		return (DataSource) edf.getDatabase();
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
		adapter.setGenerateDdl(GENERATE_DDL);
		
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
	
}
