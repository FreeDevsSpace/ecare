<?php

require_once 'include/Config.php';
// $db = new DB_Connect();

 $con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
  
  // Check connection
  if (mysqli_connect_errno())
  {
   		echo "Failed to connect to MySQL: " . mysqli_connect_error();
   		$response["error"] = TRUE;
   		$response["message"] = "Failed to connect to MySQL Database";
   		echo json_encode($response);
  } 


// json response array
$response = array("error" => FALSE);

if(isset($_POST['Consultation_ID']) && isset($_POST['Suggetion']) && isset($_POST['Prescribe_Medicine']) )
{

	$Consultation_ID=$_POST['Consultation_ID'];
	$Suggetion=$_POST['Suggetion'];
    $Prescribe_Medicine=$_POST['Prescribe_Medicine'];

	$sql = "UPDATE consultation SET Suggetion='".$Suggetion."',Prescribe_Medicine='".$Prescribe_Medicine."', Status=1 WHERE Consultation_ID=".$Consultation_ID;


    $result = mysqli_query($con,$sql);

	if ($result)
	{
		$response["error"] = FALSE;
   		$response["message"] = "Record updated successfully";
   		echo json_encode($response);
   	}
	else
	{
		$response["error"] = TRUE;
   		$response["message"] = "Error updating record: " . $con->error;
   		echo json_encode($response);
	}


}