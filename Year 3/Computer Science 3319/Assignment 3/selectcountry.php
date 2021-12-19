<?php
/* 
Programmer Name: 37
Purpose: to show countries
*/  
//to show countries
$query = "SELECT DISTINCT(country) from busTrip";

// performs a query against a database.
$result = mysqli_query($connection,$query);
if (!$result) {
        die("databases query failed.");
}

//show country as a select option
while ($row = mysqli_fetch_assoc($result))
{
	echo "<option value='".$row["country"]."'>".$row["country"]."</option>";
}
mysqli_free_result($result); // Free result set
?>

