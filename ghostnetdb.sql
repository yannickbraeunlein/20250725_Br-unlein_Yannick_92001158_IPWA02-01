-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 23. Jul 2025 um 14:14
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ghostnetdb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `GhostNet`
--

CREATE TABLE `GhostNet` (
  `id` bigint(20) NOT NULL,
  `estimatedSize` varchar(255) DEFAULT NULL,
  `gpsCoordinates` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `assignedTo_id` bigint(20) DEFAULT NULL,
  `reportedBy_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `GhostNet`
--

INSERT INTO `GhostNet` (`id`, `estimatedSize`, `gpsCoordinates`, `status`, `assignedTo_id`, `reportedBy_id`) VALUES
(1, '4.761328', '38.205633', 'RECOVERED', 1, 1),
(2, '8.466597', '50.747069', 'LOST', 2, 2),
(3, '10', '43.041500, 5.190541', 'RECOVERED', 3, 3),
(4, '50', '4.974278, 3.575541', 'RECOVERED', 2, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ReportingPerson`
--

CREATE TABLE `ReportingPerson` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `anonymous` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `ReportingPerson`
--

INSERT INTO `ReportingPerson` (`id`, `name`, `phoneNumber`, `anonymous`) VALUES
(1, 'Yannick', '123456789', b'0'),
(2, 'no name', 'no number', b'1'),
(3, 'Mark', '1234', b'0'),
(4, 'Hendrik', '0000', b'0');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `SalvagingPerson`
--

CREATE TABLE `SalvagingPerson` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `SalvagingPerson`
--

INSERT INTO `SalvagingPerson` (`id`, `name`, `phoneNumber`) VALUES
(1, 'Marvin', '987654321'),
(2, 'Tim', '1111'),
(3, 'Sven', '4321');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbrfjpn4m5tstu92e0tnt2nlm6` (`assignedTo_id`),
  ADD KEY `FKmv4j42q8bg6kbr6tt11820cln` (`reportedBy_id`);

--
-- Indizes für die Tabelle `ReportingPerson`
--
ALTER TABLE `ReportingPerson`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `SalvagingPerson`
--
ALTER TABLE `SalvagingPerson`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `ReportingPerson`
--
ALTER TABLE `ReportingPerson`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `SalvagingPerson`
--
ALTER TABLE `SalvagingPerson`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  ADD CONSTRAINT `FKbrfjpn4m5tstu92e0tnt2nlm6` FOREIGN KEY (`assignedTo_id`) REFERENCES `SalvagingPerson` (`id`),
  ADD CONSTRAINT `FKmv4j42q8bg6kbr6tt11820cln` FOREIGN KEY (`reportedBy_id`) REFERENCES `ReportingPerson` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
