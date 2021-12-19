<?php
/* 
Programmer Name: 37
Purpose: to show booking
*/  
//get selected passenger
$passengerid = $_POST["pickpassenger1"]; 

//create a select statement
$query = "SELECT tripName, firstName, lastName FROM busTrip, passenger, booking  WHERE passenger.passengerID = booking.passengerID AND booking.tripID = busTrip.tripID AND booking.passengerID=" . $passengerid; //fill in with correct query
#echo "<br>" .$query . "<br>";

//performs a query against a database.
$result = mysqli_query($connection, $query);

if (!$result) {
        die("databases query on art pieces failed. ");
}

//$row = mysqli_fetch_assoc($result);
//echo $row["firstName"]. " " .$row["lastName"];
//show each booking as a list
echo '<ul class="w3-ul w3-border">'; 
while ($row = mysqli_fetch_assoc($result)) {
	echo "<li>" . $row["tripName"] . "</li>";
}
echo "</ul>"; //end the bulleted list
mysqli_free_result($result);
?>
