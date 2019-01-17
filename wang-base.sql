/*
Navicat MySQL Data Transfer

Source Server         : mysql-loclhost
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : wang-base

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-12-14 19:35:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bas_config_param
-- ----------------------------
DROP TABLE IF EXISTS `bas_config_param`;
CREATE TABLE `bas_config_param` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `value` varchar(1700) DEFAULT NULL COMMENT '值',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统参数配置表';

-- ----------------------------
-- Records of bas_config_param
-- ----------------------------
INSERT INTO `bas_config_param` VALUES ('2', '随机会话密钥长度', 'random_key_length', '5', '随机会话密钥长度');
INSERT INTO `bas_config_param` VALUES ('3', 'RSA公钥', 'public_key', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4JcMlikcF14lDAre6ds5CFyT8CUMmUJCjkNGLNCeBhx1I/lWzqn1sDCBUCmRM1FWOJE2x2JSKFmQPz7F9OynFSh77xceN8UQXgWaty/7URqRBI4BH0JUPkOoEhU393ZTepTsM2ECeWc006axdA1RLgZbG8qEwe8s+U5+4bfyTlXmYLKfuuHygqS6wtaEHj1q2qjB7NidFncsm8yrTRpO44jv8T6NFtgSBksYD/xKbAsoiMsP+1S07wBhBBnaAJOhZIDj+q1Q0GHjQQ7fwqxWpsrUdYuAW7DG/SKW5lYd9mQ8els3CoyMFan0ZaI9NmH4jSMBPMePaNZrXfUf8P7iWQIDAQAB', 'RSA公钥');
INSERT INTO `bas_config_param` VALUES ('4', 'RSA私钥', 'private_key', 'MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDglwyWKRwXXiUMCt7p2zkIXJPwJQyZQkKOQ0Ys0J4GHHUj+VbOqfWwMIFQKZEzUVY4kTbHYlIoWZA/PsX07KcVKHvvFx43xRBeBZq3L/tRGpEEjgEfQlQ+Q6gSFTf3dlN6lOwzYQJ5ZzTTprF0DVEuBlsbyoTB7yz5Tn7ht/JOVeZgsp+64fKCpLrC1oQePWraqMHs2J0WdyybzKtNGk7jiO/xPo0W2BIGSxgP/EpsCyiIyw/7VLTvAGEEGdoAk6FkgOP6rVDQYeNBDt/CrFamytR1i4BbsMb9IpbmVh32ZDx6WzcKjIwVqfRloj02YfiNIwE8x49o1mtd9R/w/uJZAgMBAAECggEAQv/sylMjRTlZtfj8PSmgHwV3QLncw0/ICSa6pZ3qtooAzCWTJv3/BHvD6AUugu+t2BxCCkMjnVPyu1tftMxwn9g++Lmd0ydf1Zy4ZhPTVoOlZYVzgj7IYPbQCez4kENlbflFaaJ0GYGXediNscBTSnas8WJ+LljKwukO4wl8KK/ag4Bb0L8Mz803NhUA4LVtx1pyPEN33dDAwM7Skg0vWA0g+SsWE2AEsXSwfwrR+bNlK7Uqpg8OPD2MtuqzWuXzm6pafKaBVBvhrObToU9NDH8ecyiRb1SV8cy4iRL4VuJTZVYgfsu+Pjs748C6Lqz7GRckWUx83zPAQA+rtPWR8QKBgQDxrEYxTeQMIy2Ctm2ox9Zn2Q1zdW8ByKdbjBzqAIN2cwei87Im9OjhW1AKPad1UICYn9OEh0A0E1IKiZ0VF9MRHKcq/BTg60SZXtXSLg0nT+T8PZzRDKiEVDaFdP/BQZeDBcsgIoEEPbxXdFBjLcTHxFY2NosQY6FRqM2y6XJc9QKBgQDt54SSjPS2tnzgF6rnOwgxJvlQPOmf5W6FZaQOpZeS1DJAj3Qh9ZjSSyQXUXg2TBa8ExlSjqbPuBuHZ4xO4q+I6e2SMnKYh9SN8uyXKDEPXdyTJOtxXM6NPO1zoxMehmLRG4EFQbxMb78TVqH5d4s94J1fxBZ4FaiLP3C6mlrRVQKBgHuIsCirxBJgOPdEATHxVKuoy7NlJ+UliEbHjFAhCxIVIqYtI7e+IPxxDKzvbxYMnG9n41svN6Zm6dzxExahIwbwOpNCX/nIyTwMugfB+n9J26cEZ5CEg++AjI4TjFhhCu2ynG5EtOZSGGAr2JuZ3aivt9Hmd5ddFi0DuIHnysmxAoGAQMk2cg5Oqd8iPIhVcyjBP1lktXQRDwkUwuD9/zsa/tIoaSpnrsPJ/SKXTn5SJT8tRcRRtgjA5no1jPcm32id8sP5fUGdsWnz+T/5K+S3D385DR0VvMZQPWglJcN39m0UReuehAb1tz8LIsIJ0/y4/33GfYjRsZv8TQdWtIpKRw0CgYEA2Q9AyQ13CEXLg/acpjcuQGPewaROXzFybADfiCZKc0rWyAysD7ZEZQ73TZwpv0id/eSoThCTGYpbdqaWIXTmXyaYicHhOSRMfH3fabu3fRBl8TbxkHSWxBnF7zrMsQ4f47gSRuXpZVOtLszPyrtqFUD6V1nVOjy/5sdwZ/NAXxU=', 'RSA私钥');
INSERT INTO `bas_config_param` VALUES ('5', '平台编号', 'platform_code', '100200123456', '平台编号');
INSERT INTO `bas_config_param` VALUES ('6', '数据是否加密', 'data_encrypt', 'true', '数据是否加密');
INSERT INTO `bas_config_param` VALUES ('7', '有数公钥', 'ys_public_key', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqPKpWXOMPQeGvao5deS5yC2OfqdWmoUZvDiWbOAfvi3ZOwneuL1D0hn9/HxD+Jz1Gzxzl0AiQvCSNtTO50+YxfnO78pp53VIpCQZbZw458gqVkgJG7b09ua7yPc95I1V8f7xhVNR7fNd9tW52dwyxecIxhYlrNIYb4H1npkptv74YppflEdj2t6f+ISaV3t29m9FrmshjIYo7xgTDr+ENGKjhkBOqXmtLrf/rKEv/M7xeLTPjDwyYv/3ZIfgDd3gchfu3y67+fBe6mjASXJEqNfvIliLu76DX2UXc7QzStgAUn8KcOE/TP2LMG7gJzzkqoxMmSHXZiauW6hhx1+frQIDAQAB', '有数公钥');

-- ----------------------------
-- Table structure for bas_test
-- ----------------------------
DROP TABLE IF EXISTS `bas_test`;
CREATE TABLE `bas_test` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_test
-- ----------------------------
INSERT INTO `bas_test` VALUES ('1', 'zhangsan', '123456', 'zhangsan@163.com');
INSERT INTO `bas_test` VALUES ('2', 'lisi', '12345678', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('3', 'wang3', '1234', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('4', 'wang4', '1235', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('5', 'wang5', '1236', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('6', 'wang6', '1237', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('7', 'wang7', '1238', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('8', 'wang8', '1239', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('9', 'wang9', '1240', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('10', 'wang10', '1241', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('11', 'wang11', '1242', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('12', 'wang12', '1243', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('13', 'wang13', '1244', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('14', 'wang14', '1245', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('15', 'wang15', '1246', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('16', 'wang16', '1247', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('17', 'wang17', '1248', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('18', 'wang18', '1249', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('19', 'wang19', '1250', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('20', 'wang20', '1251', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('21', 'wang21', '1252', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('22', 'wang22', '1253', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('23', 'wang23', '1254', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('24', 'wang24', '1255', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('25', 'wang25', '1256', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('26', 'wang26', '1257', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('27', 'wang27', '1258', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('28', 'wang28', '1259', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('29', 'wang29', '1260', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('30', 'wang30', '1261', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('31', 'wang31', '1262', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('32', 'wang32', '1263', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('33', 'wang33', '1264', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('34', 'wang34', '1265', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('35', 'wang35', '1266', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('36', 'wang36', '1267', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('37', 'wang37', '1268', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('38', 'wang38', '1269', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('39', 'wang39', '1270', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('40', 'wang40', '1271', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('41', 'wang41', '1272', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('42', 'wang42', '1273', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('43', 'wang43', '1274', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('44', 'wang44', '1275', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('45', 'wang45', '1276', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('46', 'wang46', '1277', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('47', 'wang47', '1278', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('48', 'wang48', '1279', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('49', 'wang49', '1280', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('50', 'wang50', '1281', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('51', 'wang51', '1282', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('52', 'wang52', '1283', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('53', 'wang53', '1284', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('54', 'wang54', '1285', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('55', 'wang55', '1286', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('56', 'wang56', '1287', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('57', 'wang57', '1288', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('58', 'wang58', '1289', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('59', 'wang59', '1290', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('60', 'wang60', '1291', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('61', 'wang61', '1292', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('62', 'wang62', '1293', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('63', 'wang63', '1294', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('64', 'wang64', '1295', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('65', 'wang65', '1296', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('66', 'wang66', '1297', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('67', 'wang67', '1298', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('68', 'wang68', '1299', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('69', 'wang69', '1300', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('70', 'wang70', '1301', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('71', 'wang71', '1302', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('72', 'wang72', '1303', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('73', 'wang73', '1304', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('74', 'wang74', '1305', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('75', 'wang75', '1306', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('76', 'wang76', '1307', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('77', 'wang77', '1308', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('78', 'wang78', '1309', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('79', 'wang79', '1310', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('80', 'wang80', '1311', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('81', 'wang81', '1312', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('82', 'wang82', '1313', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('83', 'wang83', '1314', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('84', 'wang84', '1315', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('85', 'wang85', '1316', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('86', 'wang86', '1317', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('87', 'wang87', '1318', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('88', 'wang88', '1319', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('89', 'wang89', '1320', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('90', 'wang90', '1321', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('91', 'wang91', '1322', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('92', 'wang92', '1323', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('93', 'wang93', '1324', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('94', 'wang94', '1325', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('95', 'wang95', '1326', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('96', 'wang96', '1327', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('97', 'wang97', '1328', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('98', 'wang98', '1329', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('99', 'wang99', '1330', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('100', 'wang100', '1331', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('101', 'wang101', '1332', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('102', 'wang102', '1333', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('103', 'wang103', '1334', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('104', 'wang104', '1335', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('105', 'wang105', '1336', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('106', 'wang106', '1337', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('107', 'wang107', '1338', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('108', 'wang108', '1339', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('109', 'wang109', '1340', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('110', 'wang110', '1341', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('111', 'wang111', '1342', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('112', 'wang112', '1343', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('113', 'wang113', '1344', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('114', 'wang114', '1345', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('115', 'wang115', '1346', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('116', 'wang116', '1347', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('117', 'wang117', '1348', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('118', 'wang118', '1349', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('119', 'wang119', '1350', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('120', 'wang120', '1351', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('121', 'wang121', '1352', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('122', 'wang122', '1353', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('123', 'wang123', '1354', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('124', 'wang124', '1355', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('125', 'wang125', '1356', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('126', 'wang126', '1357', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('127', 'wang127', '1358', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('128', 'wang128', '1359', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('129', 'wang129', '1360', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('130', 'wang130', '1361', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('131', 'wang131', '1362', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('132', 'wang132', '1363', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('133', 'wang133', '1364', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('134', 'wang134', '1365', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('135', 'wang135', '1366', 'lisi@126.com');
INSERT INTO `bas_test` VALUES ('136', 'wang136', '1367', 'lisi@126.com');

-- ----------------------------
-- Table structure for bas_user
-- ----------------------------
DROP TABLE IF EXISTS `bas_user`;
CREATE TABLE `bas_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- Records of bas_user
-- ----------------------------
INSERT INTO `bas_user` VALUES ('2', '管理员', 'gly@163.com', '18000000000');
INSERT INTO `bas_user` VALUES ('4', '汪肖兵', 'wxb100200@163.com', '18117448780');
