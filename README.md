# NatureChoiceEShopping
Online shopping with curbside pickup using Spring MVC

Case Study - Nature Choice Organic Foods

Project Overview:

⦁	This project  is a web application for online shopping of organic goods which offers curbside pickup.
⦁	You can register and login as User or Admin.
⦁	As an User you can buy products and add them to your cart.
⦁	As an admin you can access the order list and view the orders for a particular customer.
⦁	If the order is ready the admin sends an email to the customer along with a link which the customer will enter after reaching the store.
⦁	The link will ask for car details and parking slot number so that the store person can make the delivery. 
⦁	After successful delivery the admin will sent another email to the customer that the order has been delivered.The admin is also able to create new products and edit the existing products.
⦁	This project is unique because it has features added on it to make curbside pickup easier.
⦁	The target audience will be any small business owners who want to improve their business by providing  online shopping experience that offers curbside pickup so that the customers need not expose themselves to unsafe conditions.

Tools and Specifications:

⦁	I have used Spring MVC framework to build this web application using Core JAVA and Hibernate JPA.
⦁	I have used HTML ,CSS, JavaScript for creating the web pages.
⦁	I have tested the methods using JUnit.
⦁	I have used Tomcat server and Mariadb database.
⦁	I have used Spring Security and JAVA Mail API

USER STORIES:

User Story 1: 

The application has a home page where you can login as a user(customer) or admin.HOME PAGE: The home page has information about the shop and option to Login and register.

User Story 2:

As an user you will have access to Home, ProductList and My Cart and add products to the cart.
⦁	Product List: On clicking product list, all the available products will be listed and the user can buy the product he want.
⦁	Shopping cart: The user can add items to the shopping cart and he can also update the quantity of items.After adding all items to the cart the user can enter his info on clicking “Enter Customer Info”.
⦁	Entering Customer Information: The User can give his details like name, email, phone number, address etc here.Giving invalid information will throw errors.
⦁	Shopping cart confirmation: This page will have the customer information, cart summary and the user can also reserve date and time slot for curbside pickup.On clicking “Place your order”, your order will be placed.
⦁	Thank you for your order page will be displayed with the order number.

User Story 3:

 As an admin you will be able to access Order List and Create Product
⦁	After registering and logging as Admin, the account page will have more options in the menu like Order list and Create Product.
⦁	In “Create Product”, the admin can create new product and he can also “Edit Product”.
⦁	Order-list page : This page will list the orders and on clicking view order the admin can view the order details.

User Story 4:

 As an admin I should be able to send an email to the customer after the order is ready.
⦁	Order Details: The order details page will have customer information, order summary and the products ordered in the particular order. The admin will view the products ordered by the customer and he will pick those products from the store. Once he has picked all the products he will click the “Order Ready” button.
⦁	On clicking the Order Ready button an email will be sent to the customer that his order is ready along with a link where he can enter his car details and parking slot number.

User Story 5:

As an user/customer I should be able to give my car and parking details so that my order will be delivered to my car.
⦁	Parking Page: On clicking the link in the email the parking page opens up and the user can enter his car details and parking slot number and his details will be updated on clicking “create”.

User Story 6: 

As an admin, I should be able to view the parking slot details of the customer so that i can make the delivery
⦁	On clicking “View” parking slot in the order list page the admin can view the car details and the parking slot number of the user. He can then deliver the items to the car of the user.
⦁	The admin again clicks on the view order in the order list page as shown below
⦁	The admin then clicks on the “Delivered” in the order details page and another email is sent to the user that his order has been delivered.
⦁	That particular order is removed from the orderlist.
⦁	Delivered email sent to user.

Challenges faced:

⦁	I had faced some issues while implementing Spring Security. I had missed to include <security:authorize  access="hasAnyRole('ROLE_ADMIN')"> in the jsp page
⦁	I had some problems while inserting image in the Products table. I had to use LOAD_FILE('C:\\devips\\cherry.jpg')
⦁	I had some difficulties implementing the Java Mail API. I had include two JAR files for JAVA mail API.

Implementation

Pre-requisites: Tomcat server and MySQL/Maria DB.

SQL QUERIES

Create database "ecommerce" and Create the following tables in the same order. While inserting values for "IMAGE" in the product table please give the path where the image file is stored in your local. The images are stored in 'src/main/resources/ImagesForProducts'

CREATE DATABASE ecommerce;
USE ecommerce;
CREATE TABLE IF NOT EXISTS `products` (
  `Code` varchar(20) NOT NULL,
  `Create_Date` datetime NOT NULL,
  `Image` longblob DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into products (CODE, IMAGE, NAME, PRICE, CREATE_DATE)
values ('S001', LOAD_FILE('C:\\Users\\CTstudent\\eclipse-workspace\\NatureChoiceEShoppingNew\\src\\main\\resources\\ImagesForProducts\\cherry.jpg'), 'Organic cherries', 15, CURRENT_TIMESTAMP() );
insert into products (CODE, IMAGE, NAME, PRICE, CREATE_DATE)
values ('S002', LOAD_FILE('C:\\Users\\CTstudent\\eclipse-workspace\\NatureChoiceEShoppingNew\\src\\main\\resources\\ImagesForProducts\\egg.jpg'), 'Brown Eggs a dozen', 12, current_timestamp() );
 
insert into products (CODE, IMAGE, NAME, PRICE, CREATE_DATE)
values ('S003', LOAD_FILE('C:\\Users\\CTstudent\\eclipse-workspace\\NatureChoiceEShoppingNew\\src\\main\\resources\\ImagesForProducts\\honey.jpg'), 'Wild Raw Honey', 18, current_timestamp() );
 
insert into products (CODE, IMAGE, NAME, PRICE, CREATE_DATE)
values ('S004', LOAD_FILE('C:\\Users\\CTstudent\\eclipse-workspace\\NatureChoiceEShoppingNew\\src\\main\\resources\\ImagesForProducts\\soup.jpg'), 'Homemade Tomato Soup', 20, current_timestamp() );
 
insert into products (CODE, IMAGE, NAME, PRICE, CREATE_DATE)
values ('S005', LOAD_FILE('C:\\Users\\CTstudent\\eclipse-workspace\\NatureChoiceEShoppingNew\\src\\main\\resources\\ImagesForProducts\\wheatbread.jpg'), 'Wheat Bread', 10, current_timestamp() );

CREATE TABLE IF NOT EXISTS `parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(45) DEFAULT NULL,
  `reg_no` varchar(45) DEFAULT NULL,
  `owner_name` varchar(45) DEFAULT NULL,
  `slot` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `accounts` (
  `User_Name` varchar(20) CHARACTER SET utf32 NOT NULL,
  `Active` bit(1) NOT NULL,
  `Password` varchar(20) CHARACTER SET utf32 NOT NULL,
  `User_Role` varchar(20) CHARACTER SET utf32 NOT NULL,
  PRIMARY KEY (`User_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `orders` (
  `ID` varchar(50) NOT NULL,
  `Amount` double NOT NULL,
  `Customer_Address` varchar(255) CHARACTER SET utf32 NOT NULL,
  `Customer_Email` varchar(128) CHARACTER SET utf32 NOT NULL,
  `Customer_Name` varchar(255) CHARACTER SET utf32 NOT NULL,
  `Customer_Phone` varchar(128) CHARACTER SET utf32 NOT NULL,
  `Order_Date` datetime NOT NULL,
  `Order_Num` int(11) NOT NULL,
  `Curbside_Del` varchar(45) CHARACTER SET utf32 DEFAULT NULL,
  `PAYMENT_METHOD` varchar(45) CHARACTER SET utf32 DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_sxhpvsj665kmi4f7jdu9d2791` (`Order_Num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `order_details` (
  `ID` varchar(50) CHARACTER SET utf32 NOT NULL,
  `Amount` double NOT NULL,
  `Price` double NOT NULL,
  `Quanity` int(11) NOT NULL,
  `ORDER_ID` varchar(50) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `Slot_Date` varchar(45) CHARACTER SET utf32 DEFAULT NULL,
  `Slot_Time` varchar(45) CHARACTER SET utf32 DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDER_DETAIL_ORD_FK` (`ORDER_ID`),
  KEY `ORDER_DETAIL_PROD_FK` (`PRODUCT_ID`),
  CONSTRAINT `ORDER_DETAIL_ORD_FK` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ID`),
  CONSTRAINT `ORDER_DETAIL_PROD_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Before running the code change ur database username and password in the "ds-hibernate-cfg.properties" file under "src/main/resources"

Please dont forget to add the two JAR files, (activation-1.1.jar,mail-1.4.7.jar) for Java Mail API.
