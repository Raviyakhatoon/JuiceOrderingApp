package com.juice.entity;

public class customer {
	private String customerId;
	private String customerName;
	private String emailId;
	private  String password;
	
	private Long contactNo;
	private String address;
	private  String dropLocation;
	@Override
	public String toString() {
		return "customer [customerId=" + customerId + ", \ncustomerName=" + customerName + ", emailId=" + emailId
				+ ",\npassword=" + password + ",\n contactNo=" + contactNo + ",\n address=" + address + ",\ndropLocation="
				+ dropLocation +"]";
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passsword) {
		this.password = password;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	public customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public customer(String customerId, String customerName, String emailId, String password, Long contactNo,
			String address, String dropLocation) {
		//super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.password = password;
		this.contactNo = contactNo;
		this.address = address;
		this.dropLocation = dropLocation;
	}
	
	
	

}

