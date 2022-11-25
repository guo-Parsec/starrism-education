/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : 1.15.57.220:9669
 Source Schema         : starrism_education_db

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 25/11/2022 22:38:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for se_college
-- ----------------------------
DROP TABLE IF EXISTS `se_college`;
CREATE TABLE `se_college`  (
  `id` bigint NOT NULL COMMENT '主键',
  `college_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学院编码',
  `college_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学院名称',
  `college_office_location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院办公室地点',
  `college_contact_information` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院联系方式',
  `college_primary_leader` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院主要领导',
  `data_status` int NOT NULL DEFAULT 0 COMMENT ' 数据状态 ',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 创建时间 ',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学院信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of se_college
-- ----------------------------
INSERT INTO `se_college` VALUES (1, 'C001', '教育学院', '立教楼A区402室', '022-23766041', NULL, 0, '2022-10-19 15:59:08', '2022-10-20 10:47:58');
INSERT INTO `se_college` VALUES (2, 'C002', '心理学院(心理与行为研究院)', '立教楼A区216室', '022-23766140', NULL, 0, '2022-10-19 16:01:09', '2022-10-20 10:47:58');
INSERT INTO `se_college` VALUES (3, 'C003', '文学院', '兴文楼C区111室', '022-23766062', NULL, 0, '2022-10-19 16:01:09', '2022-10-20 10:47:58');
INSERT INTO `se_college` VALUES (4, 'C004', '外国语学院', '立教楼B区116室', '022-23766038', NULL, 0, '2022-10-19 16:51:53', '2022-10-20 10:47:58');
INSERT INTO `se_college` VALUES (5, 'C005', '新闻传播学院', '图书馆C区313室', '022-23766061', NULL, 0, '2022-10-19 16:51:53', '2022-10-20 10:47:58');
INSERT INTO `se_college` VALUES (6, 'C006', '计算机与信息工程学院', '博理楼D区114室', '022-23766295', NULL, 0, '2022-10-19 16:52:35', '2022-10-20 10:47:58');

-- ----------------------------
-- Table structure for se_degree_category
-- ----------------------------
DROP TABLE IF EXISTS `se_degree_category`;
CREATE TABLE `se_degree_category`  (
  `id` bigint NOT NULL COMMENT '主键',
  `degree_category_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学位分类码',
  `degree_category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学位分类名称',
  `data_status` int NOT NULL DEFAULT 0 COMMENT ' 数据状态 ',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 创建时间 ',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学位分类信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of se_degree_category
-- ----------------------------
INSERT INTO `se_degree_category` VALUES (1, 'bachelor-degree', '学士学位', 0, '2022-10-19 16:33:47', '2022-10-19 16:33:47');
INSERT INTO `se_degree_category` VALUES (2, 'master-degree', '硕士学位', 0, '2022-10-19 16:33:47', '2022-10-19 16:38:01');
INSERT INTO `se_degree_category` VALUES (3, 'doctor-degree', '博士学位', 0, '2022-10-19 16:33:47', '2022-10-19 16:38:07');

-- ----------------------------
-- Table structure for se_grade
-- ----------------------------
DROP TABLE IF EXISTS `se_grade`;
CREATE TABLE `se_grade`  (
  `id` bigint NOT NULL COMMENT '主键',
  `degree_category_id` bigint NOT NULL COMMENT '学位分类id',
  `schooling_length_id` bigint NOT NULL COMMENT '学制id',
  `grade_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年级码',
  `grade_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年级名称',
  `graduated_status` tinyint NOT NULL DEFAULT 0 COMMENT '毕业状态[0-未毕业年级1-已毕业年级]',
  `data_status` int NOT NULL DEFAULT 0 COMMENT ' 数据状态 ',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 创建时间 ',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '年级信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of se_grade
-- ----------------------------
INSERT INTO `se_grade` VALUES (1, 1, 1, '2016', '2016级', 1, 0, '2022-10-19 16:05:41', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (2, 1, 1, '2017', '2017级', 1, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (3, 1, 1, '2018', '2018级', 1, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (4, 1, 1, '2019', '2019级', 0, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (5, 1, 1, '2020', '2020级', 0, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (6, 1, 1, '2021', '2021级', 0, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');
INSERT INTO `se_grade` VALUES (7, 1, 1, '2022', '2022级', 0, 0, '2022-10-19 16:07:15', '2022-10-19 16:45:49');

-- ----------------------------
-- Table structure for se_major
-- ----------------------------
DROP TABLE IF EXISTS `se_major`;
CREATE TABLE `se_major`  (
  `id` bigint NOT NULL COMMENT '主键',
  `college_id` bigint NOT NULL COMMENT '学院id',
  `major_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业码',
  `major_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业名称',
  `major_leader` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系主任',
  `data_status` int NOT NULL DEFAULT 0 COMMENT ' 数据状态 ',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 创建时间 ',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '专业信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of se_major
-- ----------------------------
INSERT INTO `se_major` VALUES (1, 6, 'BM00600001', '计算机科学与技术(师范)专业', NULL, 0, '2022-10-19 16:59:47', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (2, 6, 'BM00600002', '物联网工程专业', NULL, 0, '2022-10-19 17:01:06', '2022-10-20 10:47:27');
INSERT INTO `se_major` VALUES (3, 6, 'BM00600003', '网络工程专业', NULL, 0, '2022-10-19 17:01:06', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (4, 6, 'BM00600004', '数据科学与大数据技术专业', NULL, 0, '2022-10-19 17:01:06', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (5, 6, 'BM00600005', '信息工程专业', NULL, 0, '2022-10-19 17:01:58', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (6, 1, 'BM00100001', '教育学专业', NULL, 0, '2022-10-19 17:04:21', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (7, 1, 'BM00100002', '教育技术学专业', NULL, 0, '2022-10-19 17:04:21', '2022-10-20 10:47:26');
INSERT INTO `se_major` VALUES (8, 1, 'BM00100003', '学前教育专业', NULL, 0, '2022-10-19 17:04:21', '2022-10-20 10:47:27');
INSERT INTO `se_major` VALUES (9, 1, 'BM00100004', '小学教育专业', NULL, 0, '2022-10-19 17:04:21', '2022-10-20 10:47:27');

-- ----------------------------
-- Table structure for se_schooling_length
-- ----------------------------
DROP TABLE IF EXISTS `se_schooling_length`;
CREATE TABLE `se_schooling_length`  (
  `id` bigint NOT NULL COMMENT '主键',
  `degree_category_id` bigint NOT NULL COMMENT '学位类型id',
  `schooling_length_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学制码',
  `schooling_length_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学制名称',
  `schooling_length_year` int NOT NULL COMMENT '学制长度',
  `data_status` int NOT NULL DEFAULT 0 COMMENT ' 数据状态 ',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 创建时间 ',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学制信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of se_schooling_length
-- ----------------------------
INSERT INTO `se_schooling_length` VALUES (1, 1, 'full-time-four-year-bachelor', '全日制四年级学士学制', 4, 0, '2022-10-19 16:17:39', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (2, 1, 'full-time-five-year-bachelor', '全日制五年级学士学制', 5, 0, '2022-10-19 16:17:39', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (3, 1, 'full-time-six-year-bachelor', '全日制六年级学士学制', 6, 0, '2022-10-19 16:21:41', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (4, 2, 'full-time-three-year-academic-master', '全日制三年级学硕学制', 3, 0, '2022-10-19 16:27:16', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (5, 2, 'full-time-four-year-academic-master', '全日制四年级学硕学制', 4, 0, '2022-10-19 16:29:59', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (6, 2, 'full-time-five-year-academic-master', '全日制五年级学硕学制', 5, 0, '2022-10-19 16:29:59', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (7, 2, 'full-time-two-year-professional-master', '全日制二年级专硕学制', 2, 0, '2022-10-19 16:29:59', '2022-10-19 16:47:23');
INSERT INTO `se_schooling_length` VALUES (8, 2, 'full-time-three-year-professional-master', '全日制三年级专硕学制', 3, 0, '2022-10-19 16:29:59', '2022-10-19 16:47:23');

-- ----------------------------
-- Table structure for sys_dict_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_category`;
CREATE TABLE `sys_dict_category`  (
  `id` bigint NOT NULL COMMENT '主键',
  `category_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类别码',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类别名称',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统字典类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_category
-- ----------------------------
INSERT INTO `sys_dict_category` VALUES (1, 'data_status', '数据状态', 10, 0, '2022-10-19 09:28:01', '2022-10-23 18:30:31');
INSERT INTO `sys_dict_category` VALUES (2, 'sex', '性别字典', 20, 0, '2022-10-23 18:30:26', '2022-10-23 18:32:45');
INSERT INTO `sys_dict_category` VALUES (3, 'user_type', '用户类型', 30, 0, '2022-10-19 09:56:16', '2022-10-23 18:32:48');
INSERT INTO `sys_dict_category` VALUES (32983166679650304, 'test_type', '测试数据', 99999, 2, '2022-11-12 00:23:20', '2022-11-17 16:17:31');
INSERT INTO `sys_dict_category` VALUES (34583360231837696, 'test_dict_1', '测试数据', 60, 2, '2022-11-16 10:21:56', '2022-11-17 16:17:41');
INSERT INTO `sys_dict_category` VALUES (34969736093765632, 'test111', 'ceshi', 50, 2, '2022-11-17 11:57:15', '2022-11-17 16:17:49');
INSERT INTO `sys_dict_category` VALUES (34970117091758080, 'test222', 'ceshi', 50, 2, '2022-11-17 11:58:46', '2022-11-17 16:17:54');
INSERT INTO `sys_dict_category` VALUES (35406823058182144, 'visible_status', '显示状态', 30, 0, '2022-11-18 16:54:04', '2022-11-18 16:54:04');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail`  (
  `id` bigint NOT NULL COMMENT '主键',
  `sys_dict_category_id` bigint NOT NULL COMMENT '字典类别表主键',
  `dict_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典码',
  `dict_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
  `dict_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级字典id',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统字典详情表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, 'enable', '0', '启用状态', 10, NULL, 0, '2022-10-23 18:31:48', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (2, 1, 'disable', '1', '禁用状态', 20, NULL, 0, '2022-10-23 18:32:21', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (3, 1, 'delete', '2', '删除状态', 30, NULL, 0, '2022-10-23 18:32:21', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (4, 2, 'unknown', '0', '未知', 10, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (5, 2, 'female', '1', '女性', 20, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (6, 2, 'male', '2', '男性', 30, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (35407092705792000, 35406823058182144, 'visible', '0', '可见', 10, NULL, 0, '2022-11-18 16:55:09', '2022-11-18 16:55:09');
INSERT INTO `sys_dict_detail` VALUES (35407175094505472, 35406823058182144, 'invisible', '1', '不可见', 20, NULL, 0, '2022-11-18 16:55:28', '2022-11-18 16:56:08');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL COMMENT '主键',
  `request_path` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求路径',
  `op_explain` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作说明',
  `app` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问应用',
  `time_consuming` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '耗时时间(ms)',
  `request_method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求类型',
  `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求参数',
  `request_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求结果',
  `error_code` int NULL DEFAULT NULL COMMENT '错误码',
  `error_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误信息',
  `success` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1' COMMENT '请求是否成功 1-成功 2-失败',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `op_user_id` bigint NOT NULL COMMENT '操作人id',
  `op_user_account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作用户账户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (38028691154014208, '/auth/login.do', '登录认证', '认证中心服务', '643', 'POST', '[{\"account\":\"admin\",\"password\":\"123456\",\"verificationCode\":\"string\"}]', '{\"code\":200,\"data\":{\"id\":31135640900997120,\"menuIds\":[1,2,3,4,5,6,7,8],\"permissions\":[\"*:*:*\"],\"roles\":[\"full-admin\"],\"subject\":\"admin\",\"tokenId\":\"Bearer cb462cce-113a-40c6-8d85-3ffbc7cbde77\",\"tokenName\":\"Authorization\",\"urls\":[\"/**\"],\"userEntity\":{\"account\":\"admin\",\"authenticated\":true,\"birthday\":\"2022-10-20\",\"dataStatus\":0,\"dataStatusName\":\"启用状态\",\"gmtCreate\":\"2022-10-19T09:25:24\",\"gmtModify\":\"2022-11-06T22:03:14\",\"id\":31135640900997120,\"nickname\":\"超级管理员\",\"sex\":0,\"sexName\":\"未知\",\"userType\":0}},\"message\":\"操作成功\"}', NULL, NULL, '1', '2022-11-25 22:32:26', 31135640900997120, 'admin');
INSERT INTO `sys_log` VALUES (38028870485676032, '/auth/login.do', '登录认证', '认证中心服务', '275', 'POST', '[{\"account\":\"admin\",\"password\":\"123456\",\"verificationCode\":\"string\"}]', '{\"code\":200,\"data\":{\"id\":31135640900997120,\"menuIds\":[1,2,3,4,5,6,7,8],\"permissions\":[\"*:*:*\"],\"roles\":[\"full-admin\"],\"subject\":\"admin\",\"tokenId\":\"Bearer 3a8dc258-1b65-4249-87f4-99a516ca1c72\",\"tokenName\":\"Authorization\",\"urls\":[\"/**\"],\"userEntity\":{\"account\":\"admin\",\"authenticated\":true,\"birthday\":\"2022-10-20\",\"dataStatus\":0,\"dataStatusName\":\"启用状态\",\"gmtCreate\":\"2022-10-19T09:25:24\",\"gmtModify\":\"2022-11-06T22:03:14\",\"id\":31135640900997120,\"nickname\":\"超级管理员\",\"sex\":0,\"sexName\":\"未知\",\"userType\":0}},\"message\":\"操作成功\"}', NULL, NULL, '1', '2022-11-25 22:33:09', 31135640900997120, 'admin');
INSERT INTO `sys_log` VALUES (38029200166359040, '/auth/login.do', '登录认证', '认证中心服务', '538', 'POST', '[{\"account\":\"tjnu_admin\",\"password\":\"123456\",\"verificationCode\":\"string\"}]', '{\"code\":200,\"data\":{\"id\":31135640900997122,\"menuIds\":[],\"permissions\":[],\"roles\":[\"school-level-admin\"],\"subject\":\"tjnu_admin\",\"tokenId\":\"Bearer e348a05d-69dd-48ae-a8ba-1a70a75c83f8\",\"tokenName\":\"Authorization\",\"urls\":[],\"userEntity\":{\"account\":\"tjnu_admin\",\"authenticated\":true,\"birthday\":\"1958-09-10\",\"dataStatus\":0,\"dataStatusName\":\"启用状态\",\"gmtCreate\":\"2022-10-20T11:20:37\",\"gmtModify\":\"2022-11-06T22:03:14\",\"id\":31135640900997122,\"nickname\":\"教务系统管理员\",\"sex\":1,\"sexName\":\"女性\",\"userType\":0}},\"message\":\"操作成功\"}', NULL, NULL, '1', '2022-11-25 22:34:27', 31135640900997122, 'tjnu_admin');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL COMMENT '主键',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级菜单id',
  `hierarchical_path` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '/' COMMENT '层级路径',
  `menu_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单标识',
  `menu_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单标题',
  `menu_icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '菜单图标',
  `visible_status` smallint NOT NULL DEFAULT 0 COMMENT '显示状态(数据字典 0-显示 1-隐藏)',
  `menu_level` int NOT NULL DEFAULT 0 COMMENT '菜单级别',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `component` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '前端组件',
  `menu_url` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '菜单访问url',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '菜单描述',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '/', 'root', '根目录', '', 1, 0, 10, '', '/starrism/edu', '根目录', 0, '2022-11-18 15:52:39', '2022-11-18 15:52:39');
INSERT INTO `sys_menu` VALUES (2, 1, '/0', 'workbench', '工作台', '', 0, 1, 20, '', '/workbench', '', 0, '2022-11-18 15:56:24', '2022-11-18 15:56:24');
INSERT INTO `sys_menu` VALUES (3, 1, '/0', 'admin', '系统管理', '', 0, 1, 30, '', '/admin', '', 0, '2022-11-18 15:56:24', '2022-11-18 15:56:24');
INSERT INTO `sys_menu` VALUES (4, 3, '/0/3', 'user-management ', '用户管理', '', 0, 2, 40, '', '/user', '', 0, '2022-11-18 15:56:24', '2022-11-18 15:56:24');
INSERT INTO `sys_menu` VALUES (5, 3, '/0/3', 'role-management ', '角色管理', '', 0, 2, 50, '', '/role', '', 0, '2022-11-18 15:58:39', '2022-11-18 15:58:39');
INSERT INTO `sys_menu` VALUES (6, 3, '/0/3', 'permission-management ', '权限管理', '', 0, 2, 60, '', '/permission', '', 0, '2022-11-18 15:58:39', '2022-11-18 15:58:39');
INSERT INTO `sys_menu` VALUES (7, 3, '/0/3', 'dict-management', '字典管理', '', 0, 2, 70, '', '', '', 0, '2022-11-18 15:58:40', '2022-11-18 15:58:40');
INSERT INTO `sys_menu` VALUES (8, 3, '/0/3', 'log-management', '日志管理', '', 0, 2, 80, '', '', '', 0, '2022-11-18 15:58:40', '2022-11-18 15:58:40');

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` bigint NOT NULL COMMENT '主键',
  `group_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组别码',
  `group_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '组别名称',
  `param_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数码',
  `param_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参数值',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统参数表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES (1, NULL, NULL, 'DEFAULT_PASSWORD_STRATEGY', 'sePasswordMd5', 0, '2022-11-07 19:26:37', '2022-11-07 19:26:37');
INSERT INTO `sys_param` VALUES (2, 'PAGE_PARAM_GROUP', '分页参数分组', 'DEFAULT_PAGE_SIZE', '10', 0, '2022-11-11 13:48:45', '2022-11-11 13:48:45');
INSERT INTO `sys_param` VALUES (3, 'PAGE_PARAM_GROUP', '分页参数分组', 'DEFAULT_CURR_PAGE', '1', 0, '2022-11-11 13:48:45', '2022-11-11 13:48:45');

-- ----------------------------
-- Table structure for sys_permission_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_category`;
CREATE TABLE `sys_permission_category`  (
  `id` bigint NOT NULL COMMENT '主键',
  `category_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限编码',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注信息',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission_category
-- ----------------------------
INSERT INTO `sys_permission_category` VALUES (1, 'anonymous', '匿名认证', 10, '无需认证直接登录', 0, '2022-11-04 15:40:02', '2022-11-10 14:37:24');
INSERT INTO `sys_permission_category` VALUES (2, 'authenticated_common', '登录认证', 20, '登录认证后即放行', 0, '2022-11-04 15:41:08', '2022-11-10 14:35:41');
INSERT INTO `sys_permission_category` VALUES (3, 'authenticated_permission', '权限认证', 30, '登录后拥有相关权限后可放行', 0, '2022-11-04 15:42:01', '2022-11-10 14:35:41');

-- ----------------------------
-- Table structure for sys_permission_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_detail`;
CREATE TABLE `sys_permission_detail`  (
  `id` bigint NOT NULL COMMENT '主键',
  `permission_category_id` bigint NOT NULL COMMENT '权限分类id',
  `permission_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限编码',
  `permission_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `request_action_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求操作url',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注信息',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission_detail
-- ----------------------------
INSERT INTO `sys_permission_detail` VALUES (1, 3, '*:*:*', '全权限', '/**', 10, NULL, 0, '2022-11-10 16:41:02', '2022-11-18 09:10:54');
INSERT INTO `sys_permission_detail` VALUES (2, 1, 'logout', '登出', '/auth/logout.do', 20, NULL, 0, '2022-11-10 16:42:03', '2022-11-18 09:10:54');
INSERT INTO `sys_permission_detail` VALUES (3, 1, 'login', '登录', '/auth/login.do', 30, NULL, 0, '2022-11-06 21:52:46', '2022-11-18 09:10:54');
INSERT INTO `sys_permission_detail` VALUES (35319636375506944, 3, 'admin:self-role:query', '获取角色码：根据用户id获取', '/admin/sys/role/find/code/by/roles', 40, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636392284160, 3, 'admin:dict-detail:query-page', '字典详情数据分页查询', '/admin/sys/dict/detail/page', 50, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636392284161, 3, 'admin:self-permission:query', '字典类别数据更新', '/admin/sys/permission/find/url/by/roles', 60, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636392284162, 3, 'admin:dict-category:write', '字典类别数据新增', '/admin/sys/dict/category/create.do', 70, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636392284163, 3, 'admin:dict-detail:query-list', '字典详情数据非分页查询', '/admin/sys/dict/detail/list', 80, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636396478464, 3, 'admin:dict-detail:write', '字典详情数据更新', '/admin/sys/dict/detail/put.do', 90, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636396478465, 3, 'admin:self-user:query', '根据账户查询用户', '/admin/sys/user/find/by/account', 100, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636396478466, 3, 'admin:self-permission:query', '字典类别数据更新', '/admin/sys/permission/find/url/by/userId', 110, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636400672768, 3, 'admin:dict-detail:write', '字典详情数据新增', '/admin/sys/dict/detail/create.do', 120, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636400672769, 3, 'admin:dict-category:write', '字典类别数据删除', '/admin/sys/dict/category/delete.do', 130, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636400672770, 3, 'admin:dict-detail:write', '字典详情数据删除', '/admin/sys/dict/detail/delete.do', 140, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636404867072, 3, 'admin:dict-category:write', '字典类别数据更新', '/admin/sys/dict/category/put.do', 150, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636404867073, 3, 'admin:role:query-page', '角色分页查询', '/admin/sys/role/query', 160, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35319636404867074, 3, 'admin:self-permission:query', '查询角色权限：根据用户id查询权限码', '/admin/sys/permission/find/code/by/userId', 170, '', 0, '2022-11-18 11:07:38', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35357115686195200, 3, 'admin:online-user:query', '查询全部在线用户', '/auth/find/all/online/user', 180, '', 0, '2022-11-18 13:36:33', '2022-11-18 13:36:33');
INSERT INTO `sys_permission_detail` VALUES (35408264875347968, 3, 'admin:self-menu:query', '查询用户的菜单树形结构列表', '/admin/sys/menu/find/menu-tree/of/user-id', 190, '', 0, '2022-11-18 16:59:48', '2022-11-18 16:59:48');
INSERT INTO `sys_permission_detail` VALUES (35408264883736576, 3, 'admin:self-menu:query', '查询用户的菜单id列表', '/admin/sys/menu/find/menu-ids/of/user-id', 200, '', 0, '2022-11-18 16:59:48', '2022-11-18 16:59:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT '主键',
  `role_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注信息',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'full-admin', '超级管理员', 10, NULL, 0, '2022-10-20 10:57:51', '2022-10-20 10:58:05');
INSERT INTO `sys_role` VALUES (2, 'school-level-admin', '校级管理员', 20, NULL, 0, '2022-10-20 10:57:51', '2022-10-20 10:58:17');
INSERT INTO `sys_role` VALUES (3, 'school-eduction-office', '校级教务', 30, NULL, 0, '2022-10-20 11:05:07', '2022-10-20 11:06:56');
INSERT INTO `sys_role` VALUES (4, 'school-financial', '校级财务', 40, NULL, 0, '2022-10-20 11:06:31', '2022-10-20 11:07:00');
INSERT INTO `sys_role` VALUES (5, 'school-student-affairs', '校级学务', 50, NULL, 0, '2022-10-20 11:09:22', '2022-10-20 11:09:22');
INSERT INTO `sys_role` VALUES (6, 'college-level-admin', '院级管理员', 60, NULL, 0, '2022-10-20 10:57:51', '2022-10-20 11:09:34');
INSERT INTO `sys_role` VALUES (7, 'college-eduction-office', '院级教务', 70, NULL, 0, '2022-10-20 11:05:34', '2022-10-20 11:09:34');
INSERT INTO `sys_role` VALUES (8, 'college-financial', '院级财务', 80, NULL, 0, '2022-10-20 11:10:28', '2022-10-20 11:10:28');
INSERT INTO `sys_role` VALUES (9, 'college-student-affairs', '院级学务', 90, NULL, 0, '2022-10-20 11:10:28', '2022-10-20 11:10:28');
INSERT INTO `sys_role` VALUES (10, 'major-level-admin', '系级管理员', 100, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (11, 'major-eduction-office', '系级教务', 110, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (12, 'major-student-affairs', '系级学务', 120, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (13, 'counselor', '辅导员', 130, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (14, 'class-head-teacher', '班主任', 140, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (15, 'class-monitor', '班长', 150, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (16, 'class-committee', '班委', 160, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (17, 'teacher', '任课教师', 170, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:28');
INSERT INTO `sys_role` VALUES (18, 'student', '学生', 180, NULL, 0, '2022-10-20 11:15:28', '2022-10-20 11:15:43');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '权限id',
  INDEX `fk_sys_role_menu_role_id_re_sys_role_id`(`role_id` ASC) USING BTREE,
  INDEX `fk_sys_role_menu_menu_id_re_sys_menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `fk_sys_role_menu_menu_id_re_sys_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sys_role_menu_role_id_re_sys_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `permission_id` bigint NOT NULL COMMENT '权限id',
  INDEX `fk_sys_role_permission_role_id_re_sys_role_id`(`role_id` ASC) USING BTREE,
  INDEX `fk_sys_role_permission_permission_id_re_sys_permission_id`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `fk_sys_role_permission_permission_id_re_sys_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sys_role_permission_role_id_re_sys_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT '用户主键',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户信息',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `sex` int NOT NULL DEFAULT 0 COMMENT '性别',
  `avatar_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像地址',
  `birthday` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出生日期',
  `user_type` int NOT NULL DEFAULT 0 COMMENT '用户类型',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE COMMENT '账户唯一索引',
  UNIQUE INDEX `index_unique_sys_user_account_id_asce`(`id` ASC) USING BTREE COMMENT '账户唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (31135640900997120, 'admin', '37029f3c646ca4d471884ac903754946', '超级管理员', 0, NULL, '2022-10-20 00:00:00', 0, 0, '2022-10-19 09:25:24', '2022-11-06 22:03:14');
INSERT INTO `sys_user` VALUES (31135640900997122, 'tjnu_admin', 'e53fd9d14810434b93a248dbfe4bde7d', '教务系统管理员', 1, NULL, '1958-09-10 00:00:00', 0, 0, '2022-10-20 11:20:37', '2022-11-06 22:03:14');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  INDEX `fk_sys_user_role_role_id_re_sys_role_id`(`role_id` ASC) USING BTREE,
  INDEX `fk_sys_user_role_user_id_re_sys_user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_sys_user_role_role_id_re_sys_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_sys_user_role_user_id_re_sys_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (31135640900997120, 1);
INSERT INTO `sys_user_role` VALUES (31135640900997122, 2);

SET FOREIGN_KEY_CHECKS = 1;
