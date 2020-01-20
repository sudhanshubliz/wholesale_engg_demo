/**
 * 
 */
package com.coffeeshop.demo.services;

import java.util.Map;

import com.coffeeshop.demo.message.request.OwnerDashboard;

/**
 * @author sudthaku
 *
 */

public interface OwnerDashboardService {

	String createOwnerDashboard(OwnerDashboard ownerDashboard);
	
	OwnerDashboard findOwnerDashboard(String username);
	
	OwnerDashboard updateOwnerDashboard(String username,OwnerDashboard ownerDashboard);
	
	Map<String, Boolean> deleteOwnerDashboard(String username);
}
