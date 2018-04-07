<?php

  class DB_Functions{
    
    private $conn;
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    
    public function storeUser($Full_Name,$Mobile_Number,$DOB,$Email,$Address,$Blood_Group,$Password) {
         
    //    $Patient_ID = uniqid('', true);
        $hash = $this->hashSSHA($Password);
        $PSW = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"];// encrypted password
        

        $stmt = $this->conn->prepare("INSERT INTO patient_registration(Full_Name,Mobile_Number,DOB,Email,Address,Blood_Group,PSW,salt) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        

        $stmt->bind_param("ssssssss",$Full_Name, $Mobile_Number, $DOB, $Email, $Address, $Blood_Group, $PSW, $salt);        
    
        $result = $stmt->execute();
        $stmt->close(); 

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM patient_registration WHERE Email = ?");
            $stmt->bind_param("s", $Email);
            $stmt->execute();
            $patient_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $patient_registration;
        } else {
            return false;
        }
    }
      
       public function storeDoctor($Full_Name,$Mobile_Number,$DOB,$Address,$City,$Email,$Password,$Qualification,$Specialization,$Hospital_Name, $Hospital_Address,$Fees) {
         
      //  $Doctor_ID = uniqid('', true);
        $hash = $this->hashSSHA($Password);
        $PSW = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"];// encrypted password
        

        $stmt = $this->conn->prepare("INSERT INTO doctor_registration(Full_Name,Mobile_Number,DOB,Address,City,Email,Password,Qualification,Specialization,Hospital_Name,Hospital_Address,Fees,salt) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)");
        
        $stmt->bind_param("sssssssssssss",$Full_Name, $Mobile_Number, $DOB, $Address,$City,$Email,$PSW, $Qualification,$Specialization,$Hospital_Name,$Hospital_Address,$Fees,$salt);        
        
        $result = $stmt->execute();
        $stmt->close(); 

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM doctor_registration WHERE Email = ?");
            $stmt->bind_param("s", $Email);
            $stmt->execute();
            $doctor_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $doctor_registration;
        } else {
            return false;
        }
    }
      
          public function storePharmacist($Full_Name,$Mobile_Number,$DOB,$Qualification,$Address,$Blood_Group,$Email,$Password) {
         
      //$Patient_ID = uniqid('', true);
        $hash = $this->hashSSHA($Password);
        $PSW = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"];// encrypted password
        

        $stmt = $this->conn->prepare("INSERT INTO pharmacist_registration(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,PSW,salt) VALUES(?, ?, ?, ?,?, ?, ?, ?, ?)");
        
        $stmt->bind_param("sssssssss",$Full_Name, $Mobile_Number, $DOB,$Qualification,$Address,$Blood_Group,$Email,$PSW, $salt);        
        
        $result = $stmt->execute();
        $stmt->close(); 

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM pharmacist_registration WHERE Email = ?");
            $stmt->bind_param("s", $Email);
            $stmt->execute();
            $pharmacist_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $pharmacist_registration;
        } else {
            return false;
        }
    }
	

      
        
    public function storePathologist($Full_Name,$Mobile_Number,$DOB,$Qualification,$Address,$Blood_Group,$Email,$Password) {
         
        $hash = $this->hashSSHA($Password);
        $PSW = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"];// encrypted password

        $stmt = $this->conn->prepare("INSERT INTO pathologist_registration(Full_Name,Mobile_Number,DOB,Qualification,Address,Blood_Group,Email,PSW,salt) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("sssssssss",$Full_Name,$Mobile_Number,$DOB,$Qualification, $Address,$Blood_Group,$Email,$PSW, $salt);        
        

        $result = $stmt->execute();
        $stmt->close(); 

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM pathologist_registration WHERE Email = ?");
            $stmt->bind_param("s", $Email);
            $stmt->execute();
            $pathologist_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $pathologist_registration;
        } else {
            return false;
        }
    }

      
     
    /*
     * Get user by email and password
     */
     
   public function getUserByEmailAndPassword($Email, $Password) {

        $stmt = $this->conn->prepare("SELECT * FROM patient_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        if ($stmt->execute()) {
            $patient_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

             // verifying user password
            $salt = $patient_registration['salt'];
            $PSW = $patient_registration['PSW'];
            $hash = $this->checkhashSSHA($salt, $Password);
            // check for password equality
            if ($PSW == $hash) {
             
                // user authentication details are correct
                return $patient_registration;
            }
        } else {
            return NULL;
        }
    }

       
       
       public function getDoctorByEmailAndPassword($Email, $Password) {

        $stmt = $this->conn->prepare("SELECT * FROM doctor_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        if ($stmt->execute()) {
            $doctor_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();

             // verifying user password
            $salt = $doctor_registration['salt'];
            $PSW = $doctor_registration['Password'];
            $hash = $this->checkhashSSHA($salt, $Password);
            // check for password equality
            if ($PSW == $hash) {
                // user authentication details are correct
                return $doctor_registration;
            }
         else {
            
            return NULL;
        }
    }
       }
      
       public function getPathologistByEmailAndPassword($Email, $Password) {

        $stmt = $this->conn->prepare("SELECT * FROM pathologist_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);
           

        if ($stmt->execute()) {
            $pathologist_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            

             // verifying user password
            $salt = $pathologist_registration['salt'];
            $PSW = $pathologist_registration['PSW'];
            $hash = $this->checkhashSSHA($salt, $Password);
            
            // check for password equality
            if ($PSW == $hash) {
                
                // user authentication details are correct
                return $pathologist_registration;
            }
        } else {
            return NULL;
        }
    }

          /**
     * Get user by email and password
     */
   public function getPharmacistByEmailAndPassword($Email, $Password) {

        $stmt = $this->conn->prepare("SELECT * FROM pharmacist_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);
       
        if ($stmt->execute()) {
            $pharmacist_registration = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            
             // verifying user password
            $salt = $pharmacist_registration['salt'];
            $PSW = $pharmacist_registration['PSW'];
            $hash = $this->checkhashSSHA($salt, $Password);
            
            // check for password equality
            if ($PSW == $hash) {
                
                // user authentication details are correct
                return $pharmacist_registration;
            }
        } else {
            return NULL;
        }
    }

    /**
     * Check user is existed or not
     */
        
                
   public function isUserExisted($Email) {
        $stmt = $this->conn->prepare("SELECT Email from patient_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    } 
      
         
      public function isDoctorExisted($Email) {
        $stmt = $this->conn->prepare("SELECT Email from doctor_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // doctor existed 
            $stmt->close();
            return true;
        } else {
            // doctor not existed
            $stmt->close();
            return false;
        }
    } 
      
       public function isPathologistExisted($Email) {
        $stmt = $this->conn->prepare("SELECT Email from pathologist_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // doctor existed 
            $stmt->close();
            return true;
        } else {
            // doctor not existed
            $stmt->close();
            return false;
        }
    } 
      
      
    /**
     * Check user is existed or not
     */
        
                
   public function isPharmacistExisted($Email) {
        $stmt = $this->conn->prepare("SELECT Email from pharmacist_registration WHERE Email = ?");

        $stmt->bind_param("s", $Email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    } 

    



    
    // Encrypting password
     // @param password
     //returns salt and encrypted password
     
    public function hashSSHA($Password) {

        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($Password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

    
     // Decrypting password
     //@param salt, password
     // returns hash string
     
    public function checkhashSSHA($salt, $Password) {

        $hash = base64_encode(sha1($Password . $salt, true) . $salt);

        return $hash;
    } 

}

?>
