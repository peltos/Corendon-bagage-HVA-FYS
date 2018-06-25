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
-- Table structure for table `Overeenkomst`
--

DROP TABLE IF EXISTS `Overeenkomst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Overeenkomst` (
  `OvereenkomstID` int(11) NOT NULL AUTO_INCREMENT,
  `GevondenID` int(11) DEFAULT NULL,
  `VermistID` int(11) DEFAULT NULL,
  `Datum` date DEFAULT NULL,
  `Gesloten` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`OvereenkomstID`),
  KEY `fk_Vermist_has_Gevonden_Vermist_idx` (`Datum`),
  KEY `Gevonden_idx` (`GevondenID`),
  KEY `Vermist_idx` (`VermistID`),
  CONSTRAINT `Gevonden` FOREIGN KEY (`GevondenID`) REFERENCES `Gevonden` (`idGevonden`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vermist` FOREIGN KEY (`VermistID`) REFERENCES `Vermist` (`idVermist`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Overeenkomst`
--

LOCK TABLES `Overeenkomst` WRITE;
/*!40000 ALTER TABLE `Overeenkomst` DISABLE KEYS */;
INSERT INTO `Overeenkomst` VALUES (6,3,1,'2016-11-30',1),(8,5,3,'2016-12-01',0),(13,4,2,'2016-12-05',1),(15,26,9,'2016-12-20',1),(16,29,11,'2016-12-20',1),(17,30,12,'2017-01-17',0),(18,27,14,'2017-01-17',0),(19,32,13,'2017-01-17',0);
/*!40000 ALTER TABLE `Overeenkomst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-17 13:11:06
