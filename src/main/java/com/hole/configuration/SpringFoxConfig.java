package com.hole.configuration;

import java.util.Arrays;
import java.util.List;

// import java.util.Arrays;
// import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@Configuration
public class SpringFoxConfig {

  @Value("${security.oauth2.client.id}")
  private String clientId;

  @Value("${security.oauth2.client.secret}")
  private String clientSecret;
  
  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build()
      .pathMapping("/")
      .apiInfo(apiInfo())
      .securitySchemes(Arrays.asList(apiKey()))
      .securityContexts(Arrays.asList(securityContext()));
  }

  
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Registro de buracos")
        .description("API com o objetivo de registrar buracos nas avenidas e estradas das cidades")
        .license("MIT")
        .licenseUrl("**Comment**").version("0.0.1")
        .build();
    }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
      .securityReferences(defaultAuth())
      .build();
  }

  private List<SecurityReference> defaultAuth() {
    var authorizationScope = new AuthorizationScope("global", "accessEverything");
    var authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
  }

  @Bean
  public SecurityConfiguration security() {
    return SecurityConfigurationBuilder.builder()
      .clientId(clientId)
      .clientSecret(clientSecret)
      .realm("realm")
      .appName("test-app")
      .scopeSeparator(",")
      .additionalQueryStringParams(null)
      .useBasicAuthenticationWithAccessCodeGrant(false)
      .build();
  }
}
