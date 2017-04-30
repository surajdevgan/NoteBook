<?php
$dbhost = 'localhost';
$dbuser = 'u265299461_suraj';
$dbpass = '12345678';
$db = 'u265299461_testd';

@mysql_connect($dbhost, $dbuser, $dbpass);
@mysql_select_db($db);
?>