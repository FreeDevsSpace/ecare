
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



if((isset($_POST['Doctor_ID']))) { 

    // receiving the post params
    $Doctor_ID = $_POST['Doctor_ID'];
   
    

    $query ="SELECT Appointment_ID,Patient_ID,Doctor_ID,Date,Timeslote  FROM appointment
                    WHERE Doctor_ID='".$Doctor_ID."'";
       
         $result = mysqli_query($con,$query);

          $i=0;
        $responce["error"]=false;
        
        while($row = mysqli_fetch_array($result)){
        
    
        $name="Key".(string)$i;
            $responce[$name]["Appointment_ID"] = $row["Appointment_ID"];
            $responce[$name]["Patient_ID"] = $row["Patient_ID"];
            $responce[$name]["Doctor_ID"] = $row["Doctor_ID"];
            $responce[$name]["Date"] = $row["Date"];
            $responce[$name]["Timeslote"] = $row["Timeslote"];
        $i++;
        }

     echo json_encode($responce);         
    
     }

    
  mysqli_close($con);
?>
    