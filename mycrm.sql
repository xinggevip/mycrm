/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : mycrm

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-09-24 20:04:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_apt
-- ----------------------------
DROP TABLE IF EXISTS `t_apt`;
CREATE TABLE `t_apt` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `empid` int(11) DEFAULT NULL COMMENT '员工id',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `roomid` int(11) DEFAULT NULL COMMENT '预约房间id',
  `sourceid` int(11) DEFAULT NULL COMMENT '渠道id',
  `paytypeid` int(11) DEFAULT NULL COMMENT '支付类型id',
  `personnum` int(11) DEFAULT NULL COMMENT '预约人数',
  `starttime` datetime DEFAULT NULL COMMENT '预约开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '预约结束时间',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `createtime` datetime DEFAULT NULL COMMENT '创建预约时间',
  `status` int(255) DEFAULT NULL COMMENT '预约状态：0已预约、1已到场、2已放弃',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `empid` (`empid`),
  KEY `userid` (`userid`),
  KEY `roomid` (`roomid`),
  KEY `sourceid` (`sourceid`),
  KEY `paytypeid` (`paytypeid`),
  CONSTRAINT `t_apt_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `t_emp` (`id`),
  CONSTRAINT `t_apt_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_apt_ibfk_3` FOREIGN KEY (`roomid`) REFERENCES `t_room` (`id`),
  CONSTRAINT `t_apt_ibfk_4` FOREIGN KEY (`sourceid`) REFERENCES `t_source` (`id`),
  CONSTRAINT `t_apt_ibfk_5` FOREIGN KEY (`paytypeid`) REFERENCES `t_paytype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_apt
-- ----------------------------
INSERT INTO `t_apt` VALUES ('2', '1', '1', '2', '2', null, '2', '2020-10-10 22:00:58', '2020-10-10 23:00:58', '4.00', '2020-10-10 22:01:07', '2', null);
INSERT INTO `t_apt` VALUES ('3', '1', '1', '2', '2', '1', '2', '2020-10-11 13:30:10', '2020-10-11 14:30:10', '60.00', '2020-10-11 11:51:26', '1', null);
INSERT INTO `t_apt` VALUES ('4', '1', '1', '2', '2', '2', '2', '2020-10-11 12:28:23', '2020-10-11 13:28:23', '80.00', '2020-10-11 12:28:02', '1', null);
INSERT INTO `t_apt` VALUES ('5', '1', '1', '4', '1', '1', '2', '2020-10-11 12:35:37', '2020-10-11 12:36:37', '90.00', '2020-10-11 12:35:29', '1', null);
INSERT INTO `t_apt` VALUES ('7', '1', '1', '2', '2', '1', '2', '2020-10-11 13:32:17', '2020-10-11 14:32:17', '50.00', '2020-10-11 13:31:50', '1', null);
INSERT INTO `t_apt` VALUES ('8', '1', '1', '4', '1', null, '2', '2020-10-11 13:39:03', '2020-10-11 14:39:03', '200.00', '2020-10-11 13:39:17', '2', null);
INSERT INTO `t_apt` VALUES ('9', '1', '1', '1', '2', '1', '2', '2020-10-12 20:19:56', '2020-10-12 21:19:56', '50.00', '2020-10-12 20:19:41', '1', null);
INSERT INTO `t_apt` VALUES ('10', '1', '1', '3', '2', '2', '2', '2020-10-14 22:05:29', '2020-10-14 23:05:29', '100.00', '2020-10-14 21:57:25', '1', null);
INSERT INTO `t_apt` VALUES ('11', '1', '1', '2', '1', '1', '2', '2020-11-01 20:05:20', '2020-11-01 21:05:20', '100.00', '2020-11-01 20:05:09', '1', null);
INSERT INTO `t_apt` VALUES ('12', '1', '1', '2', '2', '1', '2', '2020-11-03 21:06:43', '2020-11-03 22:06:43', '200.00', '2020-11-03 21:06:24', '1', null);
INSERT INTO `t_apt` VALUES ('13', '1', '1', '3', '2', '2', '1', '2021-04-06 21:16:28', '2021-04-06 22:16:28', '100.00', '2021-04-06 21:16:03', '1', null);
INSERT INTO `t_apt` VALUES ('14', '1', '1', '2', '3', '1', '2', '2021-04-29 21:09:26', '2021-04-29 22:09:26', '20.00', '2021-04-29 21:09:11', '1', null);
INSERT INTO `t_apt` VALUES ('15', '1', '1', '3', '2', null, '2', '2021-04-29 21:14:01', '2021-04-29 22:14:01', '4.00', '2021-04-29 21:14:09', '0', null);

-- ----------------------------
-- Table structure for t_charge
-- ----------------------------
DROP TABLE IF EXISTS `t_charge`;
CREATE TABLE `t_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `empid` int(11) DEFAULT NULL COMMENT '员工id',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `aptid` int(11) DEFAULT NULL COMMENT '预约id',
  `paytypeid` int(11) DEFAULT NULL COMMENT '支付类型id',
  `moneynum` decimal(10,2) DEFAULT NULL COMMENT '充值金额',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `empid` (`empid`),
  KEY `userid` (`userid`),
  KEY `paytypeid` (`paytypeid`),
  KEY `aptid` (`aptid`),
  CONSTRAINT `t_charge_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `t_emp` (`id`),
  CONSTRAINT `t_charge_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_charge_ibfk_3` FOREIGN KEY (`paytypeid`) REFERENCES `t_paytype` (`id`),
  CONSTRAINT `t_charge_ibfk_4` FOREIGN KEY (`aptid`) REFERENCES `t_apt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_charge
-- ----------------------------
INSERT INTO `t_charge` VALUES ('2', '1', '1', '4', '2', '-80.00', '非余额支付，仅记录', '2020-10-11 12:28:32');
INSERT INTO `t_charge` VALUES ('3', '1', '1', '5', '1', '-90.00', '非余额支付，仅记录', '2020-10-11 12:35:47');
INSERT INTO `t_charge` VALUES ('4', '1', '1', null, '1', '100.00', null, '2020-10-11 12:51:57');
INSERT INTO `t_charge` VALUES ('6', '1', '1', '3', '1', '-60.00', '非余额支付，仅记录', '2020-10-11 13:30:10');
INSERT INTO `t_charge` VALUES ('7', '1', '1', '7', '1', '-50.00', '非余额支付，仅记录', '2020-10-11 13:32:17');
INSERT INTO `t_charge` VALUES ('8', '1', '1', '9', '1', '-50.00', '非余额支付，仅记录', '2020-10-12 20:19:56');
INSERT INTO `t_charge` VALUES ('9', '1', '1', '10', '2', '-100.00', '非余额支付，仅记录', '2020-10-14 22:05:29');
INSERT INTO `t_charge` VALUES ('10', '1', '1', '11', '1', '-100.00', '非余额支付，仅记录', '2020-11-01 20:05:20');
INSERT INTO `t_charge` VALUES ('11', '1', '1', '12', '1', '-200.00', '非余额支付，仅记录', '2020-11-03 21:06:43');
INSERT INTO `t_charge` VALUES ('12', '1', '1', '13', '2', '-100.00', '非余额支付，仅记录', '2021-04-06 21:16:28');
INSERT INTO `t_charge` VALUES ('13', '1', '1', '14', '1', '-20.00', '非余额支付，仅记录', '2021-04-29 21:09:26');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `phonenumber` varchar(255) DEFAULT NULL COMMENT '手机号',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1', '管理员', '123456', '男', '15937067033', '');

-- ----------------------------
-- Table structure for t_paytype
-- ----------------------------
DROP TABLE IF EXISTS `t_paytype`;
CREATE TABLE `t_paytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `paytype` varchar(255) DEFAULT NULL COMMENT '支付类型',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paytype
-- ----------------------------
INSERT INTO `t_paytype` VALUES ('1', '支付宝', '');
INSERT INTO `t_paytype` VALUES ('2', '微信', null);
INSERT INTO `t_paytype` VALUES ('3', '渠道', null);
INSERT INTO `t_paytype` VALUES ('4', '现金', null);
INSERT INTO `t_paytype` VALUES ('5', '余额', null);
INSERT INTO `t_paytype` VALUES ('6', '其他', null);

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '房间名称',
  `maxnum` int(11) DEFAULT NULL COMMENT '最多可以容纳多少人',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES ('1', '一号房间', '2', null);
INSERT INTO `t_room` VALUES ('2', '二号房间', '2', null);
INSERT INTO `t_room` VALUES ('3', '三号房间', '2', null);
INSERT INTO `t_room` VALUES ('4', '四号房间', '2', null);
INSERT INTO `t_room` VALUES ('5', '五号房间', null, null);

-- ----------------------------
-- Table structure for t_source
-- ----------------------------
DROP TABLE IF EXISTS `t_source`;
CREATE TABLE `t_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sourcename` varchar(255) DEFAULT NULL COMMENT '渠道名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_source
-- ----------------------------
INSERT INTO `t_source` VALUES ('1', '点评', '');
INSERT INTO `t_source` VALUES ('2', '美团', '');
INSERT INTO `t_source` VALUES ('3', '电话', '');
INSERT INTO `t_source` VALUES ('4', '微信', null);
INSERT INTO `t_source` VALUES ('5', '抖音', null);
INSERT INTO `t_source` VALUES ('6', '霸王餐', null);
INSERT INTO `t_source` VALUES ('7', '其他', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `empid` int(11) DEFAULT NULL COMMENT '操作员工id',
  `username` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `phonenumber` varchar(255) DEFAULT NULL COMMENT '手机号',
  `savedate` datetime DEFAULT NULL COMMENT '保存日期',
  `money` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `score` int(255) DEFAULT NULL COMMENT '积分',
  `viptype` varchar(255) DEFAULT NULL COMMENT '会员类型',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `empid` (`empid`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`empid`) REFERENCES `t_emp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', '高老板', '123456', '男', '15937067088', '2020-10-10 21:30:49', '49.00', null, null, null, null);
