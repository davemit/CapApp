<?php
 
$servername = "localhost";
$usr = "root";
$psswrd = "";
$dbname = "capappdb";



$con=mysql_connect("$servername", "$usr", "$psswrd")or die("cannot connect"); 
mysql_select_db("$dbname")or die("cannot select DB");

$sql = "select * from checkout"; 
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
while($row=mysql_fetch_assoc($result)){
$json['checkout'][]=$row;
}
}
mysql_close($con);
echo json_encode($json); 
?>