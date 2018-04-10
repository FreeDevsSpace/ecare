<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if((isset($_POST['Full_Name'])) && isset($_POST['Mobile_Number']) && isset($_POST['DOB']) && isset($_POST['Address'])&& isset($_POST['City'])&& isset($_POST['Email']) && isset($_POST['Password']) && isset($_POST['Qualification'])&& isset($_POST['Specialization'])&& isset($_POST['Hospital_Name'])&& isset($_POST['Hospital_Address'])&& isset($_POST['Fees'])) { 

    // receiving the post params
    $Full_Name = $_POST['Full_Name'];
    $Mobile_Number=$_POST['Mobile_Number'];
    $DOB=$_POST['DOB'];
    $Address=$_POST['Address'];
    $City=$_POST['City'];
    $Email = $_POST['Email'];
    $Password=$_POST['Password'];
    $Qualification = $_POST['Qualification'];
    $Specialization = $_POST['Specialization'];
    $Hospital_Name = $_POST['Hospital_Name'];
    $Hospital_Address = $_POST['Hospital_Address'];
    $Fees = $_POST['Fees'];
    
    
    

    // check if doctor is already existed with the same email
    
    if($db->isDoctorExisted($Email)){
        // doctor already existed
        
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $Email; 
        echo json_encode($response);
        
    } else {
        // create a new doctor
        $doctor_registration = $db->storeDoctor($Full_Name,$Mobile_Number,$DOB,$Address,$City,$Email, $Password,$Qualification,$Specialization,$Hospital_Name,$Hospital_Address,$Fees);
        if ($doctor_registration) {
            // user stored successfully 
            $response["error"] = FALSE;
            $response["Doctor_ID"] = $doctor_registration["Doctor_ID"];
            $response["doctor_registration"]["Full_Name"] = $doctor_registration["Full_Name"];
            $response["doctor_registration"]["Mobile_Number"] = $doctor_registration["Mobile_Number"];
            $response["doctor_registration"]["DOB"] = $doctor_registration["DOB"];
            $response["doctor_registration"]["Address"] = $doctor_registration["Address"];
            $response["doctor_registration"]["City"] = $doctor_registration["City"];
            $response["doctor_registration"]["Email"] = $doctor_registration["Email"];
            $response["doctor_registration"]["Password"] = $doctor_registration["Password"];
            $response["doctor_registration"]["Qualification"] = $doctor_registration["Qualification"];
            $response["doctor_registration"]["Specialization"] = $doctor_registration["Specialization"];
            $response["doctor_registration"]["Hospital_Name"] = $doctor_registration["Hospital_Name"];
            $response["doctor_registration"]["Hospital_Address"] = $doctor_registration["Hospital_Address"];
            $response["doctor_registration"]["Fees"] = $doctor_registration["Fees"];

            
            
            echo json_encode($response);
        } else {
            // doctor failed to store
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