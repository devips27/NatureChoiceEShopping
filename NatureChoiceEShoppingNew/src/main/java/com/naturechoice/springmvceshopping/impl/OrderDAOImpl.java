package com.naturechoice.springmvceshopping.impl;
 
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.naturechoice.springmvceshopping.dao.OrderDAO;
import com.naturechoice.springmvceshopping.dao.ProductDAO;

import com.naturechoice.springmvceshopping.entity.Order;
import com.naturechoice.springmvceshopping.entity.OrderDetail;
import com.naturechoice.springmvceshopping.entity.Parking;
import com.naturechoice.springmvceshopping.entity.Product;
import com.naturechoice.springmvceshopping.model.CartInfo;
import com.naturechoice.springmvceshopping.model.CartLineInfo;
import com.naturechoice.springmvceshopping.model.CustomerInfo;
import com.naturechoice.springmvceshopping.model.OrderDetailInfo;
import com.naturechoice.springmvceshopping.model.OrderInfo;
import com.naturechoice.springmvceshopping.model.PaginationResult;
import com.naturechoice.springmvceshopping.model.ParkingInfo;
 
@Transactional
public class OrderDAOImpl implements OrderDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductDAO productDAO;
    
    private int getMaxOrderNum() {
        String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }
 
    @Override
    public void saveOrder(CartInfo cartInfo,String date,String time) {
        Session session = sessionFactory.getCurrentSession();
 
        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();
 
        order.setId(UUID.randomUUID().toString());
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());
        order.setCustomerAddress(customerInfo.getAddress());
        order.setCurbsideChecked(customerInfo.getCurbsideChecked());
        order.setPaymentMethod(customerInfo.getPaymentMethod());
 
        session.persist(order);
 
        List<CartLineInfo> lines = cartInfo.getCartLines();
 
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
            detail.setDate(date);
            detail.setTime(time);
 
            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);
 
            session.persist(detail);
        }
 
        cartInfo.setOrderNum(orderNum);
    }
 
    // @page = 1, 2, ...
    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone,ord.curbsideChecked,ord.paymentMethod) " + " from "
                + Order.class.getName() + " ord "
                + " order by ord.orderNum desc";
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
 
        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }
 
    @Override
    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(), 
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), 
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone(),
                order.getCurbsideChecked(),order.getPaymentMethod());
    }
 
    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrderDetailInfo.class.getName() 
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "
                + " from " + OrderDetail.class.getName() + " d "
                + " where d.order.id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return query.list();
    }
    
    @Override
    public void saveParking(String orderId,String regNo,String ownerName,String slot){
    	try {
    		System.out.println("Coming Inside the Save Parking :::: ");
    		Session session = sessionFactory.getCurrentSession();
    		Parking parking = new Parking();
    		parking.setOrder_id(orderId);
    		parking.setReg_no(regNo);
    		parking.setOwner_name(ownerName);
    		parking.setSlot(slot);
    		session.persist(parking);
    		System.out.println("Coming Inside the Save Parking :::: 1");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public List<ParkingInfo> listParkingDetailInfos(String orderId) {
        String sql = "Select new " + ParkingInfo.class.getName() //
                + "(d.reg_no, d.owner_name, d.slot) "//
                + " from " + Parking.class.getName() + " d "//
                + " where d.order_id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return query.list();
    }
    
    @Override
    public ParkingInfo getParkingInfo(String orderId) {
    	Parking order = this.findOrders(orderId);
        if (order == null) {
            return null;
        }
        return new ParkingInfo(order.getReg_no(),order.getOwner_name(),order.getSlot());
    }
    
    public Parking findOrders(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Parking.class);
        crit.add(Restrictions.eq("order_id", orderId));
        return (Parking) crit.uniqueResult();
    }
 
    @Override
    public void deleteOrderList(String orderId){
    	try {
    		Session session = sessionFactory.getCurrentSession();
    		System.out.println("Coming Inside the deleteOrderList ");
    		Query query = session.createQuery("delete "+OrderDetail.class.getName()+" where ORDER_ID = :ordId");
    		query.setParameter("ordId", orderId);
    		 
    		int result = query.executeUpdate();
    		 
    		if (result > 0) {
    		    
    		    Query query1 = session.createQuery("delete "+Order.class.getName()+" where ID = :ORDERID");
    		    query1.setParameter("ORDERID", orderId);
        		 
        		int results = query1.executeUpdate();
        		
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}