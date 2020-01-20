package com.coffeeshop.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.demo.message.request.LoginForm;
import com.coffeeshop.demo.message.request.SignUpForm;
import com.coffeeshop.demo.message.response.JwtResponse;
import com.coffeeshop.demo.repository.UserRepository;
import com.coffeeshop.demo.services.OwnerDashboardService;
import com.coffeeshop.demo.services.SecurityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class SecurityController {

  

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    OwnerDashboardService ownerDashboardService;

    /**
   	 * Login to Authenticate User
   	 *
   	 * @param loginRequest
   	 *             request body
   	 * 
   	 */
    @ApiOperation(value = "Login to authenticate user.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

    	String jwtToken = securityService.getAuthenticateUser(loginRequest);
    
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
    /**
   	 * Register the User
   	 *
   	 * @param signupForm
   	 *             request body
   	 * 
   	 */

    @ApiOperation(value = "Register the User")
  	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signupForm) {
      
    	  if(userRepository.existsByUsername(signupForm.getUsername())) {
	            return new ResponseEntity<String>("Fail -> Username is already taken!",
	                    HttpStatus.BAD_REQUEST);
	        }

	        if(userRepository.existsByEmail(signupForm.getEmail())) {
	            return new ResponseEntity<String>("Fail -> Email is already in use!",
	                    HttpStatus.BAD_REQUEST);
	        }

	    String savedUser =   securityService.registerNewUser(signupForm);
        return ResponseEntity.ok().body(savedUser);
    }
    
}