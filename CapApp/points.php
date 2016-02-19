<?php

$servername = "localhost";
$usr = "root";
$psswrd = "";
$dbname = "capappdb";



$con=mysqli_connect($servername, $usr, $psswrd, $dbname);
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$username = $_POST['username'];


$result = mysqli_query($con,"SELECT points FROM user_account WHERE user_id='$username' ");
$row = mysqli_fetch_array($result);
$data = $row[0];
if($data){
echo $data;
}
else
{
	echo "wrong";
}
mysqli_close($con);
?>