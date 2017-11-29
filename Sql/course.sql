/*
Navicat MySQL Data Transfer

Source Server         : blog
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : coursetest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-29 11:11:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cno` varchar(32) NOT NULL,
  `cname` varchar(64) DEFAULT NULL,
  `cpno` varchar(64) DEFAULT NULL,
  `ccredit` varchar(64) DEFAULT NULL, 
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
