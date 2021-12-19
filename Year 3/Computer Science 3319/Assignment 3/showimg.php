<?php
/* 
Programmer Name: 37
Purpose: to show img
*/  
include "connectdb.php";

//get user input
$whichtrip = $_POST["pickbustrip2"];
//echo $whichtrip;

//create select statement
$query = 'SELECT urlmage from busTrip where tripID =' .$whichtrip;

//set null img to show when there is no image file in your database
$nullIMG = "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";

//performs a query against a database
$result = mysqli_query($connection, $query);
if (!result) {
  die("Error:" . mysqli_error($connection));
}
$row = mysqli_fetch_assoc($result);

//echo $query;
//echo "<br>";
//echo $row['urlmage'];

//if user selects field
if($whichtrip != 0){
  if($row['urlmage'] == NULL || $row['urlmage'] == ""){
      echo '<img src="' .$nullIMG . '">';
  } else {
      echo '<img src="' .$row['urlmage'] .'">';
  }
}
?>
