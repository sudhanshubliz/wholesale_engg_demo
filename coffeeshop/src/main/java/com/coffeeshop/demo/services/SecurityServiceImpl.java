/**
 * 
 */
package com.coffeeshop.demo.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coffeeshop.demo.message.request.LoginForm;
import com.coffeeshop.demo.message.request.SignUpForm;
import com.coffeeshop.demo.model.Role;
import com.coffeeshop.demo.model.RoleName;
import com.coffeeshop.demo.model.User;
import com.coffeeshop.demo.repository.RoleRepository;
import com.coffeeshop.demo.repository.UserRepository;
import com.coffeeshop.demo.security.jwt.JwtProvider;

/**
 * @author sudthaku
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;


    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    
    /**Get Authenticate User service
	 *
	 *@param loginRequest
	 *            the request body
	 * 
	 */
	@Override
	public String getAuthenticateUser(LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtProvider.generateJwtToken(authentication);

		return jwtToken;
	}
	
	/**Register User service
	 *
	 *@param signupForm
	 *            the request body
	 * 
	 */
	@Override
	public String registerNewUser(SignUpForm signupForm) {

	        // Creating user's account
	        User user = new User(signupForm.getName(), signupForm.getUsername(),
	        		signupForm.getEmail(), encoder.encode(signupForm.getPassword()));

	        Set<String> strRoles = signupForm.getRole();
	        Set<Role> roles = new HashSet<>();

	        strRoles.forEach(role -> {
	        	switch(role) {
		    		case "admin":
		    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		    			roles.add(adminRole);
		    			
		    			break;
		    		case "superadmin":
		            	Role pmRole = roleRepository.findByName(RoleName.ROLE_SUPERADMIN)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		            	roles.add(pmRole);
		            	
		    			break;
		    		default:
		        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		        		roles.add(userRole);        			
	        	}
	        });
	       
	       user.setRoles(roles);
	       User savedUser =  userRepository.save(user);
		return savedUser.toString();
	}

}
