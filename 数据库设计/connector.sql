/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-13 14:54:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for connector
-- ----------------------------
DROP TABLE IF EXISTS `connector`;
CREATE TABLE `connector` (
  `name` varchar(50) NOT NULL,
  `maxThreads` int(11) DEFAULT NULL,
  `currentThreadCount` int(11) DEFAULT NULL,
  `currentThreadsBusy` int(11) DEFAULT NULL,
  `maxTime` bigint(11) DEFAULT NULL,
  `processingTime` bigint(11) DEFAULT NULL,
  `requestCount` int(11) DEFAULT NULL,
  `errorCount` int(11) DEFAULT NULL,
  `bytesReceived` bigint(11) DEFAULT NULL,
  `bytesSent` bigint(11) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ipAddress` varchar(20) NOT NULL,
  `port` int(11) NOT NULL,
  PRIMARY KEY (`time`,`port`,`name`,`ipAddress`),
  UNIQUE KEY `unique_connector` (`name`,`time`,`ipAddress`,`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
