/*
 Navicat Premium Data Transfer

 Source Server         : diteng-admin　
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : rm-wz9512wu1my95469lpo.mysql.rds.aliyuncs.com:3306
 Source Schema         : sample-shopping

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 28/02/2021 01:14:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_items_info
-- ----------------------------
DROP TABLE IF EXISTS `s_items_info`;
CREATE TABLE `s_items_info` (
  `uid_` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `desc_` varchar(128) NOT NULL DEFAULT '' COMMENT '商品编码',
  `item_code_` varchar(32) NOT NULL,
  `spec_` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `price_` int(11) NOT NULL COMMENT '零售价（分）',
  `shelves_` int(11) NOT NULL DEFAULT '-1' COMMENT '1下架了，2上架中',
  `avatar_` varchar(128) DEFAULT '' COMMENT '商品封面',
  `delete_` int(11) DEFAULT '0' COMMENT '1已删除，2使用中',
  `update_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `remark_` varchar(32) DEFAULT '' COMMENT '商品备注',
  PRIMARY KEY (`uid_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品基本档';

-- ----------------------------
-- Table structure for s_user_address
-- ----------------------------
DROP TABLE IF EXISTS `s_user_address`;
CREATE TABLE `s_user_address` (
  `uid_` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `user_code_` int(11) NOT NULL COMMENT '用户代码',
  `province_` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
  `city_` varchar(32) NOT NULL DEFAULT '' COMMENT '市',
  `district_` varchar(32) NOT NULL DEFAULT '' COMMENT '县、区',
  `town_` varchar(32) DEFAULT '' COMMENT '镇',
  `detail_` varchar(128) NOT NULL DEFAULT '' COMMENT '详细地址',
  `default_` bit(1) DEFAULT NULL COMMENT '默认地址',
  `update_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `remark_` varchar(32) DEFAULT '' COMMENT '地址备注',
  PRIMARY KEY (`uid_`) USING BTREE,
  KEY `idx_user_code` (`user_code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址';

-- ----------------------------
-- Table structure for s_user_info
-- ----------------------------
DROP TABLE IF EXISTS `s_user_info`;
CREATE TABLE `s_user_info` (
  `uid_` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `code_` varchar(255) NOT NULL COMMENT '用户代码',
  `mobile_` varchar(14) NOT NULL DEFAULT '' COMMENT '手机号码',
  `email_` varchar(32) DEFAULT '' COMMENT '用户邮箱',
  `nickname_` varchar(16) DEFAULT '' COMMENT '用户昵称',
  `password_` varchar(128) DEFAULT '' COMMENT '用户密码',
  `avatar_` varchar(128) DEFAULT '' COMMENT '用户头像',
  `is_delete_` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `state_` int(11) DEFAULT '0' COMMENT '使用状态',
  `update_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `remark_` varchar(32) DEFAULT '' COMMENT '资料备注',
  PRIMARY KEY (`uid_`) USING BTREE,
  UNIQUE KEY `uk_code_` (`code_`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基本档';

-- ----------------------------
-- Table structure for s_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `s_user_login_log`;
CREATE TABLE `s_user_login_log` (
  `uid_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `user_code_` int(11) NOT NULL DEFAULT '0' COMMENT '用户代码',
  `client_ip_` varchar(128) NOT NULL DEFAULT '' COMMENT '登录地址',
  `client_id_` varchar(32) NOT NULL DEFAULT '' COMMENT '设备指纹',
  `create_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark_` varchar(64) DEFAULT '' COMMENT '登录备注',
  PRIMARY KEY (`uid_`) USING BTREE,
  KEY `idx_user_code` (`user_code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户日志';

-- ----------------------------
-- Table structure for t_order_body
-- ----------------------------
DROP TABLE IF EXISTS `t_order_body`;
CREATE TABLE `t_order_body` (
  `uid_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `order_sn_` int(11) NOT NULL COMMENT '订单编码',
  `order_it_` varchar(255) NOT NULL COMMENT '订单序号',
  `item_code_` int(11) NOT NULL COMMENT '商品编码',
  `item_num_` varchar(255) NOT NULL COMMENT '商品数量',
  `price_` int(11) NOT NULL COMMENT '单价/分',
  `total_` int(11) NOT NULL COMMENT '合计/分',
  `snapshot_` json NOT NULL COMMENT '商品快照JSON',
  `update_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `remark_` varchar(64) DEFAULT '' COMMENT '商品备注',
  PRIMARY KEY (`uid_`) USING BTREE,
  KEY `idx_order_sn_it` (`order_sn_`,`order_it_`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单单身表';

-- ----------------------------
-- Table structure for t_order_head
-- ----------------------------
DROP TABLE IF EXISTS `t_order_head`;
CREATE TABLE `t_order_head` (
  `uid_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键UID',
  `user_code_` int(11) NOT NULL COMMENT '用户代码',
  `order_sn_` varchar(32) NOT NULL COMMENT '订单编号',
  `province_` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
  `city_` varchar(32) NOT NULL DEFAULT '' COMMENT '市',
  `district_` varchar(64) NOT NULL DEFAULT '' COMMENT '区',
  `town_` varchar(255) DEFAULT NULL COMMENT '镇',
  `detail_` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `amount_` int(11) NOT NULL DEFAULT '0' COMMENT '订单总价/分',
  `logistics_fee_` int(11) DEFAULT '0' COMMENT '物流单价/分',
  `logistics_status_` varchar(64) DEFAULT '' COMMENT '物流状态',
  `order_status_` int(11) DEFAULT '0' COMMENT '0-待支付、1-已支付待发货、2-发货中、3-发货中、4-已完成、5-退款中、6-已退款',
  `update_time_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `buyer_message_` varchar(128) DEFAULT '' COMMENT '买家备注',
  `seller_message_` varchar(128) DEFAULT NULL COMMENT '商家备注',
  PRIMARY KEY (`uid_`) USING BTREE,
  UNIQUE KEY `uk_order_` (`order_sn_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单单头表';

SET FOREIGN_KEY_CHECKS = 1;
