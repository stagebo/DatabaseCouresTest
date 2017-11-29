/*
Navicat MySQL Data Transfer

Source Server         : blog
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : coursetest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-29 11:12:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `sno` varchar(64) DEFAULT NULL,
  `cno` varchar(64) DEFAULT NULL,
  `grade` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
