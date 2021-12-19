<?php
/* 
Programmer Name: 37
Purpose: to get passenger info
*/  
#create a query statement
$query = "SELECT pg.passengerID, firstName, lastName, passportNum, expiryDate, countryOfCtz, birthDate FROM passenger pg, passport pp WHERE pg.passengerID = pp.passengerID ORDER BY pg.lastName";

#performs a query against a database
$result = mysqli_query($connection, $query);

if(!$result){
  die("query failed");
}

#create a table
echo "<table> <tr> <th>Passenger ID</th> <th>First Name</th> <th>Last Name</th> <th>Passport Number</th> <th>Expiry Date</th> <th>Citizenship</th> <th>Birth Date</th> </tr>";

#fetches a result row
while($row = mysqli_fetch_assoc($result)){
  echo '<tr>';
  echo '<td>' .$row["passengerID"] .'</td>';
  echo '<td>' .$row["firstName"] .'</td>';
  echo '<td>' .$row["lastName"] .'</td>';
  echo '<td>' .$row["passportNum"] .'</td>';
  echo '<td>' .$row["expiryDate"] .'</td>';
  echo '<td>' .$row["countryOfCtz"] .'</td>';
  echo '<td>' .$row["birthDate"] .'</td>';
  echo '</tr>';
}
mysqli_free_result($result);
echo "</table>"; //finish creating table
?>
