-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2020 at 08:33 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+07:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `groot`
--

-- --------------------------------------------------------

--
-- Table structure for table `departs`
--

CREATE TABLE `departs` (
  `Id` int(11) NOT NULL,
  `Name` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `departs`
--
ALTER TABLE `departs`
  ADD PRIMARY KEY (`Id`);
ALTER TABLE `departs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

INSERT INTO `departs` (`Id`, `Name`) VALUES
(0, NULL),
(1, 'Dpt'),
(2, 'NTU'),
(3, 'JAV..A');


--
-- Table structure for table `records`
--

CREATE TABLE `records` (
  `Id` int(11) NOT NULL,
  `Type` bit(1) DEFAULT NULL,
  `Reason` varchar(255) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `StaffId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `records`
  ADD PRIMARY KEY (`Id`);
ALTER TABLE `records`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

INSERT INTO `records` (`Id`, `Type`, `Reason`, `Date`, `StaffId`) VALUES
(0, NULL, 'đăng ký cho vui', '2020-03-26', NULL),
(1, b'0', 'thích', '2010-03-26', 0),
(2, b'1', 'test ', '2020-03-28', 2),
(3, b'1', 'test 4', '2020-02-02', 4),
(4, b'1', 'test 5', '2020-02-29', 2),
(5, b'1', 'test 6', '2020-02-28', 2),
(6, b'1', 'test 7', '2018-02-18', 4),
(7, b'0', 'tháng tư là lời nói dối của em', '2020-04-30', 4),
(8, b'0', 'điệp viên Sáu Thiệu', '2020-04-02', 4),
(9, b'0', 'corona', '2020-02-02', 4),
(10, b'0', 'bow` troy` is Blue, coin coo is Black, lomb nack is Brown', '2019-02-19', 2);



CREATE TABLE `staffs` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Photo` text CHARACTER SET utf8 DEFAULT NULL,
  `Email` text CHARACTER SET utf8 DEFAULT NULL,
  `Phone` text CHARACTER SET utf8 DEFAULT NULL,
  `Salary` double DEFAULT NULL,
  `Level` int(2) DEFAULT NULL,
  `Notes` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `DepartId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `staffs`
  ADD PRIMARY KEY (`Id`);
ALTER TABLE `staffs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

INSERT INTO `staffs` (`Id`, `Name`, `Gender`, `Birthday`, `Photo`, `Email`, `Phone`, `Salary`, `Level`, `Notes`, `DepartId`) VALUES
(0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
(1, 'Company', NULL, NULL, 'Co.Ltd;.psd', '*@indivisys.jp', '(+84)', NULL, 0, 'Hạt muối hạt vàng, phải chia dân làng, lòng Dít đau như cắt, nước mắt đầm đìa', 1),
(2, 'Thọ MC', b'0', NULL, 'thomc.psd', 'thomc@ntu', '0258 3831 149', NULL, 1, 'trainer', 2),
(4, 'Huấn Rose', b'1', '1984-01-01', 'Rose.psd', 'captain@', '()', 9, 3, 'Không làm mà đòi có ăn thì ăn đầu buồi, ăn cứt', 0),
(5, 'trainee 1', b'0', NULL, '1.psd', 'luongngando@', '+84', 1000, 4, 'học viên 1 (nv cty)', 1),
(6, 'trainee 2', b'1', NULL, '2.psd', ' ', ' ', NULL, 2, 'học viên 2 (fresher)', 1),
(7, 'trainee n', NULL, NULL, 'anymous.psd', NULL, NULL, 2, NULL, ' ', 2),
(8, 'staff8', NULL, NULL, 'photo8', 'staff8@mail', '123', 1234, NULL, ' ', 1),
(9, 'staff9', b'0', NULL, 'staff9.psd', 'staff9@', '()', 345, 0, ' ', 2),
(10, 'Duy Mạnh', b'1', '1975-05-22', '10_ThienManh.psd', NULL, '0989026282', NULL, NULL, 'Vợ con anh Mạnh khỏe', 3),
(15, 'Trang Hạ', b'0', NULL, 's0.psd', 'trangha75@yahoo.com', 'trangha.vn', 1234, 3, 'Nụ cười cô gái năm ấy', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(128) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(128) CHARACTER SET utf8 NOT NULL,
  `Fullname` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Username`);
--

INSERT INTO `users` (`Username`, `Password`, `Fullname`) VALUES
('root', 'root', NULL),
('U23', 'admin', 'Hải quay xe');



CREATE   VIEW `recorddetails`
 AS  select `r`.`Id` AS `RecordNo`,`r`.`Type` AS `Type`,`r`.`Reason` AS `Reason`,`r`.`Date` AS `Date`,`r`.`StaffId` AS `StaffNo`,`s`.`Name` AS `StaffName`,`s`.`Phone` AS `Contact`,`s`.`Email` AS `Email`,`s`.`DepartId` AS `DepartNo`,`d`.`Name` AS `Department`
 from ( `records` `r`
    left join (`staffs` `s` left join `departs` `d`
                        on(`s`.`DepartId` = `d`.`Id`)
        ) on(`s`.`Id` = `r`.`StaffId`) ) ;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
