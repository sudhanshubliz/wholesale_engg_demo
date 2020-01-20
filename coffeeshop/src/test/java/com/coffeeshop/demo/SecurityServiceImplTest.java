package com.coffeeshop.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coffeeshop.demo.message.request.LoginForm;
import com.coffeeshop.demo.message.request.SignUpForm;
import com.coffeeshop.demo.model.Role;
import com.coffeeshop.demo.model.RoleName;
import com.coffeeshop.demo.model.User;
import com.coffeeshop.demo.repository.RoleRepository;
import com.coffeeshop.demo.repository.UserRepository;
import com.coffeeshop.demo.security.jwt.JwtProvider;
import com.coffeeshop.demo.services.SecurityServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
@SpringBootTest
public class SecurityServiceImplTest {
 
	@InjectMocks
	SecurityServiceImpl securityService;
	
	@Mock
	UserRepository  userRepository;
	 
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	JwtProvider jwtProvider;

	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(PasswordEncoder.class);

		MockitoAnnotations.initMocks(this);
	}

 	@Test
	public void testSignupNewUser() throws IOException {

		PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
		SignUpForm signupForm = new SignUpForm();

		Role roleA = new Role();
		roleA.setName(RoleName.ROLE_ADMIN);
		Role roleB = new Role();
		roleB.setName(RoleName.ROLE_USER);
		List<Role> roleList = new ArrayList<>();
		roleList.add(roleA);
		roleList.add(roleB);
		Optional<Role> role = Optional.of(roleB);
		signupForm.setUsername("Sudhanshubliz");
		signupForm.setEmail("sudhanshubliz@gmail.com");
		signupForm.setName("Sudhanshu");
		signupForm.setPassword("12345");
		signupForm.setRole(new HashSet<String>(Arrays.asList("admin", "user")));

		Mockito.when(encoder.encode(signupForm.getPassword())).thenReturn("xxxx");

		User user = new User(signupForm.getName(), signupForm.getUsername(), signupForm.getEmail(),
				encoder.encode(signupForm.getPassword()));
		user.setRoles(new HashSet<Role>(roleList));

		when(userRepository.save(any())).thenReturn(user);
		when(roleRepository.findByName(any())).thenReturn(role);

		String savedUser = securityService.registerNewUser(signupForm);
		assertThat(savedUser).isNotNull();
	}
 	
	@Test
	public void testAuthenticateLoginUser() throws IOException {

		LoginForm loginRequest = new LoginForm();
		loginRequest.setUsername("sudhanshubliz");
		loginRequest.setPassword("123456");

		Authentication authentication = Mockito.mock(Authentication.class);

		Mockito.when(authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())))
				.thenReturn(authentication);

		String token = Jwts.builder().setSubject((loginRequest.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()))
				.signWith(SignatureAlgorithm.HS512, "jwtxyscxncnvcncSecretKey").compact();

		Mockito.when(jwtProvider.generateJwtToken(authentication)).thenReturn(token);

		String authuser = securityService.getAuthenticateUser(loginRequest);
		assertThat(authuser).isNotNull();
		assertThat(authuser).isEqualTo(token);

	}
 
}
