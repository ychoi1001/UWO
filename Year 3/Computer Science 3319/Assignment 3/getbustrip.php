<?php
/* 
Programmer Name: 37
Purpose: to get a bus trip
*/  
#create a query statement
$query = "SELECT * FROM busTrip";

#perform query
$result = mysqli_query($connection,$query);
if (!$result) {
  die("databases query failed.");
}

//fetches a result row
while ($row = mysqli_fetch_assoc($result))
{
	echo "<option value='".$row["tripID"]."'>".$row["tripName"]."</option>";
}
//frees the memory associated with the result.
mysqli_free_result($result); 
?>
