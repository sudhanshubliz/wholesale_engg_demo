package com.wholesale.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wholesale.demo.model.Account;
import com.wholesale.demo.model.AccountCreditDebit;
import com.wholesale.demo.model.AccountType;
import com.wholesale.demo.repository.AccountRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 

@EnableSwagger2
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket swaggerPersonApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("pl.piomin.services.employee.controller"))
					.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
	}
	
	@Bean
	AccountRepository repository() {
	
		AccountRepository repository = new AccountRepository();
		
		repository.add(new Account("5853092009", "SavingAccount", AccountType.Saving,  "08/11/2018" , "SGD",77234.55, 
				"Jan,12,2012", 457845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		repository.add(new Account("7853092009", "SavingAccount", AccountType.Saving,  "10/11/2018" , "SGD",57234.55, 
				"Jan,12,2012", 54845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		repository.add(new Account("7853092009", "SavingAccount", AccountType.Saving,  "09/11/2018" , "SGD",886234.55, 
				"Jan,12,2012", 467845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		repository.add(new Account("2853092009", "SavingAccount", AccountType.Saving,  "11/11/2018" , "SGD",23334.55, 
				"Jan,12,2012", 677845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		repository.add(new Account("3853092009", "SavingAccount", AccountType.Saving,  "12/11/2018" , "SGD",55234.55, 
				"Jan,12,2012", 64845.45, AccountCreditDebit.Credit, "transaction Narrate"));
		repository.add(new Account("3853092009", "SavingAccount", AccountType.Saving,  "23/11/2018" , "SGD",664234.55, 
				"Jan,12,2012", 364845.45, AccountCreditDebit.Credit, "transaction Narrate"));
			
		 
		return repository;
	}
}
