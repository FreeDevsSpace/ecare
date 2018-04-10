<?php

/**
 * @author Ravi Tamada
 * @link http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$USER_TYPE1="Patient";
$USER_TYPE2="Doctor";
$USER_TYPE3="Pathologist";
$USER_TYPE4="Pharmacist";

// json response array
$response = array("error" => FALSE);


if (isset($_POST['Email']) && isset($_POST['Password'])) {

    // receiving the post params
   $Email = $_POST['Email'];
    $Password = $_POST['Password'];

    // get the user by email and password
    $patient_registration = $db->getUserByEmailAndPassword($Email, $Password); 

    if ($patient_registration != false) {
        // user is found
          $response["error"] = FALSE;
          $response["USER_TYPE"] = $USER_TYPE1;
          $response["patient_registration"]["Patient_ID"] = $patient_registration["Patient_ID"];
          $response["patient_registration"]["Full_Name"] = $patient_registration["Full_Name"];
          $response["patient_registration"]["Mobile_Number"] = $patient_registration["Mobile_Number"];
          $response["patient_registration"]["DOB"] = $patient_registration["DOB"];
          $response["patient_registration"]["Email"] = $patient_registration["Email"];
          $response["patient_registration"]["Address"] = $patient_registration["Address"];
          $response["patient_registration"]["Blood_Group"] = $patient_registration["Blood_Group"];
          $response["patient_registration"]["PSW"] = $patient_registration["PSW"];
          
        
        echo json_encode($response);
    
    
    
}else if(isset($_POST['Email']) && isset($_POST['Password'])) {
                 $Email = $_POST['Email'];
                 $Password = $_POST['Password'];
    
    $doctor_registration = $db->getDoctorByEmailAndPassword($Email, $Password);

         if($doctor_registration != false) {
        // use is found
            $response["error"] = FALSE;
            $response["USER_TYPE"] = $USER_TYPE2;
            $response["doctor_registration"]["Doctor_ID"] = $doctor_registration["Doctor_ID"];
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
         
         } else  if(isset($_POST['Email']) && isset($_POST['Password'])) {
                 $Email = $_POST['Email'];
                 $Password = $_POST['Password'];
        
         $pathologist_registration = $db->getPathologistByEmailAndPassword($Email, $Password);

         if($pathologist_registration != false) {
             
        // use is found
            $response["error"] = FALSE;
            $response["USER_TYPE"] = $USER_TYPE3;
            $response["pathologist_registration"]["Pathologist_ID"] = $pathologist_registration["Pathologist_ID"];
            $response["pathologist_registration"]["Full_Name"] = $pathologist_registration["Full_Name"];
            $response["pathologist_registration"]["Mobile_Number"] = $pathologist_registration["Mobile_Number"];
            $response["pathologist_registration"]["DOB"] = $pathologist_registration["DOB"];
            $response["pathologist_registration"]["Qualification"] = $pathologist_registration["Qualification"];
            $response["pathologist_registration"]["Address"] = $pathologist_registration["Address"];
            $response["pathologist_registration"]["Blood_Group"] = $pathologist_registration["Blood_Group"];
            $response["pathologist_registration"]["Email"] = $pathologist_registration["Email"];
            $response["pathologist_registration"]["Password"] = $pathologist_registration["PSW"];
        
        
            echo json_encode($response);
         
         }else  if(isset($_POST['Email']) && isset($_POST['Password'])) {
                 $Email = $_POST['Email'];
                 $Password = $_POST['Password'];
        
         $pharmacist_registration = $db->getPharmacistByEmailAndPassword($Email, $Password);

         if($pharmacist_registration != false) {
        // use is found
            $response["error"] = FALSE;
            $response["USER_TYPE"] = $USER_TYPE4;
            $response["pharmacist_registration"]["Pharmacist_ID"] = $pathologist_registration["Pharmacist_ID"];
            $response["pharmacist_registration"]["Full_Name"] = $pharmacist_registration["Full_Name"];
            $response["pharmacist_registration"]["Mobile_Number"] = $pharmacist_registration["Mobile_Number"];
            $response["pharmacist_registration"]["DOB"] = $pharmacist_registration["DOB"];
            $response["pharmacist_registration"]["Qualification"] = $pharmacist_registration["Qualification"];
            $response["pharmacist_registration"]["Address"] = $pharmacist_registration["Address"];
            $response["pharmacist_registration"]["Blood_Group"] = $pharmacist_registration["Blood_Group"];
            $response["pharmacist_registration"]["Email"] = $pharmacist_registration["Email"];
            $response["pharmacist_registration"]["Password"] = $pharmacist_registration["PSW"];
        
            echo json_encode($response);
           
               } else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
         }
             
             
         }else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
         }
             
             
    }else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
         }
        
        
}else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
         }
    
    

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
         }


?>

