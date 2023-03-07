package com.api.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class MusicapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicapiApplication.class, args);
	}
	
	/*
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}*/
	
	
	//this allows cors request to pass through need to make sure  to remove during final build.
	/*
	@Bean
	@ConditionalOnWarDeployment
	CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	*/
	
	@Bean
	//@ConditionalOnWarDeployment
	CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
	    //System.out.println(config.getAllowedOrigins().toString() + " = " + config.getAllowedOriginPatterns().toString());
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
	
	/*
	@Bean
	RuntimeWiringConfigurer runtimeWiringConfigurer (CentralService crm) {
		return builder -> {
				builder.type("Query", wiring -> wiring
							.dataFetcher("songs", env -> crm.getAllSongs())
							.dataFetcher("songById", env -> crm.getSongById(env.getArgument("id"))));
		};
	}
	*/

}
