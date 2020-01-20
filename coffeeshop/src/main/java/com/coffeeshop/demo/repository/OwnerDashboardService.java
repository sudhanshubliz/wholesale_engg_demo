/**
 * 
 */
package com.coffeeshop.demo.repository;

import com.coffeeshop.demo.message.request.OwnerDashboard;

/**
 * @author sudthaku
 *
 */
public interface OwnerDashboardService {

	String createOwnerDashboard(OwnerDashboard ownerDashboard);
	
	OwnerDashboard findOwnerDashboard(String username);
	
	OwnerDashboard updateOwnerDashboard(String username,OwnerDashboard ownerDashboard);
	
	boolean deleteOwnerDashboard(String username);
}
