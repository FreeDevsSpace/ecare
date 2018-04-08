
 <?php
  
 require_once 'include/Config.php';
// $db = new DB_Connect();

 $con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);

  // Check connection
  if (mysqli_connect_errno())
  {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }


 $query ="SELECT DISTINCT Specialization FROM doctor_registration";

  $result = mysqli_query($con,$query);

  $response = array();
  while($r = mysqli_fetch_array($result)) {
    $response[] = $r;
  }
  echo json_encode($response);

  mysqli_close($con);

?>




