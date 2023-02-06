package fr.tse.startuppoc.project.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;

//http://localhost:8081/swagger-ui/

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("fr.tse.startuppoc.project"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(getApiInfo());
    }
	
private ApiInfo getApiInfo() {
    	
    	return new ApiInfoBuilder()
    			.title("Documentation de l'API")
                .description("La documentation de l'API du projet Start Up POC")
                .version("1.0.2")
                .termsOfServiceUrl("CopyRight TSE 2023")
                .contact(new Contact("Yoann MASSET - Joé TEIXEIRA - Teddy SABATIER", "https://www.telecom-st-etienne.fr/", "yoann.masset@telecom-st-etienne.fr,joe.teixeira@telecom-st-etienne.fr,teddy.sabatier@telecom-st-etienne.fr"))
    			.build();
    }
}
