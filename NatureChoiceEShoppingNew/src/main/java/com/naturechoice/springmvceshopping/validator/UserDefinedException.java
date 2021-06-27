package com.naturechoice.springmvceshopping.validator;
//For Custom Exception
@SuppressWarnings("serial")
public class UserDefinedException extends RuntimeException
{
	String message;
	String name;
	
	public UserDefinedException(String message,String name)
	{
		this.message=message;
		this.name=name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}