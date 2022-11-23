package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class Config implements WebMvcConfigurer {
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	registry
	      	 .addResourceHandler("/page/post/**", "/images/**")
//	         .addResourceLocations("file:///C:/images/")
	         .setCachePeriod(20);
	 }
}
