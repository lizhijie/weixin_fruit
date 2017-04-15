/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : shangcheng

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-04-15 12:38:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buybus
-- ----------------------------
DROP TABLE IF EXISTS `buybus`;
CREATE TABLE `buybus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `who` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `count` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `about` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `about` (`about`),
  KEY `price` (`price`),
  KEY `alias` (`alias`,`name`,`about`,`price`),
  KEY `who` (`who`),
  KEY `buybus_ibfk_3` (`name`,`about`,`alias`,`price`),
  CONSTRAINT `buybus_ibfk_2` FOREIGN KEY (`who`) REFERENCES `user` (`weixin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `buybus_ibfk_3` FOREIGN KEY (`name`, `about`, `alias`, `price`) REFERENCES `goods` (`name`, `about`, `alias`, `price`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of buybus
-- ----------------------------
INSERT INTO `buybus` VALUES ('14', 'ooSvzw5nEtD-8J15QCWtkEiE5YSo', '2017-04-07 22:45:12', '1', '苹果', ' 每箱10斤,A级果', 'apple', '4');

-- ----------------------------
-- Table structure for cool
-- ----------------------------
DROP TABLE IF EXISTS `cool`;
CREATE TABLE `cool` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `who` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `recnum` varchar(255) DEFAULT NULL,
  `recname` varchar(255) DEFAULT NULL,
  `recaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`num`),
  KEY `who` (`who`),
  KEY `num` (`num`,`status`),
  CONSTRAINT `cool_ibfk_1` FOREIGN KEY (`who`) REFERENCES `user` (`weixin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cool
-- ----------------------------
INSERT INTO `cool` VALUES ('248', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '3', '2017-04-12 18:01:33', '15516937925', '李志杰', '郑州轻工业学院1');
INSERT INTO `cool` VALUES ('249', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '1', '2017-04-12 18:09:26', '15516937925', '李志杰', '郑州轻工业学院1');
INSERT INTO `cool` VALUES ('250', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '1', '2017-04-12 18:09:46', '15516937925', '李志杰', '郑州轻工业学院1');
INSERT INTO `cool` VALUES ('251', 'Guest', '4', '2017-04-13 11:17:35', '15516937925', '李志杰', '郑州轻工业学院');
INSERT INTO `cool` VALUES ('252', 'Guest', '1', '2017-04-13 14:09:16', '15516937925', '李志杰', '郑州轻工业学院');
INSERT INTO `cool` VALUES ('253', 'Guest', '1', '2017-04-13 14:35:18', '15516937925', '李志杰', '郑州轻工业学院');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `about` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `desxml` text,
  `groups` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `price` (`price`),
  KEY `name` (`name`,`alias`),
  KEY `name_2` (`name`,`about`,`price`,`alias`),
  KEY `alias` (`alias`),
  KEY `name_3` (`name`),
  KEY `id` (`id`,`alias`),
  KEY `about` (`about`),
  KEY `name_4` (`name`,`about`,`alias`,`price`),
  KEY `alias_2` (`alias`,`name`,`about`,`price`),
  KEY `group` (`groups`),
  KEY `name_5` (`name`,`alias`,`price`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`groups`) REFERENCES `groups` (`group`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', 'apple', '苹果', ' 每箱10斤,A级果', '4', '2017-04-08 22:19:24', '苹果功效作用很大，它不仅是我国最主要的果品，也是世界上种植最广、产量最多的果品。其味道酸甜适口，香甜多汁，营养丰富。美国流传一种说法：“每天吃一个苹果，就不用请医生”。此话虽然有些夸张，但苹果的营养和药用价值由此可窥见不一般。又因为苹果所含的营养既全面又易被人体消化吸收，所以，非常适合婴幼儿，老人和病人食用', 'default', '1');
INSERT INTO `goods` VALUES ('2', 'banana', '香蕉', '熟透的香蕉', '4', '2016-11-17 16:56:56', '香蕉详细介绍', 'default', '1');
INSERT INTO `goods` VALUES ('3', 'pear', '梨子', '非常甜的梨子', '2', '2016-11-17 16:57:01', '梨子介绍<p>', 'default', '1');
INSERT INTO `goods` VALUES ('4', 'orange', '橘子', '橙黄色的橘子', '5', '2017-04-08 22:09:25', '橘子详细介绍', 'default', '1');
INSERT INTO `goods` VALUES ('37', 'putao', '葡萄', '葡萄的简介', '10', '2017-04-08 17:18:35', '葡萄测试数据', 'default', '1');
INSERT INTO `goods` VALUES ('58', 'xigua', '西瓜', '简介测试', '14', '2017-04-12 13:20:41', 'watermelon 测试数据', 'default', '1');
INSERT INTO `goods` VALUES ('59', 'ssd', 'jjjada', 'wetrwet', '45', '2017-04-13 14:24:29', 'wetwet', 'default', '1');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('0000000001', 'default');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `who` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_ibfk_1` (`alias`,`name`,`price`),
  KEY `who` (`who`),
  KEY `name` (`name`,`alias`,`price`),
  KEY `num` (`num`,`status`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`who`) REFERENCES `user` (`weixin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`num`, `status`) REFERENCES `cool` (`num`, `status`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('33', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '1', '248', '苹果', 'apple', '4', '3');
INSERT INTO `orders` VALUES ('34', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '1', '249', '橘子', 'orange', '5', '1');
INSERT INTO `orders` VALUES ('35', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '2', '249', '葡萄', 'putao', '10', '1');
INSERT INTO `orders` VALUES ('37', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '1', '250', '西瓜', 'xigua', '14', '1');
INSERT INTO `orders` VALUES ('38', 'Guest', '2', '251', '葡萄', 'putao', '10', '4');
INSERT INTO `orders` VALUES ('39', 'Guest', '1', '251', '香蕉', 'banana', '4', '4');
INSERT INTO `orders` VALUES ('40', 'Guest', '2', '252', '香蕉', 'banana', '4', '1');
INSERT INTO `orders` VALUES ('41', 'Guest', '1', '252', '橘子', 'orange', '5', '1');
INSERT INTO `orders` VALUES ('43', 'Guest', '3', '253', '梨子', 'pear', '2', '1');
INSERT INTO `orders` VALUES ('44', 'Guest', '2', '253', '橘子', 'orange', '5', '1');

-- ----------------------------
-- Table structure for receiver
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weixin` varchar(255) DEFAULT NULL,
  `recnum` varchar(255) DEFAULT NULL,
  `recname` varchar(255) DEFAULT NULL,
  `recaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `weixin` (`weixin`),
  CONSTRAINT `receiver_ibfk_1` FOREIGN KEY (`weixin`) REFERENCES `user` (`weixin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receiver
-- ----------------------------
INSERT INTO `receiver` VALUES ('1', 'test', '18137672793', '代玉珍', '郑州市中牟县');
INSERT INTO `receiver` VALUES ('2', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', '15516937925', '李志杰', '郑州轻工业学院1');
INSERT INTO `receiver` VALUES ('4', 'Guest', '15516937925', '李志杰', '郑州轻工业学院');
INSERT INTO `receiver` VALUES ('5', 'ooSvzwxSjQvYiyyHsatWpOt95T2o', '流量', '代玉啊', '兔兔');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `weixin` varchar(255) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `weixin` (`weixin`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '杨曦', 'ooSvzw5nEtD-8J15QCWtkEiE5YSo', '', '1', '2016-11-17 22:42:24');
INSERT INTO `user` VALUES ('3', '王晔', 'ooSvzw2Wt69-n9Guwx6EO0TLMVmA', '', '1', '2016-11-17 22:42:28');
INSERT INTO `user` VALUES ('4', '测试', 'test', null, '1', '2016-11-18 23:24:38');
INSERT INTO `user` VALUES ('9', '李志杰', 'ooSvzw6IGK8n5c4l_zNzibY_8OZ4', null, '1', '2017-03-18 20:43:46');
INSERT INTO `user` VALUES ('11', '游客', 'Guest', null, '1', '2017-03-19 17:05:13');
INSERT INTO `user` VALUES ('12', '', 'ooSvzwxSjQvYiyyHsatWpOt95T2o', null, '1', '2017-03-20 18:54:12');

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website` (
  `id` int(11) NOT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of website
-- ----------------------------
INSERT INTO `website` VALUES ('0', '', 'test鲜果');
INSERT INTO `website` VALUES ('1', '', 'gis');
