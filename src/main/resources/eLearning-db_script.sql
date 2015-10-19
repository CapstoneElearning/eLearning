CREATE DATABASE  IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `assignment_type`
--

DROP TABLE IF EXISTS `assignment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_type` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `schedule_day` varchar(45) NOT NULL,
  `schedule_time` datetime NOT NULL,
  `startd_date` date NOT NULL,
  `end_date` date NOT NULL,
  `credits` double NOT NULL,
  `subject` int(11) DEFAULT NULL,
  `dept` int(11) DEFAULT NULL,
  `program` int(11) DEFAULT NULL,
  `instructor` int(11) DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  PRIMARY KEY (`id_pk`),
  KEY `subject_fk_idx` (`subject`),
  KEY `dept_fk_idx` (`dept`),
  KEY `program_fk_idx` (`program`),
  KEY `instructor_idx` (`instructor`),
  KEY `room_fk_idx` (`room`),
  KEY `course_subject_fk_idx` (`subject`),
  KEY `course_dept_fk_idx` (`dept`),
  KEY `course_program_fk_idx` (`program`),
  KEY `course_instructor_idx` (`instructor`),
  KEY `course_room_fk_idx` (`room`),
  CONSTRAINT `course_dept_id_fk` FOREIGN KEY (`dept`) REFERENCES `department` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_instructor_id_fk` FOREIGN KEY (`instructor`) REFERENCES `users` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_program_id_fk` FOREIGN KEY (`program`) REFERENCES `program` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_room_id_fk` FOREIGN KEY (`room`) REFERENCES `room` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_subject_id_fk` FOREIGN KEY (`subject`) REFERENCES `subject` (`subj_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_assignments`
--

DROP TABLE IF EXISTS `course_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_assignments` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(200) NOT NULL,
  `due_date` date NOT NULL,
  `allow_late_submit` int(1) DEFAULT '0' COMMENT '0- by default not allowed, 1- is allowed',
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `assignment_type` int(11) NOT NULL,
  `total_marks` double NOT NULL,
  `secured-marks` double NOT NULL,
  `document_id` int(11) NOT NULL,
  PRIMARY KEY (`id_pk`),
  KEY `assignment_type_fk_idx` (`assignment_type`),
  KEY `student_fk_idx` (`student_id`),
  KEY `document_fk_idx` (`document_id`),
  CONSTRAINT `assignment_type_fk` FOREIGN KEY (`assignment_type`) REFERENCES `assignment_type` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_fk` FOREIGN KEY (`id_pk`) REFERENCES `course_enrollment` (`enrollment_id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `document_fk` FOREIGN KEY (`document_id`) REFERENCES `documnet` (`id-pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_fk` FOREIGN KEY (`student_id`) REFERENCES `users` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_enrollment`
--

DROP TABLE IF EXISTS `course_enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_enrollment` (
  `enrollment_id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `enroll_date` date NOT NULL,
  `completion_date` date NOT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `course_enrollmentcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`enrollment_id_pk`),
  KEY `ce_student_fk_idx` (`student_id`),
  KEY `ce_course_id_fk_idx` (`course_id`),
  CONSTRAINT `ce_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ce_student_fk` FOREIGN KEY (`student_id`) REFERENCES `users` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `schoool_code` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `doc_type`
--

DROP TABLE IF EXISTS `doc_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_type` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documnet`
--

DROP TABLE IF EXISTS `documnet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documnet` (
  `id-pk` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `size` int(8) DEFAULT NULL,
  PRIMARY KEY (`id-pk`),
  CONSTRAINT `doc_type_fk` FOREIGN KEY (`id-pk`) REFERENCES `doc_type` (`id_pk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `degree` varchar(45) NOT NULL,
  PRIMARY KEY (`id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `active` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  PRIMARY KEY (`id_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subj_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(45) NOT NULL,
  `subject_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`subj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_pk` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `mname` varchar(30) DEFAULT NULL,
  `dob` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_home` varchar(12) DEFAULT NULL,
  `phone_cell` varchar(12) DEFAULT NULL,
  `address1` varchar(45) NOT NULL,
  `address2` varchar(45) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `state` varchar(30) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `pwd_hint` varchar(100) DEFAULT NULL,
  `pwd_hint_ans` varchar(45) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  `registered_on` date DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id_pk`,`username`),
  KEY `role-id_fk_idx` (`role_id`),
  CONSTRAINT `role-id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-10 19:13:24
