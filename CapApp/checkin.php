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
$lat = $_POST['latitude'];
$long = $_POST['longitude'];
$result = mysqli_query($con,"INSERT INTO checkin (latitude, longitude, parkNum ) VALUES ('$lat','$long', 0 )");

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