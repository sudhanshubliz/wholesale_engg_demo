/**
 * 
 */
package com.coffeeshop.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sudthaku
 *
 */
@Configuration
@EnableSwagger2
 public class SpringFoxConfig {
	  @Value("${coffeeshop.app.jwtSecret}")
	    private String jwtSecret;
	  
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
 	          .apis(RequestHandlerSelectors.basePackage("com.coffeeshop.demo.controller"))              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(apiInfo())
	        		  .securitySchemes(Arrays.asList(apiKey()))
	                  .securityContexts(Collections.singletonList(securityContext()));
	    }
	  
	private ApiInfo apiInfo() {
		    return new ApiInfo(
		      "Coffee SHOP REST API", 
		      "Login/Owner dashboard API.", 
		      "API TOS", 
		      "Terms of service", 
		      new Contact("Sudhanshu Thakur", "#", "sudhanshubliz@gmail.com"), 
		      "License of API", "API license URL", Collections.emptyList());
		}
  
	  private ApiKey apiKey() {
	        return new ApiKey("Bearer", "Authorization", "header");
	      }

      private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
      }
      private List<SecurityReference> defaultAuth() {
          final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
          final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
          return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
        }
}
