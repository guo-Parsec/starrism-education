/*
 Navicat Premium Data Transfer

 Source Server         : 本地Mysql
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : starrism_education_db

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 23/10/2022 22:12:40
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学院信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学位分类信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '年级信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '专业信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学制信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统字典类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_category
-- ----------------------------
INSERT INTO `sys_dict_category` VALUES (1, 'data_status', '数据状态', 10, 0, '2022-10-19 09:28:01', '2022-10-23 18:30:31');
INSERT INTO `sys_dict_category` VALUES (2, 'sex', '性别字典', 20, 0, '2022-10-23 18:30:26', '2022-10-23 18:32:45');
INSERT INTO `sys_dict_category` VALUES (3, 'user_type', '用户类型', 30, 0, '2022-10-19 09:56:16', '2022-10-23 18:32:48');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统字典详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES (1, 1, 'enable', '0', '启用状态', 10, NULL, 0, '2022-10-23 18:31:48', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (2, 1, 'disable', '1', '禁用状态', 20, NULL, 0, '2022-10-23 18:32:21', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (3, 1, 'delete', '2', '删除状态', 30, NULL, 0, '2022-10-23 18:32:21', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (4, 2, 'unknown', '0', '未知', 10, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (5, 2, 'female', '1', '女性', 20, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');
INSERT INTO `sys_dict_detail` VALUES (6, 2, 'male', '2', '男性', 30, NULL, 0, '2022-10-23 18:34:31', '2022-10-23 18:34:31');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_param
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_category`;
CREATE TABLE `sys_permission_category`  (
  `id` bigint NOT NULL COMMENT '主键',
  `permission_category_id` bigint NOT NULL COMMENT '权限分类id',
  `category_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限编码',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `sort` int NOT NULL DEFAULT 10 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注信息',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_category
-- ----------------------------

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
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '备注信息',
  `data_status` int NOT NULL DEFAULT 0 COMMENT '数据状态',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_detail
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456', '超级管理员', 0, NULL, '2022-10-20 00:00:00', 0, 0, '2022-10-19 09:25:24', '2022-10-20 11:19:36');
INSERT INTO `sys_user` VALUES (2, 'tjnu_admin', '123456', '教务系统管理员', 0, NULL, '1958-09-10 00:00:00', 0, 0, '2022-10-20 11:20:37', '2022-10-20 11:20:37');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
