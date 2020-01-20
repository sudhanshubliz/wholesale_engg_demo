package com.coffeeshop.demo.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeeshop.demo.message.request.OwnerDashboard;
import com.coffeeshop.demo.services.OwnerDashboardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/owner")
public class OwnerDashboardController {
    
    @Autowired
    OwnerDashboardService ownerDashboardService;
 
    /**
	 * create Owner Dashboard.
	 *
	 * @param ownerDashboard
	 *            ownerDashboard request
	 * @return the String
	 */
    @ApiOperation(value = "Create a Owner Dashboard")
  	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
    @PostMapping("/ownerDashboard")
 	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createOwnerDashboard(@Valid @RequestBody OwnerDashboard ownerDashboard) {
    
    String dashboardMessage =	 ownerDashboardService.createOwnerDashboard(ownerDashboard);
    	
        return ResponseEntity.ok().body(dashboardMessage);
    }
     
    /**
 	 * find Owner Dashboard
 	 *
 	 * @param username
 	 *            username request
 	 * @return the OwnerDashboard
 	 */
     @ApiOperation(value = "Search Owner Dashboard")
  	 @ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
     @GetMapping("/getByUserNameDashboard/{username}")
  	 @PreAuthorize("hasRole('ROLE_ADMIN')")
     public ResponseEntity<OwnerDashboard> findOwnerDashboard(@PathVariable(value = "username")
     String username) {
 
    	 OwnerDashboard ownerDashboard = ownerDashboardService.findOwnerDashboard(username);
    	 
    	 return ResponseEntity.ok().body(ownerDashboard);
     
     }
    
     /**
  	 * update Owner Dashboard
  	 *
  	 * @param username
  	 *            username request
  	 * @return the OwnerDashboard
  	 */
     @ApiOperation(value = "Update the Owner Dashboard")
  	 @ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
     @PutMapping("/updateOwnerDashboard/{username}")
  	 @PreAuthorize("hasRole('ROLE_ADMIN')")
     public ResponseEntity<OwnerDashboard> updateOwnerDashboard(@PathVariable(value = "username")
     String username,@Valid @RequestBody OwnerDashboard ownerDashboard) {
     	
    	 OwnerDashboard updatedOwnerData = ownerDashboardService.updateOwnerDashboard(username, ownerDashboard);
    	 
    	 return ResponseEntity.ok().body(updatedOwnerData);

      	 
     }
     
     /**
   	 * Delete the Owner Dashboard
   	 *
   	 * @param username
   	 *            username request
   	 * @return the Map<String, Boolean>
   	 */
     @ApiOperation(value = "Delete the Owner Dashboard")
  	 @ApiResponses(value = { @ApiResponse(code = 400, message = "Invaid url or parameters"), @ApiResponse(code = 500, message = "Internal server error") })
     @DeleteMapping("/deleteOwnerDashboard/{username}")
  	 @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
     public ResponseEntity<Map<String, Boolean>> deleteOwnerDashboard(@PathVariable(value = "username")
     String username) {
     	  	 
    	 Map<String, Boolean> response  =  ownerDashboardService.deleteOwnerDashboard(username);
    	 
    	 return ResponseEntity.ok().body(response);

      	 
     }
     
    
}