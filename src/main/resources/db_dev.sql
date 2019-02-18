/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 10.1.21-MariaDB : Database - auth_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`auth_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `auth_dev`;

/*Table structure for table `bss_customer` */

DROP TABLE IF EXISTS `bss_customer`;

CREATE TABLE `bss_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(64) DEFAULT NULL,
  `customer_code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bss_customer` */

LOCK TABLES `bss_customer` WRITE;

UNLOCK TABLES;

/*Table structure for table `bss_user_customer_mapping` */

DROP TABLE IF EXISTS `bss_user_customer_mapping`;

CREATE TABLE `bss_user_customer_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bss_user_customer_mapping` */

LOCK TABLES `bss_user_customer_mapping` WRITE;

UNLOCK TABLES;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(64) DEFAULT NULL,
  `resource_ids` varchar(128) DEFAULT NULL,
  `client_secret` varchar(64) DEFAULT NULL,
  `scope` varchar(64) DEFAULT NULL,
  `authorized_grant_types` varchar(32) DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) DEFAULT NULL,
  `authorities` varchar(128) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(64) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

LOCK TABLES `oauth_client_details` WRITE;

insert  into `oauth_client_details`(`id`,`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`,`status`) values (1,'app-client',NULL,'$2a$10$V5oyFeVxivdjtitu2nY58ufG.A20W4RTZre1mUNPbm9tZHdlnd.dC','all','password,refresh_token',NULL,NULL,120,7200,NULL,NULL,0),(2,'trusted',NULL,'$2a$10$V5oyFeVxivdjtitu2nY58ufG.A20W4RTZre1mUNPbm9tZHdlnd.dC','auth','password,refresh_token',NULL,NULL,120,7200,NULL,NULL,0);

UNLOCK TABLES;

/*Table structure for table `sys_authority` */

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(32) DEFAULT NULL,
  `authority_description` varchar(128) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_authority` */

LOCK TABLES `sys_authority` WRITE;

insert  into `sys_authority`(`authority_id`,`authority_name`,`authority_description`,`status`) values (1,'CREATE_USER','create user',0),(2,'READ_USER','read user',0);

UNLOCK TABLES;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `role_description` varchar(128) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

LOCK TABLES `sys_role` WRITE;

insert  into `sys_role`(`role_id`,`role_name`,`role_description`,`status`) values (1,'DEV','dev',0),(2,'ADMIN','admin',0);

UNLOCK TABLES;

/*Table structure for table `sys_role_authority_mapping` */

DROP TABLE IF EXISTS `sys_role_authority_mapping`;

CREATE TABLE `sys_role_authority_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_authority_mapping` */

LOCK TABLES `sys_role_authority_mapping` WRITE;

insert  into `sys_role_authority_mapping`(`id`,`role_id`,`authority_id`) values (1,1,1),(2,1,2),(3,2,1),(4,2,2);

UNLOCK TABLES;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

LOCK TABLES `sys_user` WRITE;

insert  into `sys_user`(`user_id`,`username`,`password`,`status`) values (1,'user','$2a$10$UAy3LhTyg1P214NVFjyF.uJ1DgCXrMS4ee79PG2XjbU.WIj4o2jzG',0);

UNLOCK TABLES;

/*Table structure for table `sys_user_group` */

DROP TABLE IF EXISTS `sys_user_group`;

CREATE TABLE `sys_user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) DEFAULT NULL,
  `group_description` varchar(128) DEFAULT NULL,
  `parent_group_id` int(11) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_group` */

LOCK TABLES `sys_user_group` WRITE;

insert  into `sys_user_group`(`group_id`,`group_name`,`group_description`,`parent_group_id`,`status`) values (1,'dev','dev',0,0);

UNLOCK TABLES;

/*Table structure for table `sys_user_group_mapping` */

DROP TABLE IF EXISTS `sys_user_group_mapping`;

CREATE TABLE `sys_user_group_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `parent_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_group_mapping` */

LOCK TABLES `sys_user_group_mapping` WRITE;

insert  into `sys_user_group_mapping`(`id`,`user_id`,`group_id`,`parent_group_id`) values (1,1,1,0);

UNLOCK TABLES;

/*Table structure for table `sys_user_role_mapping` */

DROP TABLE IF EXISTS `sys_user_role_mapping`;

CREATE TABLE `sys_user_role_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role_mapping` */

LOCK TABLES `sys_user_role_mapping` WRITE;

insert  into `sys_user_role_mapping`(`id`,`user_id`,`role_id`) values (1,1,1);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
