-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2018 at 04:05 PM
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
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `Appointment_ID` int(10) NOT NULL,
  `Patient_ID` int(10) NOT NULL,
  `Doctor_ID` int(10) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0',
  `DelStatus` tinyint(4) NOT NULL DEFAULT '0',
  `Timeslote` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`Appointment_ID`, `Patient_ID`, `Doctor_ID`, `Date`, `Status`, `DelStatus`, `Timeslote`) VALUES
(19, 1, 6, '12-4-2018', 0, 0, '3:00 PM to 3:15 PM'),
(20, 6, 7, '17-4-2018', 0, 0, '3:45 PM to 4:00 PM'),
(21, 6, 6, '19-4-2018', 0, 0, '3:00 PM to 3:15 PM'),
(22, 6, 6, '17-4-2018', 0, 0, '3:00 PM to 3:15 PM'),
(23, 2, 11, '25-4-2018', 0, 0, '3:45 PM to 4:00 PM');

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
(6, 1, 8, 'back pain', 'No', 'No', '', NULL, 0),
(7, 1, 8, 'skin disease', 'no', 'no', '', NULL, 0),
(8, 1, 8, 'skin disease', 'no', 'no', '', '', 0),
(9, 1, 6, 'Ok ok ', 'Ok ok ', 'Ok ok ', 'Ok ok ok', 'Ok ok ok', 1),
(10, 1, 7, 'cold cough', 'No', 'No', 'rest', 'citrazine', 1),
(11, 1, 8, 'Ok3', 'Ok3', 'Ok3', '', '', 0),
(12, 3, 12, 'Demo', 'Demo', 'Demo', 'Ok working ', 'Ok working ', 1),
(13, 1, 7, 'I gacacatcatxt', 'gayctacgcatcaca', 'bauvahvayvyavy', 'sbuaja', 'vsuaja', 1),
(14, 1, 12, 'hauahah', 'abysha', 'gaianvsuska ', '', NULL, 0),
(15, 6, 6, 'Body pain', 'No', 'No', '', NULL, 0),
(16, 6, 6, 'cough', 'no', 'no', 'do not drink cold water', 'combiflme', 1),
(17, 6, 8, 'stomachache', 'No', 'No', 'take rest', 'feaxon', 1),
(18, 10, 12, 'backpain', 'no', 'no', '', NULL, 0),
(19, 2, 11, 'cavity', 'no', 'no', 'bdjan', 'bshaj', 1);

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
(6, 'Aayush Patel', '9494649', '22-3-2018', 'Address :', 'Ahmedabad', 'jjj', 'MtdjVkMaUj3y55vO9IZlazeb9rtmZjA5M2VhMTdk', 'MBBS', 'Surgeons', 'Sanjivni ', 'gota', '200', 'ff093ea17d'),
(7, 'aarya patel', '9638527410', '7-3-1994', 'Address :', 'Ahmedabad', 'aarya', '2Kzk1r+2sjXQ1kAUwSCEdaiSxAU2NmM2MDJiNzk5', 'MBBS', 'Surgeons', 'Aashta ', 'sola', '120', '66c602b799'),
(8, 'Nishchal Dadhaniya', '9426672915', '12-1-1987', 'Address :', 'Gandhinagar', 'nishchal', 'HZ1LEVvzwh4PWDqWwnUoy4JaXTU0ZTU1MTQyOGFh', 'Ph.D.', 'Dermatology', 'pankti ', 'thaltej', '250', '4e551428aa'),
(9, 'khyat patel', '965823741', '7-1-1990', 'Address :', 'Gandhinagar', 'khyat', 'JtAxRsBPzqzVuohoHV4wdTrf0FA1YmM4YmQ1Y2Nh', 'BDS', 'Dermatology', 'avasthi', 'sector 2', '200', '5bc8bd5cca'),
(10, 'manushi chillar', '8258963508', '7-3-1981', 'Address :', 'Ahmedabad', 'manushi', 'IHKRmjOMBXpDfTLKtwMREbieii4xOTBkYjRkZmM0', 'BDS', 'Radiology', 'Tammana', 'gota', '300', '190db4dfc4'),
(11, 'Preety Agrawal', '8659325647', '7-3-1973', 'Address :', 'Gandhinagar', 'preety', 'zAtLHWTD2PAE1Jk7HT+YwkYtgbwxNDE4YWYwYWZk', 'BDS', 'Surgeons', 'Mansi', 'infocity', '150', '1418af0afd'),
(12, 'nidhi', '9638080527', '26-6-1990', 'Address :', 'Ahmedabad', 'nidhi', 'CSHm0toDIYtjGvve/IvwKtROIW4yMTAxM2MzNDdj', 'MBBS', 'Surgeons', 'Gaurav', 'sola', '200', '21013c347c'),
(13, 'Aarav shah', '9856236589', '17-1-1987', 'sector 4', 'Gandhinagar', 'aarav@gmail.com', 'u7/ErrClykqOrYDXUELyWVLeVXo4ZDFhMzlhMjE2', 'MBBS', 'Surgeons', 'Prastuti ', 'infocity G', '300', '8d1a39a216'),
(15, 'karan patel', '9639638522', '8-5-1990', 'Ratnaraj Gandhinagar', 'Gandhinagar', 'karan', '89uRxq7SmuLURq+XdAyITOdNMWs3MzU3ODdkMGQw', 'MBBS', 'Surgeons', 'Kaya Hospi', 'Kh 0 Gandh', '300', '735787d0d0');

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
(1, 'hwhs', '646464', '94646', 'M.D.', 'gota', 'A+', 'bb', 'aQkk6Fl9ogP1EVMMvamdhm2oR/ljYjQzOTlhZTJm', 'cb4399ae2f'),
(3, 'Anurag Basu', '9568356258', '7-4-1976', 'M.Sc.', 'sola', 'A+', 'anurag', 'V7bRERuOc7lAgrlzuX4udUhFTpgxNDllNDY0YTlk', '149e464a9d'),
(4, 'Parag vachhani', '8685985214', '20-1-1993', 'M.D.', 'navrangpura', 'A+', 'parag', 'zzRjoijVnHZ8LWeHne1IgeCtpAswNzVjY2FhNGU5', '075ccaa4e9'),
(5, 'Pari vyas', '9425807365', '8-4-2018', 'M.Sc.', 'viratnagar', 'A+', 'pari', 'WwSyvNrIMNY/ck0x+EwapWD75yYwNDMwMDYyNzlk', '043006279d'),
(6, 'asd', '83837', '6/5/', 'M.D.', 'gota', 'A+', 'asd', 'PgMF/iGyV6xG+f3TBmpOvMAYNoEzYTQ3MGRhYTYw', '3a470daa60'),
(7, 'pooja veru', '8596324785', '10-10-1994', 'M.D.', 'sola', 'AB-', 'pooja', '1tcWA0F1Xpnx84Ba9eAAt7qxgRRhZWEzMDUyNzY2', 'aea3052766'),
(8, 'geeta fogat', '9696985230', '', 'M.D.', 'bapunagar', 'B+', 'geeta', 'c6LBA+fmv222K0osGdWpliQe8gk0NjU1ODlmNTRl', '465589f54e'),
(9, 'antra', '9643949764', '25-7-2008', 'M.D.', 'sola', 'A+', 'antra', '9QkFV8us54tTwfd8Vu1cIxmUqiE0OGE2NmIzN2M5', '48a66b37c9'),
(10, 'manu', '38688', '15-4-1996', 'M.D.', 'cuxyxy', 'A+', 'manu', 'WKd36pramWXMOFB8MoRvqnDFynBjYzI1MzdlZDI3', 'cc2537ed27'),
(11, 'gauau', '64349464', '12-4-2018', 'M.D.', 'vayaja', 'A+', 'ahj', '3a7Pwn1Z0nUJGvN3i/Z/YzqrRFFlOGFjNzMwYzIx', 'e8ac730c21');

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
(1, 'aarav', '8464654236', '22-3-2005', 'a', 'gsysja', 'A+', 'CFl0RCX/bT9X+OC1vcn157vytWgwNDY3YjM4NjA0', '0467b38604'),
(2, 'hauahs', '946464', '12-3-2018', 'abb', 'shwnan', 'A+', 'e9fRgVeTlC/4NwYbizsuA93r+Ck1YmE5MWZlODRl', '5ba91fe84e'),
(3, 'bansi', '8585836950', '4-4-2011', 'bansi', 'ahiakama', 'O-', 'qulYCp69UwyaQzLPq/YkoJR/4qhiMzg4MjllNTA3', 'b38829e507'),
(4, 'Chirag patel', '7878596523', '30-4-1994', 'chirag', 'bauaka', 'A+', 'CD+KsLX1n0MK/biaRv+XoyMSNcRmYmMxNjk3NjQx', 'fbc1697641'),
(5, 'Anila patel', '9465820718', '5-4-1991', 'anila', 'ahiaka', 'A+', 'RmIJE2nzRQvIWS86CvMqXkajV6liYjdkZTk2NDk2', 'bb7de96496'),
(6, 'maya', '9850986532', '18-12-1980', 'maya', 'Aadarsh vihar', 'AB+', 'DEDuA4wgu54K3udnUI8kQusdoU81ZDQ0ZmJlY2Vi', '5d44fbeceb'),
(7, 'aasha ', '9434945870', '14-4-2009', 'aasha', 'haiabja', 'A+', 'gYp23+Z2/lRk6xCbTwQVG+G+j3ZhY2UxZjM2MTgy', 'ace1f36182'),
(8, 'Parita shah', '9586969230', '17-7-1993', 'parita@gmail.com', '304-aashirvad complex , behind himalliya mall , Ahmedabad', 'A+', 'VZtBAZLlOxZtyWNDUMm6BLIMdQZlYzE5YjQ0OTZh', 'ec19b4496a'),
(9, 'aarav patel', '9638529568', '17-7-2001', 'aarav@gmail.com', '204- aman complex , Gandhinagar', 'A+', 'WphSAqUhStb7POaRItntxBUlVVA3NDU4NzQ1ZGY2', '7458745df6'),
(10, 'manav ', '9638520784', '25-6-1997', 'manav', 'sector 2', 'AB+', '3ZruJVv9YlhvKRSa3RNM4uAlJitlN2QzMGQ5YWE2', 'e7d30d9aa6'),
(11, 'khyati patel', '9638527418', '17-6-2000', 'khyati', 'Rajkot', 'AB+', 'bYsiLzAN1SQjtF3r3ND1twJ6sp9kY2M2ZjAzY2Ez', 'dcc6f03ca3');

-- --------------------------------------------------------

--
-- Table structure for table `patient_treatment`
--

CREATE TABLE `patient_treatment` (
  `Treatment_ID` int(20) NOT NULL,
  `Patient_ID` int(20) NOT NULL,
  `Patient_Name` varchar(50) NOT NULL,
  `Blood_Group` varchar(20) NOT NULL,
  `Symptoms` varchar(500) NOT NULL,
  `Prescription` varchar(400) NOT NULL,
  `Report` varchar(200) NOT NULL,
  `Suggetion` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_treatment`
--

INSERT INTO `patient_treatment` (`Treatment_ID`, `Patient_ID`, `Patient_Name`, `Blood_Group`, `Symptoms`, `Prescription`, `Report`, `Suggetion`) VALUES
(1, 6, 'maya', 'AB+', 'zydyd', 'fufud', 'fifu', 'xuxu'),
(2, 6, 'maya', 'AB+', 'fever', 'combiflme', 'No report', 'take rest '),
(3, 11, 'khyati patel', 'AB+', 'yauahbahah', 'faya', 'gahaj', 'haua');

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
(8, 'mitti', '8586956320', '15-11-1985', 'B.Pharm', 'hsuabhd', 'A+', 'mitti', 'LsctXndqCCCVKqkw6kYDmc89mfhiMGZmNzk3YWJm', 'b0ff797abf'),
(9, 'aayu', '6464954635', '15-4-1997', 'B.Pharm', 'vauana', 'A+', 'aayu', '34mQXh3pDWqsSLm79bvEK2KUACs2ODY2MmExNzEw', '68662a1710'),
(10, 'hsia', '543484346', '11-4-2018', 'B.Pharm', 'vauan', 'A+', 'ah', '41/LvaHenMzF5YJMcJ7psbN+dDs4MmQ1NTc4NmJm', '82d55786bf'),
(11, 'hauau', '9564', '18-4-2018', 'B.Pharm', 'vsua', 'A+', 'ak', 'Ff6Hj/Kwfoh1yVzXhKqO8riFAEtmMDA5ZjEzYmUy', 'f009f13be2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`Appointment_ID`);

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
-- Indexes for table `patient_treatment`
--
ALTER TABLE `patient_treatment`
  ADD PRIMARY KEY (`Treatment_ID`);

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
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `Appointment_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `Consultation_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `doctor_registration`
--
ALTER TABLE `doctor_registration`
  MODIFY `Doctor_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `pathologist_registration`
--
ALTER TABLE `pathologist_registration`
  MODIFY `Pathologist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `patient_registration`
--
ALTER TABLE `patient_registration`
  MODIFY `Patient_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `patient_treatment`
--
ALTER TABLE `patient_treatment`
  MODIFY `Treatment_ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pharmacist_registration`
--
ALTER TABLE `pharmacist_registration`
  MODIFY `Pharmacist_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
