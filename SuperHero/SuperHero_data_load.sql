USE SuperHeroSightings;

INSERT INTO SuperPower(Description)
values  ("Flight"), ("Invisibility"), ("X-Ray Vision"), ("Spider-Web"), ("Super Strength"), ("Elasticity"), ("Telekinesis");

INSERT INTO HeroSuperPower(HeroId,SuperPowerId)
values (2,3),(4,3),(4,4),(4,2);

INSERT INTO Address(StreetAddress,City,State,PostCode)
values ("123 Elm Street", "New York", "New York", "12345");

INSERT INTO Contact(FirstName,LastName,Phone,Email,AddressId)
values ("Peter", "Parker", "1112223333","spiderman@justiceleague.com", "1");

UPDATE Hero
set ContactId = "1"
where HeroId = "4"

SELECT h.ContactId,h.Name as "HeroName",h.Description as "HeroDescription",sp.Description as "SuperPower" FROM Hero h
join HeroSuperPower
on HeroSuperPower.HeroId = h.HeroId
join SuperPower sp
on sp.SuperPowerId=HeroSuperPower.SuperPowerId

join Contact
on Hero.ContactId = Contact.ContactId
join Address
on Address.AddressId = Contact.AddressId




