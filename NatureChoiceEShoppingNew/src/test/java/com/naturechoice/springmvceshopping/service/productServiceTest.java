package com.naturechoice.springmvceshopping.service;


	import org.junit.Assert;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	
	import org.junit.FixMethodOrder;
	import org.junit.runners.MethodSorters;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
	import org.springframework.test.context.web.WebAppConfiguration;

	import com.naturechoice.springmvceshopping.config.ApplicationContextConfig;

	import com.naturechoice.springmvceshopping.entity.Product;

	import com.naturechoice.springmvceshopping.model.ProductInfo;
	
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	@WebAppConfiguration 
	@ContextConfiguration(classes=ApplicationContextConfig.class)
	@RunWith(SpringJUnit4ClassRunner.class)

	public class productServiceTest {
		
		    @Autowired
		    ProductService productService;
		    
		    @Test
		    
		    public void testAFindByCode(){
		    	Product acc = productService.findProduct("S004");
		    	System.out.println("ACC +====== "+acc.getName());
		        Assert.assertEquals("Homemade Tomato Soup", acc.getName());
		    }

		    
		    @Test
		    
		    public void testBFindByCodeInfo(){
		    	ProductInfo acc = productService.findProductInfo("S001");
		    	System.out.println("ACC +====== "+acc.getPrice());
		        Assert.assertEquals("13.0",String.valueOf(acc.getPrice()));
		    }
		    
		    
		    
		    @Test
		  
		    public void testCRegisterSave(){
		    	ProductInfo productInfo = new ProductInfo();
		    	productInfo.setCode("S007");
		    	productInfo.setName("Corn");
		    	productInfo.setPrice(3.00);
		    	productService.save(productInfo);
		    	Assert.assertEquals("Corn",productInfo.getName());
		    }
		    @Test
		 
		    public void testDUpdate(){
		    	ProductInfo productInfo = new ProductInfo();
		    	productInfo.setCode("S007");
		    	productInfo.setName("Cornflakes");
		    	productService.save(productInfo);
		        Assert.assertEquals("Cornflakes",productInfo.getName());
	    }
		    
		    @Test
		    
		    public void testEdeleteProduct() {
		    	productService.deleteProduct("S007");
		    	Assert.assertNull(null, productService.findProduct("S007"));
		    }
	

}
