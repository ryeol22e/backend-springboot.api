package backend.api.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private ProjectInterceptor projectInterceptor;
	
	public WebConfig() {
		// TODO Auto-generated constructor stub
		log.info("backend config start...");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry
			.addMapping("/**")
			.allowedOrigins("http://localhost:8500")
			.allowedMethods("*")
			.allowedHeaders("*")
			.exposedHeaders("Authorization");
		
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(projectInterceptor)
			.addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
