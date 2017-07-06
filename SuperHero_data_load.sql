USE SuperHeroSightings;

INSERT INTO Address(StreetAddress,City,State,PostCode)
values ("111 Elm Street", "New York", "New York", "12345"),
("222 Elm Street", "New York", "New York", "12345"),
("333 Elm Street", "New York", "New York", "12345"),
("444 Elm Street", "New York", "New York", "12345")
;

INSERT INTO Contact(FirstName,LastName,Phone,Email,AddressId)
values ("Peter", "Parker", "1112223333","spiderman@justiceleague.com", 1),
("Contact2", "Contact2", "1112223333","contact2@justiceleague.com", 2),
("Contact3", "Contact3", "1112223333","contact3@justiceleague.com", 3),
("Contact4", "Contact4", "1112223333","contact4@justiceleague.com", 4)
;



INSERT INTO Hero(`Name`,Description,ContactId)
values ( "Spider Man", "Hero1 Description",1 ),
( "Hero2", "Hero2 Description",2 ),
( "Hero3", "Hero3 Description",3),
( "Hero4", "Hero4 Description",3)
;


INSERT INTO SuperPower(Description)
values  ("Flight"), ("Invisibility"), ("X-Ray Vision"), ("Spider-Web"), ("Super Strength"), ("Elasticity"), ("Telekinesis");


INSERT INTO HeroSuperPower(HeroId,SuperPowerId)
values (1,3),(4,3),(4,4),(4,2);

INSERT INTO Organization(`Name`,Description)
values ("Org1","Org1 Description"),
("Org2","Org2 Description"),
("Org3","Org3 Description")
;

INSERT INTO OrgMembers(OrganizationId,HeroId)
values (1,1),(1,2),(1,3),(2,1),(2,2),(2,3);
