package com.naturechoice.springmvceshopping.dao;
 
import com.naturechoice.springmvceshopping.entity.Product;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ProductInfo;
 
public interface ProductDAO {
 
    
    
    public Product findProduct(String code);
    
    public ProductInfo findProductInfo(String code) ;
  
    
    public PaginationResult<ProductInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(ProductInfo productInfo);
    public void deleteProduct(String code);
    
}