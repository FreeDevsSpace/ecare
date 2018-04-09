
<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
// json response array
$response = array("error" => FALSE);

if((isset($_POST['Full_Name'])) && isset($_POST['Mobile_Number']) && isset($_POST['DOB']) && isset($_POST['Qualification']) && isset($_POST['Address']) &&  isset($_POST['Blood_Group']) && isset($_POST['Email']) && isset($_POST['Password'])) { 

    // receiving the post params
    $Full_Name = $_POST['Full_Name'];
    $Mobile_Number=$_POST['Mobile_Number'];
    $DOB=$_POST['DOB'];
    $Qualification = $_POST['Qualification'];
    $Address=$_POST['Address'];
    $Blood_Group=$_POST['Blood_Group'];
    $Email = $_POST['Email'];
	$Password = $_POST['Password'];
    

    // check if user is already existed with the same email
    
    if($db->isPharmacistExisted($Email)){
        // user already existed
        
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $Email; 
        echo json_encode($response);
        
    } else {
        // create a new user
        $pharmacist_registration = $db->storePharmacist($Full_Name,$Mobile_Number,$DOB,$Qualification,$Address,$Blood_Group,$Email,$Password);
        if ($pharmacist_registration) {
            // user stored successfully 
        
            $response["error"] = FALSE;
            $response["Pharmacist_ID"] = $pharmacist_registration["Pharmacist_ID"];
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


