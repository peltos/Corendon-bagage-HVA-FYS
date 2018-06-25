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
-- Table structure for table `Gebruikers`
--

DROP TABLE IF EXISTS `Gebruikers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Gebruikers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Voornaam` varchar(45) NOT NULL,
  `Tussenvoegsel` varchar(45) DEFAULT NULL,
  `Achternaam` varchar(45) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Positie` tinyint(1) NOT NULL,
  `Telefoonnummer` int(11) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`,`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gebruikers`
--

LOCK TABLES `Gebruikers` WRITE;
/*!40000 ALTER TABLE `Gebruikers` DISABLE KEYS */;
INSERT INTO `Gebruikers` VALUES (1,'Medewerker','Tussen','medewerker','medewerker','ef44844a10169131c2aaeb85f3de7731',0,612345678,'test@test.nl'),(2,'Manager','Tussen','Manager','manager','1d0258c2440a8d19e716292b231e3190',1,612345678,'test@test.com'),(3,'Medewerker','','Medewerker','1','c4ca4238a0b923820dcc509a6f75849b',0,612345678,'test@test.nl'),(4,'Manager','','Manager','2','c81e728d9d4c2f636f067f89cc14862c',1,612345678,'test@test.nl'),(10,'Test','','Gebruiker','Test','460471f6495c56b6041acdde507a7a8a',1,23891,'Testgebruiker@hotmail.com'),(11,'Maarten','','Mes','Maartenmes','57ba172a6be125cca2f449826f9980ca',1,651760306,'Maarten@hva.nl'),(12,'Alexander','','Morrison','evil-dannybrown','05a671c66aefea124cc08b76ea6d30bb',0,612345678,'alexander.morrison@hva.nl'),(13,'Johnny','','Cash','Johnnycash','3c59d2d416aa4ac9a4340f973ab62473',0,612345678,'Johnnycash@country.com');
/*!40000 ALTER TABLE `Gebruikers` ENABLE KEYS */;
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
