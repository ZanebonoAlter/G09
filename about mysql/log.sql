/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-23 11:35:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `ipAddress` varchar(20) NOT NULL,
  `port` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_type` varchar(50) NOT NULL,
  `describe_message` varchar(200) NOT NULL,
  `read_status` varchar(20) DEFAULT NULL,
  `send_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ipAddress`,`port`,`time`,`error_type`,`describe_message`),
  UNIQUE KEY `unique_log` (`ipAddress`,`port`,`time`,`error_type`,`describe_message`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
