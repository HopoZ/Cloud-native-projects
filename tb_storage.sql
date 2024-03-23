/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL8
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : tb_storage

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 23/03/2024 14:14:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `storage_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `number` int NULL DEFAULT NULL COMMENT '库存数量',
  `date` datetime NULL DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '上衣', 656, '2024-02-21 15:28:32');
INSERT INTO `storage` VALUES (2, '裤子', 984, '2024-03-12 15:29:21');
INSERT INTO `storage` VALUES (3, '毛衣', 988, '2024-03-13 15:29:49');
INSERT INTO `storage` VALUES (4, '帽子', 742, '2024-03-10 15:30:10');
INSERT INTO `storage` VALUES (5, '鞋', 638, '2024-03-21 15:30:32');

SET FOREIGN_KEY_CHECKS = 1;
