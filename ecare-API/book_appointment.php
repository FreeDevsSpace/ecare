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

if((isset($_POST['Patient_ID'])) && (isset($_POST['Doctor_ID'])) && (isset($_POST['Date'])) && isset($_POST['Timeslote'])) {

    
        
    // receiving the post params
    $Patient_ID = $_POST['Patient_ID'];
    $Doctor_ID=$_POST['Doctor_ID'];
    $Date = $_POST['Date'];
    $Timeslote=$_POST['Timeslote'];
   
    $test_query = "SELECT * FROM appointment WHERE Patient_ID=".$Patient_ID." AND Doctor_ID=".$Doctor_ID." AND Date='".$Date."' AND Timeslote='".$Timeslote."'";

    $test_result = mysqli_query($con,$test_query);

    //echo mysqli_num_rows($test_result);

    if(!mysqli_num_rows($test_result) == 0)
    {
      $response["error"] = TRUE;
      $response["message"] = "These Date and Time are booked by other Please, Select Diffrent Appointment date or time...";
      echo json_encode($response);
    }
    else
    {
  
      $query = "INSERT INTO appointment (Patient_ID,Doctor_ID,Date,Timeslote) VALUES ('$Patient_ID','$Doctor_ID','$Date','$Timeslote')";

      $result = mysqli_query($con,$query);
    
    // check if row inserted or not
    if ($result)
    {
        // successfully inserted into database
        $response["error"] = FALSE;
        $response["message"] = "Appointment Successfully Booked...";
        echo json_encode($response);
        
    }
    else
    {
        // failed to insert row
        $response["error"] = TRUE;
        $response["message"] = "Oops! An error occurred.";
        echo  json_encode($response);
    }
  }
}
else
{
    
    $response["error"] = TRUE;
    $response["message"] = "Required field(s) is missing";
    echo  json_encode($response);
}
      

      
?>
    