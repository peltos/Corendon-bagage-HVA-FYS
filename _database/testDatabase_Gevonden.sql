-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: ronpelt.synology.me    Database: testDatabase
-- ------------------------------------------------------
-- Server version	5.5.52-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Gevonden`
--

DROP TABLE IF EXISTS `Gevonden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Gevonden` (
  `idGevonden` int(255) NOT NULL AUTO_INCREMENT,
  `Tijd` time NOT NULL,
  `Datum` date NOT NULL,
  `Luchthaven` varchar(200) NOT NULL,
  `Labelnummer` int(10) DEFAULT NULL,
  `Vluchtnummer` int(10) DEFAULT NULL,
  `Bestemming` varchar(45) DEFAULT NULL,
  `BagageType` varchar(45) NOT NULL,
  `Merk` varchar(45) DEFAULT NULL,
  `Kleur` varchar(45) DEFAULT NULL,
  `BijzonderKenmerken` varchar(500) DEFAULT NULL,
  `Visibility` tinyint(1) NOT NULL,
  PRIMARY KEY (`idGevonden`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gevonden`
--

LOCK TABLES `Gevonden` WRITE;
/*!40000 ALTER TABLE `Gevonden` DISABLE KEYS */;
INSERT INTO `Gevonden` VALUES (3,'00:25:55','2016-11-30','',425876,643789,'Peking','Tas','','Zwart','',1),(4,'00:41:41','2016-11-30','NAIA',734572,154834,'Abuja','Koffer','','Paars','beschadigd',1),(5,'00:42:41','2016-11-30','Hallo',563467,235823,'Abakan','Tas','Gucci','zwart/goud','',1),(12,'13:28:54','2016-12-06','KLM',20391,39023,'Miami, FL, United States','Tower','Louis Vuitton','Black','Made in Spain',0),(26,'10:55:28','2016-12-20','rasadq',1231,213,'123','','','','',1),(27,'11:05:58','2016-12-20','etst',23,20,'kf','kokokp','ko','kop','kapos12?',1),(29,'12:19:40','2016-12-20','jo',312123,12312,'awdasdadad','type','merk','kleur','speciaal',1),(30,'11:23:43','2017-01-17','Tokyo Airport',123412,124124,'Amsterdam','Koffer','Sennheiser','Rood','Tijgerprint',1),(31,'11:25:10','2017-01-17','KLM',832893,929018,'Marrakech, Morocco','Hitman-case','HERMES','Black','Classified.',0),(32,'11:31:00','2017-01-17','France Paris',45,654,'Amsterdam','suitcase','Swiss','black','',1);
/*!40000 ALTER TABLE `Gevonden` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-17 13:11:07
