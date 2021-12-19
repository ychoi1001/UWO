<?php
/* 
Programmer Name: 37
Purpose: to delete a bus trip
*/  
include 'connectdb.php';

#get user input
$whichtrip = $_POST["pickbustrip3"];

#create a query statement
$query = 'DELETE FROM busTrip WHERE tripID ='.$whichtrip;

#echo $query ."<br>";
//perform query
$result = mysqli_query($connection,$query);

#to check if booking is selected
if($whichtrip != 0) {
  if (!$result) {
  die ("Trip has bookings, so it cannot be deleted: ". mysqli_error($connection));
  } else {
  echo "Deleted!";
  }
}
?>
