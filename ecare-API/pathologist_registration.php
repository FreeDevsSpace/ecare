
<?php

require_once 'include/DB_Functions.php';
$db = new DB_Functions();
// json response array
$response = array("error" => FALSE);

if((isset($_POST['Full_Name'])) && isset($_POST['Mobile_Number']) && isset($_POST['DOB']) && isset($_POST['Qualification']) && isset($_POST['Address']) && isset($_POST['Blood_Group']) && isset($_POST['Email']) && isset($_POST['Password'])) { 

    // receiving the post params
    $Full_Name = $_POST['Full_Name'];
    $Mobile_Number=$_POST['Mobile_Number'];
    $DOB=$_POST['DOB'];
	$Qualification=$_POST['Qualification'];
    $Address=$_POST['Address'];
    $Blood_Group=$_POST['Blood_Group'];
    $Email = $_POST['Email'];
    $Password = $_POST['Password'];
    

    // check if user is already existed with the same email
    
    if($db->isPathologistExisted($Email)){
        // user already existed
        
        $response["error"] = TRUE;
        $response["error_msg"] = "Pathologist already existed with " . $Email; 
        echo json_encode($response);
        
    } else {
        // create a new user
        $pathologist_registration = $db->storePathologist($Full_Name,$Mobile_Number,$DOB,$Qualification,$Address,$Blood_Group,$Email,$Password);
        if ($pathologist_registration) {
            // user stored successfully 
        
            $response["error"] = FALSE;
            //$response["Pathologist_ID"] = $pathologist_registration["Pathologist_ID"];
            $response["pathologist_registration"]["Full_Name"] = $pathologist_registration["Full_Name"];
            $response["pathologist_registration"]["Mobile_Number"] = $pathologist_registration["Mobile_Number"];
            $response["pathologist_registration"]["DOB"] = $pathologist_registration["DOB"];
            $response["pathologist_registration"]["Qualification"] = $pathologist_registration["Qualification"];
			$response["pathologist_registration"]["Address"] = $pathologist_registration["Address"];
            $response["pathologist_registration"]["Blood_Group"] = $pathologist_registration["Blood_Group"];
            $response["pathologist_registration"]["Email"] = $pathologist_registration["Email"];
            $response["pathologist_registration"]["Password"] = $pathologist_registration["Password"];
            
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



