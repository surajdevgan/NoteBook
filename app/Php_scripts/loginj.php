<?php

$email = $_POST["e_mail"];
$pass = $_POST["password"];

include("dbconfig.php");

$result = @mysql_query("SELECT EMAIL,PASSWORD FROM Student WHERE EMAIL = '$email' and PASSWORD = '$pass'");

$response = array();

 

 if(@mysql_num_rows($result)>0){
 	$response['success']=1;
 	$response['message']="Login success";
 }else{
 	$response['success']=0;
 	$response['message']="Login fail";
 }

 echo json_encode($response);

?>