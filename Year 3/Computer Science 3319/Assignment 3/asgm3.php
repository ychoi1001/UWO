<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Assignment 3</title>
  	<meta name="author" content="Programmer Name 37">  
	<link rel="stylesheet" type="text/css" href="asgm3.css">
	<link href="https://fonts.googleapis.com/css?family=Mali" rel="stylesheet">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
  
<!--js file-->
<script src="asgm3.js"></script>  

<!--connect to database-->
<?php
include 'connectdb.php';
?>

<h2>You can use a refresh button to check if the database is updated or not</h2>  
<!--Implement functionality 1-->
<h2>List All the Bus Trip Data:</h2>
<form action="" method="post">
(*reqired) Order by: <br>
<input type="radio" name="orderby" value="tripName">Trip Name<br>
<input type="radio" name="orderby" value="country">Country<br>

ASC or DESC:<br>
<input type="radio" name="ascdesc" value="ASC">ASC<br>
<input type="radio" name="ascdesc" value="DESC">DESC<br>
<input type="submit" value="Sort Bus trip">
  
<!--check if orderby field is set: this field is required-->
<?php
if (isset($_POST['orderby'])) {
    include "getdata.php";
  }
?>
</form>

<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>

<hr>

<!--Implement functionality 2-->
<h2>Update Bus Trip</h2>
<form action="" method="post">
NOTE: date format should be yyyy-mm-dd <br>  

Select Bus Trip:
<select name="pickbustrip" id="pickbustrip">
<option value="0">Select Here</option>
<?php include "getbustrip.php"; ?>
</select><br>

Select the field you want to update: 
<select name="pickfield" id="pickfield">
	<option value="0">Select Here</option>
	<option value="tripName">Trip Name</option>
	<option value="startDate">Start Date</option>
  	<option value="endDate">End Date</option>
  	<option value="urlmage">Image URL</option>
</select><br>

Input here: <input type="text" name="userInput"><br>	
<input type="submit" value="Let's update bus trip!">
</form>
  
<!--user needs to choose bustrip to update bustrip info-->
<?php
if ( isset($_POST['pickbustrip']) && isset($_POST['pickfield']) && isset($_POST['userInput'])) {
 //if($_POST['pickbustrip']!= 0 && $_POST['pickfield']!=0 && !empty($_POST['userInput'])){
    include "updatedata.php";
//}
}
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr>

<!-- show image here-->
<h2>Show IMG</h2>
<form action="" method="post">
Select Bus Trip:
<select name="pickbustrip2" id="pickbustrip2">
<option value="0">Select Here</option>
<?php include "getbustrip.php"; ?>
</select><br>
<input type="submit" value="Show IMG">
</form>

<!-- bus trip should be chosen -->
<?php
  if(isset($_POST['pickbustrip2'])) {
  	include "showimg.php";
  }
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr> 

<!--Implement functionality 3-->
<h2>Delete Bus Trip</h2>
<form action="" method="post">
Select Bus Trip:
<select name="pickbustrip3" id="pickbustrip3">
<option value="0">Select Here</option>
<?php include "getbustrip.php"; ?>
</select><br>
<input type="submit" value="Delete" Onclick="return ConfirmDelete()">

</form>

<!-- bus trip should be chosen -->
<?php

if(isset($_POST['pickbustrip3'])) {
  	include "deletebustrip.php";
}
?>

<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr>

<!--Implement functionality 4-->
<h2>Enter a New Trip</h2>
<form action="" method="post">
<table>
  <tr>
	<th>Trip ID</th>
    <th>Trip Name</th>
    <th>Start Date</th>
    <th>End Date</th>
	<th>Country</th>
	<th>Bus</th>
    <th>Image URL</th>
  </tr>
  <tr>
    <td><input type="text" name="tripID"></td>
	<td><input type="text" name="tripName"></td>
    <td><input type="date" name="startDate"></td>
    <td><input type="date" name="endDate"></td>
	<td><input type="text" name="country"></td>
	<td><select name="pickabus" id="pickabus"><option value="0">Select here</option><?php include "getbusnum.php"?></select></td>
    <td><input type="text" name="imageURL"></td>
  </tr>
</table>

<input type="submit" value="Enter a new trip">
</form>

<?php
if(isset($_POST['tripID'],$_POST['tripName'],$_POST['startDate'],$_POST['endDate'],$_POST['country'],$_POST['pickabus'],$_POST['imageURL']) ){
	include "addbustrip.php";
}
?>

<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr>

<!--Implement functionality 5-->
<h2>Show bus trip</h2>
<form action="" method="post">
<select name="pickcountry" id="pickcountry">
<option value="0">Select Here</option>
<?php
include "selectcountry.php";
?>
</select>
<input type="submit" value="show bus trip">
</form>

<?php
if(isset($_POST['pickcountry']) ) {
	include "getbustripbycountry.php";
}
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr>

<!--Implement functionality 6-->
<h2>Create booking for trip</h2>
<form action="" method="post">

<table>
  <tr>
	<th>Passenger</th>
    <th>Bus Trip</th>
    <th>Price</th>
  </tr>
  <tr>
	<td><select name="pickpassenger" id="pickpassenger"><option value="0">Select here</option><?php include "getpassenger.php"?></select></td>
	<td><select name="pickbustrip4" id="pickbustrip4"><option value="0">Select here</option><?php include "getbustrip.php"?></select></td>
    <td><input type="text" name="price"></td>
  </tr>
</table>

<input type="submit" value="add new booking">
</form>

<?php
if( isset( $_POST['pickpassenger'] , $_POST['pickbustrip4'] ,$_POST['price'] ) ){
    include "addbooking.php";
}
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>
  
<hr>
  
<!--Implement functionality 7-->
<h2>Show Passenger Info</h2>
<?php
	include "passengerinfo.php";
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>

<hr>
  
<!--Implement functionality 8-->
<h2>Show Booking</h2>
<form action="" method="post">
<select name="pickpassenger1" id="pickpassenger1"><option value="0">Select here</option><?php include "getpassenger.php"?></select>
<input type="submit" value="show booking">
</form>
  
<?php
if( isset($_POST['pickpassenger1'])) {
include "showbooking.php";
}
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>

<hr>

<!--Implement functionality 9-->
<h2>Delete a booking</h2>
<form action="" method="post">
<select name="pickbooking" id="pickbooking"><option value="0">Select here</option><?php include "getbooking.php"?></select>
<input type="submit" value="Delete" Onclick="return ConfirmDelete()">
</form>
  
<?php
if(isset($_POST['pickbooking'])) {
include "deletebooking.php";
}
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>

<hr>
  
<!--Implement functionality 10-->
<h2>List all the bus trips that do not have any bookings yet</h2>
<?php
include "listbustripsnobookings.php"
?>
  
<form action="asgm3.php" method="POST">
  <input type="submit" value="Refresh Page"/>
</form>

<!--Since it displays all in one page, disconnect database here-->
<?php
mysqli_close($connection);
?>

</body>
</html>

