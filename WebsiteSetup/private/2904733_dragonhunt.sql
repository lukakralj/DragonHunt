-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb24.awardspace.net
-- Generation Time: Dec 08, 2018 at 07:32 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2904733_dragonhunt`
--

-- --------------------------------------------------------

--
-- Table structure for table `Challenges`
--

CREATE TABLE `Challenges` (
  `challengeId` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `task` text NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `challengeOwner` varchar(255) NOT NULL,
  `minParticipants` int(11) DEFAULT '1',
  `isPrivate` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `OngoingChallenges`
--

CREATE TABLE `OngoingChallenges` (
  `id` int(11) NOT NULL,
  `challengeId` int(11) NOT NULL,
  `hasStarted` tinyint(1) NOT NULL DEFAULT '0',
  `completed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Participation`
--

CREATE TABLE `Participation` (
  `username` varchar(255) NOT NULL,
  `ongoingId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Teams`
--

CREATE TABLE `Teams` (
  `teamId` int(11) NOT NULL,
  `teamName` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `username` varchar(255) NOT NULL,
  `hashed_password` char(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Challenges`
--
ALTER TABLE `Challenges`
  ADD PRIMARY KEY (`challengeId`),
  ADD KEY `challengeOwner` (`challengeOwner`);

--
-- Indexes for table `OngoingChallenges`
--
ALTER TABLE `OngoingChallenges`
  ADD PRIMARY KEY (`id`),
  ADD KEY `challengeId` (`challengeId`);

--
-- Indexes for table `Participation`
--
ALTER TABLE `Participation`
  ADD PRIMARY KEY (`username`,`ongoingId`),
  ADD KEY `ongoingId` (`ongoingId`),
  ADD KEY `teamId` (`teamId`);

--
-- Indexes for table `Teams`
--
ALTER TABLE `Teams`
  ADD PRIMARY KEY (`teamId`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Challenges`
--
ALTER TABLE `Challenges`
  MODIFY `challengeId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `OngoingChallenges`
--
ALTER TABLE `OngoingChallenges`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
