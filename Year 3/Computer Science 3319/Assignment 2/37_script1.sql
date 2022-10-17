-- Creation of Database and Tables

-- List all the current databases owned by me
SHOW DATABASES;

-- Delete the database called 37_assign2db
DROP DATABASE IF EXISTS 37_assign2db;

-- Create a databasase called 37_assign2db
CREATE DATABASE 37_assign2db;

-- Connect to that database
USE 37_assign2db;

-- List all the tables
SHOW TABLES;

-- Create the tables
CREATE TABLE passenger (passengerID INT NOT NULL, firstName VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, PRIMARY KEY (passengerID));

CREATE TABLE passport (passportNum CHAR(4) NOT NULL, expiryDate DATE NOT NULL, countryOfCtz VARCHAR(30) NOT NULL, birthDate DATE NOT NULL, passengerID INT NOT NULL UNIQUE, PRIMARY KEY(passportNum), FOREIGN KEY(passengerID) REFERENCES passenger(passengerID) ON DELETE CASCADE);

CREATE TABLE bus (licenseNum CHAR(7) NOT NULL, capacity INT NOT NULL, PRIMARY KEY(licenseNum));

CREATE TABLE busTrip (tripID INT NOT NULL, startDate DATE NOT NULL, endDate DATE NOT NULL, country VARCHAR(30) NOT NULL, tripName VARCHAR(50) NOT NULL, licenseNum CHAR(7) NOT NULL, urlmage CHAR(255), PRIMARY KEY(tripID), FOREIGN KEY(licenseNum) REFERENCES bus(licenseNum));

CREATE TABLE booking (passengerID INT NOT NULL, tripID INT NOT NULL, pricePaid DECIMAL(10,2) NOT NULL, PRIMARY KEY(passengerID, tripID), FOREIGN KEY(tripID) REFERENCES busTrip(tripID), FOREIGN KEY(passengerID) REFERENCES passenger(passengerID) ON DELETE CASCADE);

-- List the tables again
SHOW TABLES;
