
DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE IF NOT EXISTS HotelReservation;


USE HotelReservation;


CREATE TABLE IF NOT EXISTS `BedType` (
	`BedTypeID` int(11) not null auto_increment,
	`BedDescription` varchar(30) not null,
    PRIMARY KEY (`BedTypeID`)
);

INSERT INTO BedType
values (1,"Double"), 
(2,"Queen"),
(3,"King"),
(4,"King Water");

CREATE TABLE IF NOT EXISTS `Address` (
	`AddressID` int(11) not null auto_increment,
	`StreetAddress` varchar(60) not null,
    `City` varchar(30) not null,
    `State` varchar(30) not null,
    `PostCode` varchar(10) not null,
	PRIMARY KEY (`AddressID`)
    );
    

CREATE TABLE IF NOT EXISTS `Customer` (
	`CustomerID` int(11) not null auto_increment,
	`AddressID` int(11) not null,
    `FirstName` varchar(30) not null,
    `LastName` varchar(30) not null,
    `Phone` varchar(10) not null,
    `Email` varchar(80) null,
    PRIMARY KEY (`CustomerID`)
    );
  
ALTER TABLE `Customer`
 ADD CONSTRAINT `fkCustomerAddress` FOREIGN KEY (`AddressID`) REFERENCES `Address`
(`AddressID`) ON DELETE NO ACTION;  

CREATE TABLE IF NOT EXISTS `PromotionType` (
	`PromotionTypeID` int(11) not null auto_increment,
    `Description` varchar(10) not null,
    `Discount` double not null,
    PRIMARY KEY (`PromotionTypeID`)
    ); 

INSERT INTO PromotionType
values
(1,"Flat",20.00),
(2,"Percentage",10.00);

 
CREATE TABLE IF NOT EXISTS `Promotion` (
	`PromotionID` int(11) not null auto_increment,
    `PromotionTypeID` int(11) not null,
	`Code` varchar(30) not null,
    `StartDate` date not null,
    `EndDate` date not null,
    PRIMARY KEY (`PromotionID`)
    ); 
 
ALTER TABLE `Promotion`
 ADD CONSTRAINT `fkPromotionPromotionType` FOREIGN KEY (`PromotionTypeID`) REFERENCES `PromotionType`
(`PromotionTypeID`) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS `AmenitiesPackage` (
	`AmenitiesPackageID` int(11) not null auto_increment,
	`AmenitiesPackageDescription` varchar(40) not null,
    `BedTypeID1` int(11) null,
    `BedTypeID2` int(11) null,
    `Refrigerator` char(1) null,
    `HotTub` char(1) null,
    `Microwave` char(1) null,
    `FirePlace` char(1) null,
    `BusinessAudioVideo` char(1) null,
    `WetBar` char(1) null,
	PRIMARY KEY (`AmenitiesPackageID`)
    ); 

ALTER TABLE `AmenitiesPackage`
 ADD CONSTRAINT `fkAmenitiesPackageBedType1` FOREIGN KEY (`BedTypeID1`) REFERENCES `BedType`
(`BedTypeID`) ON DELETE NO ACTION;

ALTER TABLE `AmenitiesPackage`
 ADD CONSTRAINT `fkAmenitiesPackageBedType2` FOREIGN KEY (`BedTypeID2`) REFERENCES `BedType`
(`BedTypeID`) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS `RoomType` (
	`RoomTypeID` int(11) not null auto_increment,
	`Description` varchar(40) not null,
	`AmenitiesPackageID` int(11) not null,
    PRIMARY KEY (`RoomTypeID`)
    );    
 
ALTER TABLE `RoomType`
 ADD CONSTRAINT `fkRoomTypeAmenitiesPackage` FOREIGN KEY (`AmenitiesPackageID`) REFERENCES `AmenitiesPackage`
(`AmenitiesPackageID`) ON DELETE NO ACTION;


CREATE TABLE IF NOT EXISTS `Room` (
	`RoomID` int(11) not null auto_increment,
	`RoomTypeID` int(11) not null,
    `Floor` int(3) not null,
    `Number` int(4) not null,
    `MaxOccupancy` int(3) not null,
    PRIMARY KEY (`RoomID`)
    );

ALTER TABLE `Room`
 ADD CONSTRAINT `fkRoomRoomType` FOREIGN KEY (`RoomTypeID`) REFERENCES `RoomType`
(`RoomTypeID`) ON DELETE NO ACTION;   
    
CREATE TABLE IF NOT EXISTS `Reservation` (
	`ReservationID` int(11) not null auto_increment,
    `CustomerID` int(11) not null,
    `PromotionID` int(11) null,
    PRIMARY KEY (`ReservationID`)
    );  
ALTER TABLE `Reservation`
 ADD CONSTRAINT `fkReservationCustomer` FOREIGN KEY (`CustomerID`) REFERENCES `Customer`
(`CustomerID`) ON DELETE NO ACTION;   
ALTER TABLE `Reservation`
 ADD CONSTRAINT `fkReservationPromotion` FOREIGN KEY (`PromotionID`) REFERENCES `Promotion`
(`PromotionID`) ON DELETE NO ACTION; 

CREATE TABLE IF NOT EXISTS `Booking` (
	`BookingID` int(11) not null auto_increment,
	`ReservationID` int(11) not null,
    `RoomID` int(11) not null,
    `StartDate` date not null,
    `EndDate` date not null,
    PRIMARY KEY (`BookingID`)
    );
    
ALTER TABLE `Booking`
 ADD CONSTRAINT `fkBookingReservation` FOREIGN KEY (`ReservationID`) REFERENCES `Reservation`
(`ReservationID`) ON DELETE NO ACTION;
ALTER TABLE `Booking`
 ADD CONSTRAINT `fkBookingRoom` FOREIGN KEY (`RoomID`) REFERENCES `Room`
(`RoomID`) ON DELETE NO ACTION;


CREATE TABLE IF NOT EXISTS `Guest` (
	`GuestID` int(11) not null auto_increment,
	`BookingID` int(11) not null,
    `FirstName` varchar(30) not null,
    `LastName` varchar(30) not null,
    `Age` int(3) not null,
    PRIMARY KEY (`GuestID`)
    );
    
ALTER TABLE `Guest`
 ADD CONSTRAINT `fkGuestBooking` FOREIGN KEY (`BookingID`) REFERENCES `Booking`
(`BookingID`) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS `Pricing` (
	`PricingID` int(11) not null auto_increment,
    `Date` date not null,
	`RoomTypeID` int(11) not null,
    `Movie` double not null,
    `Game` double not null,
    PRIMARY KEY (`PricingID`)
    );
    
ALTER TABLE `Pricing`
 ADD CONSTRAINT `fkPricingRoomType` FOREIGN KEY (`RoomTypeID`) REFERENCES `RoomType`
(`RoomTypeID`) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS `AddOns` (
	`AddOnsID` int(11) not null auto_increment,
	`BookingID` int(11) not null,
    `Date` date not null,
	`Description` varchar(40) not null,
    `Price` double not null,
    PRIMARY KEY (`AddOnsID`)
    );
    
ALTER TABLE `AddOns`
 ADD CONSTRAINT `fkAddOnsBookingID` FOREIGN KEY (`BookingID`) REFERENCES `Booking`
(`BookingID`) ON DELETE NO ACTION;    
    
