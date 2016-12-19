-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cs160_project
-- ------------------------------------------------------
-- Server version	5.6.32-log

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
-- Table structure for table `caregiver_schedules`
--

DROP TABLE IF EXISTS `caregiver_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caregiver_schedules` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `available_monday` bit(1) NOT NULL DEFAULT b'1',
  `available_tuesday` bit(1) NOT NULL DEFAULT b'1',
  `available_wednesday` bit(1) NOT NULL DEFAULT b'1',
  `available_thursday` bit(1) NOT NULL DEFAULT b'1',
  `available_friday` bit(1) NOT NULL DEFAULT b'1',
  `available_saturday` bit(1) NOT NULL DEFAULT b'1',
  `available_sunday` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`schedule_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `caregiver_schedules_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver_schedules`
--

LOCK TABLES `caregiver_schedules` WRITE;
/*!40000 ALTER TABLE `caregiver_schedules` DISABLE KEYS */;
INSERT INTO `caregiver_schedules` VALUES (3001,1001,'','','','\0','\0','\0','\0'),(3002,1002,'\0','\0','','','\0','',''),(3003,1003,'','','','\0','','\0','\0'),(3004,1004,'\0','\0','','','\0','',''),(3005,1005,'','','','\0','\0','\0','\0'),(3006,1006,'\0','','','','\0','',''),(3007,1007,'','','','\0','\0','\0','\0'),(3008,1008,'','\0','','','\0','',''),(3009,1009,'\0','\0','','','\0','','\0');
/*!40000 ALTER TABLE `caregiver_schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caregiver_skills`
--

DROP TABLE IF EXISTS `caregiver_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caregiver_skills` (
  `skills_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `parkinsons` bit(1) NOT NULL DEFAULT b'0',
  `alzheimers` bit(1) NOT NULL DEFAULT b'0',
  `stroke` bit(1) NOT NULL DEFAULT b'0',
  `cancer` bit(1) NOT NULL DEFAULT b'0',
  `hospital_sitter` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`skills_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `caregiver_skills_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregiver_skills`
--

LOCK TABLES `caregiver_skills` WRITE;
/*!40000 ALTER TABLE `caregiver_skills` DISABLE KEYS */;
INSERT INTO `caregiver_skills` VALUES (2001,1001,'No resume','','','\0','\0','\0'),(2002,1002,'No resume','','\0','\0','','\0'),(2003,1003,'No resume','\0','\0','\0','',''),(2004,1004,'No resume','','','\0','',''),(2005,1005,'No resume','','','\0','','\0'),(2006,1006,'No resume','\0','\0','\0','','\0'),(2007,1007,'No resume','','\0','','','\0'),(2008,1008,'No resume','','\0','\0','\0','\0'),(2009,1009,'No resume','','','','','');
/*!40000 ALTER TABLE `caregiver_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `dob` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipcode` varchar(45) NOT NULL,
  `account_type` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1001,'naghmeh.anvari@sjsu.edu','1234','Anvari','Naghmeh','1990-12-01','Female','1 Learning Way','San Francisco','CA','94117','Caregiver'),(1002,'maryjune@gmail.com','4321','Mary','Jane','1985-03-25','Female','123 Kopkin Dr','Hayward','CA','94567','Caregiver'),(1003,'mitch@gmail.com','4312','Mitch','Dillion','1949-03-25','Male','300 Parking Lane','San Jose','CA','95108','Care Seeker'),(1004,'thomas@gmail.com','0000','Thomas','Lam','1986-03-25','Male','300 Passing Lane','San Burno','CA','94066','Caregiver'),(1005,'kelly@gmail.com','4312','Kelly','Finch','1964-03-25','Female','100 Parking Lane','San Ramon','CA','94582','Care Seeker'),(1006,'paul@gmail.com','4312','Paul','Getty','1990-03-25','Male','300 First Lane','San Jose','CA','95108','Caregiver'),(1007,'lauren@gmail.com','4312','Lauren','Ding','1970-03-25','Female','300 Second Lane','San Jose','CA','95108','Care Seeker'),(1008,'david@gmail.com','4312','David','Smith','1964-03-25','Male','800 Parking Lane','Richmond','CA','94804','Care Seeker'),(1009,'daisy@gmail.com','4312','Daisy','Doorn','1980-03-25','Female','600 Third Lane','Berkeley','CA','94710','Caregiver');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-11 12:40:36
