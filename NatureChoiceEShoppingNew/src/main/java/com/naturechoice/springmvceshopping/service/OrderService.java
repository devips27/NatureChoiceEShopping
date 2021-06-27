package com.naturechoice.springmvceshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.naturechoice.springmvceshopping.dao.OrderDAO;
import com.naturechoice.springmvceshopping.model.CartInfo;
import com.naturechoice.springmvceshopping.model.OrderDetailInfo;
import com.naturechoice.springmvceshopping.model.OrderInfo;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ParkingInfo;
@Component
public class OrderService {
	@Autowired
    private OrderDAO orderDAO;
	
	@Transactional
	public void saveOrder(CartInfo cartInfo,String date,String time) {
		orderDAO.saveOrder(cartInfo, date, time);
	}
	@Transactional 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage){
		return orderDAO.listOrderInfo(page, maxResult, maxNavigationPage);
	}
	@Transactional
    public OrderInfo getOrderInfo(String orderId)
    {
		return orderDAO.getOrderInfo(orderId);
    }
	@Transactional
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId)
    {
		return orderDAO.listOrderDetailInfos(orderId);
    }
	@Transactional
    public void saveParking(String orderId, String regNo, String ownerName, String slot)
    {
		orderDAO.saveParking(orderId, regNo, ownerName, slot);
    }
	@Transactional
    public List<ParkingInfo> listParkingDetailInfos(String orderId)
    {
		return orderDAO.listParkingDetailInfos(orderId);
    }

    public ParkingInfo getParkingInfo(String orderId)
    {
    	return orderDAO.getParkingInfo(orderId);
    }

    public void deleteOrderList(String orderId)
    {
    	orderDAO.deleteOrderList(orderId);
    }

}
