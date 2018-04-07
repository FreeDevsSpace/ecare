<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if((isset($_POST['Full_Name'])) && isset($_POST['Mobile_Number']) && isset($_POST['DOB']) && isset($_POST['Address']) && isset($_POST['Email']) && isset($_POST['Blood_Group']) && isset($_POST['Password'])) { 

    // receiving the post params
    $Full_Name = $_POST['Full_Name'];
    $Mobile_Number=$_POST['Mobile_Number'];
    $DOB=$_POST['DOB'];
    $Address=$_POST['Address'];
    $Email = $_POST['Email'];
    $Blood_Group=$_POST['Blood_Group'];
    $Password = $_POST['Password'];
    

    // check if user is already existed with the same email
    
    if($db->isUserExisted($Email)){
        // user already existed
        
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $Email; 
        echo json_encode($response);
        
    } else {
        // create a new user
        $patient_registration = $db->storeUser($Full_Name,$Mobile_Number,$DOB,$Email,$Address,$Blood_Group,$Password);
        if ($patient_registration) {
            // user stored successfully 
        
            $response["error"] = FALSE;
          //  $response["Patient_ID"] = $patient_registration["Patient_ID"];
            $response["patient_registration"]["Full_Name"] = $patient_registration["Full_Name"];
            $response["patient_registration"]["Mobile_Number"] = $patient_registration["Mobile_Number"];
            $response["patient_registration"]["DOB"] = $patient_registration["DOB"];
            $response["patient_registration"]["Email"] = $patient_registration["Email"];
            $response["patient_registration"]["Address"] = $patient_registration["Address"];
            $response["patient_registration"]["Blood_Group"] = $patient_registration["Blood_Group"];
            $response["patient_registration"]["PSW"] = $patient_registration["PSW"];
            
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
    }
}else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters is missing!";
    echo json_encode($response);
} 
      
?>