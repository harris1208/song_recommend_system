<?php
	$test =$_POST['test'];
	$output=shell_exec("cd /home/song_recommen_system && java client_server_interface.java $test");
	echo $output;
?>
