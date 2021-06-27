package com.naturechoice.springmvceshopping.dao;
 
import java.util.List;

import com.naturechoice.springmvceshopping.model.CartInfo;
import com.naturechoice.springmvceshopping.model.OrderDetailInfo;
import com.naturechoice.springmvceshopping.model.OrderInfo;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ParkingInfo;
 
public interface OrderDAO {
 
    public void saveOrder(CartInfo cartInfo,String date,String time);
 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

    public void saveParking(String orderId, String regNo, String ownerName, String slot);

    public List<ParkingInfo> listParkingDetailInfos(String orderId);

    public ParkingInfo getParkingInfo(String orderId);

    public void deleteOrderList(String orderId);


 
}