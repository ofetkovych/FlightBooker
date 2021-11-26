-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `airport_name` varchar(45) NOT NULL,
  `acronym_of_airport` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (1,'Netherlands','Kopengagen','Ludovik','CPH');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `date_Of_Birth` datetime NOT NULL,
  `gender` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `adress` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'gfd','gd','2002-01-01 00:00:00','fd','74788745','fsd');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_of_flight` datetime NOT NULL,
  `airport_from` int NOT NULL,
  `airport_where` int NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `flight_class` varchar(45) NOT NULL,
  `number_of_seats` int NOT NULL,
  `departure` datetime NOT NULL,
  `arrival` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_flight_airport1_idx` (`airport_from`),
  KEY `fk_flight_airport2_idx` (`airport_where`),
  CONSTRAINT `fk_flight_airport1` FOREIGN KEY (`airport_from`) REFERENCES `airport` (`id`),
  CONSTRAINT `fk_flight_airport2` FOREIGN KEY (`airport_where`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'2021-11-26 15:47:46',1,1,'ZmuhAirlines','ZmuhAirlines',40,'2021-11-26 15:47:46','2021-11-26 15:47:46'),(2,'2002-01-01 00:00:00',1,1,'Zmuh airlines','luks',225,'2002-01-01 00:00:00','2002-01-01 12:00:00'),(3,'2021-11-26 15:15:16',1,1,'ZmuhAirlines','ZmuhAirlines',40,'2021-11-26 15:15:16','2021-11-26 15:15:16');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_customer`
--

DROP TABLE IF EXISTS `flight_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_customer` (
  `customer_id` int NOT NULL,
  `flight_id` int NOT NULL,
  PRIMARY KEY (`customer_id`,`flight_id`),
  KEY `fk_customer_has_flight_flight1_idx` (`flight_id`),
  KEY `fk_customer_has_flight_customer1_idx` (`customer_id`),
  CONSTRAINT `fk_customer_has_flight_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_customer_has_flight_flight1` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_customer`
--

LOCK TABLES `flight_customer` WRITE;
/*!40000 ALTER TABLE `flight_customer` DISABLE KEYS */;
INSERT INTO `flight_customer` VALUES (1,1);
/*!40000 ALTER TABLE `flight_customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-26 16:48:52
