package com.naturechoice.springmvceshopping.model;

import java.util.List;

public class ParkingInfo {
	
	private int id;
	 private String order_id;
	 private String reg_no;
	 private String owner_name;
	 private String slot;
	 private List<ParkingInfo> details;
	 
	 public ParkingInfo() {
		 
	    }
	 
	   
	    public ParkingInfo(String reg_no,
	            String owner_name, String slot) {
	        this.reg_no = reg_no;
	        this.owner_name = owner_name;
	        this.slot = slot;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getOrder_id() {
			return order_id;
		}

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}

		public String getReg_no() {
			return reg_no;
		}

		public void setReg_no(String reg_no) {
			this.reg_no = reg_no;
		}

		public String getOwner_name() {
			return owner_name;
		}

		public void setOwner_name(String owner_name) {
			this.owner_name = owner_name;
		}

		public String getSlot() {
			return slot;
		}

		public void setSlot(String slot) {
			this.slot = slot;
		}

		public List<ParkingInfo> getDetails() {
			return details;
		}

		public void setDetails(List<ParkingInfo> details) {
			this.details = details;
		}

}
