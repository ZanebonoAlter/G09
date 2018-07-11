/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-11 16:12:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for memory
-- ----------------------------
DROP TABLE IF EXISTS `memory`;
CREATE TABLE `memory` (
  `ipAddress` varchar(20) NOT NULL,
  `Free` double DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `Max` double DEFAULT NULL,
  `Port` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Port`,`Time`,`ipAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
