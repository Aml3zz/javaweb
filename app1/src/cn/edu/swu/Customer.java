package cn.edu.swu;

public class Customer {
			private String name;
			private String address;
			private String payType;
			private String phone;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getPayType() {
				return payType;
			}
			public void setPayType(String payType) {
				this.payType = payType;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
			public Customer(String name, String address, String payType, String phone) {
				super();
				this.name = name;
				this.address = address;
				this.payType = payType;
				this.phone = phone;
			}
			
			public Customer() {
				super();
				// TODO Auto-generated constructor stub
			}		
}

