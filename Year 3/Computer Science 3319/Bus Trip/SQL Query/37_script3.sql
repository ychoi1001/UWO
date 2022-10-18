-- Queries

-- Show databases
SHOW DATABASES;

-- Connect to that database
USE 37_assign2db;

-- List all the tables
SHOW TABLES;

-- Query 1. Show the trip names of all the trips from Italy
SELECT tripName FROM busTrip WHERE country = "Italy";

-- Query 2. Show the last names of all passengers with no repeats
SELECT DISTINCT(lastName) FROM passenger;

-- Query 3. Show all the data in the bus trip table, but show them in order of their trip names from A to Z;
SELECT * FROM busTrip ORDER BY tripName;

-- Query 4. List the trip name and country and start date of all trips that start after April 30 of 2022.
SELECT tripName, country, startDate FROM busTrip WHERE startDate > "2022-04-30";

-- Query 5. List the first name, last name and birth date of all citizens of the USA. 
SELECT firstName, lastName, p.birthDate FROM passport p INNER JOIN passenger AS pg ON p.passengerID = pg.passengerID WHERE p.countryOfCtz = "USA";

-- Query 6. List the trip name and the number of seat available (Capacity of the assigned bus) for that trip for any trips starting between and including April 1, 2022 to Sept 1, 2022 (just check the starting dates, not the ending dates)
SELECT t.tripName, capacity FROM busTrip t INNER JOIN bus AS b ON t.licenseNum = b.licenseNum WHERE t.startDate >= "2022-04-01" AND t.startDate <= "2022-09-01";

-- Query 7. List all the data for both the passport and the passenger for any passenger whose passport has expired or will expire within 1 year from the current date. (Google the MySQL command's CURDATE()  and ADDDATE() to help you make this work no matter what date it is today).
SELECT * FROM passport INNER JOIN passenger ON passport.passengerID = passenger.passengerID WHERE passport.expiryDate < ADDDATE(CURDATE(), 365);

-- Query 8. List the first name, last name and bus trip name for any trips that anyone whose last name starts with S.
-- SELECT firstName, lastName, tripName FROM passenger INNER JOIN booking ON passenger.passengerID = booking.passengerID INNER JOIN busTrip ON busTrip.tripID = booking.tripID WHERE passenger.lastName LIKE "S%";
SELECT firstName, lastName, tripName FROM passenger p, busTrip bt, booking b WHERE (p.passengerID = b.passengerID AND bt.tripID = b.tripID AND lastName LIKE "S%");

-- Query 9. Count the number of passengers taking the Castles of Germany trip. List that total number of passengers and the trip name and trip id. 
SELECT COUNT(*) AS "# of Passengers Taking the Castles of Germany Trip", bt.tripName, bt.tripID FROM booking b, busTrip bt WHERE (b.tripID = bt.tripID AND bt.tripName = "Castles of Germany");

-- Query 10. List the countries that have trips and total amount of dollars brought in for each country. Each country name should just show up once along with the total.
SELECT country, SUM(pricePaid) FROM busTrip bt, booking b WHERE (bt.tripID = b.tripID) GROUP BY country;

-- Query 11. List the passengers first and last names, citizenship country and the trip name  and trip country name of any passengers taking trips in a country that they are NOT a citizen of.
SELECT firstName, lastName, countryOfCtz, tripName, country FROM passenger p, passport pp, busTrip bt, booking b WHERE (p.passengerID = pp.passengerID AND bt.tripID = b.tripID AND b.passengerID = pp.passengerID AND pp.countryOfCtz != bt.country);

-- Query 12. List the bus trip id and  bus trip name of all bus trips that do not have any passengers yet. 
SELECT tripID, tripName FROM busTrip WHERE tripID NOT IN (SELECT tripID FROM booking);

-- Query 13. Figure out which passenger is paying the most amount of money to our company (i.e. spending the most money in total on our trips). If there is a tie for top amount, list all the passengers paying that amount. List the first name, last name, country of citizenship and the total amount of money that passenger is spending. You might want to write a helper view and then a query on that view. (HARD - without using a view this took me a while (but you can use a view)!)
-- Create helper view
CREATE view helperView AS select firstName, lastName, countryOfCtz, SUM(pricePaid) AS totalPrice FROM passenger p, passport pp, booking b WHERE (p.passengerID = pp.passengerID AND p.passengerID = b.passengerID) GROUP BY b.passengerID ORDER BY SUM(pricePaid) DESC;

-- show helper view
SELECT * FROM helperView;

-- Figure out which passenger is paying the most amount of money to our company
SELECT firstName, lastName, countryOfCtz, totalPrice FROM helperView WHERE totalPrice = (SELECT MAX(totalPrice) FROM helperView);

-- Query 14. Find the names of any bus trips with bookings but with less than 4 people taking them. (Hint: you will have to use the key words Group By and Having)
SELECT tripName FROM busTrip bt, booking b WHERE (bt.tripID = b.tripID) GROUP BY b.tripID HAVING COUNT(b.tripID) < 4;

-- Query 15. We want to make sure that bus trips have enough seats. Write a query that figures out if any of the bus trips have too many passengers for the size of bus assigned to them.  Display the trip name, the number of passengers booked on it and the capacity and license plate number for the bus. Write the query so that the columns have the following neat titles "Bus Trip Name", "Current Number of Passengers", "Current Bus Assigned License Plate", "Capacity of Assigned Bus".
SELECT tripName AS "Bus Trip Name", COUNT(b.tripID) AS "Current Number of Passengers", bt.licenseNum AS "Current Bus Assigned License Plate", capacity AS "Capacity of Assigned Bus" FROM booking b, busTrip bt, bus WHERE (bus.licenseNum = bt.licenseNum AND b.tripID = bt.tripID) GROUP BY tripName;