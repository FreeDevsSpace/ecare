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

if(isset($_POST['Patient_ID']))
{
  
  	$Patient_ID = $_POST['Patient_ID'];
    $query = "SELECT * FROM consultation WHERE Patient_ID='".$Patient_ID."' AND Status=1";

    $result = mysqli_query($con,$query);
    
    // check if row inserted or not
    if (mysqli_num_rows($result))
    {
      $response["error"] = FALSE;
      $i=0;
      while($row = mysqli_fetch_array($result))
      {
        $name="Key".(string)$i;
        $response[$name]["Consultation_ID"]=$row['Consultation_ID'];
        $response[$name]["Patient_ID"]=$row['Patient_ID'];
           $response[$name]["Doctor_ID"]=$row['Doctor_ID'];
        $response[$name]["Doctor_Name"]=Find_Doctor_Name($con,$row['Doctor_ID']);
        $response[$name]["Problem"]=$row['Problem'];
        $response[$name]["Additional_Info_Medicine"]=$row['Additional_Info_Medicine'];
        $response[$name]["Additional_Info_Allergy"]=$row['Additional_Info_Allergy'];
        $response[$name]["Suggetion"]=$row['Suggetion'];
        $response[$name]["Prescribe_Medicine"]=$row['Prescribe_Medicine'];
          

        $i++;
      }
        // successfully inserted into database
        
        echo json_encode($response);
        
    }
    else
    {
        // failed to insert row
        $response["error"] = TRUE;
        $response["message"] = "No Consultations available !!";
        echo  json_encode($response);
    }
}
else
{
    
    $response["error"] = TRUE;
    $response["message"] = "Required field(s) is missing";
    echo  json_encode($response);
}


function Find_Doctor_Name($con,$Doctor_ID){

    $query = "SELECT Full_Name FROM doctor_registration WHERE Doctor_ID=".$Doctor_ID;
    $result = mysqli_query($con,$query);
    $row = mysqli_fetch_array($result);
    return $row['Full_Name'];
}
      

      
?>
