DROP DATABASE IF EXISTS SuperHeroSightings;

CREATE DATABASE IF NOT EXISTS SuperHeroSightings;


USE SuperHeroSightings;

CREATE TABLE IF NOT EXISTS `SuperPower` (
	`SuperPowerDbId` int(11) not null auto_increment,
    `SuperPowerId` varchar(11) not  null,
	`Description` varchar(40) not null,
    PRIMARY KEY (`SuperPowerId`)
);


CREATE TABLE IF NOT EXISTS `Address` (
	`AddressId` int(11) not null auto_increment,
	`StreetAddress` varchar(60) not null,
    `City` varchar(30) not null,
    `State` varchar(30) not null,
    `PostCode` varchar(10) not null,
	PRIMARY KEY (`AddressId`)
    );


CREATE TABLE IF NOT EXISTS `Contact` (
	`ContactId` int(11) not null auto_increment,
    `FirstName` varchar(30) not null,
    `LastName` varchar(30) null,
    `Phone` varchar(10) not null,
    `Email` varchar(80) null,
    `AddressId` int(11) null,
    PRIMARY KEY (`ContactId`)
);

ALTER TABLE `Contact`
 ADD CONSTRAINT `fkContactAddress` FOREIGN KEY (`AddressId`) REFERENCES `Address`
(`AddressId`) ON DELETE NO ACTION;


CREATE TABLE IF NOT EXISTS `Hero` (
	`HeroId` int(11) not null auto_increment,
	`SuperPowerId` int(11) not null,
	`ContactId` int(11) null,
    `Name` varchar(30) not null,
	`Description` varchar(30) null,
    PRIMARY KEY (`HeroId`)
);

ALTER TABLE `Hero`
 ADD CONSTRAINT `fkHeroSuperPower` FOREIGN KEY (`SuperPowerId`) REFERENCES `SuperPower`
(`SuperPowerId`) ON DELETE NO ACTION;  

ALTER TABLE `Hero`
 ADD CONSTRAINT `fkHeroContact` FOREIGN KEY (`ContactId`) REFERENCES `Contact`
(`ContactId`) ON DELETE NO ACTION;  


CREATE TABLE IF NOT EXISTS `Location` (
	`LocationId` int(11) not null auto_increment,
    `Name` varchar(30) not null,
	`Description` varchar(50) not null,
    `AddressId` int(11) null,
    `Latitude` double null,
    `Longitude` double null,
    PRIMARY KEY (`LocationId`)
);

ALTER TABLE `Location`
 ADD CONSTRAINT `fkLocationAddress` FOREIGN KEY (`AddressId`) REFERENCES `Address`
(`AddressId`) ON DELETE NO ACTION;



CREATE TABLE IF NOT EXISTS `Organization` (
	`OrganizationId` int(11) not null auto_increment,
    `Name` varchar(30) not null,
	`Description` varchar(50) null,
    `ContactId` int(11) null,
    `AddressId` int(11) null,
    PRIMARY KEY (`OrganizationId`)
);

ALTER TABLE `Organization`
 ADD CONSTRAINT `fkOrganizationContact` FOREIGN KEY (`ContactId`) REFERENCES `Contact`
(`ContactId`) ON DELETE NO ACTION;  

ALTER TABLE `Organization`
 ADD CONSTRAINT `fkOrganizationAddress` FOREIGN KEY (`AddressId`) REFERENCES `Address`
(`AddressId`) ON DELETE NO ACTION;


CREATE TABLE IF NOT EXISTS `HeroSuperPower` (
	`HeroId` int(11) not null,
    `SuperPowerId` int(11) not null,
    PRIMARY KEY ( `HeroId` , `SuperPowerId` )
);

ALTER TABLE `HeroSuperPower`
 ADD CONSTRAINT `fkHeroSuperPowerHero` FOREIGN KEY (`HeroId`) REFERENCES `Hero`
(`HeroId`) ON DELETE NO ACTION;  

ALTER TABLE `HeroSuperPower`
 ADD CONSTRAINT `fkHeroSuperPowerSuperPower` FOREIGN KEY (`SuperPowerId`) REFERENCES `SuperPower`
(`SuperPowerId`) ON DELETE NO ACTION;  

CREATE TABLE IF NOT EXISTS `OrgMembers` (
	`OrgMembersId` int(11) not null auto_increment,
    `OrganizationId` int(11) not null,
    `ContactId` int(11) not null,
    PRIMARY KEY (`OrgMembersId`)
);

ALTER TABLE `OrgMembers`
 ADD CONSTRAINT `fkOrgMembersOrganization` FOREIGN KEY (`OrganizationId`) REFERENCES `Organization`
(`OrganizationId`) ON DELETE NO ACTION;  

ALTER TABLE `OrgMembers`
 ADD CONSTRAINT `fkOrgMembersContact` FOREIGN KEY (`ContactId`) REFERENCES `Contact`
(`ContactId`) ON DELETE NO ACTION;  


CREATE TABLE IF NOT EXISTS `Sighting` (
	`SightingId` int(11) not null auto_increment,
	`HeroId` int(11) not null,
    `LocationId` int(11) not null,
    `Date` date not null,
	PRIMARY KEY (`SightingId`)
);

ALTER TABLE `Sighting`
 ADD CONSTRAINT `fkSightingHero` FOREIGN KEY (`HeroId`) REFERENCES `Hero`
(`HeroId`) ON DELETE NO ACTION;  

ALTER TABLE `Sighting`
 ADD CONSTRAINT `fkSightingLocation` FOREIGN KEY (`LocationId`) REFERENCES `Location`
(`LocationId`) ON DELETE NO ACTION;  


    