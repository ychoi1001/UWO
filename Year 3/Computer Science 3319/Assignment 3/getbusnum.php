<?php
/* 
Programmer Name: 37
Purpose: to get a bus number
*/  
#create a query statement
$query = "SELECT * FROM bus";

#performs a query against a database
$result = mysqli_query($connection,$query);
   if (!$result) {
        die("databases query failed.");
    }

#fetches a result row
while ($row = mysqli_fetch_assoc($result)) {
  echo "<option value='".$row["licenseNum"]."'>".$row["licenseNum"]."</option>";
}

//frees the memory associated with the result.
mysqli_free_result($result);
?>
