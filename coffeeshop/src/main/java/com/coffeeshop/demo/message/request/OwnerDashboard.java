/**
 * 
 */
package com.coffeeshop.demo.message.request;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author sudthaku
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="ownerdashboard")
public class OwnerDashboard {
	
	@Id
	@Column(name = "ownerdashboard_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String ownerUserName;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ownerAddress_Id")
	private OwnerAddress ownerAddress;
	
	 @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	    @JoinTable(
	            name = "owner_dash_coffeetype_join_table",
	            joinColumns = @JoinColumn(name = "ownerdashboard_id"),
	            inverseJoinColumns = @JoinColumn(name = "coffeetype_id")
	            )
	private Set<CoffeeType> coffeeType;
	private int noOfQueue;
	private int maxSizeofQueue;
	private String openTime;
	private String closeTime;
	 @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	    @JoinTable(
	            name = "owner_dash_customer_details_join_table",
	            joinColumns = @JoinColumn(name = "ownerdashboard_id"),
	            inverseJoinColumns = @JoinColumn(name = "customer_id")
	            )
	 private List<CustomerDetails> customerDetails;
	 
	 
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the ownerUserName
	 */
	public String getOwnerUserName() {
		return ownerUserName;
	}
	/**
	 * @param ownerUserName the ownerUserName to set
	 */
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
 
	 
	/**
	 * @return the ownerAddress
	 */
	public OwnerAddress getOwnerAddress() {
		return ownerAddress;
	}
	/**
	 * @param ownerAddress the ownerAddress to set
	 */
	public void setOwnerAddress(OwnerAddress ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	/**
	 * @return the noOfQueue
	 */
	public int getNoOfQueue() {
		return noOfQueue;
	}
	/**
	 * @param noOfQueue the noOfQueue to set
	 */
	public void setNoOfQueue(int noOfQueue) {
		this.noOfQueue = noOfQueue;
	}
	/**
	 * @return the maxSizeofQueue
	 */
	public int getMaxSizeofQueue() {
		return maxSizeofQueue;
	}
	/**
	 * @param maxSizeofQueue the maxSizeofQueue to set
	 */
	public void setMaxSizeofQueue(int maxSizeofQueue) {
		this.maxSizeofQueue = maxSizeofQueue;
	}
	 
	 
	
	/**
	 * @return the openTime
	 */
	public String getOpenTime() {
		return openTime;
	}
	/**
	 * @param openTime the openTime to set
	 */
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	/**
	 * @return the closeTime
	 */
	public String getCloseTime() {
		return closeTime;
	}
	/**
	 * @param closeTime the closeTime to set
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	/**
	 * @return the coffeeType
	 */
	public Set<CoffeeType> getCoffeeType() {
		return coffeeType;
	}
	/**
	 * @param coffeeType the coffeeType to set
	 */
	public void setCoffeeType(Set<CoffeeType> coffeeType) {
		this.coffeeType = coffeeType;
	}
	/**
	 * @return the customerDetails
	 */
	public List<CustomerDetails> getCustomerDetails() {
		return customerDetails;
	}
	/**
	 * @param customerDetails the customerDetails to set
	 */
	public void setCustomerDetails(List<CustomerDetails> customerDetails) {
		this.customerDetails = customerDetails;
	}
	@Override
	public String toString() {
		return "OwnerDashboard [id=" + id + ", ownerUserName=" + ownerUserName + ", ownerAddress=" + ownerAddress
				+ ", coffeeType=" + coffeeType + ", noOfQueue=" + noOfQueue + ", maxSizeofQueue=" + maxSizeofQueue
				+ ", openTime=" + openTime + ", closeTime=" + closeTime + ", customerDetails=" + customerDetails + "]";
	}
	 
	 
	
	

}
