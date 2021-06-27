package com.naturechoice.springmvceshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.naturechoice.springmvceshopping.dao.ProductDAO;
import com.naturechoice.springmvceshopping.entity.Product;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ProductInfo;


@Component
public class ProductService {
	
	@Autowired
    private ProductDAO productDAO;
	@Transactional
	public Product findProduct(String code) {
	return productDAO.findProduct(code);
	}
	@Transactional
	public ProductInfo findProductInfo(String code) {
		return productDAO.findProductInfo(code);
	}
  
	@Transactional
    public PaginationResult<ProductInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  )
    {
    	return productDAO.queryProducts(page, maxResult, maxNavigationPage);
    }
	@Transactional
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName)
    {
    	return productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
    }
	@Transactional
    public void save(ProductInfo productInfo)
    {
    	productDAO.save(productInfo);
    }
	@Transactional
	public void deleteProduct(String code) {
		productDAO.deleteProduct(code);
	}
}
