package backend.api.project.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Order(Ordered.LOWEST_PRECEDENCE)
public class YamlEnvProcessor implements EnvironmentPostProcessor {
	private static final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
	private static final String SPRING_PROFILES = "spring.profiles";
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		// TODO Auto-generated method stub
		Resource path = new ClassPathResource("local/application-local.yml");
		List<PropertySource<?>> propertySources = new ArrayList<PropertySource<?>>();
		
		try {
			propertySources = loader.load("resources-config", path);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("yaml error {} :" + e.getMessage());
		}
		
		String activeProfile = environment.getActiveProfiles()[0];
		MutablePropertySources envPropertyBox = environment.getPropertySources();
		
		// profile과 무관한 프로퍼티 파일
		propertySources.stream()
			.filter(source -> source.getProperty(SPRING_PROFILES)!=null && !"".equals(source.getProperty(SPRING_PROFILES)))
			.findFirst()
			.ifPresent(source -> envPropertyBox.addLast(source));
		
		// profile 관련 프로퍼티 파일
		PropertySource<?> profileProperty = propertySources.stream()
			.filter(source -> activeProfile.equals(String.valueOf(source.getProperty(SPRING_PROFILES))))
			.findFirst()
			.orElse(propertySources.get(0));
		envPropertyBox.addLast(profileProperty);
		
	}

}
