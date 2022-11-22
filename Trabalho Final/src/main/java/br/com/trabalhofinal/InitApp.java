package br.com.trabalhofinal;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
		};
	}
	
	@Bean
    public Hibernate5Module jsonHibernate5Module() {
        return new Hibernate5Module();
    }
	
	@Bean
	public CorsFilter corsFilter() {
	    
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    final CorsConfiguration config = new CorsConfiguration();
	    
	    config.setAllowCredentials(true);
	    
	    config.setAllowedOrigins(Collections.singletonList("*"));
	    
	    config.setAllowedHeaders(Arrays.asList(
	    		"Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin", 
				"Access-Control-Request-Method", 
	    		"Access-Control-Request-Headers",
	    		"Origin","Cache-Control", 
	    		"Content-Type", "Authorization", 
	    		"Access-Control-Allow-Headers"));
	    
	    config.setExposedHeaders(Arrays.asList(
	    		"Access-Control-Allow-Headers",
	    		"Access-Control-Allow-Origin",
				"Authorization", "x-xsrf-token", "Access-Control-Allow-Headers",
	    		"Origin","Accept", "X-Requested-With", "Location",
	    		"Content-Type", "Access-Control-Request-Method",
	    		"Access-Control-Request-Headers"));
	    
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    
	    source.registerCorsConfiguration("/**", config);	    
	    
	    return new CorsFilter(source);	    	   
	    
	}

}
