package com.coffeeshop.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.coffeeshop.demo.model.Role;
import com.coffeeshop.demo.model.RoleName;
import com.coffeeshop.demo.repository.RoleRepository;

@EnableJpaRepositories(basePackages = { "com.coffeeshop.demo.repository" })
@SpringBootApplication
public class CoffeeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}
	
	
}

@Component
class StartUpDataRunner implements CommandLineRunner{

	@Autowired
	RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
       
    	Role roleA = new Role();
		roleA.setName(RoleName.ROLE_ADMIN);
		Role roleB = new Role();
		roleB.setName(RoleName.ROLE_SUPERADMIN);
		Role roleC = new Role();
		roleC.setName(RoleName.ROLE_USER);
		List<Role> roleList = new ArrayList<>();
		roleList.add(roleA);
		roleList.add(roleB);
		roleList.add(roleC);
		
		
		List<Role> roles = roleRepository.findAll();
		if(roles.isEmpty())
        roleRepository.saveAll(roleList);
	
		 
		
	}
	
}