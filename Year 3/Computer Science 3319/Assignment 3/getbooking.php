<?php
/* 
Programmer Name: 37
Purpose: to get a booking
*/  
#create a query statement
$query = "SELECT b.passengerID, b.tripID, firstName, lastName, tripName, pricePaid FROM passenger pg, busTrip bt, booking b WHERE pg.passengerID = b.passengerID AND bt.tripID = b.tripID";

#perform query
$result = mysqli_query($connection,$query);

#check database query is not failed or not
if (!$result) {
        die("databases query failed.");
}

#fetch data as a select option
while ($row = mysqli_fetch_assoc($result))
{
	echo "<option value='".$row["passengerID"]."_".$row["tripID"] ."'>".$row["firstName"]. " ".$row["lastName"]. " : " .$row["tripName"]. " - " .$row["pricePaid"]. "</option>";
}

//frees the memory associated with the result.
mysqli_free_result($result);
?>

