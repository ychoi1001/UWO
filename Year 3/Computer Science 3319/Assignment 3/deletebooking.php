<?php
/* 
Programmer Name: 37
Purpose: to delete a selected booking
*/  
include 'connectdb.php';

#get user input
$whichbooking = $_POST["pickbooking"];
#echo $whichbooking;

#split a string in different strings: passenger id and trip id
$pgid_tripid = explode("_", $whichbooking);
$pgid = $pgid_tripid[0];
$tripid = $pgid_tripid[1];

#create a query statement
$query = 'DELETE FROM booking WHERE tripID ='.$tripid. ' AND passengerID = '.$pgid;

#echo $query ."<br>";

#send error if something went wrong
if (!mysqli_query($connection,$query)) {
	die ("Error while trying to delete booking: ". mysqli_error($connection));
} else {
	echo "Deleted"; 
}
?>
