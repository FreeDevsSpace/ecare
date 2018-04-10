-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 10, 2018 at 11:47 PM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

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
  `Suggetion` varchar(300) DEFAULT NULL,
  `Prescribe_Medicine` varchar(200) DEFAULT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`Consultation_ID`, `Patient_ID`, `Doctor_ID`, `Problem`, `Additional_Info_Medicine`, `Additional_Info_Allergy`, `Suggetion`, `Prescribe_Medicine`, `Status`) VALUES
(4, 1, 1, 'Acne', 'Yes', 'Yes', '', '', 1),
(5, 1, 1, 'Hii', 'Hy', 'No', '', '', 1),
(6, 1, 8, 'hayauajfatailshgaiah', 'hsyaiaj', 'jayaiba', '', NULL, 0),
(7, 1, 8, 'skin disease', 'no', 'no', '', NULL, 0),
(8, 1, 8, 'skin disease', 'no', 'no', '', '', 0),
(9, 1, 6, 'Ok ok ', 'Ok ok ', 'Ok ok ', 'Ok ok ok', 'Ok ok ok', 1),
(10, 1, 7, 'Ok2', 'Ok2', 'Ok2', '', NULL, 0),
(11, 1, 8, 'Ok3', 'Ok3', 'Ok3', '', NULL, 0),
(12, 3, 12, 'Demo', 'Demo', 'Demo', 'Ok working ', 'Ok working ', 1);

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
(6, 'Aayush Patel', '9494649', '22-3-2018', 'Address :', 'Ahmedabad', 'jjj', 'MtdjVkMaUj3y55vO9IZlazeb9rtmZjA5M2VhMTdk', 'MBBS', 'Surgeons', 'Sanjivni ', 'gota', '5946', 'ff093ea17d'),
(7, 'aarya patel', '9638527410', '7-3-1994', 'Address :', 'Ahmedabad', 'aarya', '2Kzk1r+2sjXQ1kAUwSCEdaiSxAU2NmM2MDJiNzk5', 'MBBS', 'Surgeons', 'vshaja', 'jsiska', '120', '66c602b799'),
(8, 'Nishchal Dadhaniya', '9426672915', '12-1-1987', 'Address :', 'Gandhinagar', 'nishchal', 'HZ1LEVvzwh4PWDqWwnUoy4JaXTU0ZTU1MTQyOGFh', 'Ph.D.', 'Dermatology', 'pankti mul', 'hauaja', '250', '4e551428aa'),
(9, 'khyat patel', '965823741', '7-1-1990', 'Address :', 'Gandhinagar', 'khyat', 'JtAxRsBPzqzVuohoHV4wdTrf0FA1YmM4YmQ1Y2Nh', 'BDS', 'Dermatology', 'haiaia', 'hsuajak', '200', '5bc8bd5cca'),
(10, 'manushi chillar', '8258963508', '7-3-1981', 'Address :', 'Ahmedabad', 'manushi', 'IHKRmjOMBXpDfTLKtwMREbieii4xOTBkYjRkZmM0', 'BDS', 'Radiology', 'hahahab', 'hsuaja', '300', '190db4dfc4'),
(11, 'Preety Agrawal', '8659325647', '7-3-1973', 'Address :', 'Gandhinagar', 'preety', 'zAtLHWTD2PAE1Jk7HT+YwkYtgbwxNDE4YWYwYWZk', 'BDS', 'Surgeons', 'bajama', 'uaiaka', '150', '1418af0afd'),
(12, 'nidhi', '9638080527', '26-6-1990', 'Address :', 'Ahmedabad', 'nidhi', 'CSHm0toDIYtjGvve/IvwKtROIW4yMTAxM2MzNDdj', 'MBBS', 'Surgeons', 'gauaja', 'bsuwna', '200', '21013c347c');

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
(3, 'Anurag Basu', '9568356258', '7-4-1976', 'M.Sc.', '', 'A+', 'anurag', 'V7bRERuOc7lAgrlzuX4udUhFTpgxNDllNDY0YTlk', '149e464a9d'),
(4, 'Parag vachhani', '8685985214', '20-1-1993', 'M.D.', 'hajajak', 'A+', 'parag', 'zzRjoijVnHZ8LWeHne1IgeCtpAswNzVjY2FhNGU5', '075ccaa4e9'),
(5, 'Pari vyas', '9425807365', '8-4-2018', 'M.Sc.', 'haiakak', 'A+', 'pari', 'WwSyvNrIMNY/ck0x+EwapWD75yYwNDMwMDYyNzlk', '043006279d'),
(6, 'asd', '83837', '6/5/', 'M.D.', 'xgxhx', 'A+', 'asd', 'PgMF/iGyV6xG+f3TBmpOvMAYNoEzYTQ3MGRhYTYw', '3a470daa60'),
(7, 'pooja veru', '8596324785', '10-10-1994', 'M.D.', 'bs in dbisnausd\n', 'AB-', 'pooja', '1tcWA0F1Xpnx84Ba9eAAt7qxgRRhZWEzMDUyNzY2', 'aea3052766'),
(8, 'geeta fogat', '9696985230', '', 'M.D.', 'hsiansjs', 'B+', 'geeta', 'c6LBA+fmv222K0osGdWpliQe8gk0NjU1ODlmNTRl', '465589f54e'),
(9, 'antra', '9643949764', '25-7-2008', 'M.D.', 'BB sianabs', 'A+', 'antra', '9QkFV8us54tTwfd8Vu1cIxmUqiE0OGE2NmIzN2M5', '48a66b37c9'),
(10, 'anita', '9865986596', '13-4-2005', 'M.D.', 'baujaa', 'A+', 'anita', 'DYmOCjgTm0rZOxDS3jxkeBL/p4UzM2JlODNmMGZk', '33be83f0fd');

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
(2, 'hauahs', '946464', '12-3-2018', 'abb', 'shwnan', 'A+', 'e9fRgVeTlC/4NwYbizsuA93r+Ck1YmE5MWZlODRl', '5ba91fe84e'),
(3, 'bansi', '8585836950', '4-4-2011', 'bansi', 'ahiakama', 'O-', 'qulYCp69UwyaQzLPq/YkoJR/4qhiMzg4MjllNTA3', 'b38829e507'),
(4, 'Chirag patel', '7878596523', '30-4-1994', 'chirag', 'bauaka', 'A+', 'CD+KsLX1n0MK/biaRv+XoyMSNcRmYmMxNjk3NjQx', 'fbc1697641'),
(5, 'Anila patel', '9465820718', '5-4-1991', 'anila', 'ahiaka', 'A+', 'RmIJE2nzRQvIWS86CvMqXkajV6liYjdkZTk2NDk2', 'bb7de96496'),
(6, 'maya', '9850986532', '18-12-1980', 'maya', 'yahwbeuskabsbs', 'AB+', 'DEDuA4wgu54K3udnUI8kQusdoU81ZDQ0ZmJlY2Vi', '5d44fbeceb'),
(7, 'aasha ', '9434945870', '14-4-2009', 'aasha', 'haiabja', 'A+', 'gYp23+Z2/lRk6xCbTwQVG+G+j3ZhY2UxZjM2MTgy', 'ace1f36182');

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
(1, 'gg', '88', '8-4-2018', 'B.Pharm', 'gfgg', 'A+', 'll', 'T8HPuNHG2//5zTu5/1RfRe44U2I4YzRkMjZjOGE1', '8c4d26c8a5'),
(2, 'cccc', '68668', '5-8-1987', 'B.Pharm', 'vjcuc', 'A+', 'cccc', 'eQ80mh4PfBRMsxRj43rfH67c9jw5MmY3ZmZmMDFl', '92f7fff01e'),
(3, 'ggg', '832838255', '7-11-1978', 'B.Pharm', 'cycuc', 'A+', 'ggg', 'YoleRk6j98JsgOAXaqdJsjyzzE0yZTM3MzJiNmY3', '2e3732b6f7'),
(4, 'hasvi', '943494', '17-7-1991', 'Ph.D.', 'gsuqia', 'A+', 'hasvi', 'InsOulInrYhVgUPrnQZpj85s98NjZWYzN2IxMTIz', 'cef37b1123'),
(5, 'Kavya', '8529637410', '17-9-1992', 'B.Pharm', 'hauaikq', 'A+', 'kavya', 'z/JouoTj20TDyPPiw2UmpwCHk244OWY5ZDRjNDVh', '89f9d4c45a'),
(6, 'aneri patel', '9398520741', '23-8-1984', 'B.Pharm', 'gausja\n', 'A+', 'aneri', '69+poga/zZNpih6zNqXYnY7lhJU1MTYxNTliZjcz', '516159bf73'),
(7, 'niti', '9696321212', '22-2-1997', 'M.Sc.', 'haian', 'A+', 'niti', 'eJe1UcSpSbBgS3tdqbRB21/KsjEyZjNiNjUyZmVl', '2f3b652fee'),
(8, 'mitti', '8586956320', '15-11-1985', 'B.Pharm', 'hsuabhd', 'A+', 'mitti', 'LsctXndqCCCVKqkw6kYDmc89mfhiMGZmNzk3YWJm', 'b0ff797abf');

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
  MODIFY `Consultation_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `doctor_registration`
--
ALTER TABLE `doctor_registration`
  MODIFY `Doctor_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `pathologist_registration`
--
ALTER TABLE `pathologist_registration`
  MODIFY `Pathologist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `patient_registration`
--
ALTER TABLE `patient_registration`
  MODIFY `Patient_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pharmacist_registration`
--
ALTER TABLE `pharmacist_registration`
  MODIFY `Pharmacist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
