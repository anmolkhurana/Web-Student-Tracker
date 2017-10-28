# Web-Student-Tracker
JAVA, JDBC, JSTL, JSP, HTML, CSS, MySql, SERVLET, MVC  

Introduction:

This project basically allows one to understand the MVC architecture that is running in the real world scenarios. I have used JAVA, JSP, 
JSTL and SERVLETS to create a web app that keeps a track of the students. I have used connection pool concept in this project. Focusing on 
core JSP, conncetion pool concepts and JSTL helped me a lot to know stuff that goes in to the real world programming.

Functionalities:

ADD a record
UPDATE a record
DELETE a record

Important points to be noted:

There are 2 sql scripts that you have to run in Mysql. This will create a table 'student' table for you and will add data to it.

first run :

CREATE USER 'webstudent'@'localhost' IDENTIFIED BY 'webstudent';

GRANT ALL PRIVILEGES ON * . * TO 'webstudent'@'localhost';

Then run :

CREATE DATABASE  IF NOT EXISTS `web_student_tracker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `web_student_tracker`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: web_student_tracker
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Anmol','Khurana','khuranaanmol22294@gmail.com'),(2,'Saksham','Khurana','khuranasaksham@gmail.com'),(3,'Ajay','Rao','ajay@gmail.com'),(4,'Biljot','Neelyam','biljot@gmail.com'),(5,'Maxwell','Dembla','max@gmail.com');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-13 21:50:59



Future work:

SEARCH command can be still be added.
