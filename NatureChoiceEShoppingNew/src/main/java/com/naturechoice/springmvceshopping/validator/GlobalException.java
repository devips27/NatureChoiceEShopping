package com.naturechoice.springmvceshopping.validator;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
//For custom exception
@ControllerAdvice
public class GlobalException 
{
	
	@ExceptionHandler(UserDefinedException.class)
	public ModelAndView  processCustomException(UserDefinedException ud)
	{
		ModelAndView mav = new ModelAndView("exceptionPage");
		 mav.addObject("name", ud.getName());
	     mav.addObject("message", ud.getMessage());
	 
	     return mav;
	}	
}