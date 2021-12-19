<?php
/* 
Programmer Name: 37
Purpose: to add booking using user inputs
*/  
include 'connectdb.php';

#get user input
$passengerid = $_POST["pickpassenger"];
$bustrip = $_POST["pickbustrip4"];
$price = $_POST["price"];

#echo $passengerName. "<br>";
#echo $bustrip. "<br>";
#echo $price. "<br>";

#create a query to insert new booking
$query= 'INSERT INTO booking VALUES (' . $passengerid. ',' . $bustrip . ', ' . $price . ')'; 
#echo $query . "<br>";

#to add new booking
if (!mysqli_query($connection,$query)) {
	die ("Error while trying to add new booking: ". mysqli_error($connection));
} else {
	echo "Added!";
}

?>
