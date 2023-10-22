/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : power_sell_sys

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 22/10/2023 18:04:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `power_id` int NOT NULL COMMENT '电源id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `star` tinyint NOT NULL DEFAULT 1 COMMENT '好评星级',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 5, '1233', 5, '2023-10-21 21:08:31');

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL,
  `power_id` int NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favorites
-- ----------------------------
INSERT INTO `favorites` VALUES (2, 1, 6, '2023-10-21 21:04:39');
INSERT INTO `favorites` VALUES (3, 1, 7, '2023-10-22 00:41:54');
INSERT INTO `favorites` VALUES (5, 1, 4, '2023-10-22 16:39:09');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int NOT NULL,
  `job_number` smallint NULL DEFAULT NULL COMMENT '工号',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 1111, '123456', 'otto');

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
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `supply_time` datetime(0) NULL DEFAULT NULL COMMENT '供应时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of needs
-- ----------------------------

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
  `create_time` datetime(0) NOT NULL COMMENT '订购日期',
  `count` int NULL DEFAULT NULL COMMENT '当天订购人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for power_types
-- ----------------------------
DROP TABLE IF EXISTS `power_types`;
CREATE TABLE `power_types`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of power_types
-- ----------------------------
INSERT INTO `power_types` VALUES (1, '移动端');

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
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改日期',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `sum` int NULL DEFAULT NULL COMMENT '访问数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of powers
-- ----------------------------
INSERT INTO `powers` VALUES (1, '野兽电源', 1, 'senbasuki', 40000, 10, 114.50, 14, 1, '2023-10-21 02:52:45', '2023-10-21 02:52:48', '野兽电源，更持久，更耐用', 514);
INSERT INTO `powers` VALUES (2, '金轮电源', 1, 'v520', 4000, 10, 50.00, 0, 1, '2023-10-21 17:25:19', '2023-10-21 17:25:19', '金轮电源，值得信赖，所有芜湖人都在用', 0);
INSERT INTO `powers` VALUES (3, '野兽电源v2', 1, 'senbasukiv2', 1000, 50, 5.00, 0, 1, '2023-10-21 17:32:17', '2023-10-21 17:32:17', '野兽电源全新升级，更小巧', 0);
INSERT INTO `powers` VALUES (4, '金轮电源v2', 1, 'wuhu', 10000, 7, 105.00, 15, 1, '2023-10-21 17:36:19', '2023-10-21 17:36:19', '更大容量，更持久', 0);
INSERT INTO `powers` VALUES (5, '乔桑电源', 1, 'A10', 15000, 50, 100.00, 10, 1, '2023-10-21 17:39:37', '2023-10-21 17:39:37', '超大容量', 0);
INSERT INTO `powers` VALUES (6, '乔桑电源', 1, 'A113', 100000, 1, 1000.00, 0, 1, '2023-10-21 17:40:53', '2023-10-21 17:40:53', '巨大', 0);
INSERT INTO `powers` VALUES (7, 'ipower', 1, 'A114', 100, 1000, 10.00, 0, 1, '2023-10-21 17:45:01', '2023-10-21 17:45:01', 'cnmios', 0);
INSERT INTO `powers` VALUES (8, 'ipower', 1, 'A514', 2100, 5, 500.00, 10, 1, '2023-10-21 19:26:35', '2023-10-21 19:26:35', 'ipower new good.', 0);

-- ----------------------------
-- Table structure for suppliers
-- ----------------------------
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `account` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商姓名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商密码',
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `zip_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of suppliers
-- ----------------------------
INSERT INTO `suppliers` VALUES (1, '000001', '张桑', '123456789', '12345678910', '下北泽', '114514');

-- ----------------------------
-- Table structure for supplies
-- ----------------------------
DROP TABLE IF EXISTS `supplies`;
CREATE TABLE `supplies`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `supplier_id` int NOT NULL COMMENT '供应商id',
  `power_id` int NOT NULL COMMENT '电源id',
  `sum` int UNSIGNED NULL DEFAULT NULL COMMENT '供应数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建日期',
  `supply_time` datetime(0) NULL DEFAULT NULL COMMENT '供应日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplies
-- ----------------------------

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
  `balance` double NULL DEFAULT NULL COMMENT '余额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次修改',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '00000001', '123456', 'otto', 1, 24, '123456', '12345678910', '下北泽', 123, '2023-10-16 01:20:59', '2023-10-16 01:21:02', '2023-10-22 18:02:06');

-- ----------------------------
-- View structure for favorites_power
-- ----------------------------
DROP VIEW IF EXISTS `favorites_power`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `favorites_power` AS select `p`.`name` AS `name`,`p`.`stock` AS `stock`,`p`.`price` AS `price`,`p`.`discount` AS `discount`,`p`.`describe` AS `describe`,`f`.`user_id` AS `user_id`,`p`.`capacity` AS `capacity`,`pt`.`value` AS `value`,`p`.`id` AS `power_id`,`f`.`id` AS `id` from ((`powers` `p` join `favorites` `f` on((`p`.`id` = `f`.`power_id`))) join `power_types` `pt` on((`p`.`type` = `pt`.`id`)));

-- ----------------------------
-- View structure for goods
-- ----------------------------
DROP VIEW IF EXISTS `goods`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods` AS select `t`.`value` AS `type`,`p`.`id` AS `id`,`p`.`name` AS `name`,`p`.`model` AS `model`,`p`.`capacity` AS `capacity`,`p`.`stock` AS `stock`,`p`.`price` AS `price`,`p`.`discount` AS `discount`,`p`.`status` AS `status`,`p`.`create_time` AS `create_time`,`p`.`update_time` AS `update_time`,`p`.`describe` AS `describe`,`p`.`type` AS `type_id`,`p`.`sum` AS `sum` from (`power_types` `t` join `powers` `p`) where (`t`.`id` = `p`.`type`);

-- ----------------------------
-- View structure for judge
-- ----------------------------
DROP VIEW IF EXISTS `judge`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `judge` AS select `users`.`account` AS `u_account`,`powers`.`name` AS `p_name`,`powers`.`capacity` AS `p_capacity`,`powers`.`describe` AS `p_describe` from (`users` join `powers`);

SET FOREIGN_KEY_CHECKS = 1;
