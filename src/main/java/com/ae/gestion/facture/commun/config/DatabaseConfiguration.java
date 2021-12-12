package com.ae.gestion.facture.commun.config;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableJpaRepositories(value = {
  "com.ae.gestion.facture.client.repository",
  "com.ae.gestion.facture.document.repository",
  "com.ae.gestion.facture.facture.repository",
  "com.ae.gestion.facture.virement.repository",
  "com.ae.gestion.facture.security.repository",
})
public class DatabaseConfiguration implements WebMvcConfigurer {
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new SpecificationArgumentResolver());
  }
}
