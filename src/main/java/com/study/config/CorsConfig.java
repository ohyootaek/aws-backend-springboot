package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  private final Environment environment;

  public CorsConfig(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String activeProfile = environment.getActiveProfiles()[0]; // 현재 활성화된 프로필 가져오기

    if ("prod".equals(activeProfile)) {
      registry.addMapping("/**")
          .allowedOrigins("http://cariros.store", "https://cariros.store", "http://www.cariros.store", "https://www.cariros.store")
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .allowCredentials(true);
    } else if ("local".equals(activeProfile)) {
      registry.addMapping("/**")
          .allowedOrigins("http://localhost:5173") // 로컬 환경에 맞는 origin
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .allowCredentials(true);
    }
  }
}
