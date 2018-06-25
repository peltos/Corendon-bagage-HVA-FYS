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
-- Table structure for table `Vermist`
--

DROP TABLE IF EXISTS `Vermist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vermist` (
  `idVermist` int(255) NOT NULL AUTO_INCREMENT,
  `Tijd` time NOT NULL,
  `Datum` date NOT NULL,
  `Luchthaven` varchar(200) NOT NULL,
  `Labelnummer` int(11) DEFAULT NULL,
  `Vluchtnummer` int(11) DEFAULT NULL,
  `Bestemming` varchar(45) DEFAULT NULL,
  `BagageType` varchar(45) NOT NULL,
  `Merk` varchar(45) DEFAULT NULL,
  `Kleur` varchar(45) DEFAULT NULL,
  `BijzonderKenmerken` varchar(500) DEFAULT NULL,
  `Naam` varchar(100) NOT NULL,
  `Adres` varchar(100) DEFAULT NULL,
  `Woonplaats` varchar(100) DEFAULT NULL,
  `Postcode` varchar(8) DEFAULT NULL,
  `Land` varchar(100) DEFAULT NULL,
  `Telefoon` int(11) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `Visibility` tinyint(1) NOT NULL,
  PRIMARY KEY (`idVermist`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vermist`
--

LOCK TABLES `Vermist` WRITE;
/*!40000 ALTER TABLE `Vermist` DISABLE KEYS */;
INSERT INTO `Vermist` VALUES (1,'20:30:00','2016-11-29','Shiphol',4142144,45345,'Amsterdam','Lelijk','Delcey','Rood','Stickers','piet Henk','bloemenkring 16','Utrecht','6754VDDD','Nederland',678345690,'test@corendom.nl',1),(2,'00:58:55','2016-11-30','',345346,234356,'','Tas','gucci','Zwart / Goud','','Ruby','KarwijStraat 21','Utrecht','2351TV','Nederland',628356256,'test@test.nl',1),(3,'01:01:37','2016-11-30','sdfds',123125123,124123512,'sfasd','asdf','sfs','sadf','sfasdf','sdfas','fsdf','sdfasdf','sdfasdf','sfda',345345,'sfsdfa',1),(8,'14:03:35','2016-12-06','KLM',232,3929039,'Miami, FL, United States','Miami, FL, United States','Slingerland, NYC, New York','Slingerland, NYC, New York','Slingerland, NYC, New Yorkkek','Miami, FL, United States','Miami, FL, United States','Miami, FL, United States','1102','AMSTERDAM',64312942,'slingerland@fbi.gov',0),(9,'14:31:08','2016-12-06','Battlefield Airlines',3802930,83203290,'Miami, FL, United States','Slinger-M30','Battlefield','Battlefield/Orange','DICE','Maarten Slingerland','Slingerstraat 392','Slinger','1102 SL','Boef',51234912,'slingerland@slinder.nl',1),(11,'12:38:05','2016-12-20','jo',123123,123213,'nederland','type','merk','kleur','speciaal','Egbert Trebge','straat','dorp','1515AO','nederland',15151515,'Egbert@hotmail.nl',1),(12,'18:29:43','2016-12-25','dasdasd',123,123,'asddasd','asdas','sadasd','asdasd','asdasd','asdad','asdasd','asdsad','3123AD','asdasd',123123,'asdadsad',1),(13,'11:26:02','2017-01-17','Amsterdam',24,657,'Tokyo','case','eastpack','blue','','Herbert','koninglaan 12','','1234hu','The Netherlands',223236584,'herbet@gmail.com',1),(14,'11:26:09','2017-01-17','Tokyo Airport',217389,127893,'China','Koffer','NY','Blauw','','Willem Dwars','Ietsstraat 123','Ergens','1013LC','Thailand',62373812,'Willemiets@hotmail.com',1),(15,'11:29:48','2017-01-17','Amsterdam',78,956,'USA Washington','Bag with wheels','adidas','red','big adidas logo','rick grijms','dookustraat 17','','1632gf','The Netherlands',227396185,'Famgrijms@gmail.com',0);
/*!40000 ALTER TABLE `Vermist` ENABLE KEYS */;
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
