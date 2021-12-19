<?php
/* 
Programmer Name: 37
Purpose: to add a bus trip using user inputs
*/  
include 'connectdb.php';

#get user inputs
$tripid = $_POST["tripID"];
$tripname = $_POST["tripName"];
$startd = $_POST["startDate"];
$endd = $_POST["endDate"];
$country = $_POST["country"];
$img = $_POST["imageURL"];
$whichbus = $_POST["pickabus"];

#function for checking if start date is smaller than end date
function checkingdate($startd, $endd){
  if ($startd <= $endd) {
    return true;
  }else if ($startd > $endd) {
    return false;
  }
}

#checking date -> get returned boolean value
$invalidorvalid = checkingdate($startd, $endd);

#if start date and end date are valid
if ($invalidorvalid) {

#create a query to add new bus trip
$query= 'INSERT INTO busTrip (tripID, tripName, startDate, endDate, country, urlmage, licenseNum) VALUES (' . $tripid.',"' . $tripname . '", "' . $startd . '", "' .$endd . '", "' .$country . '", "' .$img. '", "' . $whichbus . '");';

#check if every field is filled (image url field doesn't need to be filled) 
if ($tripid != "" && $tripname != "" && $startd != "" && $endd != "" && $country != "" && $whichbus == 0) {
  $result = mysqli_query($connection, $query);
    if (!$result) {
        die("Error: insert failed: " . mysqli_error($connection));
    } else {
      echo "Updated! <br>";
    }
  }
} else {
  #give some hints for user
  echo "invalid input! Please try again.<br>";
  echo "hint 1 - check if you insert valid dates. start date should be smaller than end date<br>";
  echo "hint 2 - check if you do not fill in all the blanks (but you do not need to add image url)";
}
?>

