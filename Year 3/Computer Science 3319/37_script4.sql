-- Deletions/Views

-- Show databases
SHOW DATABASES;

-- Connect to that database
USE 37_assign2db;

-- List all the tables
SHOW TABLES;

-- Create a view that shows the first name, last name, trip name, trip country and trip price for every passenger who taking a trip. 
CREATE VIEW v AS SELECT firstName, lastName, tripName, country, pricePaid FROM passenger p, busTrip bt, booking b WHERE (b.passengerID = p.passengerID AND b.tripID = bt.tripID);

-- Prove that your view works by selecting all the rows from it.
SELECT * FROM v;

-- Run your view again but this time just select all the columns from the view but only the rows where the trip has a trip name that will take you to a place involving "Castles" . Order the rows in ascending order by the price the passengers paid starting at the lowest price.  
SELECT * FROM v WHERE tripName LIKE "%Castles%" ORDER BY pricePaid ASC;

-- Show all the bus table information
SELECT * FROM bus;

-- Delete any bus whose license plate contains "UWO" in it.
DELETE FROM bus WHERE licenseNum LIKE "%UWO%";

-- Show all the bus information again to make sure it was remove
SELECT * FROM bus;

-- Show all the data from passport table
SELECT * FROM passport;

-- Show all the data from the passenger table
SELECT * FROM passenger;

-- Delete all the passport from Canada
DELETE FROM passport WHERE countryOfCtz = "Canada";

-- Show all the data from passport table
SELECT * FROM passport;

-- Show all the data from the passenger table
SELECT * FROM passenger;

-- Put a comment (-- the reason why ...) in your script file to explain which tables were affected and why
-- When passport gets deleted, the corresponding passenger doesn not get deleted because ON DELETE CASCADE allows us to delete data from child tables automatically when we delete the data from the parent table. Since passport table has a foreign key "passengerID", passport table is the child table, and "passengerID" in passenger table is the primary key. Therefore, passenger table is the parent table. So, deleting any passport does not affect the passenger table.

-- Show everything in the bustrip table. 
SELECT * FROM busTrip;

-- Delete the California Wines trip
DELETE FROM busTrip WHERE tripName = "California Wines";

-- Show everything in the bustrip table again to make sure it was deleted.
SELECT * FROM busTrip;

-- Try to delete the Arrivaderci Roma trip
DELETE FROM busTrip WHERE tripName = "Arrivaderci Roma";

-- Put a comment ( -- reason why ..) in your script file to explain why this trip could not be deleted.
-- Because of a foreign key contraint, it fails to delete certain rows. booking table and busTrip table have a relationship. booking table has tripID field as its primary key and foreign key. busTrip table has tripID as its primary key. Therefore, when I try to delete a bustrip where tripName is Arrivaderci Roma, it occurs an error since the busTrip still has bookings for that trip. One of the examples that causes an error to occur if the busTrip still has any bookings so that passengers money can be returned is "All of the Simpsons are doing the Arrivaderci Roma trip"

-- Show everything in the passenger table
SELECT * FROM passenger;

-- Delete anyone with the first name Pam
DELETE FROM passenger WHERE firstName = "Pam";

-- Show everything in passenger table again to make sure she was deleted.
SELECT * FROM passenger;

-- Select everything from the view your created for this fourth script from the passenger table (you cannot delete from views, they just display data but don't actually hold the data)
SELECT * FROM v;

-- Delete anyone with the last name Simpson.
DELETE FROM passenger WHERE lastName = "Simpson";

-- Select everything from that view again and make sure the bookings for the Simpsons disappeared when they were deleted.
SELECT * FROM v;
