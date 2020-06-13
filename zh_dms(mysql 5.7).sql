
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classion
-- ----------------------------
DROP TABLE IF EXISTS `classion`;
CREATE TABLE `classion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classion` varchar(255) NOT NULL COMMENT '人员类型',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_name` varchar(255) NOT NULL COMMENT '操作人员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classion
-- ----------------------------
INSERT INTO `classion` VALUES ('2', '新员工', '2019-07-02 09:59:00', 'admin');
INSERT INTO `classion` VALUES ('4', '交流干部', '2019-07-02 09:59:54', 'admin');
INSERT INTO `classion` VALUES ('5', '借调人员', '2019-07-02 10:00:05', 'admin');
INSERT INTO `classion` VALUES ('6', '总经理', '2019-07-02 13:52:32', 'admin');

-- ----------------------------
-- Table structure for cost
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `c_name` varchar(50) NOT NULL COMMENT '收费项',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_name` varchar(20) DEFAULT NULL COMMENT '添加人员',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES ('1', '房租', '2019-07-03 13:07:05', 'admin');
INSERT INTO `cost` VALUES ('2', '物业管理费', '2019-07-03 13:07:13', 'admin');
INSERT INTO `cost` VALUES ('4', 'test', '2019-08-02 14:23:35', 'admin');
INSERT INTO `cost` VALUES ('5', '乱收费', '2019-11-03 10:48:40', 'admin');

-- ----------------------------
-- Table structure for cprelationship
-- ----------------------------
DROP TABLE IF EXISTS `cprelationship`;
CREATE TABLE `cprelationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NOT NULL,
  `r_id` int(11) NOT NULL,
  `p_id` int(11) DEFAULT NULL,
  `value` double(255,2) NOT NULL,
  `status` int(1) NOT NULL,
  `bg_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `p_id` (`p_id`) USING BTREE,
  KEY `c_id` (`c_id`) USING BTREE,
  CONSTRAINT `cprelationship_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `personnel` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cprelationship_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `cost` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cprelationship
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dpm_id` int(11) NOT NULL AUTO_INCREMENT,
  `dpm_name` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dpm_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '会计部', '2019-08-14 13:01:37', '自动添加');
INSERT INTO `department` VALUES ('2', '总务部', '2019-08-14 14:00:02', '自动添加');
INSERT INTO `department` VALUES ('3', '1SDSD', '2019-08-14 14:00:02', '自动添加');
INSERT INTO `department` VALUES ('4', 'koumu', '2019-07-03 17:57:29', 'admin');
INSERT INTO `department` VALUES ('5', 'iuonh', '2019-07-03 17:57:46', 'admin');
INSERT INTO `department` VALUES ('6', 'new1', '2019-07-04 16:41:43', 'admin');
INSERT INTO `department` VALUES ('7', 'new2', '2019-07-04 16:42:02', 'admin');
INSERT INTO `department` VALUES ('8', '技术研发部', '2019-08-27 20:22:00', '自动添加');
INSERT INTO `department` VALUES ('9', 'qw', '2020-01-13 11:35:04', '自动添加');
INSERT INTO `department` VALUES ('10', '21212', '2020-01-13 18:10:32', '自动添加');

-- ----------------------------
-- Table structure for dgrelationship
-- ----------------------------
DROP TABLE IF EXISTS `dgrelationship`;
CREATE TABLE `dgrelationship` (
  `gd_id` int(11) NOT NULL AUTO_INCREMENT,
  `d_id` int(11) NOT NULL COMMENT '房屋ID',
  `g_id` int(11) NOT NULL COMMENT '物品ID',
  `gd_number` int(11) NOT NULL COMMENT '物品数量',
  `r_id` int(11) DEFAULT NULL COMMENT '房间ID(0表示为共用)',
  PRIMARY KEY (`gd_id`) USING BTREE,
  KEY `d_id` (`d_id`) USING BTREE,
  KEY `g_id` (`g_id`) USING BTREE,
  CONSTRAINT `dgrelationship_ibfk_1` FOREIGN KEY (`d_id`) REFERENCES `dorm` (`d_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dgrelationship_ibfk_2` FOREIGN KEY (`g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dgrelationship
-- ----------------------------

-- ----------------------------
-- Table structure for dorm
-- ----------------------------
DROP TABLE IF EXISTS `dorm`;
CREATE TABLE `dorm` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿舍ID',
  `u_id` int(11) DEFAULT NULL COMMENT '使用权管理',
  `t_id` int(11) DEFAULT NULL COMMENT '使用单位ID',
  `parent_id` int(11) NOT NULL COMMENT '小区地址ID',
  `d_address` varchar(255) NOT NULL COMMENT '宿舍地址（房号）',
  `d_area` float(4,0) unsigned DEFAULT NULL COMMENT '面积',
  `d_specification` int(1) unsigned zerofill DEFAULT NULL COMMENT '规格（1、 2房一厅 2、3房一厅 0、小区）',
  `d_type` int(1) unsigned zerofill DEFAULT NULL COMMENT '类型（1、合并 2、拆分 0、小区）',
  `d_num` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '现住人数',
  `d_allnum` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '可住人数',
  `order_num` int(11) DEFAULT NULL COMMENT '序号',
  `d_superior_address` varchar(50) DEFAULT NULL COMMENT '小区地址',
  PRIMARY KEY (`d_id`) USING BTREE,
  KEY `d_type` (`d_type`) USING BTREE,
  KEY `使用权` (`u_id`) USING BTREE,
  KEY `使用单位` (`t_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dorm
-- ----------------------------
INSERT INTO `dorm` VALUES ('226', null, null, '0', '广东财经大学', null, null, null, '0', '0', '1', null);
INSERT INTO `dorm` VALUES ('227', '7', '5', '226', '广东财经大学21701', '100', '3', '2', '0', '4', '1', '广东财经大学');
INSERT INTO `dorm` VALUES ('228', '7', '6', '226', '广东财经大学21702', '100', '3', '2', '0', '4', '2', '广东财经大学');
INSERT INTO `dorm` VALUES ('229', '7', '5', '226', '广东财经大学21703', '100', '3', '2', '0', '4', '3', '广东财经大学');

-- ----------------------------
-- Table structure for dorm_room
-- ----------------------------
DROP TABLE IF EXISTS `dorm_room`;
CREATE TABLE `dorm_room` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间ID',
  `parent_id` int(11) NOT NULL COMMENT '房屋ID',
  `room_name` varchar(255) NOT NULL COMMENT '房间名字',
  `p_id` int(11) DEFAULT NULL COMMENT '人员ID',
  `room_aera` float(255,2) DEFAULT NULL COMMENT '房间面积',
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE,
  CONSTRAINT `dorm_room_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `dorm` (`d_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dorm_room
-- ----------------------------
INSERT INTO `dorm_room` VALUES ('283', '227', '全套', null, '100.00', '0');
INSERT INTO `dorm_room` VALUES ('284', '227', 'A房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('285', '227', 'B房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('286', '227', 'C房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('287', '227', 'D房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('288', '228', '全套', null, '100.00', '0');
INSERT INTO `dorm_room` VALUES ('289', '228', 'A房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('290', '228', 'B房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('291', '228', 'C房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('292', '228', 'D房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('293', '229', '全套', null, '100.00', '0');
INSERT INTO `dorm_room` VALUES ('294', '229', 'A房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('295', '229', 'B房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('296', '229', 'C房间', null, '25.00', '1');
INSERT INTO `dorm_room` VALUES ('297', '229', 'D房间', null, '25.00', '1');

-- ----------------------------
-- Table structure for dprelationship
-- ----------------------------
DROP TABLE IF EXISTS `dprelationship`;
CREATE TABLE `dprelationship` (
  `pd_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入住关系ID',
  `p_id` int(11) NOT NULL COMMENT '人员ID',
  `d_id` int(11) NOT NULL COMMENT '房屋ID',
  `r_id` int(11) NOT NULL COMMENT '房间ID',
  `check_in_time` date NOT NULL COMMENT '登记入住时间',
  `expected_due_time` date NOT NULL COMMENT '预期到期时间',
  `leave_time` date DEFAULT NULL COMMENT '退租时间',
  `deposit` int(11) NOT NULL COMMENT '是否已交押金',
  `deposit_money` float DEFAULT NULL COMMENT '金额',
  `status` int(1) NOT NULL COMMENT '入住状态（1、在住 0、退租）',
  PRIMARY KEY (`pd_id`) USING BTREE,
  KEY `p_id` (`p_id`) USING BTREE,
  KEY `d_id` (`d_id`) USING BTREE,
  KEY `r_id` (`r_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dprelationship
-- ----------------------------
INSERT INTO `dprelationship` VALUES ('70', '71', '227', '284', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('71', '72', '227', '285', '2019-12-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('72', '73', '227', '286', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('73', '74', '227', '287', '2020-01-01', '2020-02-08', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('74', '75', '228', '289', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('75', '76', '228', '290', '2019-12-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('76', '77', '228', '291', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('77', '78', '228', '292', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');
INSERT INTO `dprelationship` VALUES ('78', '79', '229', '294', '2020-01-01', '2020-01-31', '2020-01-14', '0', '0', '0');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物品ID',
  `g_name` varchar(255) NOT NULL COMMENT '物品名称',
  `g_specification` varchar(255) NOT NULL COMMENT '物品规格',
  `g_type` varchar(255) NOT NULL COMMENT '物品类型',
  `g_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('2', '电视机', '32', '电器', null);
INSERT INTO `goods` VALUES ('3', '冰箱', '300升', '电器', '0');
INSERT INTO `goods` VALUES ('4', '床', '1.2米', '固定资产', null);
INSERT INTO `goods` VALUES ('5', '空调', '2匹', '固定资产', null);
INSERT INTO `goods` VALUES ('6', '书桌', '1.5米', '活动家具', null);
INSERT INTO `goods` VALUES ('7', '椅子', '张', '活动家具', null);
INSERT INTO `goods` VALUES ('8', '铁衣柜', '0.8米*2*0.4', '铁家具', null);
INSERT INTO `goods` VALUES ('9', '铁储物柜', '0.8*2*0.4', '铁家具', null);

-- ----------------------------
-- Table structure for month
-- ----------------------------
DROP TABLE IF EXISTS `month`;
CREATE TABLE `month` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `y_name` int(11) NOT NULL,
  `m_name` int(2) NOT NULL,
  `m_bgtime` varchar(50) CHARACTER SET utf8 NOT NULL,
  `m_endtime` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of month
-- ----------------------------
INSERT INTO `month` VALUES ('1', '2019', '1', '2019-01-01 00:00:00', '2019-01-31 00:00:00');
INSERT INTO `month` VALUES ('2', '2019', '2', '2019-02-01 00:00:00', '2019-02-28 00:00:00');
INSERT INTO `month` VALUES ('3', '2019', '3', '2019-03-01 00:00:00', '2019-03-31 00:00:00');
INSERT INTO `month` VALUES ('4', '2019', '4', '2019-04-01 00:00:00', '2019-04-30 00:00:00');
INSERT INTO `month` VALUES ('5', '2019', '5', '2019-05-01 00:00:00', '2019-05-31 00:00:00');
INSERT INTO `month` VALUES ('6', '2019', '6', '2019-06-01 00:00:00', '2019-06-30 00:00:00');
INSERT INTO `month` VALUES ('7', '2019', '7', '2019-07-01 00:00:00', '2019-07-31 00:00:00');
INSERT INTO `month` VALUES ('8', '2019', '8', '2019-08-01 00:00:00', '2019-08-31 00:00:00');
INSERT INTO `month` VALUES ('9', '2019', '9', '2019-09-01 00:00:00', '2019-09-30 00:00:00');
INSERT INTO `month` VALUES ('10', '2019', '10', '2019-10-01 00:00:00', '2019-10-31 00:00:00');
INSERT INTO `month` VALUES ('11', '2019', '11', '2019-11-01 00:00:00', '2019-11-30 00:00:00');
INSERT INTO `month` VALUES ('12', '2019', '12', '2019-12-01 00:00:00', '2019-12-31 00:00:00');
INSERT INTO `month` VALUES ('28', '2020', '1', '2020-1-01 00:00:00', '2020-1-28 00:00:00');
INSERT INTO `month` VALUES ('29', '2020', '2', '2020-2-01 00:00:00', '2020-2-31 00:00:00');
INSERT INTO `month` VALUES ('30', '2020', '3', '2020-3-01 00:00:00', '2020-3-30 00:00:00');
INSERT INTO `month` VALUES ('31', '2020', '4', '2020-4-01 00:00:00', '2020-4-31 00:00:00');
INSERT INTO `month` VALUES ('32', '2020', '5', '2020-5-01 00:00:00', '2020-5-30 00:00:00');
INSERT INTO `month` VALUES ('33', '2020', '6', '2020-6-01 00:00:00', '2020-6-31 00:00:00');
INSERT INTO `month` VALUES ('34', '2020', '7', '2020-7-01 00:00:00', '2020-7-31 00:00:00');
INSERT INTO `month` VALUES ('35', '2020', '8', '2020-8-01 00:00:00', '2020-8-30 00:00:00');
INSERT INTO `month` VALUES ('36', '2020', '9', '2020-9-01 00:00:00', '2020-9-31 00:00:00');
INSERT INTO `month` VALUES ('37', '2020', '10', '2020-10-01 00:00:00', '2020-10-30 00:00:00');
INSERT INTO `month` VALUES ('38', '2020', '11', '2020-11-01 00:00:00', '2020-11-31 00:00:00');
INSERT INTO `month` VALUES ('39', '2020', '12', '2020-12-01 00:00:00', '2020-12-31 00:00:00');

-- ----------------------------
-- Table structure for personnel
-- ----------------------------
DROP TABLE IF EXISTS `personnel`;
CREATE TABLE `personnel` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人员ID',
  `p_name` varchar(255) NOT NULL COMMENT '姓名',
  `p_department` varchar(255) NOT NULL COMMENT '部室',
  `p_sex` int(11) NOT NULL COMMENT '性别（1、男 2、女）',
  `initiation_time` date NOT NULL COMMENT '入行时间',
  `p_phone` varchar(255) NOT NULL COMMENT '联系电话',
  `p_categroy` int(11) NOT NULL COMMENT '人员类型（1、交流干部 2、借调人员 3、新员工）',
  `p_number` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of personnel
-- ----------------------------
INSERT INTO `personnel` VALUES ('71', '1', '总务部', '1', '2020-01-01', '1', '2', '1');
INSERT INTO `personnel` VALUES ('72', '2', '会计部', '0', '2020-01-01', '2', '2', '2');
INSERT INTO `personnel` VALUES ('73', '3', '总务部', '0', '2020-01-01', '3', '2', '3');
INSERT INTO `personnel` VALUES ('74', '4', '1SDSD', '1', '2020-01-01', '4', '2', '4');
INSERT INTO `personnel` VALUES ('75', '5', 'iuonh', '1', '2020-01-01', '5', '2', '5');
INSERT INTO `personnel` VALUES ('76', '6', 'new1', '0', '2020-01-01', '6', '2', '6');
INSERT INTO `personnel` VALUES ('77', '7', 'new1', '0', '2020-01-01', '7', '4', '7');
INSERT INTO `personnel` VALUES ('78', '8', '会计部', '0', '2020-01-01', '121', '2', '11');
INSERT INTO `personnel` VALUES ('79', '9', '总务部', '1', '2020-01-01', '9', '2', '9');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'com.framework.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720026636F6D2E6672616D65776F726B2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000672656E72656E74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', null, 'com.framework.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720026636F6D2E6672616D65776F726B2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', '2012-20130413KB1484810971286', '1484814326779', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1484814600000', '1484813537283', '5', 'WAITING', 'CRON', '1484730532000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720026636F6D2E6672616D65776F726B2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740004746573747400096672616D65776F726B74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1484814600000', '1484813751557', '5', 'PAUSED', 'CRON', '1484730533000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720026636F6D2E6672616D65776F726B2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(255) NOT NULL,
  `dorm` varchar(255) NOT NULL,
  `p_categroy` varchar(255) DEFAULT NULL,
  `p_department` varchar(255) DEFAULT NULL,
  `cost_items` varchar(255) NOT NULL,
  `record_month` varchar(255) NOT NULL,
  `record_year` varchar(255) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2317 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('2306', '71', '1', '广东财经大学21701', '新员工', '总务部', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2307', '72', '2', '广东财经大学21701', '新员工', '会计部', '[]', '12', '2019', '0');
INSERT INTO `record` VALUES ('2308', '72', '2', '广东财经大学21701', '新员工', '会计部', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2309', '73', '3', '广东财经大学21701', '新员工', '总务部', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2310', '74', '4', '广东财经大学21701', '新员工', '1SDSD', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2311', '75', '5', '广东财经大学21702', '新员工', 'iuonh', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2312', '76', '6', '广东财经大学21702', '新员工', 'new1', '[]', '12', '2019', '0');
INSERT INTO `record` VALUES ('2313', '76', '6', '广东财经大学21702', '新员工', 'new1', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2314', '77', '7', '广东财经大学21702', '交流干部', 'new1', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2315', '78', '8', '广东财经大学21702', '新员工', '会计部', '[]', '1', '2020', '0');
INSERT INTO `record` VALUES ('2316', '79', '9', '广东财经大学21703', '新员工', '总务部', '[]', '1', '2020', '0');

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'framework', '0 0/30 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'test', 'renren', '0', null, '1038', '2017-01-18 23:00:52');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'test', 'renren', '0', null, '1056', '2017-01-19 15:16:51');
INSERT INTO `schedule_job_log` VALUES ('3', '1', 'testTask', 'test', 'renren', '0', null, '1075', '2017-01-19 15:30:41');
INSERT INTO `schedule_job_log` VALUES ('4', '1', 'testTask', 'test', 'renren', '0', null, '1017', '2017-01-19 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('5', '1', 'testTask', 'test', 'framework', '0', null, '1003', '2017-01-19 16:12:20');
INSERT INTO `schedule_job_log` VALUES ('6', '2', 'testTask', 'test2', null, '0', null, '7', '2017-01-19 16:15:52');
INSERT INTO `schedule_job_log` VALUES ('7', '2', 'testTask', 'test2', null, '0', null, '25', '2017-01-19 16:16:18');

-- ----------------------------
-- Table structure for show_data
-- ----------------------------
DROP TABLE IF EXISTS `show_data`;
CREATE TABLE `show_data` (
  `id` int(11) NOT NULL,
  `entry_num` int(11) NOT NULL,
  `retreate_num` int(11) NOT NULL,
  `warning_num` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of show_data
-- ----------------------------
INSERT INTO `show_data` VALUES ('1', '78', '42', '0');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'R & D Department', '技术部', '1', '研发部和开发部统称为技术部');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule.html', null, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('28', '1', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', '1', 'fa fa-rocket', '7');
INSERT INTO `sys_menu` VALUES ('29', '0', '房屋管理', 'wait', null, '0', 'fa fa-home', '1');
INSERT INTO `sys_menu` VALUES ('30', '0', '入住管理', 'wait', null, '0', 'fa fa-bed', '3');
INSERT INTO `sys_menu` VALUES ('31', '0', '物品管理', 'wait', null, '0', 'fa fa-suitcase', '1');
INSERT INTO `sys_menu` VALUES ('32', '0', '收费管理', 'wait', null, '0', 'fa fa-jpy', '2');
INSERT INTO `sys_menu` VALUES ('34', '0', '数据报表', 'wait', null, '0', 'fa fa-table', '5');
INSERT INTO `sys_menu` VALUES ('35', '32', '费用类型', 'sys/cost.html', 'cost:list,cost:info,cost:save,cost:update,cost:delete', '1', null, '1');
INSERT INTO `sys_menu` VALUES ('36', '56', '人员信息', 'sys/personnel.html', 'personnel:list,personnel:info,personnel:save,personnel:update,personnel:delete', '1', null, '1');
INSERT INTO `sys_menu` VALUES ('37', '29', '基本信息', 'sys/dorm_add.html', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('38', '29', '使用状况', 'sys/dorm.html', null, '1', null, '3');
INSERT INTO `sys_menu` VALUES ('39', '31', '基本信息', 'sys/goods.html', 'goods:list,goods:info,goods:save,goods:update,goods:delete', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('40', '30', '入住登记', 'sys/dprelationship.html', 'dprelationship:list,dprelationship:info,dprelationship:save,dprelationship:update,dprelationship:delete', '1', null, '3');
INSERT INTO `sys_menu` VALUES ('41', '30', '退租登记', 'sys/dprelationship_rentreate.html', 'dprelationship:list,dprelationship:info,dprelationship:save,dprelationship:update,dprelationship:delete', '1', null, '4');
INSERT INTO `sys_menu` VALUES ('43', '29', '房屋配置', 'sys/dgrelationship.html', 'dgrelationship:list,dgrelationship:info,dgrelationship:save,dgrelationship:update,dgrelationship:delete', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('44', '34', '费用报表', 'sys/expensetable.html', 'expensetable:list', '1', null, '1');
INSERT INTO `sys_menu` VALUES ('45', '34', '房屋汇总报表', 'sys/dormtable.html', 'dormtable:list', '1', null, '1');
INSERT INTO `sys_menu` VALUES ('46', '56', '部室信息', 'sys/department.html', 'department:list,department:info,department:save,department:update,department:delete', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('48', '0', '控制台', 'sys/home.html', null, '1', 'fa fa-tachometer', '0');
INSERT INTO `sys_menu` VALUES ('50', '29', '使用权管理', 'sys/useadmin.html', 'useadmin:list,useadmin:info,useadmin:save,useadmin:update,useadmin:delete', '1', null, '5');
INSERT INTO `sys_menu` VALUES ('51', '34', '房屋细明报表', 'sys/dormdetailed.html', null, '1', null, '1');
INSERT INTO `sys_menu` VALUES ('52', '29', '使用单位', 'sys/useunit.html', 'useunit:list,useunit:info,useunit:save,useunit:update,useunit:delete', '1', null, '4');
INSERT INTO `sys_menu` VALUES ('54', '34', '月份定义', 'sys/month.html', 'month:list,month:info,month:update,month:delete,month:save', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('55', '30', '入住记录', 'sys/dprelationship_record.html', null, '1', null, '5');
INSERT INTO `sys_menu` VALUES ('56', '0', '人员管理', null, null, '0', 'fa fa-user', '4');
INSERT INTO `sys_menu` VALUES ('57', '56', '人员类别', 'sys/classion.html', 'classion:list,classion:update,classion:save,classion:info,classion:delete', '1', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员，拥有所有权限', '2017-01-19 15:44:09');
INSERT INTO `sys_role` VALUES ('2', '房屋管理员', '拥有房屋管理所有权限', '2019-07-22 16:35:06');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('155', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('156', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('157', '2', '37');
INSERT INTO `sys_role_menu` VALUES ('158', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('159', '2', '38');
INSERT INTO `sys_role_menu` VALUES ('160', '2', '52');
INSERT INTO `sys_role_menu` VALUES ('161', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('162', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('163', '2', '39');
INSERT INTO `sys_role_menu` VALUES ('164', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('165', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('166', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('167', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('168', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('169', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('170', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('171', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('172', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('173', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('174', '2', '51');
INSERT INTO `sys_role_menu` VALUES ('175', '2', '54');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1069878616@qq.com', '18566023083', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('3', 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, null, '1', '2019-07-22 16:35:39');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('5', '1', '1');
INSERT INTO `sys_user_role` VALUES ('6', '3', '2');

-- ----------------------------
-- Table structure for use_admin
-- ----------------------------
DROP TABLE IF EXISTS `use_admin`;
CREATE TABLE `use_admin` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `use_admin` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of use_admin
-- ----------------------------
INSERT INTO `use_admin` VALUES ('7', '广东省分行', '2019-07-03 14:47:40', 'admin');
INSERT INTO `use_admin` VALUES ('8', '越秀支行', '2019-07-03 14:47:51', 'admin');
INSERT INTO `use_admin` VALUES ('9', '荔湾支行', '2019-07-03 14:48:02', 'admin');

-- ----------------------------
-- Table structure for use_unit
-- ----------------------------
DROP TABLE IF EXISTS `use_unit`;
CREATE TABLE `use_unit` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '使用单位ID',
  `use_unit` varchar(50) NOT NULL COMMENT '使用单位',
  `add_time` datetime NOT NULL COMMENT '新增时间',
  `add_name` varchar(255) NOT NULL COMMENT '操作人员',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of use_unit
-- ----------------------------
INSERT INTO `use_unit` VALUES ('5', 'test', '2019-07-03 12:56:07', 'admin');
INSERT INTO `use_unit` VALUES ('6', '广东省分行', '2019-07-03 14:46:47', 'admin');
INSERT INTO `use_unit` VALUES ('7', '越秀支行', '2019-07-03 14:47:07', 'admin');
INSERT INTO `use_unit` VALUES ('8', '荔湾支行', '2019-07-03 14:47:18', 'admin');
INSERT INTO `use_unit` VALUES ('9', '赤岗支行', '2019-07-04 10:54:17', 'admin');
INSERT INTO `use_unit` VALUES ('10', 'year1', '2019-07-04 16:42:14', 'admin');
INSERT INTO `use_unit` VALUES ('11', 'year2', '2019-07-04 16:42:32', 'admin');
