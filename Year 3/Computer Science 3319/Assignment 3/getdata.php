<?php
/* 
Programmer Name: 37
Purpose: to get bus trip data
*/  
include 'connectdb.php';

//create a table
echo "<table> <tr> <th>Trip ID</th> <th>Trip Name</th> <th>Country</th> <th>Start Date</th> <th>End Date</th> <th>License Number</th> <th>Image URL</th> </tr>";

//get orderby and ascdesc variables' values
$whichCol = $_POST["orderby"];
$aOrD = $_POST["ascdesc"];

//create two queries for two options
if($aOrD == "") {
  $query = "SELECT * FROM busTrip ORDER BY " . $whichCol;
}else if($aOrD != "" && $whichCol != ""){
  $query = "SELECT * FROM busTrip ORDER BY " . $whichCol . " " .$aOrD;
}

//echo "<br>" .$query . "<br>";
//echo $whichCol;
//echo $aOrD;

#perform query
$result = mysqli_query($connection, $query);

if(!$result){
  die("query failed");
}

//list all the bus trip data order by (trip name/country) (asc/desc)
while($row = mysqli_fetch_assoc($result)){
  echo '<tr>';
  echo '<td>' .$row["tripID"] .'</td>';
  echo '<td>' .$row["tripName"] .'</td>';
  echo '<td>' .$row["country"] .'</td>';
  echo '<td>' .$row["startDate"] .'</td>';
  echo '<td>' .$row["endDate"] .'</td>';
  echo '<td>' .$row["licenseNum"] .'</td>';
  echo '<td>' .$row["urlmage"] .'</td>';
  echo '</tr>';
}
mysqli_free_result($result);

echo "</table>"; //end of table
?>

