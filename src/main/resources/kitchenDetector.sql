DROP DATABASE IF EXISTS kitchenmanager;
DROP DATABASE IF EXISTS kitchenDetector;
CREATE DATABASE kitchenDetector;

USE kitchenDetector;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(15) NOT NULL COMMENT '用户显示及登陆的用户名',
  `password` varchar(15) NOT NULL COMMENT '用户登录使用的密码',
  `mail` varchar(30) DEFAULT NULL COMMENT '用户绑定的电子邮箱，可用于登录，也可用于找回密码',
  `ID` varchar(20) NOT NULL COMMENT '由系统生成的唯一标识',
  `phone` int(12) DEFAULT NULL COMMENT '用户的电话账号，可用于登录',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into `user`(`username`,`password`,`mail`,`ID`,`phone`) values ('lzl','123456','123123@qq.com','10001',110110110),('xyc','123456','123321@qq.com','10002',120120120),('ly','123456','321123@qq.com','10003',130130130);

DROP TABLE IF EXISTS `camera`;
CREATE TABLE `camera` (
  `cameraName` varchar(20) NOT NULL COMMENT '摄像头在页面上显示的名称',
  `remark` text COMMENT '备注：对摄像头播放内容的描述',
  `belongID` varchar(20) NOT NULL COMMENT '归属用户的ID',
  `cameraID` varchar(20) NOT NULL COMMENT '摄像头ID，摄像头的唯一标识',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '摄像头开启状态：默认为开启',
  `IP` varchar(20) NOT NULL COMMENT 'RTMP等协议拉流的地址',
  `brand` varchar(20) DEFAULT NULL COMMENT '品牌',
  `time_start` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `time_reload` datetime NOT NULL COMMENT '重载时间',
  PRIMARY KEY (`cameraID`),
  FOREIGN KEY (`belongID`) REFERENCES user(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `camera` */

insert  into `camera`(`cameraName`,`remark`,`belongID`,`cameraID`,`status`,`IP`,`brand`,`time_start`,`time_reload`) values ('摄像头A','用于观察厨房冰箱','10001','10001001',1,'192.168.0.1','小米','2022-07-19 22:24:51.000000','2022-07-19 22:24:54'),('摄像头B','用于查看走道行人是否携带口罩','10002','10002001',1,'192.168.0.2','海康','2022-07-19 22:25:51.000000','2022-07-19 22:25:50'),('摄像头C','用于观看 大家有没有摸鱼','10003','10003001',1,'192.168.0.3','华为','2022-07-19 22:26:26.000000','2022-07-19 22:26:28');

/*Table structure for table `configure` */

DROP TABLE IF EXISTS `configure`;

CREATE TABLE `configure` (
  `image_resolution` varchar(20) DEFAULT NULL COMMENT '分辨率',
  `frameRate` int(1) DEFAULT NULL COMMENT '帧率',
  `interFrame` int(4) DEFAULT NULL COMMENT '帧间隔',
  `streamType` varchar(5) DEFAULT NULL COMMENT '推流格式',
  `cameraID` varchar(20) NOT NULL COMMENT '所属摄像头ID',
  PRIMARY KEY (`cameraID`),
  FOREIGN KEY (`cameraID`) REFERENCES camera(`cameraID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into `configure`(`image_resolution`,`frameRate`,`interFrame`,`streamType`,`cameraID`) values ('800*600',30,1,'RTMP','10001001'),('800*600',25,1,'RTSP','10002001'),('1024*720',60,1,'RTMP','10003001');

DROP TABLE IF EXISTS `exceptions`;

CREATE TABLE `exceptions` (
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '记录异常发生的时间，其中timestamp 类型 在使用时  会自动调用当前的是时间',
  `cameraID` varchar(20) NOT NULL COMMENT '异常所归属的摄像头',
  `user_ID` varchar(20) NOT NULL COMMENT '异常所归属的user',
  `type` varchar(10) NOT NULL COMMENT '异常所属的类型',
  `status` tinyint(1) NOT NULL COMMENT '异常是否已读',
  `NO` int(10) NOT NULL AUTO_INCREMENT COMMENT '异常的编号 “可考虑使用字符串”',
  `remark` text COMMENT '对于该异常的描述 可由 user 更改',
  PRIMARY KEY (`NO`),
  FOREIGN KEY (`cameraID`) REFERENCES camera(`cameraID`),
  FOREIGN KEY (`user_ID`) REFERENCES user(`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


insert  into `exceptions`(`time`,`cameraID`,`user_ID`,`type`,`status`,`NO`,`remark`) values ('2022-07-19 22:28:32.000000','10001001','10001','吸烟',1,1,NULL),('2022-07-19 22:29:22.000000','10002001','10002','未带口罩',0,2,NULL),('2022-07-19 22:29:53.000000','10003001','10003','老鼠异常',1,3,NULL);
