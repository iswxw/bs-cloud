/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : bs-wxw

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 23/08/2020 10:28:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` bigint(50) NULL DEFAULT NULL COMMENT '用户ID',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `phone` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货电话',
  `zipcode` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `province` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `address` char(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 详细地址',
  `defaultAddress` tinyint(1) NULL DEFAULT NULL COMMENT '是否是默认地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (1, 4, '魏小伟', '15844258876', '1234', '北京', '北京', '海淀', '北京海淀区', 1);
INSERT INTO `tb_address` VALUES (2, 4, '刘备', '15844258876', '123', '北京', '北京', '朝阳区', '北京朝阳区', 0);

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `image` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌图片地址',
  `letter` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌的首字母',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌表，一个品牌下有多个商品（spu），一对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_brand
-- ----------------------------
INSERT INTO `tb_brand` VALUES (1, '红苹果', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62NM-ABYk8AAAzh5XLues393.png', 'H');
INSERT INTO `tb_brand` VALUES (6, '橙子玖原', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62M2SAJNPZAAAy5lrqPwY409.png', 'C');
INSERT INTO `tb_brand` VALUES (8, '香蕉都乐（Dole）', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6Ju-CAWLQKAAAlYutC95M258.jpg', 'X');
INSERT INTO `tb_brand` VALUES (9, '胜利果梨', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62IxqAfHFeAAA4y5uhMaM637.jpg', 'S');
INSERT INTO `tb_brand` VALUES (10, '萝卜宝贝', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62JAmACZFaAAAjb_fD40U295.jpg', 'L');
INSERT INTO `tb_brand` VALUES (11, '小土豆', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62JLWARv6SAAA2q9fpnaE901.jpg', 'X');
INSERT INTO `tb_brand` VALUES (12, '菠菜（BOCAL）', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62JX-ALjaQAAAukt4A-vw440.jpg', 'B');
INSERT INTO `tb_brand` VALUES (13, '辣白菜', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62NXGAZ7STAABNNzlQFsA747.png', 'L');
INSERT INTO `tb_brand` VALUES (14, '玉米棒', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62NCWANrpvAAA63NbIVuk298.png', 'Y');
INSERT INTO `tb_brand` VALUES (15, '黄瓜山', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62NnKAM9JmAAAnYb43azA790.png', 'H');
INSERT INTO `tb_brand` VALUES (16, '香菇脆', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62KXCAbGa-AAAwoGB5mgY134.jpg', 'X');
INSERT INTO `tb_brand` VALUES (17, '黑木耳', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62KfuAKga_AABGPMeXTYQ149.jpg', 'H');
INSERT INTO `tb_brand` VALUES (18, '碾米机风', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62KziAEocaAABqej0mtIQ120.jpg', 'N');
INSERT INTO `tb_brand` VALUES (19, '拖拉机酷牛', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62K8WAf-8QAAClBxjcxAo097.jpg', 'T');
INSERT INTO `tb_brand` VALUES (20, '榨油机良邦', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62LFOAX0o_AAAcU1xfm4Q237.jpg', 'Z');
INSERT INTO `tb_brand` VALUES (21, '有机肥料', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62LSWACj-gAABKs070foU591.jpg', 'Y');
INSERT INTO `tb_brand` VALUES (22, '复合肥料', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62LcqAGGOrAAAiozsqbVs757.jpg', 'F');
INSERT INTO `tb_brand` VALUES (23, '生物肥料', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62Lj6AVh6HAAArxZHZn4E743.jpg', 'S');
INSERT INTO `tb_brand` VALUES (24, '杀虫剂', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62L5CAbfUZAAA8olYVpvU693.png', 'S');
INSERT INTO `tb_brand` VALUES (26, '土壤调理', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62MJmATPPMAAA9_uRF5ws515.png', 'T');
INSERT INTO `tb_brand` VALUES (27, '道具收容所', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62MSOAXiyWAAAtPEomjIU509.png', 'D');
INSERT INTO `tb_brand` VALUES (28, '镐头场', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62McaAJU-zAAAyI3DObE0051.png', 'G');
INSERT INTO `tb_brand` VALUES (29, '防护工具', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62MlyAZHvBAABNQCd6a60494.png', 'F');
INSERT INTO `tb_brand` VALUES (30, '除草剂', 'http://image.wxw.com/group1/M00/00/02/rBEZ8F6_ubSARuYAAABD2WaeSjw092.png', 'C');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父类目id,顶级类目填0',
  `is_parent` tinyint(1) NOT NULL COMMENT '是否为父节点，0为否，1为是',
  `sort` int(4) NOT NULL COMMENT '排序指数，越小越靠前',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '新鲜水果', 0, 1, 1);
INSERT INTO `tb_category` VALUES (2, '种子种苗', 0, 1, 1);
INSERT INTO `tb_category` VALUES (3, '安心蔬菜', 0, 1, 1);
INSERT INTO `tb_category` VALUES (4, '农资农机', 0, 1, 1);
INSERT INTO `tb_category` VALUES (5, '海鲜水产', 0, 1, 1);
INSERT INTO `tb_category` VALUES (6, '粮油米面', 0, 1, 1);
INSERT INTO `tb_category` VALUES (7, '精选肉类', 0, 1, 1);
INSERT INTO `tb_category` VALUES (8, '蛋类', 7, 0, 2);
INSERT INTO `tb_category` VALUES (9, '热销水果', 1, 1, 2);
INSERT INTO `tb_category` VALUES (10, '水果种苗', 2, 0, 2);
INSERT INTO `tb_category` VALUES (11, '根茎菜类', 3, 1, 2);
INSERT INTO `tb_category` VALUES (12, '农用机械', 4, 1, 2);
INSERT INTO `tb_category` VALUES (13, '淡水鱼类', 5, 0, 2);
INSERT INTO `tb_category` VALUES (14, '谷类作物', 6, 0, 2);
INSERT INTO `tb_category` VALUES (17, '热带鲜果', 1, 1, 2);
INSERT INTO `tb_category` VALUES (19, '知名品牌', 1, 1, 3);
INSERT INTO `tb_category` VALUES (20, '蔬菜种子', 2, 0, 2);
INSERT INTO `tb_category` VALUES (21, '蔬菜种苗', 2, 0, 2);
INSERT INTO `tb_category` VALUES (23, '粮油类种子', 2, 0, 3);
INSERT INTO `tb_category` VALUES (25, '叶菜类', 3, 1, 2);
INSERT INTO `tb_category` VALUES (27, '茄果瓜类', 3, 1, 3);
INSERT INTO `tb_category` VALUES (30, '食用菌类', 3, 1, 4);
INSERT INTO `tb_category` VALUES (32, '无害肥料', 4, 1, 2);
INSERT INTO `tb_category` VALUES (34, '绿色农药', 4, 1, 3);
INSERT INTO `tb_category` VALUES (36, '农用物资', 4, 1, 4);
INSERT INTO `tb_category` VALUES (38, '虾类', 5, 0, 2);
INSERT INTO `tb_category` VALUES (42, '面食米食', 6, 0, 3);
INSERT INTO `tb_category` VALUES (44, '肉类', 7, 0, 2);
INSERT INTO `tb_category` VALUES (46, '活畜', 7, 0, 3);
INSERT INTO `tb_category` VALUES (48, '苹果', 9, 0, 1);
INSERT INTO `tb_category` VALUES (50, '芒果', 17, 0, 1);
INSERT INTO `tb_category` VALUES (52, '香蕉', 17, 0, 2);
INSERT INTO `tb_category` VALUES (56, '梨', 9, 0, 2);
INSERT INTO `tb_category` VALUES (58, '香蕉', 9, 0, 3);
INSERT INTO `tb_category` VALUES (62, '新奇士', 19, 0, 1);
INSERT INTO `tb_category` VALUES (63, '爱奇果', 19, 0, 2);
INSERT INTO `tb_category` VALUES (64, '佳农', 19, 0, 3);
INSERT INTO `tb_category` VALUES (65, '菠菜', 25, 0, 1);
INSERT INTO `tb_category` VALUES (66, '白菜', 25, 0, 2);
INSERT INTO `tb_category` VALUES (68, '萝卜', 11, 0, 1);
INSERT INTO `tb_category` VALUES (69, '土豆', 11, 0, 2);
INSERT INTO `tb_category` VALUES (73, '玉米', 27, 0, 1);
INSERT INTO `tb_category` VALUES (74, '黄瓜', 27, 0, 2);
INSERT INTO `tb_category` VALUES (75, '香菇', 30, 0, 1);
INSERT INTO `tb_category` VALUES (76, '木耳', 30, 0, 2);
INSERT INTO `tb_category` VALUES (77, '碾米机械', 12, 0, 1);
INSERT INTO `tb_category` VALUES (78, '农用拖拉机', 12, 0, 2);
INSERT INTO `tb_category` VALUES (79, '榨油机械', 12, 0, 3);
INSERT INTO `tb_category` VALUES (80, '有机肥料', 32, 0, 1);
INSERT INTO `tb_category` VALUES (81, '复合肥料', 32, 0, 2);
INSERT INTO `tb_category` VALUES (82, '生物肥料', 32, 0, 3);
INSERT INTO `tb_category` VALUES (85, '杀虫剂', 34, 0, 1);
INSERT INTO `tb_category` VALUES (86, '除草剂', 34, 0, 2);
INSERT INTO `tb_category` VALUES (87, '土壤调理剂', 34, 0, 3);
INSERT INTO `tb_category` VALUES (88, '道具类', 36, 0, 1);
INSERT INTO `tb_category` VALUES (92, '镐头类', 36, 0, 2);
INSERT INTO `tb_category` VALUES (93, '防护工具类', 36, 0, 3);

-- ----------------------------
-- Table structure for tb_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_category_brand`;
CREATE TABLE `tb_category_brand`  (
  `category_id` bigint(20) NOT NULL COMMENT '商品类目id',
  `brand_id` bigint(20) NOT NULL COMMENT '品牌id',
  PRIMARY KEY (`category_id`, `brand_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类和品牌的中间表，两者是多对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category_brand
-- ----------------------------
INSERT INTO `tb_category_brand` VALUES (17, 6);
INSERT INTO `tb_category_brand` VALUES (48, 1);
INSERT INTO `tb_category_brand` VALUES (52, 8);
INSERT INTO `tb_category_brand` VALUES (56, 9);
INSERT INTO `tb_category_brand` VALUES (65, 12);
INSERT INTO `tb_category_brand` VALUES (66, 13);
INSERT INTO `tb_category_brand` VALUES (68, 10);
INSERT INTO `tb_category_brand` VALUES (69, 11);
INSERT INTO `tb_category_brand` VALUES (73, 14);
INSERT INTO `tb_category_brand` VALUES (74, 15);
INSERT INTO `tb_category_brand` VALUES (75, 16);
INSERT INTO `tb_category_brand` VALUES (76, 17);
INSERT INTO `tb_category_brand` VALUES (77, 18);
INSERT INTO `tb_category_brand` VALUES (78, 19);
INSERT INTO `tb_category_brand` VALUES (79, 20);
INSERT INTO `tb_category_brand` VALUES (80, 21);
INSERT INTO `tb_category_brand` VALUES (81, 22);
INSERT INTO `tb_category_brand` VALUES (82, 23);
INSERT INTO `tb_category_brand` VALUES (85, 24);
INSERT INTO `tb_category_brand` VALUES (86, 30);
INSERT INTO `tb_category_brand` VALUES (87, 26);
INSERT INTO `tb_category_brand` VALUES (88, 27);
INSERT INTO `tb_category_brand` VALUES (92, 28);
INSERT INTO `tb_category_brand` VALUES (93, 29);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `total_pay` bigint(20) NOT NULL COMMENT '总金额，单位为分',
  `actual_pay` bigint(20) NOT NULL COMMENT '实付金额。单位:分。如:20007，表示:200元7分',
  `promotion_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `payment_type` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '支付类型，1、在线支付，2、货到付款',
  `post_fee` bigint(20) NOT NULL COMMENT '邮费。单位:分。如:20007，表示:200元7分',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流单号',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `buyer_message` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '买家昵称',
  `buyer_rate` tinyint(1) NULL DEFAULT NULL COMMENT '买家是否已经评价,0未评价，1已评价',
  `receiver_state` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收获地址（省）',
  `receiver_city` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收获地址（市）',
  `receiver_district` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收获地址（区/县）',
  `receiver_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收获地址（街道、住址等详细地址）',
  `receiver_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人手机',
  `receiver_zip` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人邮编',
  `receiver` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人',
  `invoice_type` int(1) NULL DEFAULT 0 COMMENT '发票类型(0无发票1普通发票，2电子发票，3增值税发票)',
  `source_type` int(1) NULL DEFAULT 2 COMMENT '订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE,
  INDEX `buyer_nick`(`buyer_nick`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1269456316011778049 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (1257499680632344576, 2, 2, '', 1, 0, '2020-05-05 10:37:42', NULL, NULL, '5', NULL, '何珍胜', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257517385104101376, 2, 2, '', 1, 0, '2020-05-05 11:48:03', NULL, NULL, '5', NULL, '何珍胜', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257636276237111296, 23, 23, '', 1, 0, '2020-05-05 19:40:29', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257637603545583616, 23, 23, '', 1, 0, '2020-05-05 19:45:45', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257641564373651456, 23, 23, '', 1, 0, '2020-05-05 20:01:29', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257642387350622208, 23, 23, '', 1, 0, '2020-05-05 20:04:46', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257644476734771200, 23, 23, '', 1, 0, '2020-05-05 20:13:04', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257645379437072384, 23, 23, '', 1, 0, '2020-05-05 20:16:39', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257648337843261440, 23, 23, '', 1, 0, '2020-05-05 20:28:24', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257649875600936960, 23, 23, '', 1, 0, '2020-05-05 20:34:31', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1257652306334322688, 23, 23, '', 1, 0, '2020-05-05 20:44:11', NULL, NULL, '4', NULL, '魏小伟', 0, '上海', '上海', '浦东新签', '航头镇航头路18号传智播客3号楼', '15800000000', '210000', '小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1260864418653278208, 21600, 21600, '', 1, 1, '2020-05-14 17:27:58', NULL, NULL, '4', '测试IT', '魏小伟', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '卢旭', 0, 2);
INSERT INTO `tb_order` VALUES (1260866454744600576, 21600, 21600, '', 1, 1, '2020-05-14 17:36:03', NULL, NULL, '4', '测试IT', '魏小伟', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '卢旭', 0, 2);
INSERT INTO `tb_order` VALUES (1261563192816046080, 2990, 2990, '', 1, 1, '2020-05-16 15:44:39', NULL, NULL, '10', '测试IT', '美团', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1261565900297998336, 18, 18, '', 1, 1, '2020-05-16 15:55:24', NULL, NULL, '10', '测试IT', '美团', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1261573213037662208, 18, 18, '', 1, 1, '2020-05-16 16:24:28', NULL, NULL, '10', '测试IT', '美团', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1261575669914144768, 18, 18, '', 1, 1, '2020-05-16 16:34:13', NULL, NULL, '10', '测试IT', '美团', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1261578684192657408, 36, 36, '', 1, 1, '2020-05-16 16:46:12', NULL, NULL, '10', '测试IT', '美团', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1269285821316468736, 10800, 10800, '', 1, 1, '2020-06-06 23:11:37', NULL, NULL, '7', '测试IT', '京东', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1269286669945802752, 10800, 10800, '', 1, 1, '2020-06-06 23:14:59', NULL, NULL, '7', '测试IT', '京东', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1269287719813648384, 18, 18, '', 1, 1, '2020-06-06 23:19:09', NULL, NULL, '7', '测试IT', '京东', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1269288045648154624, 18, 18, '', 1, 1, '2020-06-06 23:20:27', NULL, NULL, '7', '测试IT', '京东', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);
INSERT INTO `tb_order` VALUES (1269456316011778048, 18, 18, '', 1, 1, '2020-06-07 10:29:06', NULL, NULL, '7', '测试IT', '京东', 0, '1', '北京', '海淀', '北京海淀区', '15844258876', '1234', '魏小伟', 0, 2);

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku商品id',
  `num` int(11) NOT NULL COMMENT '购买数量',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `own_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品动态属性键值集',
  `price` bigint(20) NOT NULL COMMENT '价格,单位：分',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (1, 1257499680632344576, 10, 1, '苹果（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 2, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg');
INSERT INTO `tb_order_detail` VALUES (2, 1257517385104101376, 10, 1, '苹果（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 2, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62ap2AAcFtAAAc3PdlPAc426.jpg');
INSERT INTO `tb_order_detail` VALUES (3, 1257636276237111296, 10, 1, '香蕉（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JvSOASD_oAAAgWpcnfaw700.jpg');
INSERT INTO `tb_order_detail` VALUES (4, 1257637603545583616, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (5, 1257641564373651456, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (6, 1257642387350622208, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (7, 1257644476734771200, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (8, 1257645379437072384, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (9, 1257648337843261440, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (10, 1257649875600936960, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (11, 1257652306334322688, 10, 1, 'MAC（Apple）iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机3', '{\"机身颜色\":\"钻雕蓝\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', 23, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png');
INSERT INTO `tb_order_detail` VALUES (12, 1260864418653278208, 25, 2, '天水红富士苹果 300 1年 天水', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"天水\"}', 10, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg');
INSERT INTO `tb_order_detail` VALUES (13, 1260866454744600576, 25, 2, '天水红富士苹果 300 1年 天水', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"天水\"}', 10, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg');
INSERT INTO `tb_order_detail` VALUES (14, 1261563192816046080, 31, 1, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果 4 7 百运通', '{\"1\":\"4\",\"2\":\"7\",\"5\":\"百运通\"}', 2990, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JvSOASD_oAAAgWpcnfaw700.jpg');
INSERT INTO `tb_order_detail` VALUES (15, 1261565900297998336, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (16, 1261573213037662208, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (17, 1261575669914144768, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (18, 1261578684192657408, 43, 2, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (19, 1269285821316468736, 25, 1, '天水红富士苹果 300 1年 天水', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"天水\"}', 10800, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg');
INSERT INTO `tb_order_detail` VALUES (20, 1269286669945802752, 25, 1, '天水红富士苹果 300 1年 天水', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"天水\"}', 10800, 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg');
INSERT INTO `tb_order_detail` VALUES (21, 1269287719813648384, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (22, 1269288045648154624, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');
INSERT INTO `tb_order_detail` VALUES (23, 1269456316011778048, 43, 1, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 18, 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg');

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status`  (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  `comment_time` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_status
-- ----------------------------
INSERT INTO `tb_order_status` VALUES (1257499680632344576, 1, '2020-05-05 10:37:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257517385104101376, 1, '2020-05-05 11:48:03', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257636276237111296, 1, '2020-05-05 19:40:29', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257637603545583616, 1, '2020-05-05 19:45:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257641564373651456, 1, '2020-05-05 20:01:29', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257642387350622208, 1, '2020-05-05 20:04:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257644476734771200, 1, '2020-05-05 20:13:04', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257645379437072384, 1, '2020-05-05 20:16:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257648337843261440, 1, '2020-05-05 20:28:24', '2020-05-05 20:29:34', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257649875600936960, 1, '2020-05-05 20:34:31', '2020-05-05 20:36:17', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1257652306334322688, 1, '2020-05-05 20:44:11', '2020-05-05 20:45:13', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1260864418653278208, 1, '2020-05-14 17:27:58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1260866454744600576, 1, '2020-05-14 17:36:03', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1261563192816046080, 1, '2020-05-16 15:44:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1261565900297998336, 1, '2020-05-16 15:55:24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1261573213037662208, 1, '2020-05-16 16:24:28', '2020-05-16 16:25:25', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1261575669914144768, 1, '2020-05-16 16:34:13', '2020-05-16 16:34:54', NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1261578684192657408, 1, '2020-05-16 16:46:12', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1269285821316468736, 1, '2020-06-06 23:11:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1269286669945802752, 1, '2020-06-06 23:14:59', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1269287719813648384, 1, '2020-06-06 23:19:09', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1269288045648154624, 1, '2020-06-06 23:20:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order_status` VALUES (1269456316011778048, 1, '2020-06-07 10:29:06', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_log`;
CREATE TABLE `tb_pay_log`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `total_fee` bigint(20) NULL DEFAULT NULL COMMENT '支付金额（分）',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `transaction_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付交易号码',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '交易状态，1 未支付, 2已支付, 3 已退款, 4 支付错误, 5 已关闭',
  `pay_type` tinyint(1) NULL DEFAULT NULL COMMENT '支付方式，1 支付宝支付, 2 货到付款',
  `bank_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `closed_time` datetime(0) NULL DEFAULT NULL COMMENT '关闭时间',
  `refund_time` datetime(0) NULL DEFAULT NULL COMMENT '退款时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1261575669914144769 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pay_log
-- ----------------------------
INSERT INTO `tb_pay_log` VALUES (4, 23, 4, '2020050522001426340500736930', 1, 1, NULL, NULL, '2020-05-05 20:29:34', NULL, NULL);
INSERT INTO `tb_pay_log` VALUES (5, 23, 4, '2020050522001426340500736931', 1, 1, NULL, NULL, '2020-05-05 20:36:17', NULL, NULL);
INSERT INTO `tb_pay_log` VALUES (1257652306334322688, 23, 4, '2020050522001426340500736932', 1, 1, '中信银行', '2020-05-05 20:44:11', '2020-05-05 20:45:13', NULL, NULL);
INSERT INTO `tb_pay_log` VALUES (1261573213037662208, 18, 10, '2020051622001426340500743972', 1, 1, '中信银行', '2020-05-16 16:24:28', '2020-05-16 16:25:25', NULL, NULL);
INSERT INTO `tb_pay_log` VALUES (1261575669914144768, 18, 10, '2020051622001426340500744146', 1, 1, '中信银行', '2020-05-16 16:34:13', '2020-05-16 16:34:54', NULL, NULL);

-- ----------------------------
-- Table structure for tb_seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_seckill_order`;
CREATE TABLE `tb_seckill_order`  (
  `id` bigint(50) NOT NULL COMMENT '秒杀编号',
  `userId` bigint(50) NULL DEFAULT NULL COMMENT '秒杀用户',
  `skuId` bigint(50) NULL DEFAULT NULL COMMENT '商品ID',
  `orderId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_sku
-- ----------------------------
DROP TABLE IF EXISTS `tb_sku`;
CREATE TABLE `tb_sku`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'sku id',
  `spu_id` bigint(20) NOT NULL COMMENT 'spu id',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
  `images` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品的图片，多个图片以‘,’分割',
  `price` bigint(15) NOT NULL DEFAULT 0 COMMENT '销售价格，单位为分',
  `indexes` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '特有规格属性在spu属性模板中的对应下标组合',
  `own_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效，0无效，1有效',
  `create_time` datetime(0) NOT NULL COMMENT '添加时间',
  `last_update_time` datetime(0) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_spu_id`(`spu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sku
-- ----------------------------
INSERT INTO `tb_sku` VALUES (13, 8, '正宗甘肃天水黄元帅苹果水果5斤新鲜当季黄金帅丑苹果整箱 2500g  2500 30 甘肃天水', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62QyyAWzjoAACeoVaV16A95.jpeg,http://image.wxw.com/group1/M00/00/00/rBEZ8F62Qy-AC3fOAABihg0ISyc14.jpeg,http://image.wxw.com/group1/M00/00/00/rBEZ8F62QzKAS9X_AACKFq6he2c40.jpeg,http://image.wxw.com/group1/M00/00/00/rBEZ8F62QzWAbVFaAAB3NhO1OTk55.jpeg,http://image.wxw.com/group1/M00/00/00/rBEZ8F62QzmAbUxdAACAH8S4sJo87.jpeg', 1200, '0_0_0', '{\"1\":\"2500\",\"2\":\"30\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 13:44:34', '2020-05-09 13:44:34');
INSERT INTO `tb_sku` VALUES (14, 9, '福建土楼红皮香蕉新鲜当季热带水果红美人香蕉五斤精致包装 红香蕉5斤装 2.5 7 中国大陆', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62T1KAZiukAADqD0weQbk557.png,http://image.wxw.com/group1/M00/00/00/rBEZ8F62T1mAaeKwAAA2k8xrcA8995.jpg', 1000, '0_0_0', '{\"1\":\"2.5\",\"2\":\"7\",\"5\":\"中国大陆\"}', 1, '2020-05-09 14:36:29', '2020-05-09 14:36:29');
INSERT INTO `tb_sku` VALUES (15, 9, '福建土楼红皮香蕉新鲜当季热带水果红美人香蕉五斤精致包装 红香蕉5斤装 2.5 14 中国大陆', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62T2GADvLFAABxyO91Q68642.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62T2SAFXaBAADPhPO3DWI562.jpg', 1500, '0_1_0', '{\"1\":\"2.5\",\"2\":\"14\",\"5\":\"中国大陆\"}', 1, '2020-05-09 14:36:29', '2020-05-09 14:36:29');
INSERT INTO `tb_sku` VALUES (16, 10, '高山农家香甜香蕉 新鲜青香蕉 4.5kg 2.5 15 广西', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62UWmAKdxIAABwcsyt5Rc312.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62UW6ADH74AACf2xZnofA297.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62UXOAQs7HAAA8RS198GE913.jpg', 1500, '0_0_0', '{\"1\":\"2.5\",\"2\":\"15\",\"5\":\"广西\"}', 1, '2020-05-09 14:45:10', '2020-05-09 14:45:10');
INSERT INTO `tb_sku` VALUES (17, 11, '新疆库尔勒香梨 精选特级 单果120g以上 净重2.5kg 新生鲜水果 孕妇可食 2.5 7 福建', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62U1-AXz-7AACnGJ6Cs_Y91.jpeg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62U2KAaIVPAACLaaGAnuY649.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62U2aAFsGYAABdu3JmFv475.jpeg', 2000, '0_0_0', '{\"1\":\"2.5\",\"2\":\"7\",\"5\":\"福建\"}', 1, '2020-05-09 14:53:33', '2020-05-09 14:53:33');
INSERT INTO `tb_sku` VALUES (18, 12, '都乐Dole 比利时啤梨12只装 单果120g起 进口新鲜水果 1.9kg 20天 比利时', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62VLuAbmEhAAAsuoKeAMo795.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62VL6AZxacAACcvmPq8tU798.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62VMKAJF1aAAA4Ibq44KQ511.jpg', 3900, '0_0_0', '{\"1\":\"1.9kg\",\"2\":\"20天\",\"5\":\"比利时\"}', 1, '2020-05-09 14:59:16', '2020-05-09 14:59:16');
INSERT INTO `tb_sku` VALUES (20, 14, '碾米机家用小型胚芽米机鲜米机打米机精米机糙米机稻谷脱壳机碾大米机 碾米粉碎机不带电机 3000 3年 中国', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62V--AB_VvAAD_O2r7aTk868.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62V_OABxx2AABNqY0BE3w646.jpg', 200000, '0_0_0', '{\"1\":\"3000\",\"2\":\"3年\",\"5\":\"中国\"}', 1, '2020-05-09 15:12:56', '2020-05-09 15:12:56');
INSERT INTO `tb_sku` VALUES (21, 15, '轮式拖拉机 拖拉机 30000 5年 德国', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62WmWAS2TwAADHSGafzYI751.png,http://image.wxw.com/group1/M00/00/01/rBEZ8F62WmiAKbyOAABsk4JqLlo690.jpg', 5400000, '0_0_0', '{\"1\":\"30000\",\"2\":\"5年\",\"5\":\"德国\"}', 1, '2020-05-09 15:23:34', '2020-05-09 15:23:34');
INSERT INTO `tb_sku` VALUES (22, 15, '轮式拖拉机 拖拉机 50000 5年 德国', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62Wm-AE3zXAADHSGafzYI054.png,http://image.wxw.com/group1/M00/00/01/rBEZ8F62WnKAVXD-AAClBxjcxAo123.jpg', 6900000, '1_0_0', '{\"1\":\"50000\",\"2\":\"5年\",\"5\":\"德国\"}', 1, '2020-05-09 15:23:34', '2020-05-09 15:23:34');
INSERT INTO `tb_sku` VALUES (23, 16, '十八油坊ZYJ-709新款榨油机家用全自动带温控小型304不绣钢炸油机 榨油机 5000 5年 中国大陆', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62XQGAODLNAABMvEBvK80288.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62XQSAe2u7AAA_tF_kHqc439.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62XQiADnADAAEKbRZ-o5o894.jpg', 238000, '0_0_0', '{\"1\":\"5000\",\"2\":\"5年\",\"5\":\"中国大陆\"}', 1, '2020-05-09 15:34:35', '2020-05-09 15:34:35');
INSERT INTO `tb_sku` VALUES (24, 17, '陕西洛川红富士苹果 12个 净重2kg 果径75mm 新鲜水果 1.5kg 30天 庆阳', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62ZVWADGgxAAAu7VVHd0Q699.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62ZVqAEEIVAAAqaeC9F6g406.jpg', 3000, '0_0_0', '{\"1\":\"1.5kg\",\"2\":\"30天\",\"5\":\"庆阳\"}', 1, '2020-05-09 16:10:04', '2020-05-09 16:10:04');
INSERT INTO `tb_sku` VALUES (25, 5, '天水红富士苹果 300 1年 天水', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnL6AQpUSAAAqaeC9F6g045.jpg,http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnMKAWAxMAAAu7VVHd0Q971.jpg,http://image.wxw.com/group1/M00/00/00/rBEZ8F6JnM6AaGFAAACb9w6NbxA701.jpg', 10800, '0_0_0', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"天水\"}', 1, '2020-05-09 16:33:08', '2020-05-09 16:33:08');
INSERT INTO `tb_sku` VALUES (26, 5, '天水红富士苹果 300 1年 庆阳', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62ap2AAcFtAAAc3PdlPAc426.jpg', 4000, '0_0_1', '{\"1\":\"300\",\"2\":\"1年\",\"5\":\"庆阳\"}', 1, '2020-05-09 16:33:08', '2020-05-09 16:33:08');
INSERT INTO `tb_sku` VALUES (27, 5, '天水红富士苹果 200 1年 天水', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62aq2AUWnZAAAqaeC9F6g727.jpg', 5800, '1_0_0', '{\"1\":\"200\",\"2\":\"1年\",\"5\":\"天水\"}', 1, '2020-05-09 16:33:08', '2020-05-09 16:33:08');
INSERT INTO `tb_sku` VALUES (28, 5, '天水红富士苹果 200 1年 庆阳', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62aryAJ3eQAAAu7VVHd0Q705.jpg', 2900, '1_0_1', '{\"1\":\"200\",\"2\":\"1年\",\"5\":\"庆阳\"}', 1, '2020-05-09 16:33:08', '2020-05-09 16:33:08');
INSERT INTO `tb_sku` VALUES (31, 6, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果 4 7 百运通', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F6JvSOASD_oAAAgWpcnfaw700.jpg,http://image.wxw.com/group1/M00/00/00/rBEZ8F6JvSiAYpvQAADUp-uoJL8363.png', 2990, '0_0_0', '{\"1\":\"4\",\"2\":\"7\",\"5\":\"百运通\"}', 1, '2020-05-09 17:46:36', '2020-05-09 17:46:36');
INSERT INTO `tb_sku` VALUES (32, 6, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果 4 7 天津产', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62bB6AEjp3AAAgWpcnfaw743.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62bCKANAM2AADUp-uoJL8162.png', 3800, '0_0_1', '{\"1\":\"4\",\"2\":\"7\",\"5\":\"天津产\"}', 1, '2020-05-09 17:46:36', '2020-05-09 17:46:36');
INSERT INTO `tb_sku` VALUES (33, 6, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果 4 半年 百运通', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e-SAbia2AADqD0weQbk611.png', 2300, '0_1_0', '{\"1\":\"4\",\"2\":\"半年\",\"5\":\"百运通\"}', 1, '2020-05-09 17:46:36', '2020-05-09 17:46:36');
INSERT INTO `tb_sku` VALUES (34, 6, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果 4 半年 天津产', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62e_SAUVmeAACf2xZnofA614.jpg', 2500, '0_1_1', '{\"1\":\"4\",\"2\":\"半年\",\"5\":\"天津产\"}', 1, '2020-05-09 17:46:36', '2020-05-09 17:46:36');
INSERT INTO `tb_sku` VALUES (35, 7, '甘肃天水花牛苹果/蛇果 12个 单果160g-240g 净重4斤以上 新鲜水果 2kg 30天 甘肃天水', 'http://image.wxw.com/group1/M00/00/00/rBEZ8F62P7uAPCN_AACb9w6NbxA513.jpg,http://image.wxw.com/group1/M00/00/00/rBEZ8F62P7-AEQwAAAAc3PdlPAc020.jpg', 1000, '0_0_0', '{\"1\":\"2kg\",\"2\":\"30天\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 17:49:42', '2020-05-09 17:49:42');
INSERT INTO `tb_sku` VALUES (36, 7, '甘肃天水花牛苹果/蛇果 12个 单果160g-240g 净重4斤以上 新鲜水果 2kg 30天 甘肃庆阳', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fIqAYl3eAAAu7VVHd0Q651.jpg', 2000, '0_0_1', '{\"1\":\"2kg\",\"2\":\"30天\",\"5\":\"甘肃庆阳\"}', 1, '2020-05-09 17:49:42', '2020-05-09 17:49:42');
INSERT INTO `tb_sku` VALUES (37, 7, '甘肃天水花牛苹果/蛇果 12个 单果160g-240g 净重4斤以上 新鲜水果 2kg 2个月 甘肃天水', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fJuAahJeAAAqaeC9F6g341.jpg', 6800, '0_1_0', '{\"1\":\"2kg\",\"2\":\"2个月\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 17:49:42', '2020-05-09 17:49:42');
INSERT INTO `tb_sku` VALUES (38, 7, '甘肃天水花牛苹果/蛇果 12个 单果160g-240g 净重4斤以上 新鲜水果 2kg 2个月 甘肃庆阳', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fKuABxf6AACb9w6NbxA929.jpg', 3800, '0_1_1', '{\"1\":\"2kg\",\"2\":\"2个月\",\"5\":\"甘肃庆阳\"}', 1, '2020-05-09 17:49:42', '2020-05-09 17:49:42');
INSERT INTO `tb_sku` VALUES (39, 13, '精米机 家用小型碾米机 鲜米机碾米机 稻谷去脱壳机 打米机 打谷机 磨米碾米机 10000 3年 中国大陆', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62VoaAW367AAEVVuWNC0g963.jpg,http://image.wxw.com/group1/M00/00/01/rBEZ8F62VoqAZTD4AACSO2aIPKY614.jpg', 150000, '0_0_0', '{\"1\":\"10000\",\"2\":\"3年\",\"5\":\"中国大陆\"}', 1, '2020-05-09 17:52:29', '2020-05-09 17:52:29');
INSERT INTO `tb_sku` VALUES (40, 13, '精米机 家用小型碾米机 鲜米机碾米机 稻谷去脱壳机 打米机 打谷机 磨米碾米机 10000 3年 加拿大', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fTKAIyBrAACSO2aIPKY078.jpg', 2800000, '0_0_1', '{\"1\":\"10000\",\"2\":\"3年\",\"5\":\"加拿大\"}', 1, '2020-05-09 17:52:29', '2020-05-09 17:52:29');
INSERT INTO `tb_sku` VALUES (41, 13, '精米机 家用小型碾米机 鲜米机碾米机 稻谷去脱壳机 打米机 打谷机 磨米碾米机 10000 5年 中国大陆', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fUWAPETFAABNqY0BE3w499.jpg', 230000, '0_1_0', '{\"1\":\"10000\",\"2\":\"5年\",\"5\":\"中国大陆\"}', 1, '2020-05-09 17:52:29', '2020-05-09 17:52:29');
INSERT INTO `tb_sku` VALUES (42, 15, '精米机 家用小型碾米机 鲜米机碾米机 稻谷去脱壳机 打米机 打谷机 磨米碾米机 10000 5年 加拿大', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62fVmAXIjNAAD_O2r7aTk859.jpg', 80000, '0_1_1', '{\"1\":\"10000\",\"2\":\"5年\",\"5\":\"加拿大\"}', 1, '2020-05-09 17:52:29', '2020-05-09 17:52:29');
INSERT INTO `tb_sku` VALUES (43, 18, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 云南玉溪', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f1WAf4AHAAC0IWGZzRE51.jpeg', 18, '0_0_0', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"云南玉溪\"}', 1, '2020-05-09 18:01:38', '2020-05-09 18:01:38');
INSERT INTO `tb_sku` VALUES (44, 18, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 15天 甘肃天水', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f2KAdJM2AACsA7zhfVs73.jpeg', 25, '0_0_1', '{\"1\":\"1kg\",\"2\":\"15天\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 18:01:38', '2020-05-09 18:01:38');
INSERT INTO `tb_sku` VALUES (45, 18, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 30天 云南玉溪', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f3CAKyiGAACHW78cZ-U341.jpg', 37, '0_1_0', '{\"1\":\"1kg\",\"2\":\"30天\",\"5\":\"云南玉溪\"}', 1, '2020-05-09 18:01:38', '2020-05-09 18:01:38');
INSERT INTO `tb_sku` VALUES (46, 18, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜 1kg 30天 甘肃天水', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62f36APDazAAEdSiTpqkg95.jpeg', 56, '0_1_1', '{\"1\":\"1kg\",\"2\":\"30天\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 18:01:38', '2020-05-09 18:01:38');
INSERT INTO `tb_sku` VALUES (47, 18, '家美舒达 潍县萝卜 山东特产 潍坊青萝卜 水果萝卜 约2.5kg 烧烤食材 新鲜蔬菜 蔬菜礼盒 国家地理标志产品 2.5kg 20天 山东潍坊', 'http://image.wxw.com/group1/M00/00/01/rBEZ8F62gWqACzvSAACKv0H8IJA352.jpg', 2700, '0_0_0', '{\"1\":\"2.5kg\",\"2\":\"20天\",\"5\":\"山东潍坊\"}', 1, '2020-05-09 18:10:38', '2020-05-09 18:10:38');
INSERT INTO `tb_sku` VALUES (48, 19, '家美舒达 潍县萝卜 山东特产 潍坊青萝卜 水果萝卜 约2.5kg 烧烤食材 新鲜蔬菜 蔬菜礼盒 国家地理标志产品 2.5kg 20天 甘肃天水', 'http://image.wxw.com/group1/M00/00/02/rBEZ8F62gXqAd3aWAAC5zoIj6SA023.jpg', 3800, '0_0_1', '{\"1\":\"2.5kg\",\"2\":\"20天\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 18:10:38', '2020-05-09 18:10:38');
INSERT INTO `tb_sku` VALUES (49, 19, '家美舒达 潍县萝卜 山东特产 潍坊青萝卜 水果萝卜 约2.5kg 烧烤食材 新鲜蔬菜 蔬菜礼盒 国家地理标志产品 2.5kg 30天 山东潍坊', 'http://image.wxw.com/group1/M00/00/02/rBEZ8F62gYyAENztAABvZ5dD9tc350.jpg', 4900, '0_1_0', '{\"1\":\"2.5kg\",\"2\":\"30天\",\"5\":\"山东潍坊\"}', 1, '2020-05-09 18:10:38', '2020-05-09 18:10:38');
INSERT INTO `tb_sku` VALUES (50, 18, '家美舒达 潍县萝卜 山东特产 潍坊青萝卜 水果萝卜 约2.5kg 烧烤食材 新鲜蔬菜 蔬菜礼盒 国家地理标志产品 2.5kg 30天 甘肃天水', 'http://image.wxw.com/group1/M00/00/02/rBEZ8F62gZuAdPFtAAA4J37StgU782.jpg', 1900, '0_1_1', '{\"1\":\"2.5kg\",\"2\":\"30天\",\"5\":\"甘肃天水\"}', 1, '2020-05-09 18:10:38', '2020-05-09 18:10:38');

-- ----------------------------
-- Table structure for tb_spec_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_spec_group`;
CREATE TABLE `tb_spec_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` bigint(20) NOT NULL COMMENT '商品分类id，一个分类下有多个规格组',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格组的名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_category`(`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格参数的分组表，每个商品分类下有多个规格参数组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spec_group
-- ----------------------------
INSERT INTO `tb_spec_group` VALUES (1, 48, '主体');
INSERT INTO `tb_spec_group` VALUES (2, 48, '包装清单');
INSERT INTO `tb_spec_group` VALUES (3, 77, '主体');
INSERT INTO `tb_spec_group` VALUES (4, 77, '包装清单');
INSERT INTO `tb_spec_group` VALUES (5, 68, '主体');
INSERT INTO `tb_spec_group` VALUES (6, 68, '包装清单');

-- ----------------------------
-- Table structure for tb_spec_param
-- ----------------------------
DROP TABLE IF EXISTS `tb_spec_param`;
CREATE TABLE `tb_spec_param`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` bigint(20) NOT NULL COMMENT '商品分类id',
  `group_id` bigint(20) NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名',
  `numeric` tinyint(1) NOT NULL COMMENT '是否是数字类型参数，true或false',
  `unit` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '数字类型参数的单位，非数字类型可以为空',
  `generic` tinyint(1) NOT NULL COMMENT '是否是sku通用属性，true或false',
  `searching` tinyint(1) NOT NULL COMMENT '是否用于搜索过滤，true或false',
  `segments` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_group`(`group_id`) USING BTREE,
  INDEX `key_category`(`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格参数组下的参数名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spec_param
-- ----------------------------
INSERT INTO `tb_spec_param` VALUES (1, 48, 1, '净含量', 0, 'kg', 0, 1, '0-3');
INSERT INTO `tb_spec_param` VALUES (2, 48, 1, '保质期', 1, '天', 0, 1, '0-30');
INSERT INTO `tb_spec_param` VALUES (3, 48, 1, '贮存条件', 1, '℃', 1, 1, '0-4');
INSERT INTO `tb_spec_param` VALUES (5, 48, 2, '源产地 ', 1, '粒', 0, 1, '1-12');

-- ----------------------------
-- Table structure for tb_spu
-- ----------------------------
DROP TABLE IF EXISTS `tb_spu`;
CREATE TABLE `tb_spu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'spu id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `sub_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '子标题',
  `cid1` bigint(20) NOT NULL COMMENT '1级类目id',
  `cid2` bigint(20) NOT NULL COMMENT '2级类目id',
  `cid3` bigint(20) NOT NULL COMMENT '3级类目id',
  `brand_id` bigint(20) NOT NULL COMMENT '商品所属品牌id',
  `saleable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否上架，0下架，1上架',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效，0已删除，1有效',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'spu表，该表描述的是一个抽象性的商品，比如 iphone8' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spu
-- ----------------------------
INSERT INTO `tb_spu` VALUES (5, '天水红富士苹果', '新鲜水果', 1, 9, 48, 1, 1, 1, '2020-04-05 17:02:21', '2020-05-09 16:33:08');
INSERT INTO `tb_spu` VALUES (6, '都乐Dole 菲律宾进口高地蕉 香蕉 2把装 总重约1.4kg 新鲜水果', '好吃又减肥', 1, 17, 52, 8, 1, 1, '2020-04-05 19:12:44', '2020-05-09 17:46:36');
INSERT INTO `tb_spu` VALUES (7, '甘肃天水花牛苹果/蛇果 12个 单果160g-240g 净重4斤以上 新鲜水果', ' 价格不贵，很好吃，服务很好很新鲜', 1, 9, 48, 1, 1, 1, '2020-05-09 13:29:47', '2020-05-09 17:49:42');
INSERT INTO `tb_spu` VALUES (8, '正宗甘肃天水黄元帅苹果水果5斤新鲜当季黄金帅丑苹果整箱 2500g ', '黄元帅需要催熟至金黄色才香糯甜川~~~金冠苹果，又名黄金帅 黄元帅 、 黄香蕉，华珍苹果，也有的农民叫它香蕉苹果 黄元帅苹果种碳水化合物 、 水分 、 纤维 、 钾含量都 高的水果，对于缓解便秘 、 消除水肿均有一定帮助 ，适 臧肥时食用 。 成熟后果皮呈金苋，阳面带有红晕，皮薄 锈斑，有光泽;肉质细密，呈黄白色，汁液较多，昧深醇香， 酸适口 。 黄元帅需要催熟至金黄色才香糯甜!!!', 1, 9, 48, 1, 1, 1, '2020-05-09 13:44:34', '2020-05-09 13:44:34');
INSERT INTO `tb_spu` VALUES (9, '福建土楼红皮香蕉新鲜当季热带水果红美人香蕉五斤精致包装 红香蕉5斤装', ' 红香蕉味道真不错，口感比正常的香蕉还要香甜一些！就是我在北方由于天气原因困得时间能稍微久点，不耽误吃呦！哈哈哈，老人孩子都喜欢吃呢，价格也不贵，超级划算！', 1, 17, 52, 8, 1, 1, '2020-05-09 14:36:29', '2020-05-09 14:36:29');
INSERT INTO `tb_spu` VALUES (10, '高山农家香甜香蕉 新鲜青香蕉 4.5kg', '发的是青的！发的是青的！发的是青的！考虑到运输因素发的是七八分熟（还是青的、硬的）收到后捂一捂软了黄了就可以食用了哦', 1, 9, 58, 8, 1, 1, '2020-05-09 14:45:10', '2020-05-09 14:45:10');
INSERT INTO `tb_spu` VALUES (11, '新疆库尔勒香梨 精选特级 单果120g以上 净重2.5kg 新生鲜水果 孕妇可食', '很脆，口感很好，汁多爽口，很新鲜，活动价买了两箱，喜欢吃的下单吧，', 1, 9, 56, 9, 1, 1, '2020-05-09 14:53:33', '2020-05-09 14:53:33');
INSERT INTO `tb_spu` VALUES (12, '都乐Dole 比利时啤梨12只装 单果120g起 进口新鲜水果', ' 放冰箱一周后食用，变软后削皮食用，非常好吃', 1, 9, 56, 9, 1, 1, '2020-05-09 14:59:16', '2020-05-09 14:59:16');
INSERT INTO `tb_spu` VALUES (13, '精米机 家用小型碾米机 鲜米机碾米机 稻谷去脱壳机 打米机 打谷机 磨米碾米机', '我们是收做大米的要带着去现场收谷子用', 4, 12, 77, 18, 1, 1, '2020-05-09 15:07:06', '2020-05-09 17:52:29');
INSERT INTO `tb_spu` VALUES (14, '碾米机家用小型胚芽米机鲜米机打米机精米机糙米机稻谷脱壳机碾大米机 碾米粉碎机不带电机', '商城向您保证所售商品均为正品行货，京东自营商品开具机打发票或电子发票。', 4, 12, 77, 18, 1, 1, '2020-05-09 15:12:56', '2020-05-09 15:12:56');
INSERT INTO `tb_spu` VALUES (15, '轮式拖拉机 拖拉机', '宏发机械有限公司专业生产青储机打捆机清粪车撒料车', 4, 12, 78, 19, 1, 1, '2020-05-09 15:23:34', '2020-05-09 15:23:34');
INSERT INTO `tb_spu` VALUES (16, '十八油坊ZYJ-709新款榨油机家用全自动带温控小型304不绣钢炸油机 榨油机', ' 榨油机很好用，出油率很高。这是在京东买的，性价比很高的产品，非常满意！', 4, 12, 79, 20, 1, 1, '2020-05-09 15:34:35', '2020-05-09 15:34:35');
INSERT INTO `tb_spu` VALUES (17, '陕西洛川红富士苹果 12个 净重2kg 果径75mm 新鲜水果', '又脆又甜，很好', 1, 9, 48, 1, 1, 1, '2020-05-09 16:10:04', '2020-05-09 16:10:04');
INSERT INTO `tb_spu` VALUES (18, '绿鲜知 荷兰土豆 洋芋 约1kg 烧烤火锅食材 新鲜蔬菜', ' 土豆到了，个头大小匀称一次两个够吃了，京东生鲜的包装是真不错买来的也很新鲜，平时比较爱吃土豆，炖牛肉了炝土豆丝了，青椒土豆片，都不错，京东物流真的快，新鲜度也好，感谢京东在疫情期间的付出，武汉加油！中国加油！', 3, 11, 69, 11, 1, 1, '2020-05-09 18:01:38', '2020-05-09 18:01:38');
INSERT INTO `tb_spu` VALUES (19, '家美舒达 潍县萝卜 山东特产 潍坊青萝卜 水果萝卜 约2.5kg 烧烤食材 新鲜蔬菜 蔬菜礼盒 国家地理标志产品', '皮色深绿、肉质翠绿坚实、细腻多汁、水多味甜', 3, 11, 68, 10, 1, 1, '2020-05-09 18:10:38', '2020-05-09 18:10:38');

-- ----------------------------
-- Table structure for tb_spu_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_spu_detail`;
CREATE TABLE `tb_spu_detail`  (
  `spu_id` bigint(20) NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述信息',
  `generic_spec` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '通用规格参数数据',
  `special_spec` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '特有规格参数及可选值信息，json格式',
  `packing_list` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '包装清单',
  `after_service` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '售后服务',
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spu_detail
-- ----------------------------
INSERT INTO `tb_spu_detail` VALUES (5, '<p class=\"ql-align-center\"><br></p><p><br></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/519/029/12321920915_1210087694.jpg\" alt=\"1\" height=\"790\" width=\"790\"></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/938/941/12287149839_1210087694.jpg\" alt=\"4\" height=\"790\" width=\"790\"></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/439/641/12287146934_1210087694.jpg\" alt=\"3\" height=\"790\" width=\"790\"></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/301/603/12249306103_1210087694.jpg\" alt=\"2\" height=\"790\" width=\"790\"></p><p><br></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/435/839/12321938534_1210087694.jpg\" alt=\"5\" height=\"790\" width=\"790\"></p><p><br></p><p><img src=\"https://cbu01.alicdn.com/img/ibank/2019/538/863/12911368835_1210087694.jpg\" alt=\"undefined\" height=\"790\" width=\"790\"></p>', '{\"3\":\"0-30\"}', '{\"1\":[\"300\",\"200\"],\"2\":[\"1年\"],\"5\":[\"天水\",\"庆阳\"]}', '天水红富士 12粒优质装×1', '生鲜“优鲜赔”绿色通道，专业生鲜客服团队—让您售后无忧。');
INSERT INTO `tb_spu_detail` VALUES (6, '<p><span style=\"color: rgb(102, 102, 102);\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/108053/6/8415/208258/5e675696E829a7ec7/42914b4179acbc40.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/106656/12/14610/244608/5e675697E97abea38/dac406468349e4b9.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/88449/27/14548/219733/5e675697E02daacc5/b99a8763b89b391a.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/105939/30/14570/189937/5e675697E96c17954/cc0eabde8274ffc9.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/92374/39/14723/163570/5e675698E88e8caf1/d2f4b5df9f7b20ee.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/96894/25/14652/172149/5e675698Ec79d0fa4/54ed0c11532af769.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/98604/36/14498/180975/5e675698Ef55218fa/fa69639358a86292.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/97679/27/14585/159198/5e675698Ee11966f7/61b55f36ea36b4b3.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/106810/3/14547/180447/5e675698E69b30265/29035f40983e36e6.jpg\"></span></p>', '{\"3\":\"控温10-15\"}', '{\"1\":[\"4\"],\"2\":[\"7\",\"半年\"],\"5\":[\"百运通\",\"天津产\"]}', '进口菲律宾香蕉 2把', '专业生鲜客服团队—让您售后无忧');
INSERT INTO `tb_spu_detail` VALUES (7, '<p>购买必读 因快递限制重量10斤。超重会额外增加更多费用。且箱子重量不一，所以10斤装均为带箱约9.5-10斤左右，净重约为8.5以上。介意者还望理解。 ●收到快递请您在24小时之内检查是否有损伤，有问题以包装箱为背景拍照联系在线客服，客服会根据情况给您进行赔偿处理由于苹果为不规则物体，规格由圆形果尺卡苹果最大直径来确定，并非直尺衡量。 ●苹果品质产地不-样，请勿拿其他价格便宜的苹果与正宗洛川苹果进行对比 脆甜多汁，山西和陕西两大冷库链发出。非当季水果，非现摘现发，坏果包赔。 提供服务:支持快递 支持代卖 小果70mm左右，中果80mm左右，大果85mm左右 购买需知:鲜果不支持拒收，不支持退货退款，坏果赔付 发货时限:48小时内发货 坏果问题:亲，收到的坏果可以赔付的，请您拍照快递面单和坏果照片提供给我，坏多少赔付多少哦 发哪个快递:不接受指定快递，为亲安排适合亲尽快收货地址的快递​</p><p class=\"ql-align-center\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/20/35205404984f420581f9b1e8d829e9c0.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/20/f5f836d44e314725938661b8d6c4b440.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/02/17/3ea6e7a583424e3fa78a9945cbe6cb79.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/02/17/b290690f539147139e538c4ec9e4c250.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/02/17/1bf9b387778b44589b3be4a4a84b7b82.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/02/17/5e9171296e3a46a9b9c8e0852f94f3b4.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/02/17/3bc8a9a6264944d7af6f355c9d2315cd.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/03/02/d31adfde08a14b5599e78abf864de8af.jpeg\" alt=\" 【破损包赔】陕西脆甜红富士苹果冰糖心新鲜水果整箱批发10斤\"></p><p><img src=\"https://files.cnhnb.com/fas/supplydetails/img/6539dbe.png\"></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"2kg\"],\"2\":[\"30天\",\"2个月\"],\"5\":[\"甘肃天水\",\"甘肃庆阳\"]}', '自营水果 产地直采 潘苹果 12个 花牛苹果 国产红蛇果 4.5斤 优质装×1', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (8, '<p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/106584/40/17490/205197/5e880758E5a3e8c7c/3abc84a2a85e1d2b.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/110170/31/11236/185214/5e880758Ea726ee07/85a0ed8b2dc9e466.jpg\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/89099/36/17498/247261/5e88075bE2359eb4e/f4585ec06de218f0.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/99036/1/17602/211311/5e88075dE6dc0e3e8/d9925a0913b2bfcd.jpg\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/109295/23/11257/336832/5e88075dE7b49f737/9f2492c437725e58.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/116806/39/175/233784/5e88075fEcc18198a/8f237516a62549d3.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/95427/21/17732/187677/5e880760E649cab8e/244f268a64493b3c.jpg\"><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/103536/10/17477/286452/5e880761E3288ebad/a07142463d9f15b4.jpg\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/94591/14/17480/281489/5e880761E94b3432f/784f8178c2d0bafd.jpg\"></span></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"2500\"],\"2\":[\"30\"],\"5\":[\"甘肃天水\"]}', '甘肃天水黄元帅苹果 12粒优质装×1', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (9, '<p class=\"ql-align-center\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/28/27c224a9b5c0422992942250d96e6497.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/28/4dcf6953c1934defb2e9c3a5ab2c8def.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/09/18/5fdfbf622b3e49878f958534d9bf9e2b.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/09/18/cefad63b5cad41fe99eef5a9814f44ca.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/05/06/c759dbbfa90743398b034603cf81bf4d.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/28/2b7aa407ca404f17bfa0db7066875f3b.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/04/28/da4e79cac1fb478dadc329d23ba6adbd.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/09/18/e617a9dfa5ff4c57a7d467fad18d2cbc.jpeg\" alt=\"红香蕉 红皮美人蕉现采现发 生蕉发货没有催熟剂更健康净重9斤5斤3斤\"></p><p><img src=\"https://files.cnhnb.com/fas/supplydetails/img/6539dbe.png\"></p>', '{\"3\":\"控温10-15℃\"}', '{\"1\":[\"2.5\"],\"2\":[\"7\",\"14\"],\"5\":[\"中国大陆\"]}', '1X红香蕉三斤/箱', '京东平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！');
INSERT INTO `tb_spu_detail` VALUES (10, '<p><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/28904/2/124/141928/5c074130E3ff939a8/2dd4d0b79c975311.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/16021/26/112/173758/5c074132Ee1da5997/ca49fe698cb7860a.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/25790/14/123/189164/5c074133Ed367124c/8df269ecb6c7183a.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/8528/29/7098/70695/5c074134E8feac571/b216ae4252559cac.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/21953/7/100/69498/5c074135E92a5aa5c/5ae67479e694c4e8.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/21159/5/108/157460/5c074136Ea017527a/7058f74628319c3b.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/23955/10/115/179593/5c074137Eaf6901df/7a579b6bd5113b95.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/8774/7/7752/244531/5c074138E61ab994d/c11a5e2c32965a01.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/26015/14/111/231726/5c074139E0722040a/a93856980b9fc944.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/27888/6/123/256599/5c07413bEfd707cba/a9b27acc93b96fbf.jpg\"></p><p><br></p><p><br></p><p><img src=\"https://img10.360buyimg.com/imgzone/jfs/t17449/250/492378595/413525/e38479c8/5a91650bN4b01e7e1.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/99534/5/4170/263618/5de5df9aE99cc48fb/cc57ceee5e810acd.jpg\"></p><p><img src=\"https://img10.360buyimg.com/imgzone/jfs/t29818/163/366369763/118833/b2595afc/5bf17dbdNa4417d2c.jpg\"></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"2.5\"],\"2\":[\"15\"],\"5\":[\"广西\"]}', '进口青香蕉2kg装*1', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (11, '<p class=\"ql-align-center\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/b0a1a6a37a5e45ad9dfcf147757f316c.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/7cdad7b4943e4f708812919463cad857.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/b163dee5464d457fa93eb88aa0c9d0b8.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/1e67de605b0d40c499f51b6e23687440.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/84cf89e7a2834911b770b58d2ed7da2c.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/15/c7ac5333d1c247d496d3c4426fbdbc49.jpeg\" alt=\" （包邮） 砀山梨2元一斤皇冠梨酥梨10斤带箱水果新鲜现摘\"></p><p><img src=\"https://files.cnhnb.com/fas/supplydetails/img/6539dbe.png\"></p>', '{\"3\":\"控温10-15℃\"}', '{\"1\":[\"2.5\"],\"2\":[\"7\"],\"5\":[\"福建\"]}', '\n2.5kg库尔勒香梨×1', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (12, '<p class=\"ql-align-center\"><a href=\"https://pro.jd.com/mall/active/2v1S1wu5QCKwvrQibhnJaU7JUFGe/index.html\" target=\"_blank\" style=\"color: rgb(102, 102, 102);\"><img src=\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t1/113571/8/5632/61538/5eb51fe9E295bedb6/a047364c92ecc611.jpg\"></a></p><p><br></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t18550/74/821485785/127818/e7a37c9/5aa88d2fN21a3217f.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img30.360buyimg.com/sku/jfs/t1/56228/1/11909/171509/5d8dc915E5a337937/6c1423b6f253d9da.jpg\" height=\"441\" width=\"750\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t14380/356/2606440474/115246/41d8d5a2/5aaa055bN965b8bbf.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t14896/17/2609185506/252734/97fe7220/5aaa055bNce9bbc46.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t18439/194/835760351/207428/c1d47cad/5aaa055bN7bd40ed4.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t15652/365/2482374762/243854/7b1a3f03/5aaa055bN3410a1b2.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t18331/161/857019863/230290/9637c856/5aaa055cN52c1d2f1.jpg\"></p><p class=\"ql-align-center\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t15946/80/2413729038/420786/9cfbb20a/5aa88d38Ncad699f6.jpg\"></p><p><br></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"1.9kg\"],\"2\":[\"20天\"],\"5\":[\"比利时\"]}', '都乐 Dole 比利时啤梨12只装 单果约120g~160g', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (13, '<p><a href=\"https://pro.jd.com/mall/active/2MR9KBv91E65vEqxvh6GqjYpbBth/index.html\" target=\"_blank\" style=\"color: rgb(243, 2, 19);\"><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t1/116425/2/5166/302464/5eb27201Ed599dce8/2427cfae8dcee18b.jpg\"></a></p><p><br></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20941/259/1189566135/382985/ddc0b7c0/5b20feb3N266fc5d8.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t19921/111/1597774073/296004/95acd284/5b20feb2Nc07a1e79.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t22363/107/1141175881/302463/de0dea33/5b20feb2N497e7b3f.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20749/63/1128358897/438248/936ae24e/5b20feb3Neb01a063.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20218/207/1162665377/315132/f98923da/5b20feb2N8b1b88c6.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21106/361/1155596957/183936/60a6474b/5b20feb1Nec6290d4.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21631/190/1176873035/157655/44524db9/5b20feb2Nb68819bf.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20044/166/1589856247/139829/8245bc32/5b20feb4Nf4f9306c.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20185/22/1154986682/183278/87561c52/5b20feb3N26458907.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21406/12/1154597363/224269/bcd8cb38/5b20feb4Ne12b255e.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21865/305/1142966493/296824/edeace8a/5b20feb5N2ca4c300.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21538/265/1192566152/235415/7299ff68/5b20feb4N73f5f8f6.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t21535/259/1130495052/198001/915a2158/5b20feb4N85507be0.jpg\"></p><p><img src=\"http://img30.360buyimg.com/popWaterMark/jfs/t20863/9/1173705753/151219/95c1f3f2/5b20feb4N0a254d5c.jpg\"></p>', '{\"3\":\"常温\"}', '{\"1\":[\"10000\"],\"2\":[\"3年\",\"5年\"],\"5\":[\"中国大陆\",\"加拿大\"]}', '暂无', '京东平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！');
INSERT INTO `tb_spu_detail` VALUES (14, '<p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/55153/14/6805/70710/5d4528d6Ed13496c0/4a8791955f3937a2.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/39855/33/13223/137139/5d4528d6Ef06c4a76/8e734aaa441e3cf8.gif\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/78564/21/6159/100174/5d4528d6E8c58f596/424ef47f3c9ed2c9.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/66017/31/6118/83258/5d4528d7Eb4b087e0/6270a46f163ca1ab.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/54835/38/6716/296103/5d4528d7Effbe0eab/56848968465d1ed7.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/81811/18/6295/115154/5d4528d8E3436604f/4a97eb68515c3c05.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/51678/24/6786/53323/5d4528d9Ed15b12d0/3f8a52137b905395.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/58690/20/6607/104267/5d4528d9E3d5d2903/35224d47eb1dbac6.gif\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/45780/23/6621/93850/5d4528daEc1b3350d/ed3bc3dae6472659.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/44855/20/6537/78019/5d4528daE544b27b2/8edb6537e03071ce.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/76608/39/6155/57973/5d4528dbE9b1674c9/14479a88c6cc0375.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/79974/16/6285/99281/5d4528dbEa6b02d93/ea010210b595cc41.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/80589/21/6238/117046/5d4528dbE0a8aff48/caeb88a3f7587b75.gif\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/48950/5/6617/92726/5d4528dbEa57b58bf/ea4358fe0c71a34c.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/72559/1/6150/106981/5d4528dbEb6f6e62f/1cf8937d3b8329a7.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/57156/9/6725/136827/5d4528dcEf33da896/28961a94c383aaf9.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/78812/26/6086/83870/5d4528dcE818421be/0a8c36cb3c4f5c5f.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/43866/30/10794/87674/5d4528ddE1ba85e8b/f102cb9762ef0fd5.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/75881/10/6190/85411/5d4528ddE8b42a41a/777b0b2a900a11a6.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/55838/3/6668/87546/5d4528deE3ed3c067/ed3be11fb47c1b68.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/48502/1/6710/260158/5d4528deEc95b641a/478f3a58ece3c188.gif\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/64141/12/6197/55199/5d4528deE9abe58bf/be3d9b94530ff414.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/60148/17/6238/72564/5d4528deE20c22f69/7b91c531acabc956.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/81735/24/6368/233059/5d4528e0E482c2a6a/f7afb4568872986a.gif\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/74928/6/6300/102899/5d4528e0Ec7b41c92/0add56b5c98112d7.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/73093/4/6223/99571/5d4528e0E8a7403d5/a801e1b220df2b11.jpg\"></span></p><p><span style=\"color: rgb(102, 102, 102);\"><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/68550/33/6212/122235/5d4528e0Eb3fbcea7/f2b80df268e93fb0.gif\"></span></p><p><img src=\"http://img12.360buyimg.com/imgzone/jfs/t1/39469/24/13221/197806/5d4528e2E9f86f5b8/38431546f3ffcb63.gif\"></p><p>&nbsp;&nbsp;</p><p>结构用途：</p><p><br></p><p><br></p><p>本机器主要是由米机、电机、机架等三大部分组成，米机主要由机盖、中座、机座、米辊、米筛、米刀、进料斗、溜筛、皮带轮、调节装置组成。</p><p><br></p><p>被加工物的含水量与含杂量会直接影响米质及机器功效、负荷，用于其他作用则会导致与机器的预期用途相违背。</p><p>1、操作方法：开机前应仔细检查机器各部位紧固件是否松动，打开机盖检蛮米辊间隙是否正确。一般米刀与米辊筋尖部的间隙是：碾筋始端3毫米左右、碾筋末端2.5毫米左右。</p><p>2、合上开关，待机空运转1－3分钟，如果运转正常，则可进料加工。开进料插板时，应缓慢抽开，使机器逐渐增加负载，否则突然加大，可能使机器堵塞闷车。</p><p><br></p><p>2、中途停机，应先关进料口，让碾米室的谷碾完再行停机。</p><p><br></p><p>3，现在起&nbsp;碾米机&nbsp;烤油漆不固定是一种油漆&nbsp;橙色也有</p><p>&nbsp;</p><p><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/80390/31/6252/129594/5d4528e2E5bcee5b6/6f747b58dab99d90.jpg\"></p><p><br></p>', '{\"3\":\"常温\"}', '{\"1\":[\"3000\"],\"2\":[\"3年\"],\"5\":[\"中国\"]}', '暂无', '平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！');
INSERT INTO `tb_spu_detail` VALUES (15, '<p><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/97957/34/12873/424217/5e4f7ae8E4cae0fd5/234df0520fedd61c.jpg\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/100780/5/12752/312605/5e4f7ae9E48955ff5/ed9868e4b6dd8afa.jpg\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/92235/39/12679/294173/5e4f7ae9Ef03813a6/d2fc3a3bd6878c10.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/109965/18/6636/369450/5e4f7aeaE6b9c4117/95f5ad941da6d8fe.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/98357/27/12661/149466/5e4f7aeaE70e92aa9/04097978834ebbbe.jpg\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/107776/22/6606/135989/5e4f7aeaEe25e9791/5345b8065f7dcb5a.jpg\"></p><p><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/109712/11/6672/70597/5e4f7aeaE4b88c5f3/d9f63583bf75d96a.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/95226/5/12837/156622/5e4f7aebE7d4568ac/ae4516e02e403770.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/103266/13/12812/347588/5e4f7aebE7a29a07f/ab7d412795bf4f4d.jpg\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/86522/31/12738/322352/5e4f7aecEb2cf09e7/889bfcee934d4872.jpg\"><img src=\"http://img20.360buyimg.com/imgzone/jfs/t1/99410/25/12851/198474/5e4f7aedEbf4eb122/0b461ec98fd2a366.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/109598/29/6623/148654/5e4f7aedEe9fc020e/31de676e23830b8f.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/106033/27/12882/151260/5e4f7aedEc9d0ea8c/ea9fbdc52c108f59.jpg\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/99921/39/12842/262919/5e4f7aeeE0601ece3/a32b3017b2429c62.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/89962/40/12864/234648/5e4f7aeeEc1ba9380/d4f404885bbfeb8f.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/92478/30/12846/268367/5e4f7aeeE86f60c29/059a334c0fd90a14.jpg\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/87819/36/12734/165349/5e4f7aeeE88776e10/60a6b95ab8a2ffc2.jpg\"><img src=\"http://img10.360buyimg.com/imgzone/jfs/t1/93444/7/12869/260978/5e4f7aefEedf0c428/2ff2d4e76d28f773.jpg\"><img src=\"http://img14.360buyimg.com/imgzone/jfs/t1/93922/27/12807/321553/5e4f7af0Efe4256dc/83c997641dce3fce.jpg\"><img src=\"http://img13.360buyimg.com/imgzone/jfs/t1/107604/28/6522/264555/5e4f7af1E614981b3/5596fff8840a1a1c.jpg\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/97679/18/12974/210932/5e4f7af1Ef7c7f881/61b55f36ea36b4b3.jpg\"><img src=\"http://img30.360buyimg.com/imgzone/jfs/t1/91715/11/12768/148393/5e4f7af1E9dac7361/685f48a3cfacf376.jpg\"><img src=\"http://img11.360buyimg.com/imgzone/jfs/t1/93434/5/12834/244157/5e4f7af2E5734c96a/a08d99582a6177ba.jpg\"></p>', '{\"3\":\"常温\"}', '{\"1\":[\"30000\",\"50000\"],\"2\":[\"5年\"],\"5\":[\"德国\"]}', '1x1 68000台', ' 非加工定制');
INSERT INTO `tb_spu_detail` VALUES (16, '<p><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/56489/23/11514/200079/5d86cae5E78b89afb/d1a96b19dc16d161.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/76612/13/10940/223599/5d86cae5E6992bd46/2d3e85d285cb47c8.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/66880/2/10923/120466/5d86cae5E2ea12026/2837362c1335ce89.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/59813/35/11002/126849/5d86cae5Eafe444aa/c093a7a7259fd370.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/75267/31/11132/171704/5d86cae5E2e77e385/f8de384228a1d086.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/60973/16/10989/82108/5d86cae6E78f67d84/d05594ef2a9dad7b.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/65909/12/10960/208366/5d86cae6Eac4ff4fd/3210a6e03e4166db.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/75749/15/11189/147219/5d86cae6Ea7bd6f0d/b752ebac842d4bbf.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/44320/38/15723/133540/5d86cae6E292cf7eb/c74b68b8f830bbdb.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/62385/36/11027/90093/5d86cae6E1b982ae6/5c6a43069aa28abe.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/68132/2/10921/172775/5d86cae6Ef97171a1/6493220283bc2d95.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/83189/16/10965/158670/5d86cae6E24083bc0/3fc1a61740cd64eb.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/65254/11/11032/107531/5d86cae7E239347b2/79fe0401d7563fed.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/84209/21/11080/69889/5d86cae7E222b8272/03b3e70a08ad9034.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/77663/2/10851/193616/5d86cae7E31c07a52/eefd889a887cd83a.jpg\"><img src=\"https://img10.360buyimg.com/imgzone/jfs/t1/45111/32/11462/120850/5d86cae7Ebd4f1c68/43d938b8c7f971f7.jpg\"></p><p><br></p>', '{\"3\":\"常温\"}', '{\"1\":[\"5000\"],\"2\":[\"5年\"],\"5\":[\"中国大陆\"]}', '主机*1，电源线*1，榨镗*1，榨杆*1，插销*1，下料斗*1，接油盅*1，过滤网*1，隔热手套*1，清洁刷*1，说明书*1，合格证*1，保修卡*1', '本产品全国联保，享受三包服务，质保期为：7天无理由退换货,质量问题厂家包往返运费；保修1年！');
INSERT INTO `tb_spu_detail` VALUES (17, '<p><span style=\"color: rgb(0, 0, 0);\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/6f0e3b9aada8407abc89baba9f5381a2.jpeg\" alt=\" 嘎啦苹果70mm——80mm10斤装多地区包邮\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/1c17f6dc61a44a26b90768a93c051046.jpeg\" alt=\" 嘎啦苹果70mm——80mm10斤装多地区包邮\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/7b08df291d5f454288c72ed1c00ba238.jpeg\" alt=\" 嘎啦苹果70mm——80mm10斤装多地区包邮\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/123e100048ef400a9c0ec72362d5df2c.jpeg\" alt=\" 嘎啦苹果70mm——80mm10斤装多地区包邮\"></span></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"1.5kg\"],\"2\":[\"30天\"],\"5\":[\"庆阳\"]}', '陕西洛川红富士苹果 12粒 净重2.7kg 果径80mm', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (18, '<p><span style=\"color: rgb(0, 0, 0);\"><img src=\"https://img.cnhnb.com/group1/M00/52/28/B561ABM442ChRkRlhidrmAEfw7AAIZCahyT5g94.JPEG\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://img.cnhnb.com/group1/M00/52/28/B8BDDBM9DDChRkRlhidrmAaWEBAALzXJyGD4892.JPEG\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://img.cnhnb.com/group1/M00/52/28/5C5197HD64ChRkRlhidrmAMx9LAAKy2qsxZZw40.JPEG\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://img.cnhnb.com/group1/M00/52/28/592667H999ChRkRlhidrqAFqdpAAH4DTQCz_w59.JPEG\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://img.cnhnb.com/group1/M00/52/28/992D1BQA0FChRkRlhidrqAWIlyAAIhaAJjfSY40.JPEG\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/13/fa19a2f9f4974da2bc47b9a6f8faa158.jpeg\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/13/d116227d11334cdbb87ff7e2ba8c4976.jpeg\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/13/dfee552b61d44ef7a6edc850cd2ddc9b.jpeg\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/11/13/2cd0c2816e1d4e9f990584f67a6d3ee4.jpeg\" alt=\" 荷兰十五新土豆大量上市，规格齐全，价格美丽！全国接单！\"></span></p>', '{\"3\":\"控温10-15℃\"}', '{\"1\":[\"1kg\"],\"2\":[\"15天\",\"30天\"],\"5\":[\"云南玉溪\",\"甘肃天水\"]}', '荷兰土豆约1000g', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');
INSERT INTO `tb_spu_detail` VALUES (19, '<p><span style=\"color: rgb(0, 0, 0);\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/04/05/c7a7a6b1b9054338a1c96994ef867280.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2019/04/05/968787fb009f44a8b3dec0938b963902.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/51da0f7e1c5a41bfbb27527b0a032a09.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/22d3cce8f49a458daa94fc58fd534623.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/01/28/b8a148a61adc4bed9c4dc2cdcf04762a.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/03/07/a4d3776e074e4689bca45251fa004621.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/03/25/3495dff8a6ab48469d722b29efb614b2.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"><img src=\"https://image.cnhnb.com/image/jpeg/head/2020/03/25/0df1d7e03c2745fd814428596349cb04.jpeg\" alt=\" 库存青萝卜1斤到1.5斤袋装适合走市场和养殖场。\"></span></p>', '{\"3\":\"冷藏 0-4℃\"}', '{\"1\":[\"2.5kg\"],\"2\":[\"20天\",\"30天\"],\"5\":[\"山东潍坊\",\"甘肃天水\"]}', '纸盒×1 、标签× 4', '生鲜自营商品的破损或腐坏等问题，请在商品签收后48小时内提交“优鲜赔”申请，100分钟内审核通过后即享补偿，无需返回商品；非鲜活易腐类商品需上门取件。');

-- ----------------------------
-- Table structure for tb_stock
-- ----------------------------
DROP TABLE IF EXISTS `tb_stock`;
CREATE TABLE `tb_stock`  (
  `sku_id` bigint(20) NOT NULL COMMENT '库存对应的商品sku id',
  `seckill_stock` int(9) NULL DEFAULT 0 COMMENT '可秒杀库存',
  `seckill_total` int(9) NULL DEFAULT 0 COMMENT '秒杀总数量',
  `stock` int(9) NOT NULL COMMENT '库存数量',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表，代表库存，秒杀库存等信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stock
-- ----------------------------
INSERT INTO `tb_stock` VALUES (13, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (14, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (15, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (16, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (17, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (18, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (20, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (21, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (22, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (23, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (24, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (25, 0, 0, 94);
INSERT INTO `tb_stock` VALUES (26, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (27, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (28, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (31, 0, 0, 99);
INSERT INTO `tb_stock` VALUES (32, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (33, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (34, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (35, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (36, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (37, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (38, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (39, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (40, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (41, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (42, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (43, 0, 0, 92);
INSERT INTO `tb_stock` VALUES (44, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (45, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (46, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (47, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (48, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (49, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (50, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (1258992539260784642, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1258996259478409218, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259009326236930049, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259009326253707266, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1259011511750332418, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1259013620407308290, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259015060626444289, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (1259017032133873665, 0, 0, 3000);
INSERT INTO `tb_stock` VALUES (1259018496793219073, 0, 0, 2000);
INSERT INTO `tb_stock` VALUES (1259021173874855937, 0, 0, 10);
INSERT INTO `tb_stock` VALUES (1259021173937770498, 0, 0, 5);
INSERT INTO `tb_stock` VALUES (1259023946678898690, 0, 0, 19);
INSERT INTO `tb_stock` VALUES (1259032878025641986, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1259038679112978434, 0, 0, 200);
INSERT INTO `tb_stock` VALUES (1259038679129755649, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (1259038679138144257, 0, 0, 200);
INSERT INTO `tb_stock` VALUES (1259038679159115778, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259040163242283009, 0, 0, 100);
INSERT INTO `tb_stock` VALUES (1259040163259060225, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1259057168838053889, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259057168850636802, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259057168875802625, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259057168888385537, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259057948508835842, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259057948525613057, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259057948542390274, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259057948563361794, 0, 0, 50);
INSERT INTO `tb_stock` VALUES (1259058650111037442, 0, 0, 20);
INSERT INTO `tb_stock` VALUES (1259058650127814657, 0, 0, 10);
INSERT INTO `tb_stock` VALUES (1259058650148786178, 0, 0, 10);
INSERT INTO `tb_stock` VALUES (1259058650173952002, 0, 0, 30);
INSERT INTO `tb_stock` VALUES (1259060953014620162, 0, 0, 2000);
INSERT INTO `tb_stock` VALUES (1259060953027203073, 0, 0, 2000);
INSERT INTO `tb_stock` VALUES (1259060953048174594, 0, 0, 2000);
INSERT INTO `tb_stock` VALUES (1259060953060757506, 0, 0, 2000);
INSERT INTO `tb_stock` VALUES (1259063218240770050, 0, 0, 300);
INSERT INTO `tb_stock` VALUES (1259063218249158657, 0, 0, 200);
INSERT INTO `tb_stock` VALUES (1259063218265935873, 0, 0, 300);
INSERT INTO `tb_stock` VALUES (1259063218278518785, 0, 0, 200);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码加密的salt值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'wxw', '123456', '1222222222', '2020-04-15 16:27:56', '1233');
INSERT INTO `tb_user` VALUES (4, '魏小伟', 'beb8c73ce02dfbf2ad22f92fa07914e1', '110', '2020-04-18 14:54:42', '7e4e1573d7384d4d87139949b0ff719f');
INSERT INTO `tb_user` VALUES (5, '何珍胜', 'f692680dcf6dc8063601d18c7ad6a974', '10086', '2020-04-18 15:00:00', '45dd4b914da14715b6dd4f3bb483e5a1');
INSERT INTO `tb_user` VALUES (6, '陇上江南', '6f351c37b068508078b023ad8d6c7dd3', '13411111111', '2020-04-18 15:49:33', 'f64a017374a748c5a8986186bf73c48e');
INSERT INTO `tb_user` VALUES (7, '京东', 'd4b7a451175849100fc98c790925ef36', '15488753365', '2020-05-08 00:38:43', '557eba1e359c4fc7985593bbc516be35');
INSERT INTO `tb_user` VALUES (8, '阿里巴巴', 'dbcc85b738a1f1a06569d27c3f204fa9', '15644589985', '2020-05-08 00:41:02', 'fe1b5bfb73504d4f83c6b3e726b21a95');
INSERT INTO `tb_user` VALUES (9, '百度', 'bcbd253b41c5e7f03303e3eb185fcf1b', '15655487754', '2020-05-08 00:43:24', '339f6fc7d1764681bcd1e93f170598a6');
INSERT INTO `tb_user` VALUES (10, '美团', '29e3a091f2ca3ffef36063e9fab39c26', '15655485564', '2020-05-16 14:20:37', 'e37e5dc233b043b4af2ec00c28b0fb8b');

SET FOREIGN_KEY_CHECKS = 1;
