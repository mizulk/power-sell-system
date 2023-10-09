/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : power_sell_sys

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 09/10/2023 22:33:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `user_id` int NOT NULL COMMENT '用户id',
  `power_id` int NOT NULL COMMENT '电源id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `star` tinyint NOT NULL DEFAULT 1 COMMENT '星'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `user_id` int NOT NULL,
  `power_id` int NOT NULL,
  `create_time` datetime NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int NOT NULL,
  `job_number` smallint NULL DEFAULT NULL COMMENT '工号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for needs
-- ----------------------------
DROP TABLE IF EXISTS `needs`;
CREATE TABLE `needs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `supplier_id` int NULL DEFAULT NULL COMMENT '供应商id',
  `type` int NULL DEFAULT 0 COMMENT '电源类型',
  `min_capacity` int UNSIGNED NOT NULL COMMENT '最小电池容量',
  `max_capacity` int UNSIGNED NOT NULL COMMENT '最大电池容量',
  `sum` int UNSIGNED NOT NULL COMMENT '需求数量',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `supply_time` datetime NULL DEFAULT NULL COMMENT '供应时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `power_id` int NOT NULL COMMENT '电源id',
  `sum` int UNSIGNED NOT NULL COMMENT '订购数量',
  `amount` decimal(10, 2) UNSIGNED NOT NULL COMMENT '订购金额',
  `create_time` datetime NOT NULL COMMENT '订购日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for power_types
-- ----------------------------
DROP TABLE IF EXISTS `power_types`;
CREATE TABLE `power_types`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for powers
-- ----------------------------
DROP TABLE IF EXISTS `powers`;
CREATE TABLE `powers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电源名称',
  `type` int NULL DEFAULT NULL COMMENT '电源类型',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电源型号',
  `capacity` int NULL DEFAULT NULL COMMENT '电源容量',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `discount` tinyint NULL DEFAULT 0 COMMENT '折扣',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改日期',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `discount check` CHECK (`discount` between 0 and 100)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名',
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplies
-- ----------------------------
DROP TABLE IF EXISTS `supplies`;
CREATE TABLE `supplies`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `supplier_id` int NOT NULL COMMENT '供应商id',
  `power_id` int NOT NULL COMMENT '电源id',
  `sum` int UNSIGNED NULL DEFAULT NULL COMMENT '供应数量',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `supply_time` datetime NULL DEFAULT NULL COMMENT '供应日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `account` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别',
  `age` smallint NULL DEFAULT NULL COMMENT '年龄',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '注册日期',
  `update_time` datetime NOT NULL COMMENT '最近一次修改',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `sex check` CHECK (`sex` in (0,1))
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for goods
-- ----------------------------
DROP VIEW IF EXISTS `goods`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods` AS select `t`.`value` AS `type`,`p`.`id` AS `id`,`p`.`name` AS `name`,`p`.`model` AS `model`,`p`.`capacity` AS `capacity`,`p`.`stock` AS `stock`,`p`.`price` AS `price`,`p`.`discount` AS `discount`,`p`.`status` AS `status`,`p`.`create_time` AS `create_time`,`p`.`update_time` AS `update_time`,`p`.`describe` AS `describe`,`p`.`type` AS `type_id` from (`power_types` `t` join `powers` `p`) where (`t`.`id` = `p`.`type`);

SET FOREIGN_KEY_CHECKS = 1;
