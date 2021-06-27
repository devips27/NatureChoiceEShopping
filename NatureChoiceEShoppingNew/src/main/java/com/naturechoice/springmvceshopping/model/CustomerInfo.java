package com.naturechoice.springmvceshopping.model;
 
public class CustomerInfo {
 
    private String name;
    private String address;
    private String email;
    private String phone;
    
    private boolean valid;
    private String curbsideChecked;
    String paymentMethod;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public boolean isValid() {
        return valid;
    }
 
    public void setValid(boolean valid) {
        this.valid = valid;
    }

	public String getCurbsideChecked() {
		return curbsideChecked;
	}

	public void setCurbsideChecked(String curbsideChecked) {
		this.curbsideChecked = curbsideChecked;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}