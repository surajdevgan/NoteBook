<?php

$fname = $_POST['FirstName'];
$lname = $_POST['LastName'];
$email = $_POST['email'];
$gender = $_POST['gender'];
$city = $_POST['city'];
$pass = $_POST['password'];
$token = $_POST['token'];

include("dbconfig.php");

$result = mysql_query("INSERT INTO Student VALUES(null,'$fname','$lname','$email','$gender','$city','$pass','$token')");	
$response = array();
 if($result){
 	$response['success']=1;
 	$response['message']="Record Inserted Sucessfully";
 }else{
 	$response['success']=0;
 	$response['message']="Insertion Failure";
 }
 
 echo json_encode($response);

?>