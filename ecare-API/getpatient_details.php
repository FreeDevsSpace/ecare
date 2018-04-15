
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

if((isset($_POST['Patient_ID'])) ) {

    $Patient_ID = $_POST['Patient_ID'];
       
    $query = "SELECT Patient_ID, Full_Name,Blood_Group FROM patient_registration WHERE Patient_ID='$Patient_ID'";

    
    $result = mysqli_query($con,$query);
    $row = mysqli_fetch_array($result);
      if($row>0){
          
      
        $response["error"]=false;
        $response["Patient_ID"]=$row["Patient_ID"];    
        $response["Patient_Name"]=$row["Full_Name"];
        $response["Blood_Group"]=$row["Blood_Group"]; 
    
      }else{
           $response["error"] = TRUE;
        $response["message"] = "Patient ID does not exist";
        echo  json_encode($response);
      }

        echo json_encode($response);
        
}

?>




