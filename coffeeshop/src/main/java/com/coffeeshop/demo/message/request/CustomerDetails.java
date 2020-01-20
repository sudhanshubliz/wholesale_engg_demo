/**
 * 
 */
package com.coffeeshop.demo.message.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author sudthaku
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@ApiModel(description = "Customer details request")
public class CustomerDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	@ApiModelProperty(notes = "Customer Name")
	@NotNull(message = "Customer Name cannot be null")
	private String customerName;
	
 	private String emaild;
	private String phoneNumber;
	private int scoreNumber;
  
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the emaild
	 */
	public String getEmaild() {
		return emaild;
	}
	/**
	 * @param emaild the emaild to set
	 */
	public void setEmaild(String emaild) {
		this.emaild = emaild;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the scoreNumber
	 */
	public int getScoreNumber() {
		return scoreNumber;
	}
	/**
	 * @param scoreNumber the scoreNumber to set
	 */
	public void setScoreNumber(int scoreNumber) {
		this.scoreNumber = scoreNumber;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
