-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2018 at 05:17 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.1.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecare`
--

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `Consultation_ID` int(10) NOT NULL,
  `Patient_ID` int(10) DEFAULT NULL,
  `Doctor_ID` int(10) DEFAULT NULL,
  `Problem` varchar(500) DEFAULT NULL,
  `Additional_Info_Medicine` varchar(200) DEFAULT NULL,
  `Additional_Info_Allergy` varchar(200) DEFAULT NULL,
  `Suggetion` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`Consultation_ID`, `Patient_ID`, `Doctor_ID`, `Problem`, `Additional_Info_Medicine`, `Additional_Info_Allergy`, `Suggetion`) VALUES
(1, 1, 1, 'Stavanger', 'vgh', 'Norway', '');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_registration`
--

CREATE TABLE `doctor_registration` (
  `Doctor_ID` int(10) NOT NULL,
  `Full_Name` varchar(50) NOT NULL,
  `Mobile_Number` varchar(20) NOT NULL,
  `DOB` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Qualification` varchar(80) NOT NULL,
  `Specialization` varchar(80) NOT NULL,
  `Hospital_Name` varchar(10) NOT NULL,
  `Hospital_Address` varchar(10) NOT NULL,
  `Fees` varchar(10) NOT NULL,
  `salt` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_registration`
--

INSERT INTO `doctor_registration` (`Doctor_ID`, `Full_Name`, `Mobile_Number`, `DOB`, `Address`, `City`, `Email`, `Password`, `Qualification`, `Specialization`, `Hospital_Name`, `Hospital_Address`, `Fees`, `salt`) VALUES
(6, 'Aayush Patel', '9494649', '22-3-2018', 'Address :', 'Ahmedabad', 'jjj', 'MtdjVkMaUj3y55vO9IZlazeb9rtmZjA5M2VhMTdk', 'MBBS', 'Surgeons', 'Sanjivni ', 'gota', '5946', 'ff093ea17d');

-- --------------------------------------------------------

--
-- Table structure for table `pathologist_registration`
--

CREATE TABLE `pathologist_registration` (
  `Pathologist_ID` int(10) NOT NULL,
  `Full_Name` varchar(50) NOT NULL,
  `Mobile_Number` varchar(20) NOT NULL,
  `DOB` varchar(50) NOT NULL,
  `Qualification` varchar(80) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Blood_Group` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `PSW` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pathologist_registration`
--

INSERT INTO `pathologist_registration` (`Pathologist_ID`, `Full_Name`, `Mobile_Number`, `DOB`, `Qualification`, `Address`, `Blood_Group`, `Email`, `PSW`, `salt`) VALUES
(1, 'hwhs', '646464', '94646', 'M.D.', 'vsvah\n', 'A+', 'bb', 'aQkk6Fl9ogP1EVMMvamdhm2oR/ljYjQzOTlhZTJm', 'cb4399ae2f'),
(2, '', '', '', 'M.D.', '', 'A+', '', '5WlZE3PUV0ytgMM5jHQTChECM+g4Y2E1ZDkxZmFi', '8ca5d91fab');

-- --------------------------------------------------------

--
-- Table structure for table `patient_registration`
--

CREATE TABLE `patient_registration` (
  `Patient_ID` int(10) NOT NULL,
  `Full_Name` varchar(50) NOT NULL,
  `Mobile_Number` varchar(20) NOT NULL,
  `DOB` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Blood_Group` varchar(50) NOT NULL,
  `PSW` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_registration`
--

INSERT INTO `patient_registration` (`Patient_ID`, `Full_Name`, `Mobile_Number`, `DOB`, `Email`, `Address`, `Blood_Group`, `PSW`, `salt`) VALUES
(1, 'hsjs', '8464654236', '22-3-2005', 'a', 'gsysja', 'A+', 'CFl0RCX/bT9X+OC1vcn157vytWgwNDY3YjM4NjA0', '0467b38604'),
(2, 'hauahs', '946464', '12-3-2018', 'abb', 'shwnan', 'A+', 'e9fRgVeTlC/4NwYbizsuA93r+Ck1YmE5MWZlODRl', '5ba91fe84e');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist_registration`
--

CREATE TABLE `pharmacist_registration` (
  `Pharmacist_ID` int(10) NOT NULL,
  `Full_Name` varchar(50) NOT NULL,
  `Mobile_Number` varchar(20) NOT NULL,
  `DOB` varchar(50) NOT NULL,
  `Qualification` varchar(80) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Blood_Group` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `PSW` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacist_registration`
--

INSERT INTO `pharmacist_registration` (`Pharmacist_ID`, `Full_Name`, `Mobile_Number`, `DOB`, `Qualification`, `Address`, `Blood_Group`, `Email`, `PSW`, `salt`) VALUES
(1, 'gg', '88', '8-4-2018', 'B.Pharm', 'gfgg', 'A+', 'll', 'T8HPuNHG2//5zTu5/1RfRe44U2I4YzRkMjZjOGE1', '8c4d26c8a5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`Consultation_ID`);

--
-- Indexes for table `doctor_registration`
--
ALTER TABLE `doctor_registration`
  ADD PRIMARY KEY (`Doctor_ID`);

--
-- Indexes for table `pathologist_registration`
--
ALTER TABLE `pathologist_registration`
  ADD PRIMARY KEY (`Pathologist_ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `patient_registration`
--
ALTER TABLE `patient_registration`
  ADD PRIMARY KEY (`Patient_ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `pharmacist_registration`
--
ALTER TABLE `pharmacist_registration`
  ADD PRIMARY KEY (`Pharmacist_ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `Consultation_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctor_registration`
--
ALTER TABLE `doctor_registration`
  MODIFY `Doctor_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pathologist_registration`
--
ALTER TABLE `pathologist_registration`
  MODIFY `Pathologist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `patient_registration`
--
ALTER TABLE `patient_registration`
  MODIFY `Patient_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pharmacist_registration`
--
ALTER TABLE `pharmacist_registration`
  MODIFY `Pharmacist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
