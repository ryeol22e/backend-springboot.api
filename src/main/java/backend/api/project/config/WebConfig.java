package backend.api.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public WebConfig() {
		// TODO Auto-generated constructor stub
		log.info("backend config start...");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry
			.addMapping("/**")
			.allowedOrigins("http://localhost:8080");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
