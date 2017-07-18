/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.21 : Database - mychat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`mychat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mychat`;

/*Table structure for table `chatlogin` */

DROP TABLE IF EXISTS `chatlogin`;

CREATE TABLE `chatlogin` (
  `id` smallint(6) NOT NULL DEFAULT '0',
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `MobileNo` varchar(10) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `chatlogin` */

insert  into `chatlogin`(`id`,`username`,`password`,`name`,`MobileNo`,`status`) values (1,'myself','admin12','Harshita Khandelwal','9782590536',0),(2,'itsme','12345','Shipra Khandelwal','8769003391',0),(3,'laksh','laksh','Lkashya','7854123698',0),(4,'shipra','shipra','shipra','8769003391',0),(5,'sunita','2236263','sunita','8386061080',0),(6,'ajaykh','1234','ajay','9829244263',0);

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `st` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `status` */

insert  into `status`(`st`) values ('true');

/*Table structure for table `storesocket` */

DROP TABLE IF EXISTS `storesocket`;

CREATE TABLE `storesocket` (
  `id` smallint(6) NOT NULL DEFAULT '0',
  `addr` varchar(20) DEFAULT NULL,
  `port` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `storesocket` */

/*Table structure for table `time` */

DROP TABLE IF EXISTS `time`;

CREATE TABLE `time` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user` varchar(10) DEFAULT NULL,
  `msg` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `time` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
