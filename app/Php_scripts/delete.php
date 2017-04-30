<?php

$id=$_POST['id'];

include("dbconfig.php");

$result = @mysql_query("delete from Student where ID=$id");	
$response =array();
 if($result){
 	$response['success']=1;
 	$response['message']="Record Deleted Sucessfully";
 }else{
 	$response['success']=0;
 	$response['message']="Deletion Failure";
 }
 echo json_encode($response);

?>