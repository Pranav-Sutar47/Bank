-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2024 at 03:02 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `info`
--

CREATE TABLE `info` (
  `AcNo` bigint(10) NOT NULL,
  `ACType` varchar(9) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `AdharNo` varchar(12) NOT NULL,
  `Balance` double NOT NULL,
  `MobileNo` varchar(10) NOT NULL,
  `DateTime` datetime NOT NULL DEFAULT current_timestamp(),
  `Mpin` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`AcNo`, `ACType`, `Name`, `AdharNo`, `Balance`, `MobileNo`, `DateTime`, `Mpin`) VALUES
(6043388298, 'Savings', 'Pranav', '628294052027', 449796000, '9730628692', '2024-01-06 19:01:54', 1234),
(6043388306, 'Saving', 'Rohan', '987654123654', 209050, '9765464804', '2024-01-07 10:31:41', 4747);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`AcNo`),
  ADD UNIQUE KEY `AdharNo` (`AdharNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `info`
--
ALTER TABLE `info`
  MODIFY `AcNo` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6043388310;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
