<?php
/* 
Programmer Name: 37
Purpose: to get bus trip by country
*/  
# get user input
$whichcountry = $_POST["pickcountry"]; 
#echo $whichcountry;

#create a query statement
$query = 'SELECT * FROM busTrip WHERE country="' . $whichcountry.'"'; //fill in with correct query
#echo "hihihihihi";
#echo "<br>" .$query . "<br>";

#perform query
$result = mysqli_query($connection, $query);

if (!$result) {
        die("databases query on art pieces failed. ");
}

#create a table
echo "<table> <tr> <th>Trip iD</th> <th>Trip Name</th> <th>Country</th> <th>Start Date</th>  <th>End Date</th> <th>License Number</th> <th>Image URL</th>  </tr>";

#fetches a result row
while ($row = mysqli_fetch_assoc($result)) {
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
echo "</table>"; //end of table

#frees the memory associated with the result.
mysqli_free_result($result);
?>
