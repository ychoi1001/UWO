-- Insertion and Modification

-- List all the current databases owned by me
SHOW DATABASES;

-- Connect to that database
USE 37_assign2db;

-- List all the tables
SHOW TABLES;

-- prove the bus table is empty
SELECT * FROM bus;

-- load data into the bus table
LOAD DATA LOCAL INFILE 'bus_data.txt' INTO TABLE bus FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';

-- prove the bus table now has data
SELECT * FROM bus;

-- prove the passenger table is empty
SELECT * FROM passenger;

-- insert data
INSERT INTO passenger VALUES(11, "Homer", "Simpson");
INSERT INTO passenger VALUES(22, "Marge", "Simpson");
INSERT INTO passenger VALUES(33, "Bart", "Simpson");
INSERT INTO passenger VALUES(34, "Lisa", "Simpson");
INSERT INTO passenger VALUES(35, "Maggie", "Simpson");
INSERT INTO passenger VALUES(44, "Ned", "Flanders");
INSERT INTO passenger VALUES(45, "Todd", "Flanders");
INSERT INTO passenger VALUES(66, "Heidi", "Klum");
INSERT INTO passenger VALUES(77, "Michael", "Scott");
INSERT INTO passenger VALUES(78, "Dwight", "Schrute");
INSERT INTO passenger VALUES(79, "Pam", "Beesly");
INSERT INTO passenger VALUES(80, "Creed", "Bratton");
INSERT INTO passenger VALUES(99, "Beth", "Harmon");

-- prove the passenger table now has data
SELECT * FROM passenger;

-- prove the passport table is empty
SELECT * FROM passport;

-- insert data
INSERT INTO passport VALUES("US10", "2025-01-01", "USA", "1970-02-19", 11);
INSERT INTO passport VALUES("US12", "2025-01-01", "USA", "1972-08-12", 22);
INSERT INTO passport VALUES("US13", "2025-01-01", "USA", "2001-05-12", 33);
INSERT INTO passport VALUES("US14", "2025-01-01", "USA", "2004-03-19", 34);
INSERT INTO passport VALUES("US15", "2025-01-01", "USA", "2012-09-17", 35);
INSERT INTO passport VALUES("US22", "2030-04-04", "USA", "1950-06-11", 44);
INSERT INTO passport VALUES("US23", "2018-03-03", "USA", "1940-06-24", 45);
INSERT INTO passport VALUES("GE11", "2027-01-01", "Germany", "1970-07-12", 66);
INSERT INTO passport VALUES("US88", "2030-02-13", "Canada", "1979-04-25", 77);
INSERT INTO passport VALUES("US89", "2022-02-02", "Canada", "1976-04-08", 78);
INSERT INTO passport VALUES("US90", "2020-02-28", "Italy", "1980-04-04", 79);
INSERT INTO passport VALUES("US91", "2030-01-01", "Germany", "1959-02-02", 80);
INSERT INTO passport VALUES("US01", "2030-01-01", "USA", "1948-11-02", 99);

-- prove the passport table now has data
SELECT * FROM passport;

-- prove the busTip table is empty
SELECT * FROM busTrip;

-- insert data
INSERT INTO busTrip VALUES(1, "2022-01-01", "2022-01-17", "Germany", "Castles of Germany", "VAN1111", "");
INSERT INTO busTrip VALUES(2, "2022-03-03", "2022-03-14", "Italy", "Tuscany Sunsets", "TAXI222", '');
INSERT INTO busTrip VALUES(3, "2022-05-05", "2022-05-10", "USA", "California Wines", "VAN2222", "");
INSERT INTO busTrip VALUES(4, "2022-04-04", "2022-04-14", "Bermuda", "Beaches Galore", "TAXI222, """);
INSERT INTO busTrip VALUES(5, "2022-06-01", "2022-06-22", "Canada", "Cottage Country", "CAND123", "");
INSERT INTO busTrip VALUES(6, "2022-07-05", "2022-07-15", "Italy", "Arrivaderci Roma", "TAXI111", "");
INSERT INTO busTrip VALUES(7, "2022-09-09", "2022-09-29", "UK", "Shetland and Orkney", "TAXI111", "");
INSERT INTO busTrip VALUES(8, "2022-06-10", "2022-06-20", "USA", "Disney World and Sea World", "VAN2222", "");
INSERT INTO busTrip VALUES(9, "2022-12-20", "2022-12-27", "France", "Dream Trip", "VAN2222", "");
INSERT INTO busTrip VALUES(10, "2022-05-11", "2022-05-17", "Japan", "Nightmare Trip", "TAXI111", "");

-- prove the busTrip table now has data
SELECT * FROM busTrip;

-- prove the booking table is empty
SELECT * FROM booking;

-- insert data
-- All of the Simpson are going to do the Castles of Germany trip. The 2 adults paid 500 dollars each and 3 children paid 200 dollars each
INSERT INTO booking VALUES (11, 1, 500);
INSERT INTO booking VALUES (22, 1, 500);
INSERT INTO booking VALUES (33, 1, 200);
INSERT INTO booking VALUES (34, 1, 200);
INSERT INTO booking VALUES (35, 1, 200);

-- Heidi Klum is also doing the Castles of Germany trip.  She paid 600.99 dollars.
INSERT INTO booking VALUES (66, 1, 600.99);

-- Both of the Flanders are going to Disney World/Sea World.  Ned paid 400 dollars and Todd paid 200 dollars
INSERT INTO booking VALUES (44, 8, 400);
INSERT INTO booking VALUES (45, 8, 200);

-- Dwight and Creed are going Beaches Galore trip. They both paid 600 dollars.
INSERT INTO booking VALUES (78, 4, 600);
INSERT INTO booking VALUES (80, 4, 600);

-- Dwight is also doing the Castles of Germany trip. He paid 550 dollars
INSERT INTO booking VALUES (78, 1, 550);

-- Bart and Lisa Simpson are going to Disney World and Sea World. They both paid 300 dollars.
INSERT INTO booking VALUES (33, 8, 300);
INSERT INTO booking VALUES (34, 8, 300);

-- All of the Simpsons are doing the Arrivaderci Roma trip. The parents paid 600 dollars and each of the kids paid 100 dollars.
INSERT INTO booking VALUES (11, 6, 600);
INSERT INTO booking VALUES (22, 6, 600);
INSERT INTO booking VALUES (33, 6, 100);
INSERT INTO booking VALUES (34, 6, 100);
INSERT INTO booking VALUES (35, 6, 100);

-- Homer Simpson and Ned Flanders and Michael Scott are all doing the "Shetland Orkney" trip. Homer paid 300, Ned paid 400 and Michael paid 500 dollars
INSERT INTO booking VALUES (11, 7, 300);
INSERT INTO booking VALUES (44, 7, 400);
INSERT INTO booking VALUES (77, 7, 500);

-- TV character take your nightmare trip. You can decide on the cost (must be greater than 0)
INSERT INTO booking VALUES (99, 10, 250);

-- prove the booking table now has data
SELECT * FROM booking;

-- change the coutry of passport for Dwight Schrute to be Germany
-- show the table before updating
SELECT * FROM passport;

-- update the table
UPDATE passport SET countryOfCtz = "Germany" WHERE passengerID in (SELECT passengerID FROM passenger WHERE firstName = "Dwight" and lastName = "Schrute");

-- show the table after updating
SELECT * FROM passport;

-- Change any trip that is from the USA to use VAN1111
-- show the table before updating
SELECT * FROM busTrip;

-- update the table
UPDATE busTrip SET licenseNum = "VAN1111" WHERE country = "USA";

-- show the table after updating
SELECT * FROM busTrip;
