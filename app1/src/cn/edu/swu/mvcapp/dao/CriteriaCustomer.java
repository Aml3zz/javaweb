package cn.edu.swu.mvcapp.dao;

public class CriteriaCustomer {

	private String nameString;
	private String addressString;
	private String phoneString;
	
	
	public String getNameString() {
		if(nameString == null)
			nameString = "%%";
		else {
			nameString = "%" + nameString + "%";
		}
		
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getAddressString() {
		if(addressString == null)
			addressString = "%%";
		else {
			addressString = "%" + addressString + "%";
		}
		
		return addressString;
	}
	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}
	public String getPhoneString() {
		if(phoneString == null)
			phoneString = "%%";
		else {
			phoneString = "%" + phoneString+ "%";
		}
		
		return phoneString;
	}
	public void setPhoneString(String phoneString) {
		this.phoneString = phoneString;
	}
	public CriteriaCustomer(String nameString, String addressString, String phoneString) {
		super();
		this.nameString = nameString;
		this.addressString = addressString;
		this.phoneString = phoneString;
	}
	
	
	
}

