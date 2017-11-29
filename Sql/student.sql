/*
Navicat MySQL Data Transfer

Source Server         : blog
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : coursetest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-29 11:12:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` varchar(32) NOT NULL,
  `sname` varchar(64) DEFAULT NULL,
  `ssex` int(11) DEFAULT NULL,
  `sage` int(11) DEFAULT NULL,
  `sdept` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
