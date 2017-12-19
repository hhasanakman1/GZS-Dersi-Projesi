-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema arduino
--

CREATE DATABASE IF NOT EXISTS arduino;
USE arduino;

--
-- Definition of table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
CREATE TABLE `kullanici` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kadi` varchar(45) NOT NULL,
  `sifre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kullanici`
--

/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` (`id`,`kadi`,`sifre`) VALUES 
 (1,'admin','admin');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;


--
-- Definition of table `veri`
--

DROP TABLE IF EXISTS `veri`;
CREATE TABLE `veri` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Sicaklik` int(10) unsigned NOT NULL,
  `Nem` int(10) unsigned NOT NULL,
  `Toprak_Nem` int(10) unsigned NOT NULL,
  `Tarih` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `veri`
--

/*!40000 ALTER TABLE `veri` DISABLE KEYS */;
/*!40000 ALTER TABLE `veri` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
