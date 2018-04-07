
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



if((isset($_POST['City'])) && isset($_POST['Qualification']) && isset($_POST['Specialization'])) { 

    // receiving the post params
    $City = $_POST['City'];
    $Qualification=$_POST['Qualification'];
    $Specialization=$_POST['Specialization'];
    

    $query ="SELECT Doctor_ID,Full_Name,Qualification,Specialization,Hospital_Name,Hospital_Address,City,Fees  FROM doctor_registration
                    WHERE City='".$City."' AND Qualification='".$Qualification."'AND Specialization='".$Specialization."'";
    
  //  SELECT Full_Name,Qualification,Specialization,Hospital_Name,Hospital_Address,City FROM doctor_registration
    //            WHERE City="Gandhinagar" AND Qualification="MD" AND Specialization="Dermatology"
    
    
         $result = mysqli_query($con,$query);

          $i=0;
        $responce["error"]=false;
        
        while($row = mysqli_fetch_array($result)){
    
        $name="Key".(string)$i;
            $responce[$name]["Doctor_ID"] = $row["Doctor_ID"];
            $responce[$name]["Full_Name"] = $row["Full_Name"];
            $responce[$name]["Qualification"] = $row["Qualification"];
            $responce[$name]["Specialization"] = $row["Specialization"];
            $responce[$name]["Hospital_Name"] = $row["Hospital_Name"];
            $responce[$name]["Hospital_Address"] = $row["Hospital_Address"];
            $responce[$name]["City"] = $row["City"];
            $responce[$name]["Fees"] = $row["Fees"];
        
        $i++;
        }

     
      
     echo json_encode($responce);         
    
     }

    
  mysqli_close($con);
?>
    