/**
 * 
 */
package com.coffeeshop.demo.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.coffeeshop.demo.exception.ResourceNotFoundException;
import com.coffeeshop.demo.message.request.OwnerDashboard;
import com.coffeeshop.demo.repository.OwnerRepository;

/**
 * @author sudthaku
 *
 */
@Service
public class OwnerDashboardServiceImpl implements OwnerDashboardService {
 
    @Autowired
    OwnerRepository ownerRepository;

	/**Create Owner Dashboard service
	 *
	 *@param ownerDashboard
	 *            the request body
	 */
	@Override
	public String createOwnerDashboard(OwnerDashboard ownerDashboard) {
		Authentication authentication =   SecurityContextHolder.getContext().getAuthentication();
		
        Optional<OwnerDashboard>   odashboard = ownerRepository.findByOwnerUserName(authentication.getName());
        if(!odashboard.isPresent()) {
        ownerDashboard.setOwnerUserName(authentication.getName());
        ownerRepository.save(ownerDashboard);
            return "Owner Dashboard is successfuly created";
        }else
        	return "Owner Dashboard is already available";
 	}


	/**Find Owner Dashboard service
	 *
	 *@param username
	 *            the request body
	 */
	@Override
	public OwnerDashboard findOwnerDashboard(String username) {

		 OwnerDashboard   ownerDashboard = ownerRepository.findByOwnerUserName(username).
				 orElseThrow(() -> new ResourceNotFoundException("Owner's dashboard data", "username", username));

		return ownerDashboard;
	}


	/**Update Owner Dashboard service
	 *
	 *@param username
	 *            the request body
	 * @param ownerDashboard
	 *            the request body
	 */
	@Override
	public OwnerDashboard updateOwnerDashboard(String username, OwnerDashboard ownerDashboard) {

   	 OwnerDashboard   dashboardResult = ownerRepository.findByOwnerUserName(username).
   			 orElseThrow(() -> new ResourceNotFoundException("Owner's Username", "username", username));
   	 
   	 dashboardResult.setOwnerAddress(ownerDashboard.getOwnerAddress());
   	 dashboardResult.setCustomerDetails(ownerDashboard.getCustomerDetails());
   	 dashboardResult.setCoffeeType(ownerDashboard.getCoffeeType());
   	 dashboardResult.setCloseTime(ownerDashboard.getCloseTime());
   	 dashboardResult.setOpenTime(ownerDashboard.getOpenTime());
   	 dashboardResult.setMaxSizeofQueue(ownerDashboard.getMaxSizeofQueue());
   	 dashboardResult.setNoOfQueue(ownerDashboard.getNoOfQueue());

   	final OwnerDashboard updatedOwnerData = ownerRepository.save(dashboardResult);
   	
		return updatedOwnerData;
	}

	/**Delete Owner Dashboard service
	 *
	 *@param username
	 *            the request body
	 * 
	 */
	@Override
	public Map<String, Boolean> deleteOwnerDashboard(String username) {

   	 OwnerDashboard   dashboardResult = ownerRepository.findByOwnerUserName(username).
   			 orElseThrow(() -> new ResourceNotFoundException("Owner's Username", "username", username));
   	  
   	 Map<String, Boolean> response = new HashMap<>();
   	 ownerRepository.delete(dashboardResult);
   	 
   	 response.put("Owner dashboard is successfully deleted", Boolean.TRUE);  
   	 
		return response;
	}

}
