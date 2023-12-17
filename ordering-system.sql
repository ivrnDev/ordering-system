-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2023 at 08:04 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `activity_today`
--
CREATE DATABASE IF NOT EXISTS `activity_today` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `activity_today`;
--
-- Database: `attendance_info`
--
CREATE DATABASE IF NOT EXISTS `attendance_info` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `attendance_info`;

-- --------------------------------------------------------

--
-- Table structure for table `records`
--

CREATE TABLE `records` (
  `ID` int(11) NOT NULL,
  `Last Name` varchar(250) NOT NULL,
  `First Name` varchar(250) NOT NULL,
  `Section` varchar(250) NOT NULL,
  `Date` varchar(200) NOT NULL,
  `Time` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `records`
--

INSERT INTO `records` (`ID`, `Last Name`, `First Name`, `Section`, `Date`, `Time`) VALUES
(25, '', 'Ivan Rne', '', '', ''),
(26, 'Aflkwfaff', 'Ivan Ren VIlalmfofsfaf', 'fafwfawfs', 'fetegdgsd', 'gsdgdgsgs'),
(27, 'fafsfsf', 'jeoewjgee', 'sfdffse', 'efefsfsf', 'hrhzg'),
(28, '', '', '', '', ''),
(29, '', 'Ivan Ren VIllamora', 'w41242', '', ''),
(30, '', '', '', '', ''),
(31, '', 'hatdosfd', '', '', ''),
(32, '', 'kolodofd', '', '', ''),
(33, '', 'jdfkef', '', '', ''),
(34, '', 'edw', '', '', ''),
(35, '', '', '', 'sfsf', ''),
(36, '', '', '', '', 'efsef'),
(37, '', 'dvdvdvdvdvdvdvdvd', '', '', ''),
(38, '', '', '', '', ''),
(39, '', 'jokolosdadsdadsw', '', '', ''),
(40, '', 'hujufef', '', '', ''),
(41, '', 'juks', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `records`
--
ALTER TABLE `records`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- Database: `cc103`
--
CREATE DATABASE IF NOT EXISTS `cc103` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cc103`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Username` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Username`, `Password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `ID` int(11) NOT NULL,
  `Last Name` varchar(250) NOT NULL,
  `First Name` varchar(250) NOT NULL,
  `Middle Name` varchar(250) NOT NULL,
  `Username` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`ID`, `Last Name`, `First Name`, `Middle Name`, `Username`, `Password`) VALUES
(1000, 'Villamora', 'Ivan Ren', 'Manguiat', 'ivanren', 'ivanren');

-- --------------------------------------------------------

--
-- Table structure for table `pos`
--

CREATE TABLE `pos` (
  `Order ID` int(11) NOT NULL,
  `Product ID` int(11) NOT NULL,
  `Item` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pos`
--

INSERT INTO `pos` (`Order ID`, `Product ID`, `Item`, `Quantity`, `Price`, `Date`, `Time`) VALUES
(1000, 1, 'HAM & CHEESE', 2, 150, '2019-01-09', '03:44:06'),
(1001, 2, 'PEPPERONI', 1, 85, '2019-01-09', '03:44:06'),
(1002, 4, 'HAWAIIAN', 1, 85, '2019-01-09', '03:44:06'),
(1003, 3, 'VEGETARIAN', 1, 75, '2019-01-09', '03:44:06'),
(1004, 5, 'BACON', 3, 285, '2019-01-09', '03:44:06'),
(1005, 1, 'HAM & CHEESE', 1, 75, '2019-01-09', '03:50:42'),
(1006, 3, 'VEGETARIAN', 1, 75, '2019-01-09', '03:50:42'),
(1007, 4, 'HAWAIIAN', 1, 85, '2019-01-09', '03:50:42'),
(1008, 5, 'BACON', 1, 95, '2019-01-09', '03:50:42'),
(1009, 2, 'PEPPERONI', 1, 85, '2019-01-09', '03:50:42'),
(1010, 5, 'BACON', 1, 95, '2019-01-17', '00:51:37'),
(1011, 5, 'BACON', 1, 95, '2019-01-17', '00:51:44'),
(1012, 2, 'PEPPERONI', 1, 85, '2019-01-17', '00:51:44'),
(1013, 1, 'HAM & CHEESE', 1, 75, '2019-01-17', '00:51:44'),
(1014, 4, 'HAWAIIAN', 4, 340, '2019-06-28', '13:45:06'),
(1015, 5, 'BACON', 1, 95, '2019-06-28', '13:45:06'),
(1016, 3, 'VEGETARIAN', 2, 150, '2019-06-28', '13:45:06'),
(1017, 1, 'HAM & CHEESE', 3, 225, '2019-06-28', '13:45:06'),
(1018, 2, 'PEPPERONI', 1, 85, '2019-06-28', '13:45:06'),
(1019, 4, 'HAWAIIAN', 3, 255, '2019-06-30', '15:40:22'),
(1020, 5, 'BACON', 3, 285, '2019-06-30', '15:40:22'),
(1021, 4, 'HAWAIIAN', 4, 340, '2020-03-22', '03:47:32'),
(1022, 5, 'BACON', 4, 380, '2020-03-22', '03:47:33'),
(1023, 2, 'PEPPERONI', 3, 255, '2020-03-22', '03:47:33'),
(1024, 1, 'HAM & CHEESE', 1, 75, '2020-03-22', '03:47:33'),
(1025, 4, 'HAWAIIAN', 1, 85, '2020-03-28', '09:38:45'),
(1026, 3, 'VEGETARIAN', 1, 75, '2020-03-28', '09:38:46'),
(1027, 2, 'PEPPERONI', 1, 85, '2020-03-28', '09:38:46'),
(1028, 1, 'HAM & CHEESE', 2, 150, '2020-03-28', '09:38:46'),
(1029, 4, 'HAWAIIAN', 3, 255, '2020-07-12', '17:31:04'),
(1030, 3, 'VEGETARIAN', 3, 225, '2020-07-12', '17:31:05'),
(1031, 2, 'PEPPERONI', 3, 255, '2020-07-12', '17:31:05'),
(1032, 1, 'HAM & CHEESE', 6, 450, '2020-07-12', '17:31:05'),
(1033, 5, 'BACON', 6, 570, '2020-07-12', '17:31:05'),
(1034, 4, 'HAWAIIAN', 5, 425, '2020-11-03', '00:39:14'),
(1035, 3, 'VEGETARIAN', 3, 225, '2020-11-03', '00:39:15'),
(1036, 2, 'PEPPERONI', 4, 340, '2020-11-03', '00:39:15'),
(1037, 1, 'HAM & CHEESE', 9, 675, '2020-11-03', '00:39:15'),
(1038, 5, 'BACON', 9, 855, '2020-11-03', '00:39:15'),
(1039, 4, 'HAWAIIAN', 2, 170, '2021-01-05', '07:32:27'),
(1040, 5, 'BACON', 2, 190, '2021-01-05', '07:32:27'),
(1041, 3, 'VEGETARIAN', 1, 75, '2021-01-05', '07:32:27'),
(1042, 4, 'HAWAIIAN', 5, 425, '2021-01-06', '07:32:35'),
(1043, 5, 'BACON', 5, 475, '2021-01-06', '07:32:35'),
(1044, 3, 'VEGETARIAN', 1, 75, '2021-01-06', '07:32:35'),
(1045, 4, 'HAWAIIAN', 7, 595, '2021-07-02', '10:24:44'),
(1046, 5, 'BACON', 7, 665, '2021-07-02', '10:24:44'),
(1047, 3, 'VEGETARIAN', 3, 225, '2021-07-02', '10:24:44'),
(1048, 1, 'HAM & CHEESE', 1, 75, '2021-07-02', '10:24:44'),
(1049, 2, 'PEPPERONI', 2, 170, '2021-07-02', '10:24:44'),
(1050, 4, 'HAWAIIAN', 9, 765, '2021-11-03', '10:24:52'),
(1051, 5, 'BACON', 7, 665, '2021-11-03', '10:24:52'),
(1052, 3, 'VEGETARIAN', 3, 225, '2021-11-03', '10:24:52'),
(1053, 1, 'HAM & CHEESE', 1, 75, '2021-11-03', '10:24:52'),
(1054, 2, 'PEPPERONI', 2, 170, '2021-11-03', '10:24:52'),
(1055, 4, 'HAWAIIAN', 4, 340, '2021-11-13', '05:25:03'),
(1056, 3, 'VEGETARIAN', 2, 150, '2021-11-13', '05:25:04'),
(1057, 1, 'HAM & CHEESE', 2, 150, '2021-11-13', '05:25:04'),
(1058, 5, 'BACON', 4, 380, '2021-11-13', '05:25:04'),
(1059, 4, 'HAWAIIAN', 5, 425, '2022-04-06', '10:17:10'),
(1060, 3, 'VEGETARIAN', 3, 225, '2022-04-06', '10:17:11'),
(1061, 1, 'HAM & CHEESE', 2, 150, '2022-04-06', '10:17:11'),
(1062, 5, 'BACON', 5, 475, '2022-04-06', '10:17:11'),
(1063, 4, 'HAWAIIAN', 6, 510, '2022-04-08', '10:17:25'),
(1064, 3, 'VEGETARIAN', 4, 300, '2022-04-08', '10:17:25'),
(1065, 1, 'HAM & CHEESE', 3, 225, '2022-04-08', '10:17:25'),
(1066, 5, 'BACON', 6, 570, '2022-04-08', '10:17:25'),
(1067, 4, 'HAWAIIAN', 8, 680, '2022-07-13', '00:17:23'),
(1068, 3, 'VEGETARIAN', 4, 300, '2022-07-13', '00:17:23'),
(1069, 1, 'HAM & CHEESE', 3, 225, '2022-07-13', '00:17:23'),
(1070, 5, 'BACON', 11, 1045, '2022-07-13', '00:17:23'),
(1071, 4, 'HAWAIIAN', 3, 255, '2022-11-05', '19:23:35'),
(1072, 3, 'VEGETARIAN', 2, 150, '2022-11-05', '19:23:35'),
(1073, 5, 'BACON', 2, 190, '2022-11-05', '19:23:35'),
(1074, 1, 'HAM & CHEESE', 1, 75, '2022-11-05', '19:23:35'),
(1075, 2, 'PEPPERONI', 1, 85, '2022-11-05', '19:23:35'),
(1076, 4, 'HAWAIIAN', 1, 85, '2023-01-07', '17:22:46'),
(1077, 3, 'VEGETARIAN', 1, 75, '2023-01-07', '17:22:46'),
(1078, 5, 'BACON', 1, 95, '2023-01-07', '17:22:46'),
(1079, 2, 'PEPPERONI', 1, 85, '2023-01-07', '17:22:46'),
(1080, 1, 'HAM & CHEESE', 3, 225, '2023-01-07', '17:22:46'),
(1081, 3, 'VEGETARIAN', 3, 225, '2023-04-02', '22:16:01'),
(1082, 5, 'BACON', 2, 190, '2023-04-02', '22:16:01'),
(1083, 2, 'PEPPERONI', 2, 170, '2023-04-02', '22:16:01'),
(1084, 1, 'HAM & CHEESE', 1, 75, '2023-04-02', '22:16:01'),
(1085, 4, 'HAWAIIAN', 1, 85, '2023-04-02', '22:16:01'),
(1086, 4, 'HAWAIIAN', 2, 170, '2023-05-08', '22:52:42'),
(1087, 3, 'VEGETARIAN', 1, 75, '2023-05-08', '22:52:43'),
(1088, 1, 'HAM & CHEESE', 1, 75, '2023-05-08', '22:52:43');

-- --------------------------------------------------------

--
-- Table structure for table `salestable`
--

CREATE TABLE `salestable` (
  `Product ID` int(11) NOT NULL,
  `Item` varchar(111) NOT NULL,
  `Total Sales` int(111) NOT NULL,
  `Total Amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salestable`
--

INSERT INTO `salestable` (`Product ID`, `Item`, `Total Sales`, `Total Amount`) VALUES
(1, 'HAM & CHEESE', 43, 3225),
(2, 'PEPPERONI', 23, 1955),
(3, 'VEGETARIAN', 39, 2925),
(4, 'HAWAIIAN', 75, 6375),
(5, 'BACON', 81, 7695);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pos`
--
ALTER TABLE `pos`
  ADD PRIMARY KEY (`Order ID`);

--
-- Indexes for table `salestable`
--
ALTER TABLE `salestable`
  ADD PRIMARY KEY (`Product ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1001;

--
-- AUTO_INCREMENT for table `pos`
--
ALTER TABLE `pos`
  MODIFY `Order ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1089;

--
-- AUTO_INCREMENT for table `salestable`
--
ALTER TABLE `salestable`
  MODIFY `Product ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Database: `login`
--
CREATE DATABASE IF NOT EXISTS `login` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `login`;

-- --------------------------------------------------------

--
-- Table structure for table `logintable`
--

CREATE TABLE `logintable` (
  `Username` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `logintable`
--

INSERT INTO `logintable` (`Username`, `Password`) VALUES
('ivanren', 'ivanrenvillamora'),
('jomari', 'jomarimadriaga'),
('alexis', 'alexisespinoza'),
('kirby', 'kirbygalicano'),
('rhea', 'rheacorrea');
--
-- Database: `loginform`
--
CREATE DATABASE IF NOT EXISTS `loginform` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `loginform`;

-- --------------------------------------------------------

--
-- Table structure for table `logintable`
--

CREATE TABLE `logintable` (
  `ID` int(11) NOT NULL,
  `Username` varchar(250) NOT NULL,
  `Password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `logintable`
--

INSERT INTO `logintable` (`ID`, `Username`, `Password`) VALUES
(1, 'ivanren', 'ivanren');

-- --------------------------------------------------------

--
-- Table structure for table `studrecord`
--

CREATE TABLE `studrecord` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `age` int(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logintable`
--
ALTER TABLE `logintable`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `studrecord`
--
ALTER TABLE `studrecord`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logintable`
--
ALTER TABLE `logintable`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `studrecord`
--
ALTER TABLE `studrecord`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Database: `pet_shop`
--
CREATE DATABASE IF NOT EXISTS `pet_shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pet_shop`;

-- --------------------------------------------------------

--
-- Table structure for table `pets`
--

CREATE TABLE `pets` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `breed` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pets`
--

INSERT INTO `pets` (`id`, `name`, `breed`, `age`, `owner`, `address`) VALUES
(1, 'Yoshi', 'Aspin', 1, 'Lolo and Lola', 'Batangas'),
(2, 'Omicron', 'Aspin', 2, 'Lolo and Lola', 'Rizal'),
(3, 'Basha', 'Beagle', 4, 'Mama', 'Batangas');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pets`
--
ALTER TABLE `pets`
  ADD PRIMARY KEY (`id`);
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Table structure for table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Table structure for table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin DEFAULT NULL,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Table structure for table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

-- --------------------------------------------------------

--
-- Table structure for table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

--
-- Dumping data for table `pma__export_templates`
--

INSERT INTO `pma__export_templates` (`id`, `username`, `export_type`, `template_name`, `template_data`) VALUES
(1, 'root', 'server', 'CC103FinalDatabase', '{\"quick_or_custom\":\"quick\",\"what\":\"sql\",\"db_select[]\":[\"activity_today\",\"attendance_info\",\"cc103\",\"login\",\"loginform\",\"pet_shop\",\"phpmyadmin\",\"pos\",\"studentsdatabase\",\"teachersdatabase\",\"userregistration\",\"visitormanagement\"],\"aliases_new\":\"\",\"output_format\":\"sendit\",\"filename_template\":\"@SERVER@\",\"remember_template\":\"on\",\"charset\":\"utf-8\",\"compression\":\"none\",\"maxsize\":\"\",\"codegen_structure_or_data\":\"data\",\"codegen_format\":\"0\",\"csv_separator\":\",\",\"csv_enclosed\":\"\\\"\",\"csv_escaped\":\"\\\"\",\"csv_terminated\":\"AUTO\",\"csv_null\":\"NULL\",\"csv_structure_or_data\":\"data\",\"excel_null\":\"NULL\",\"excel_columns\":\"something\",\"excel_edition\":\"win\",\"excel_structure_or_data\":\"data\",\"json_structure_or_data\":\"data\",\"json_unicode\":\"something\",\"latex_caption\":\"something\",\"latex_structure_or_data\":\"structure_and_data\",\"latex_structure_caption\":\"Structure of table @TABLE@\",\"latex_structure_continued_caption\":\"Structure of table @TABLE@ (continued)\",\"latex_structure_label\":\"tab:@TABLE@-structure\",\"latex_relation\":\"something\",\"latex_comments\":\"something\",\"latex_mime\":\"something\",\"latex_columns\":\"something\",\"latex_data_caption\":\"Content of table @TABLE@\",\"latex_data_continued_caption\":\"Content of table @TABLE@ (continued)\",\"latex_data_label\":\"tab:@TABLE@-data\",\"latex_null\":\"\\\\textit{NULL}\",\"mediawiki_structure_or_data\":\"data\",\"mediawiki_caption\":\"something\",\"mediawiki_headers\":\"something\",\"htmlword_structure_or_data\":\"structure_and_data\",\"htmlword_null\":\"NULL\",\"ods_null\":\"NULL\",\"ods_structure_or_data\":\"data\",\"odt_structure_or_data\":\"structure_and_data\",\"odt_relation\":\"something\",\"odt_comments\":\"something\",\"odt_mime\":\"something\",\"odt_columns\":\"something\",\"odt_null\":\"NULL\",\"pdf_report_title\":\"\",\"pdf_structure_or_data\":\"data\",\"phparray_structure_or_data\":\"data\",\"sql_include_comments\":\"something\",\"sql_header_comment\":\"\",\"sql_use_transaction\":\"something\",\"sql_compatibility\":\"NONE\",\"sql_structure_or_data\":\"structure_and_data\",\"sql_create_table\":\"something\",\"sql_auto_increment\":\"something\",\"sql_create_view\":\"something\",\"sql_create_trigger\":\"something\",\"sql_backquotes\":\"something\",\"sql_type\":\"INSERT\",\"sql_insert_syntax\":\"both\",\"sql_max_query_size\":\"50000\",\"sql_hex_for_binary\":\"something\",\"sql_utc_time\":\"something\",\"texytext_structure_or_data\":\"structure_and_data\",\"texytext_null\":\"NULL\",\"yaml_structure_or_data\":\"data\",\"\":null,\"as_separate_files\":null,\"csv_removeCRLF\":null,\"csv_columns\":null,\"excel_removeCRLF\":null,\"json_pretty_print\":null,\"htmlword_columns\":null,\"ods_columns\":null,\"sql_dates\":null,\"sql_relation\":null,\"sql_mime\":null,\"sql_disable_fk\":null,\"sql_views_as_tables\":null,\"sql_metadata\":null,\"sql_drop_database\":null,\"sql_drop_table\":null,\"sql_if_not_exists\":null,\"sql_view_current_user\":null,\"sql_or_replace_view\":null,\"sql_procedure_function\":null,\"sql_truncate\":null,\"sql_delayed\":null,\"sql_ignore\":null,\"texytext_columns\":null}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Table structure for table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Table structure for table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"cc103\",\"table\":\"pos\"},{\"db\":\"cc103\",\"table\":\"salestable\"},{\"db\":\"cc103\",\"table\":\"admin\"},{\"db\":\"loginform\",\"table\":\"studrecord\"},{\"db\":\"loginform\",\"table\":\"loginTable\"},{\"db\":\"loginform\",\"table\":\"logintable\"},{\"db\":\"login\",\"table\":\"logintable\"},{\"db\":\"pet_shop\",\"table\":\"pets\"},{\"db\":\"cc103\",\"table\":\"employees\"},{\"db\":\"productsales\",\"table\":\"productsales\"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Table structure for table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Dumping data for table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'pos', 'sales', '{\"CREATE_TIME\":\"2023-04-04 04:12:41\",\"col_order\":[0,1,2,3,4],\"col_visib\":[1,1,1,1,1]}', '2023-04-04 12:59:17');

-- --------------------------------------------------------

--
-- Table structure for table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin DEFAULT NULL,
  `data_sql` longtext COLLATE utf8_bin DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data for table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2023-05-09 06:02:55', '{\"Console\\/Mode\":\"show\",\"Console\\/Height\":0}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Table structure for table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indexes for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indexes for table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Database: `pos`
--
CREATE DATABASE IF NOT EXISTS `pos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pos`;

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `Order ID` int(11) NOT NULL,
  `ProductID` varchar(11) NOT NULL,
  `Item` varchar(50) NOT NULL,
  `Quantity` varchar(50) NOT NULL,
  `Price` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`Order ID`, `ProductID`, `Item`, `Quantity`, `Price`) VALUES
(1000, '123', '2132', 'fwef', '312');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`Order ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `Order ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1001;
--
-- Database: `studentsdatabase`
--
CREATE DATABASE IF NOT EXISTS `studentsdatabase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `studentsdatabase`;

-- --------------------------------------------------------

--
-- Table structure for table `studentrecords`
--

CREATE TABLE `studentrecords` (
  `ID` int(11) NOT NULL,
  `First Name` varchar(250) NOT NULL,
  `Last Name` varchar(250) NOT NULL,
  `Section` varchar(250) NOT NULL,
  `Date` varchar(15) NOT NULL,
  `Time` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentrecords`
--

INSERT INTO `studentrecords` (`ID`, `First Name`, `Last Name`, `Section`, `Date`, `Time`) VALUES
(1, 'Ivan Ren', 'Villamora', 'SBIT-1G', '12/13/2022', '2:00 Pm'),
(2, 'Jomari', 'Madriaga', 'SBIT-1G', '12/14/2022', '11:59 AM'),
(3, 'Alexis Aice', 'Espinoza', 'SBIT-1G', '12/15/2022', '2:32 PM'),
(4, 'Jonathan', 'Santos', 'SBIT-1G', '12/15/2022', '3:25 PM'),
(5, 'Kirby', 'Galicano', 'SBIT-1G', '12/15/2022', '6:00 PM'),
(6, 'kfsfs', 'fafs', 'fasf', 'fsa', 'fa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studentrecords`
--
ALTER TABLE `studentrecords`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `studentrecords`
--
ALTER TABLE `studentrecords`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Database: `teachersdatabase`
--
CREATE DATABASE IF NOT EXISTS `teachersdatabase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `teachersdatabase`;

-- --------------------------------------------------------

--
-- Table structure for table `teacherrecords`
--

CREATE TABLE `teacherrecords` (
  `ID` int(11) NOT NULL,
  `First Name` varchar(300) NOT NULL,
  `Last Name` varchar(300) NOT NULL,
  `Subject` varchar(50) NOT NULL,
  `Date` varchar(15) NOT NULL,
  `Time` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacherrecords`
--

INSERT INTO `teacherrecords` (`ID`, `First Name`, `Last Name`, `Subject`, `Date`, `Time`) VALUES
(1, 'Balasta', 'Arnel', 'WS101', '12/13/2022', '3:30 PM'),
(2, 'Gonzaga', 'Mikee', 'CC101', '12/14/2022', '7:00 AM'),
(3, 'Luna', 'Merlie', 'NSTP', '12/23/2022', '1:09 PM'),
(4, 'Uy', 'Josephine', 'MMW', '12/15/2022', '4:09 PM'),
(5, 'fw', 'fw', 'fw', 'fw', 'fw'),
(6, '', 'odw', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `teacherrecords`
--
ALTER TABLE `teacherrecords`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `teacherrecords`
--
ALTER TABLE `teacherrecords`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Database: `userregistration`
--
CREATE DATABASE IF NOT EXISTS `userregistration` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `userregistration`;

-- --------------------------------------------------------

--
-- Table structure for table `registration_java`
--

CREATE TABLE `registration_java` (
  `id` int(11) NOT NULL,
  `fname` varchar(150) NOT NULL,
  `mname` varchar(150) NOT NULL,
  `lname` varchar(150) NOT NULL,
  `addr` varchar(250) NOT NULL,
  `cont` int(100) NOT NULL,
  `gend` varchar(100) NOT NULL,
  `user` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registration_java`
--
ALTER TABLE `registration_java`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `registration_java`
--
ALTER TABLE `registration_java`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Database: `visitormanagement`
--
CREATE DATABASE IF NOT EXISTS `visitormanagement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `visitormanagement`;

-- --------------------------------------------------------

--
-- Table structure for table `crudrecords`
--

CREATE TABLE `crudrecords` (
  `ID` int(11) NOT NULL,
  `First Name` varchar(250) NOT NULL,
  `Last Name` varchar(250) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `Email` varchar(250) NOT NULL,
  `Phone Number` int(11) NOT NULL,
  `Purpose` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `crudrecords`
--

INSERT INTO `crudrecords` (`ID`, `First Name`, `Last Name`, `Address`, `Email`, `Phone Number`, `Purpose`) VALUES
(2, 'Vince', 'Mercadillo', 'Blk.12 Lot 16 Sta. Barbara St. Quezon City', 'vincejonas@gmail.com', 2147483647, 'Building Inspection'),
(3, 'Krizha', 'Cortez', 'Blk. 13 Lot 18 Dolmar St. Caloocan City', 'krizha9832gmail.com', 921422321, 'Inquiries'),
(4, 'Althea342', 'De', 'Blk.', 'althea_deluna@gmail.com', 2147483647, 'Apartment');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `crudrecords`
--
ALTER TABLE `crudrecords`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `crudrecords`
--
ALTER TABLE `crudrecords`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
