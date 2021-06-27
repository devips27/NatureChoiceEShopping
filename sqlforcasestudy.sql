CREATE DATABASE chkdb;
USE chkdb;
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
