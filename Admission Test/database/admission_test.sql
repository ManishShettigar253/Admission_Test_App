-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2024 at 08:27 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `admission_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `mcqs`
--

CREATE TABLE `mcqs` (
  `id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `option_a` varchar(100) NOT NULL,
  `option_b` varchar(100) NOT NULL,
  `option_c` varchar(100) NOT NULL,
  `option_d` varchar(100) NOT NULL,
  `correct_option` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mcqs`
--

INSERT INTO `mcqs` (`id`, `question`, `option_a`, `option_b`, `option_c`, `option_d`, `correct_option`) VALUES
(1, 'Which package is primarily used for building graphical user interfaces (GUIs) in Java?', ' java.io', ' java.util', ' java.awt', ' java.lang', 'c'),
(2, 'What does JDBC stand for?', 'Java Database Console', 'Java Database Connection', 'Java Database Class', 'Java Database Compiler', 'b'),
(3, 'Which component is used to create a button in Java Swing?', 'JList', 'JTextField', 'JButton', 'JCheckBox', 'c'),
(4, 'What does AWT stand for in Java?', 'Advanced Window Toolkit', 'Abstract Window Toolkit', 'Advanced Widget Toolkit', 'Abstract Widget Toolkit', 'b'),
(5, 'Which class is used to represent a connection to a database in JDBC?', 'java.sql.Connection', 'java.sql.DriverManager', 'java.sql.ResultSet', 'java.sql.Statement', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `scores`
--

CREATE TABLE `scores` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `score` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `scores`
--

INSERT INTO `scores` (`id`, `username`, `score`, `timestamp`) VALUES
(1, 'manish', 0, '2024-04-19 17:08:26'),
(2, 'manish', 0, '2024-04-19 17:11:45'),
(3, 'niketh', 0, '2024-04-19 17:14:10'),
(4, 'niketh', 0, '2024-04-19 17:52:15'),
(5, 'manish', 0, '2024-04-19 17:54:32'),
(6, 'manish', 0, '2024-04-19 17:57:50'),
(7, 'manish', 0, '2024-04-19 18:04:41'),
(8, 'manish', 0, '2024-04-19 18:05:27'),
(9, 'manish', 0, '2024-04-19 18:15:15'),
(10, 'manish', 0, '2024-04-19 18:19:44'),
(11, 'manish', 0, '2024-04-19 18:20:02'),
(12, 'manish', 0, '2024-04-19 18:22:14'),
(13, 'manish', 0, '2024-04-19 18:23:35'),
(14, 'manish', 0, '2024-04-19 18:23:43'),
(15, 'manish', 5, '2024-04-19 18:26:21'),
(16, 'manish', 5, '2024-04-19 18:26:35');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'manish', 'manish'),
(2, 'niketh', 'niketh');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mcqs`
--
ALTER TABLE `mcqs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `scores`
--
ALTER TABLE `scores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mcqs`
--
ALTER TABLE `mcqs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `scores`
--
ALTER TABLE `scores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
