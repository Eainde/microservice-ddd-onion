package com.eainde.ddd.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
