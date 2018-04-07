<?php

require_once 'include/Config.php';
// $db = new DB_Connect();

 $con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
  // Check connection
  if (mysqli_connect_errno())
  {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
  } 


// json response array
$response = array("error" => FALSE);

if((isset($_POST['Patient_ID'])) &&(isset($_POST['Doctor_ID'])) &&(isset($_POST['Problem'])) && isset($_POST['Additional_Info_Medicine']) && isset($_POST['Additional_Info_Allergy'])) { 

    
        
    // receiving the post params
    $Patient_ID = $_POST['Patient_ID'];
    $Doctor_ID=$_POST['Doctor_ID'];
    $Problem = $_POST['Problem'];
    $Additional_Info_Medicine=$_POST['Additional_Info_Medicine'];
    $Additional_Info_Allergy=$_POST['Additional_Info_Allergy'];
    $Suggetion='';
   
  //INSERT INTO consultation (Patient_ID,Problem,Additional_Info_Medicine,Additional_Info_Allergy) VALUES (1234,ghvhg,hv h,ghhg)
    
     $query = mysqli_query("INSERT INTO consultation(Patient_ID,Doctor_ID,Problem,Additional_Info_Medicine,Additional_Info_Allergy,Suggetion) VALUES ('$Patient_ID','$Doctor_ID','$Problem','$Additional_Info_Medicine','$Additional_Info_Allergy','$Suggetion')");

    $result = mysqli_query($con,$query);
    
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        //$response["error"] = FALSE;
        $response["success"] = true;
        $response["message"] = "Consultation Successfully Registered.";
         echo json_encode($response);
        
    } else {
        // failed to insert row
        $response["success"] = false;
        $response["message"] = "Oops! An error occurred.";
        echo  json_encode($response);
    }
}/* else {
    
    $response["success"] = false;
    $response["message"] = "Required field(s) is missing";
    echo  json_encode($response);
} */
      

      
?>
    