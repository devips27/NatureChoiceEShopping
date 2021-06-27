package com.naturechoice.springmvceshopping.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturechoice.springmvceshopping.dao.AccountDAO;
import com.naturechoice.springmvceshopping.dao.OrderDAO;
import com.naturechoice.springmvceshopping.dao.ProductDAO;
import com.naturechoice.springmvceshopping.impl.AccountDAOImpl;
import com.naturechoice.springmvceshopping.model.OrderDetailInfo;
import com.naturechoice.springmvceshopping.model.OrderInfo;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ParkingInfo;
import com.naturechoice.springmvceshopping.model.ProductInfo;
import com.naturechoice.springmvceshopping.service.AccountService;
import com.naturechoice.springmvceshopping.service.OrderService;
import com.naturechoice.springmvceshopping.service.ProductService;
import com.naturechoice.springmvceshopping.util.ConfirmationEmail;
import com.naturechoice.springmvceshopping.util.EmailDetails;
import com.naturechoice.springmvceshopping.util.SendEmail;
import com.naturechoice.springmvceshopping.validator.ProductInfoValidator;
import com.naturechoice.springmvceshopping.validator.UserDefinedException;
import com.sun.mail.util.MailLogger;
 
@Controller
// Enable Hibernate Transaction.
@Transactional

@EnableWebMvc
public class AdminController {
 
        
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ProductInfoValidator productInfoValidator;
    
    @Autowired
    private AccountService accountService;
 
    // Configured In ApplicationContextConfig.
    @Autowired
    private ResourceBundleMessageSource messageSource;
    
    String urlOrderId="";
    
    @InitBinder
  //It is used to register validators
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == ProductInfo.class) {
            dataBinder.setValidator(productInfoValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
    
    @RequestMapping(value = { "/ParkingLot" }, method = RequestMethod.GET)
    public String getParking(Model model, @RequestParam("orderId") String orderId) {
    	urlOrderId = orderId;
    	System.out.println("URL Order Id ::: "+orderId);
        return "Parking";
    }
    
    @RequestMapping(value = { "/ParkingLot" }, method = RequestMethod.POST)
    public String postParking(HttpServletRequest request) {
    	System.out.println("Registration Post Form ::: ");
    	try {
			System.out.println("Reg No ::: "+request.getParameter("reg_no"));
			System.out.println("Owner Name ::: "+request.getParameter("owner_name"));
			System.out.println("Slot Number ::: "+request.getParameter("slot"));
			orderService.saveParking(urlOrderId, request.getParameter("reg_no"), request.getParameter("owner_name"), request.getParameter("slot"));
		} catch (Exception e) {
			e.printStackTrace();
		}
   
    	 return "parkindex";
    }
    
    @RequestMapping(value = { "/Parking_View" }, method = RequestMethod.GET)
    public String parkingView(Model model, @RequestParam("orderId") String orderId) {
    	System.out.println("Coming inside parking View Method :::");
    	ParkingInfo parkingInfo = null;
    	if (orderId != null) {
    		parkingInfo = this.orderService.getParkingInfo(orderId);
        }
        if (parkingInfo == null) {
            return "redirect:/orderList";
        }
        List<ParkingInfo> details = this.orderService.listParkingDetailInfos(orderId);
        System.out.println("Parking details :: "+details.get(0).getReg_no());
        
        parkingInfo.setDetails(details);
 
        model.addAttribute("parkingInf", parkingInfo);
        System.out.println("Parking Info :: "+parkingInfo.getReg_no());
        return "ParkingView";
    }
    
 
    // GET: Show Login Page
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
    
    @RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
    public String registrationForm() {
    	System.out.println("Registration FOrm ::: ");
    	
        return "registration";
    }
    
    @RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
    public String registrationSave(HttpServletRequest request) {
    	System.out.println("Registration Post Form ::: ");
    
			System.out.println("User Name ::: "+request.getParameter("name"));
			System.out.println("Password ::: "+request.getParameter("psw"));
			System.out.println("Role ::: "+request.getParameter("role"));
			//Enabled Custom Exception
			if (request.getParameter("name").length()>10)
			{
				throw new UserDefinedException("Custom Exception has occured", "UserName length should be less than 10");
			}
			
			accountService.registerSave(request.getParameter("name"),request.getParameter("psw"),
							 request.getParameter("role"));
		
		
        return "login";
    }
 
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
 
    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, 
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        PaginationResult<OrderInfo> paginationResult 
        = orderService.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }
 
    // GET: Show product.
    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
 
        if (code != null && code.length() > 0) {
            productInfo = productService.findProductInfo(code);
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        model.addAttribute("productForm", productInfo);
        return "product";
    }
 
    // POST: Save product
    @RequestMapping(value = { "/product" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model, 
            @ModelAttribute("productForm") @Validated ProductInfo productInfo, 
            BindingResult result, 
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productService.save(productInfo);
        } catch (Exception e) {
           
            String message = e.getMessage();
            model.addAttribute("message", message);
             return "product";
 
        }
        return "redirect:/productList";
    }
    
    @RequestMapping(value = { "/productDelete" }, method = RequestMethod.GET)
    public  String productDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
    	
    	productService.deleteProduct(code);
    	 return "redirect:/productList";
    	
    }
    
    
    
    
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderService.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = this.orderService.listOrderDetailInfos(orderId);
        
        List<ParkingInfo> parkingDetails = this.orderService.listParkingDetailInfos(orderId);
        System.out.println("Parking details :: "+parkingDetails.size());
        orderInfo.setDetails(details);
        model.addAttribute("SIZE", parkingDetails.size());
        model.addAttribute("orderInfo", orderInfo);
        return "order";
    }
    
    @RequestMapping(value = { "/orderReady" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String orderStatusChange(HttpServletRequest request) {
    	System.out.println("emailId -- "+request.getParameter("mail"));
    	System.out.println("Id -- "+request.getParameter("id"));

    	String from = EmailDetails.from;
     	String pwd = EmailDetails.pwd;
    	
    	
    	String to= request.getParameter("mail");
    	String message = "Order Ready!! Choose Parking Lot";
	     try {
	    	 SendEmail.mailSend(from, pwd, to,message, request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return "redirect:/orderList";
    }
    
    @RequestMapping(value = { "/delivered" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String orderDelivered(HttpServletRequest request) {
    	System.out.println("emailId -- "+request.getParameter("mail"));
    	System.out.println("Id -- "+request.getParameter("id"));
 
    	String from = EmailDetails.from;
     	String pwd = EmailDetails.pwd;
    	String to= request.getParameter("mail");
    	String message = "Thanks for Purchasing!!! Have a Great Day";
    	String content = "Your package is delivered ............ Happy Shopping with us.";
	     try {
	    	 orderService.deleteOrderList(request.getParameter("id"));
	    	ConfirmationEmail.mailSend(from, pwd, to,message, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return "redirect:/orderList";
    }
    
}