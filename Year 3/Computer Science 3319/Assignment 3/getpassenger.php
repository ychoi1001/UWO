<?php
/* 
Programmer Name: 37
Purpose: to get a passenger
*/  
#creat a query statement
$query = "SELECT * FROM passenger";

#perform query
$result = mysqli_query($connection,$query);
if (!$result) {
        die("databases query failed.");
}

#fetches a result row - create a select
while ($row = mysqli_fetch_assoc($result))
{
	echo "<option value='".$row["passengerID"]."'>".$row["firstName"]. " ".$row["lastName"]. "</option>";
}
mysqli_free_result($result);
?>

