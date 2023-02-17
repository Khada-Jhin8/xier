/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.10-log : Database - xier
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xier` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xier`;

/*Table structure for table `t_reply_wa_message` */

DROP TABLE IF EXISTS `t_reply_wa_message`;

CREATE TABLE `t_reply_wa_message` (
  `replyId` varchar(50) NOT NULL,
  `lsh` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `fromUser` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_reply_wa_message` */

insert  into `t_reply_wa_message`(`replyId`,`lsh`,`content`,`create_time`,`fromUser`) values 
('1','9b1643fcbf3c1a317c61937815889278','嗯嗯','2023-02-10 17:53:27','甜美春'),
('2','9b1643fcbf3c1a317c61937815889278','好的','2023-02-10 17:53:24','甜美春');

/*Table structure for table `t_wa_message` */

DROP TABLE IF EXISTS `t_wa_message`;

CREATE TABLE `t_wa_message` (
  `lsh` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_time` datetime NOT NULL,
  `fromUser` varchar(50) NOT NULL DEFAULT '郑经人',
  PRIMARY KEY (`lsh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_wa_message` */

insert  into `t_wa_message`(`lsh`,`content`,`create_time`,`fromUser`) values 
('0b1833073b4497d0d752c80bb02a026b','这山间的风，这林间的水，都不如你','2023-02-10 14:48:46','郑经人'),
('9b1643fcbf3c1a317c61937815889278','山河远阔，人间烟火，无一是你，无一不是你。','2023-02-10 17:26:04','郑经人');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
