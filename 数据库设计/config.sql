/*
Navicat MySQL Data Transfer

Source Server         : javaee
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tomcat_status

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-13 14:54:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `ipAddress` varchar(20) NOT NULL,
  `port` int(11) NOT NULL,
  `memory_total` double DEFAULT NULL,
  `memory_pool_Compressed_Class_Space_used` double DEFAULT NULL,
  `connector_currentThreadsCount` double DEFAULT NULL,
  `connector_currentThreadsBusy` double DEFAULT NULL,
  `memory_pool_Code_Cache_used` double DEFAULT NULL,
  `memory_pool_Metaspace_used` double DEFAULT NULL,
  `memory_pool_PS_Eden_Space_used` double DEFAULT NULL,
  `memory_pool_PS_Old_Gen_used` double DEFAULT NULL,
  `memory_pool_PS_Survivor_Space_used` double DEFAULT NULL,
  PRIMARY KEY (`ipAddress`,`port`),
  UNIQUE KEY `unique_config` (`ipAddress`,`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
