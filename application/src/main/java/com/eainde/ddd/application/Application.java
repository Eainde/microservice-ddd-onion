package com.eainde.ddd.application;

import com.sun.istack.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.eainde.ddd")
@PropertySource({
  "classpath:service/application.properties",
  "classpath:service/application-${spring.profiles.active}.properties",
  "classpath:database/application.properties",
  "classpath:database/application-${spring.profiles.active}.properties"
})
@EnableJpaRepositories(basePackages = "com.eainde.ddd")
@EntityScan("com.eainde.ddd.entity")
public class Application {
  private static final String LOCALHOST="http://localhost:[*]";
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  public WebMvcConfigurer corsConfigureE0(){
    return configureFor(LOCALHOST);
  }

  private WebMvcConfigurer configureFor(String...origins){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**").maxAge(3600).allowedMethods("*").allowedOriginPatterns(origins);
      }
    };
  }
}
