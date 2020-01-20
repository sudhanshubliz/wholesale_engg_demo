package com.coffeeshop.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.coffeeshop.demo.message.request.CoffeeType;
import com.coffeeshop.demo.message.request.CustomerDetails;
import com.coffeeshop.demo.message.request.OwnerAddress;
import com.coffeeshop.demo.message.request.OwnerDashboard;
import com.coffeeshop.demo.repository.OwnerRepository;
import com.coffeeshop.demo.services.OwnerDashboardServiceImpl;
 
@SpringBootTest
public class OwnerDashboardServiceImplTest {
 
	@InjectMocks
	OwnerDashboardServiceImpl ownerDashboardServiceImpl;
	
	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	AuthenticationManager authenticationManager;
 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

 	@Test
	public void testcreateOwnerDashboard() throws IOException {
		Optional<OwnerDashboard> odashboard = createOwnerDashboard();

		 
		when(ownerRepository.findByOwnerUserName(any())).thenReturn(odashboard);
		when(ownerRepository.save(any())).thenReturn(odashboard);
		Authentication authentication = Mockito.mock(Authentication.class);
  
		
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		
		String ownerData = ownerDashboardServiceImpl.createOwnerDashboard(odashboard.get());
		
		assertThat(ownerData).isNotNull();

		}
 	 
	@Test
	public void testFindOwnerDashboard() throws IOException {
   
		Optional<OwnerDashboard> odashboard = createOwnerDashboard();

		String username = "Sudhanshubliz@gmail.com";
		Mockito.when(ownerRepository.findByOwnerUserName(username)).thenReturn(odashboard);
		
		OwnerDashboard dashboardResult = ownerDashboardServiceImpl.findOwnerDashboard(username);
	
		assertThat(dashboardResult).isNotNull();

	}
	 
	@Test
	public void testUpdateOwnerDashboard() throws IOException {
 
		Optional<OwnerDashboard> odashboard = createOwnerDashboard();
		String username = "Sudhanshubliz@gmail.com";
 
		Mockito.when(ownerRepository.findByOwnerUserName(username)).thenReturn(odashboard);

		when(ownerRepository.save(any())).thenReturn(odashboard.get());

		OwnerDashboard dashboardResult = ownerDashboardServiceImpl.updateOwnerDashboard(username, odashboard.get());
		
		assertThat(dashboardResult).isNotNull();
	}
	
	@Test
	public void testDeleteOwnerDashboard() throws IOException {

		Optional<OwnerDashboard> odashboard = createOwnerDashboard();

		String username = "Sudhanshubliz@gmail.com";

		Mockito.when(ownerRepository.findByOwnerUserName(username)).thenReturn(odashboard);

		Map<String, Boolean> dashboardResult = ownerDashboardServiceImpl.deleteOwnerDashboard(username);

		assertThat(dashboardResult.get("Owner dashboard is successfully deleted")).isEqualTo(Boolean.TRUE);

	}
	
	 static Optional<OwnerDashboard> createOwnerDashboard() {
		LocalTime time = LocalTime.now();
		DateTimeFormatter timeFormatter3 = DateTimeFormatter.ofPattern("hh:mm a");
		String times = time.format(timeFormatter3);
		OwnerDashboard dashboard = new OwnerDashboard();
		dashboard.setCloseTime(times);
		dashboard.setOpenTime(times);
		CoffeeType type = new CoffeeType();
		type.setCoffeeName("Cappuccino");
		type.setPrice(100);
		Set<CoffeeType> cofeetype = new HashSet<>();
		cofeetype.add(type);
		dashboard.setCoffeeType(cofeetype);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerName("Alex");
		customerDetails.setEmaild("alex@gmail.com");
		customerDetails.setPhoneNumber("991009292");
		customerDetails.setScoreNumber(30);
		List<CustomerDetails> customerDetailList = new ArrayList<>();
		customerDetailList.add(customerDetails);
		dashboard.setCustomerDetails(customerDetailList);
		dashboard.setMaxSizeofQueue(50);
		dashboard.setNoOfQueue(10);
		OwnerAddress ownerAddress = new OwnerAddress();
		ownerAddress.setAddress("New Delhi");
		ownerAddress.setPhoneNumber("991990000");
		ownerAddress.setPinCode("110092");

		dashboard.setOwnerAddress(ownerAddress);
		dashboard.setOwnerUserName("sudhanshu");
 		
		Optional<OwnerDashboard>   odashboard  = Optional.of(dashboard);
		return odashboard;
	}
	
}
