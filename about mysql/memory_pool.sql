/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-11 16:12:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for memory_pool
-- ----------------------------
DROP TABLE IF EXISTS `memory_pool`;
CREATE TABLE `memory_pool` (
  `Name` varchar(50) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Initial` double(20,0) DEFAULT NULL,
  `Committed` double(20,0) DEFAULT NULL,
  `Maximum` double(20,0) DEFAULT NULL,
  `Used` double(20,0) DEFAULT NULL,
  `Port` int(11) NOT NULL,
  `ipAddress` varchar(20) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Time`,`Port`,`Name`,`ipAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
