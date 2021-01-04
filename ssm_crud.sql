/*
SQLyog Ultimate v8.32 
MySQL - 8.0.22 : Database - ssm_crud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm_crud` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ssm_crud`;

/*Table structure for table `t_dept` */

DROP TABLE IF EXISTS `t_dept`;

CREATE TABLE `t_dept` (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_dept` */

insert  into `t_dept`(`dept_id`,`dept_name`) values (1,'行政部'),(2,'研发部'),(3,'人事部'),(4,'开发部'),(5,'测试部');

/*Table structure for table `t_emp` */

DROP TABLE IF EXISTS `t_emp`;

CREATE TABLE `t_emp` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `d_id` int DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_emp_dept` (`d_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `t_dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_emp` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
