package org.fiveware.test.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"org.fiveware.test.web", "org.fiveware.test.service", })
public class WebAppConfig extends WebMvcConfigurerAdapter {

}
