/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : db_jar

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-01-05 10:06:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_jar
-- ----------------------------
DROP TABLE IF EXISTS `t_jar`;
CREATE TABLE `t_jar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `jar_id` varchar(32) NOT NULL COMMENT 'jar包ID',
  `name` varchar(100) NOT NULL COMMENT 'jar包名称',
  `path` varchar(200) NOT NULL COMMENT 'jar包地址',
  `type` varchar(20) NOT NULL COMMENT 'jar包类型',
  `click` int(11) NOT NULL COMMENT '点击次数',
  `down_hit` int(11) NOT NULL COMMENT '下载次数',
  `index_state` int(11) NOT NULL COMMENT '是否生成索引 0-否 1-是',
  `tag_state` int(11) NOT NULL COMMENT '是否生成标签 0-否 1-是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE,
  UNIQUE KEY `uk_jar_id` (`jar_id`) USING BTREE,
  KEY `k_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1921056 DEFAULT CHARSET=utf8 COMMENT='jar包信息表';

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20665 DEFAULT CHARSET=utf8 COMMENT='标签信息表';
SET FOREIGN_KEY_CHECKS=1;
