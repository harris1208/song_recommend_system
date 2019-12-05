<?php
	$con=mysqli_connect('localhost','root','000000','song_db');
	if(!$con)
	{
		die("Connection Failed." . mysql_connect_error());
	}
	$result=mysqli_query($con,"SELECT * FROM song");
	$row = array();
	while($r = mysqli_fetch_assoc($result))
	{
		$rows[]=$r;
	}
	$json_string = json_encode($rows,JSON_UNESCAPED_UNICODE);
	echo $json_string;
	mysqli_close($con);
?>
