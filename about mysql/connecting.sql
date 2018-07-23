/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-13 14:54:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for connecting
-- ----------------------------
DROP TABLE IF EXISTS `connecting`;
CREATE TABLE `connecting` (
  `ipAddress` varchar(20) NOT NULL,
  `port` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ipAddress`,`port`),
  UNIQUE KEY `uniqe_connecting` (`ipAddress`,`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
