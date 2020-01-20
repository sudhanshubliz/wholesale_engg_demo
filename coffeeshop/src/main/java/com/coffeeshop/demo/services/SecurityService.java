/**
 * 
 */
package com.coffeeshop.demo.services;

import com.coffeeshop.demo.message.request.LoginForm;
import com.coffeeshop.demo.message.request.SignUpForm;

/**
 * @author sudthaku
 *
 */
public interface SecurityService {

	 String getAuthenticateUser(LoginForm loginRequest);
	 
	 String registerNewUser(SignUpForm signupForm);
}
