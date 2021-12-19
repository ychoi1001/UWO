<?php
/* 
Programmer Name: 37
Purpose: to update bus trip data
*/  
include 'connectdb.php';

#create a function for chekcing if user input correct format of image link
function jpgorpng($userinput) {
  #jpg and png are case insensitive
  $patten ="/.(jpg|png)$/i";
  
  //if user uses correct format of image link, return true
  //else - return false
  if(preg_match($patten, $userinput)) {
    //$validorinvalid = true;
    return true;
  }else {
    //$validorinvalid = false;
    return false;
  }
}

#function for checking date format (it should be yyyy-mm-dd)
function checkdateformat($userinput) {
  $patten ="/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/";
  if(preg_match($patten, $userinput)) {
    $validorinvalid = true;
    return $validorinvalid;
  }else {
    $validorinvalid = false;
    return $validorinvalid;
  }
}

#checking if start date is smaller than end date
function checkingdate($startd, $endd){
  if ($startd <= $endd) {
    return true;
  }else if ($startd > $endd) {
    return false;
  }
}

//get user inputs
$whichtrip = $_POST["pickbustrip"];
$whichfield = $_POST["pickfield"];
$userinput = $_POST["userInput"];

//echo $whichtrip." which trip <br>";
//echo $whichfield." which field <br>";
//echo $userinput." userinput <br>";

//get all the infomation of bus trip that user choose
$bustripquery = 'SELECT * FROM busTrip WHERE tripID = '.$whichtrip;
//echo $bustripquery;
$result = mysqli_query($connection, $bustripquery);

if (!$result) {
  die("databases query failed.");
} 

#fetches a result row
$row=mysqli_fetch_assoc($result);
//echo $row['startDate']. " <br>";
//echo $row['endDate']." <br>";

//set it "" first
$validorinvalid = "";

//check which field user chose so user input can be validated
if ($whichfield == 'urlmage') {
  //echo $userinput;
  $validorinvalid = jpgorpng($userinput);
  
} else if ($whichfield == "startDate" || $whichfield == "endDate") {
  $dateformat = checkdateformat($userinput);
  if ($dateformat){
    if ($whichfield == "startDate") {
      $validorinvalid = checkingdate($userinput, $row['endDate']);
    }else if ($whichfield == "endDate") {
      $validorinvalid = checkingdate($row['startDate'], $userinput);
    }
  }

} else if ($whichfield == "tripName") {
  $validorinvalid = true;
}

//echo "<br><br>";
//echo "valid or invalid";
//echo "<br>";
//echo $validorinvalid;

#echo $whichfield != 0;
#echo "which field";
#echo $whichtrip !=0;
#echo "which trip";

//if user input is valid, update field
if ($validorinvalid && $whichtrip != 0) {  
	$updatequery = 'UPDATE busTrip SET ' .$whichfield.  '= "'.$userinput.'" WHERE tripID = '.$whichtrip;
  	#echo $updatequery;
	$result2 = mysqli_query($connection, $updatequery);  
    if (!$result2) {
        die("Error: insert failed: " . mysqli_error($connection));
    } 
    echo "Updated! <br>";
} else {
  echo "invalid input! Please try again.";
}

?>
