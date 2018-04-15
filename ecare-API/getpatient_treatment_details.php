
 <?php
  
 require_once 'include/Config.php';
// $db = new DB_Connect();

 $con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
$responce= array("error"=>FALSE);
  // Check connection
  if (mysqli_connect_errno())
  {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

if((isset($_POST['Patient_ID'])) && (isset($_POST['Patient_Name'])) && (isset($_POST['Blood_Group'])) && (isset($_POST['Symptoms'])) && (isset($_POST['Prescription'])) && (isset($_POST['Report'])) && (isset($_POST['Suggetion']))){
    
    $Patient_ID = $_POST['Patient_ID'];
    $Patient_Name = $_POST['Patient_Name'];
    $Blood_Group = $_POST['Blood_Group'];
    $Symptoms = $_POST['Symptoms'];
    $Prescription = $_POST['Prescription'];
    $Report = $_POST['Report'];
    $Suggetion = $_POST['Suggetion'];
    

    $query ="INSERT INTO patient_treatment(Patient_ID,Patient_Name,Blood_Group,Symptoms,Prescription,Report,Suggetion)VALUES('$Patient_ID','$Patient_Name',
    '$Blood_Group','$Symptoms','$Prescription','$Report','$Suggetion')";

          $result = mysqli_query($con,$query);
    
    // check if row inserted or not
    if ($result)
    {
        // successfully inserted into database
        $response["error"] = FALSE;
        $response["message"] = "Data Store Successfully...";
        echo json_encode($response);
        
    }
    else
    {
        // failed to insert row
        $response["error"] = TRUE;
        $response["message"] = "Oops! An error occurred.";
        echo  json_encode($response);
    }
  
}else {
    $response["error"] = TRUE;
    $response["message"] = "Required parameters is missing!";
    echo json_encode($response);
} 
      
?>
    