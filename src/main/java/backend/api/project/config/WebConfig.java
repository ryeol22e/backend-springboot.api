package backend.api.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	public WebConfig() {
		// TODO Auto-generated constructor stub
		System.out.println(":::::::::::backend api config open:::::::::::");
	}
	
	

}
