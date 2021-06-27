package com.naturechoice.springmvceshopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking")
public class Parking implements Serializable {
	
	 private static final long serialVersionUID = 7550745928843183535L;
	 
	 private int id;
	 private String order_id;
	 private String reg_no;
	 private String owner_name;
	 private String slot;
	 
	 @Id
	 @Column(name = "id", length = 50, nullable = false)
	 public int getId() {
	     return id;
	 }
	 
	 public void setId(int id) {
		 this.id = id;
	 }
	 
	 @Column(name = "order_id", nullable = false) 
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@Column(name = "reg_no", nullable = false)
	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	
	@Column(name = "owner_name", nullable = false)
	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	@Column(name = "slot", nullable = false)
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}
	 
}
