<?php
/* 
Programmer Name: 37
Purpose: to get bus trips that do not have any bookings
*/  
#create a query statement
$query = "SELECT * FROM busTrip WHERE tripID NOT IN (SELECT tripID FROM booking)";

#performs query 
$result = mysqli_query($connection, $query);

if(!$result){
	die("query failed");
}

#create a table
echo "<table> <tr> <th>tripID</th> <th>Start Date</th> <th>End Date</th> <th>Country</th> <th>Trip Name</th> <th>License Number</th> <th>Image URL</th> </tr>";

#fetches a result row
while($row = mysqli_fetch_assoc($result)){
  echo '<tr>';
  echo '<td>' .$row["tripID"] .'</td>';
  echo '<td>' .$row["startDate"] .'</td>';
  echo '<td>' .$row["endDate"] .'</td>';
  echo '<td>' .$row["country"] .'</td>';
  echo '<td>' .$row["tripName"] .'</td>';
  echo '<td>' .$row["licenseNum"] .'</td>';
  echo '<td>' .$row["urlmage"] .'</td>';
  echo '</tr>';
}
mysqli_free_result($result);
echo "</table>"; //finish creating a table
?>

