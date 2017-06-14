DROP  DATABASE IF EXISTS VendingMachine;

CREATE DATABASE IF NOT EXISTS VendingMachine;

USE VendingMachine;

CREATE TABLE IF NOT EXISTS items (
 item_id int(11) NOT NULL AUTO_INCREMENT,
 product_id varchar(10) NOT NULL,
 itemName varchar(20) NOT NULL,
 price varchar(10) NOT NULL,
 quantity int(2) NOT NULL,
 PRIMARY KEY (item_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

INSERT INTO items
(product_id, itemName, price, quantity )
values ("1", "Doritos", "1.25", 5 ),
("2", "Lays", "1.25", 7),
("3", "Snickers", "1.50", 2),
("4", "Milky Way","1.25", 1),
("5", "Three Musketeers", "1.50", 6),
("6", "Lays BBQ", "1.25", 3),
("7", "M&M Plain","1.75", 10),
("8", "Milk Duds", "1.65", 5),
("9", "M&M Peanut", "1.75", 5) ;