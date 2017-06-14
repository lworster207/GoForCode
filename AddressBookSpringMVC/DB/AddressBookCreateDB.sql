DROP DATABASE IF EXISTS AddressBook;

CREATE DATABASE IF NOT EXISTS address_book;


USE address_book;

CREATE TABLE IF NOT EXISTS AddressBook (
	addressbook_id int(11) not null auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
	street varchar(60) not null,
    city varchar(30) not null,
    state varchar(30) not null,
    post_code varchar(10) not null,
	PRIMARY KEY (addressbook_id)
    );
    