/*
 Navicat MySQL Data Transfer

 Source Server         : shixun
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : power_sell_sys

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 19/10/2023 20:48:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `power_id` int(0) NOT NULL COMMENT '电源id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `star` tinyint(0) NOT NULL DEFAULT 1 COMMENT '星'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `user_id` int(0) NOT NULL,
  `power_id` int(0) NOT NULL,
  `create_time` datetime(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(0) NOT NULL,
  `job_number` smallint(0) NULL DEFAULT NULL COMMENT '工号',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 1111, '123456', 'otto');

-- ----------------------------
-- Table structure for needs
-- ----------------------------
DROP TABLE IF EXISTS `needs`;
CREATE TABLE `needs`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `type` int(0) NULL DEFAULT 0 COMMENT '电源类型',
  `min_capacity` int(0) UNSIGNED NOT NULL COMMENT '最小电池容量',
  `max_capacity` int(0) UNSIGNED NOT NULL COMMENT '最大电池容量',
  `sum` int(0) UNSIGNED NOT NULL COMMENT '需求数量',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `supply_time` datetime(0) NULL DEFAULT NULL COMMENT '供应时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of needs
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `power_id` int(0) NOT NULL COMMENT '电源id',
  `type` int(0) NOT NULL COMMENT '电源类型',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电源型号',
  `capacity` int(0) NOT NULL COMMENT '电源容量',
  `sum` int(0) UNSIGNED NOT NULL COMMENT '订购数量',
  `amount` decimal(10, 2) UNSIGNED NOT NULL COMMENT '订购金额',
  `create_time` datetime(0) NOT NULL COMMENT '订购日期',
  `count` int(0) NULL DEFAULT NULL COMMENT '当天订购人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for power_types
-- ----------------------------
DROP TABLE IF EXISTS `power_types`;
CREATE TABLE `power_types`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of power_types
-- ----------------------------

-- ----------------------------
-- Table structure for powers
-- ----------------------------
DROP TABLE IF EXISTS `powers`;
CREATE TABLE `powers`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电源名称',
  `type` int(0) NULL DEFAULT NULL COMMENT '电源类型',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电源型号',
  `capacity` int(0) NULL DEFAULT NULL COMMENT '电源容量',
  `stock` int(0) NULL DEFAULT NULL COMMENT '库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `discount` tinyint(0) NULL DEFAULT 0 COMMENT '折扣',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改日期',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `sum` int(0) NULL DEFAULT NULL COMMENT '访问数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of powers
-- ----------------------------

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `account` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商姓名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商密码',
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suppliers
-- ----------------------------

-- ----------------------------
-- Table structure for supplies
-- ----------------------------
DROP TABLE IF EXISTS `supplies`;
CREATE TABLE `supplies`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `supplier_id` int(0) NOT NULL COMMENT '供应商id',
  `power_id` int(0) NOT NULL COMMENT '电源id',
  `sum` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '供应数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建日期',
  `supply_time` datetime(0) NULL DEFAULT NULL COMMENT '供应日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplies
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `account` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` tinyint(0) NULL DEFAULT NULL COMMENT '性别',
  `age` smallint(0) NULL DEFAULT NULL COMMENT '年龄',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `balance` double NULL DEFAULT NULL COMMENT '余额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次修改',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '00000001', '123456', 'otto', 1, 24, '123456', '12345678910', 'cccccc', 123, '2023-10-16 01:20:59', '2023-10-16 01:21:02', '2023-10-16 01:21:05');

-- ----------------------------
-- View structure for goods
-- ----------------------------
DROP VIEW IF EXISTS `goods`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods` AS select `t`.`value` AS `type`,`p`.`id` AS `id`,`p`.`name` AS `name`,`p`.`model` AS `model`,`p`.`capacity` AS `capacity`,`p`.`stock` AS `stock`,`p`.`price` AS `price`,`p`.`discount` AS `discount`,`p`.`status` AS `status`,`p`.`create_time` AS `create_time`,`p`.`update_time` AS `update_time`,`p`.`describe` AS `describe`,`p`.`type` AS `type_id`,`p`.`sum` AS `sum` from (`power_types` `t` join `powers` `p`) where (`t`.`id` = `p`.`type`);

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- View structure for judge
-- ----------------------------
DROP VIEW IF EXISTS `judge`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `judge` AS select `users`.`account` AS `u_account`,`powers`.`name` AS `p_name`,`powers`.`capacity` AS `p_capacity`,`powers`.`describe` AS `p_describe` from (`users` join `powers`);

-- ----------------------------
-- Records of users
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
