<?php
/* 
Programmer Name: 37
Purpose: to connect to database
*/  
#connect to database 
$dbhost = "localhost";
$dbuser= "root";
$dbpass = "passwordhere";
$dbname = "37_assign2db";
$connection = mysqli_connect($dbhost, $dbuser,$dbpass,$dbname);

//send error if something went wrong
if (mysqli_connect_errno()) {
  die("database connection failed :" . mysqli_connect_error() . "(" . mysqli_connect_errno() . ")");
}
?>