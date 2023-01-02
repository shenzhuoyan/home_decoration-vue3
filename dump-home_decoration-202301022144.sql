-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 192.168.56.101    Database: home_decoration
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.11-MariaDB-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anli`
--

DROP TABLE IF EXISTS `anli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anli` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `content` varchar(10000) NOT NULL,
  `designer_id` int(11) NOT NULL DEFAULT 1,
  `introduction` varchar(500) NOT NULL,
  `picture` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anli`
--

LOCK TABLES `anli` WRITE;
/*!40000 ALTER TABLE `anli` DISABLE KEYS */;
INSERT INTO `anli` VALUES (2,'1','2022-04-27','<p>测试案例1</p>',3,'案例1','/picture/get/2022-04-27_11-15-06-61.png'),(3,'案例2','2022-04-27','<p>测试案例2</p>',2,'这是案例2','/picture/get/2022-04-27_11-15-54-542.jpg'),(4,'案例3','2022-04-27','<p>这是案例3</p>',1,'案例3是也','/picture/get/2022-04-27_11-17-05-53.jpg'),(5,'案例4','2022-04-27','<p>是极，是极</p>',2,'此处案例4','/picture/get/2022-04-27_11-17-32-324.jpg'),(6,'案例5','2022-04-27','<p>案例5哟</p>',3,'这里案例5','/picture/get/2022-04-27_11-18-09-95.jpg'),(7,'案例6','2022-04-27','<p>啊哈挨打</p>',1,'案例6了哟','/picture/get/2022-04-27_11-18-29-296.jpg'),(8,'7','2022-04-27','<p>大师傅</p>',2,'案例7','/picture/get/2022-04-27_11-37-38-382.jpg'),(9,'测试vue3','2023-01-01','<p>日月重开大宋天</p>',2,'使用vue3重写','/picture/get/2023-01-01_11-19-19-194f2d7a6458d82263ab5d91cca7747db9.jpg'),(11,'测试3','2023-01-01','<p>解决办法就是嵌套一层data, 修改data就行。</p>',2,'上一次因为reactive对象直接赋值不会触发视图更新','/picture/get/2023-01-01_11-31-53-535c67185a41eec29c02b86bead7bad783.jpeg');
/*!40000 ALTER TABLE `anli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designer`
--

DROP TABLE IF EXISTS `designer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `picture` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designer`
--

LOCK TABLES `designer` WRITE;
/*!40000 ALTER TABLE `designer` DISABLE KEYS */;
INSERT INTO `designer` VALUES (1,'风火火','<p style=\"text-align: center;\">风火火其人</p><p style=\"text-align: left;\"> &nbsp; &nbsp;屠猪贩狗之流，插标卖首之辈。火火火吼吼，火后火啊四大。是了解克列科季夫楼上的风，啊四大类分解啊四大看了房间。</p><p style=\"text-align: left;\"><img src=\"/picture/get/2023-01-02_08-59-43-435c4097b1e44135b9.jpg\" alt=\"\" data-href=\"\" style=\"\"></p>','2022-04-26','/picture/get/2023-01-02_08-58-29-295c4097b1e44135b9.jpg'),(2,'李四爷','<p>李四姐</p>','2022-04-26','/picture/get/2022-04-26_07-27-11-11tx.jpg'),(3,'王五先生','<p>hin好</p>','2022-04-26','/picture/get/2022-04-26_09-46-03-31.png');
/*!40000 ALTER TABLE `designer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `good` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture` varchar(1000) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `price` float NOT NULL DEFAULT 99.99,
  `designer_id` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (1,'/picture/get/2022-04-26_06-55-47-472.jpg','家装服务','<p>封装</p>','这是一个很好用的服务',99.99,1),(2,'/picture/get/2022-04-26_10-25-12-12tx.jpg','室内装修','<p>啊这</p><p>话说</p><p>测试</p><p><img src=\"/picture/get/2022-04-26_10-26-06-6linus.jpeg\"></p>','室内装修是一个很好的东西，建议试试',1111,2);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `public_time` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES (1,1,'1','2022-04-25 02:47:39'),(2,1,'<h1 class=\"ql-align-center\">Z家装公司</h1><p class=\"ql-align-center\">本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这<img src=\"/picture/get/2022-04-25_10-54-18-182.png\"></p><p>本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这</p><p class=\"ql-align-center\"><img src=\"/picture/get/2022-04-25_10-54-32-321.png\"></p><p class=\"ql-align-center\">本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这本公司是一个很好的公司，这里是本公司的介绍，是的，的确，没错，啊这</p>','2022-04-25 02:54:41'),(3,2,'123-456-7890','2022-04-25 02:54:57'),(4,3,'审审审123','2022-04-25 02:55:21'),(5,4,'江苏省苏州市哈哈区嘻嘻街道嘿嘿路123号','2022-04-25 02:56:50'),(11,5,'/picture/get/2022-04-27_02-23-39-39banner4.jpg','2022-04-27 06:23:41'),(13,4,'北京市朝阳区朝阳街道朝阳大街朝阳路1号','2022-12-31 17:13:05'),(19,5,'/picture/get/2022-12-31_08-07-52-522022-04-27_02-23-36-36banner3.jpg','2022-12-31 20:07:52'),(21,5,'/picture/get/2022-12-31_08-12-41-412022-04-27_01-55-13-13banner2.jpg','2022-12-31 20:12:41'),(23,1,'<h1 style=\"text-align: center;\">飞熊科技</h1><p> &nbsp; &nbsp; &nbsp; &nbsp;DF:Don\'t Fragment是IP协议的首部“标志”中标志(flag) 占 3 位，只有前两位有意义。标志字段的最低位是 MF (More Fragment)。MF =1 表示后面“还有分片”。MF = 0 表示最后一个<a href=\"https://baike.baidu.com/item/%E5%88%86%E7%89%87/12712599?fromModule=lemma_inlink\" target=\"_blank\">分片</a>。标志字段中间的一位是 DF (Don\'t Fragment) 只有当 DF =0 时才允许分片。DF:Don\'t Fragment是IP协议的首部“标志”中标志(flag) 占 3 位，只有前两位有意义。标志字段的最低位是 MF (More Fragment)。MF =1 表示后面“还有分片”。MF = 0 表示最后一个<a href=\"https://baike.baidu.com/item/%E5%88%86%E7%89%87/12712599?fromModule=lemma_inlink\" target=\"_blank\">分片</a>。标志字段中间的一位是 DF (Don\'t Fragment) 只有当 DF =0 时才允许分片。DF:Don\'t Fragment是IP协议的首部“标志”中标志(flag) 占 3 位，只有前两位有意义。标志字段的最低位是 MF (More Fragment)。MF =1 表示后面“还有分片”。MF = 0 表示最后一个<a href=\"https://baike.baidu.com/item/%E5%88%86%E7%89%87/12712599?fromModule=lemma_inlink\" target=\"_blank\">分片</a>。标志字段中间的一位是 DF (Don\'t Fragment) 只有当 DF =0 时才允许分片。DF:Don\'t Fragment是IP协议的首部“标志”中标志(flag) 占 3 位，只有前两位有意义。标志字段的最低位是 MF (More Fragment)。MF =1 表示后面“还有分片”。MF = 0 表示最后一个<a href=\"https://baike.baidu.com/item/%E5%88%86%E7%89%87/12712599?fromModule=lemma_inlink\" target=\"_blank\">分片</a>。标志字段中间的一位是 DF (Don\'t Fragment) 只有当 DF =0 时才允许分片。DF:Don\'t Fragment是IP协议的首部“标志”中标志(flag) 占 3 位，只有前两位有意义。标志字段的最低位是 MF (More Fragment)。MF =1 表示后面“还有分片”。MF = 0 表示最后一个<a href=\"https://baike.baidu.com/item/%E5%88%86%E7%89%87/12712599?fromModule=lemma_inlink\" target=\"_blank\">分片</a>。标志字段中间的一位是 DF (Don\'t Fragment) 只有当 DF =0 时才允许分片。</p><p style=\"text-align: center;\"><img src=\"/picture/get/2023-01-01_10-41-16-164f2d7a6458d82263ab5d91cca7747db9.jpg\" alt=\"\" data-href=\"\" style=\"\"></p><p style=\"text-align: center;\"><img src=\"/picture/get/2022-12-31_07-20-37-37d0952210f82ee08975d423a3f5708f98.jpg\" alt=\"\" data-href=\"\" style=\"\"></p>','2023-01-01 01:18:51');
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `title` varchar(100) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'2022-04-25','关于新建通知的通知','<p>第一段</p><p>第二段</p>',2),(2,'2022-04-25','标题','<p>测试1</p>',1),(3,'2022-04-26','通知测试1','<p><strong>这里是通知测试1</strong></p><p>大师傅阿萨德阿斯蒂芬阿萨德豆腐干豆腐花更符合电饭锅</p><p><br></p><p><img src=\"/picture/get/2022-04-26_09-46-03-31.png\"></p>',1),(4,'2022-04-26','测试通知2','<p>阿巴阿巴阿巴阿巴</p>',2),(5,'2022-04-26','通知3','<p>通知3</p>',1),(6,'2022-04-26','通知4','<p>通知4</p>',0),(7,'2022-04-26','测试通知5','<p>1111</p>',1),(8,'2022-04-26','关于通知功能完成的通知','<p>1111</p>',2),(9,'2022-04-26','再一个通知','<p>这是一个哦天哪之</p>',1),(10,'2022-04-26','还有一个通知','<p>123</p>',1),(11,'2022-04-26','第11个通知','<p>阿斯蒂芬as</p>',2),(12,'2022-04-26','12','<p>123</p>',2),(13,'2022-04-26','12312','<p>123123</p>',1),(14,'2022-04-26','一页12','<p>123123</p>',1),(15,'2022-04-26','原来如此','<p>1234234</p>',1),(16,'2022-04-26','通知补充','<p>沙士大夫</p>',2),(17,'2022-04-26','好歹好歹','<p>撒打算1234 4</p>',1),(18,'2022-04-26','一起','<p>阿斯蒂芬</p>',2),(19,'2022-04-26','测试1 个','<p>阿斯蒂芬 分隔符</p>',0),(20,'2022-04-26','多发几个通知','',1),(21,'2022-04-26','多好哇','<p>而是将开放的工会副本的</p>',1),(22,'2022-04-26','再来一个','<p>发送到士大夫的双方各广东佛山</p>',0),(23,'2023-01-02','《“芯”动一夏》新品发布会！','<h1 style=\"text-align: center;\">问刘十九</h1><p style=\"text-align: center;\"><span style=\"font-size: 19px;\">白居易</span></p><p style=\"text-align: center;\"><span style=\"font-size: 32px;\">绿蚁新醅酒，</span></p><p style=\"text-align: center;\"><span style=\"font-size: 32px;\">红泥小火炉。</span></p><p style=\"text-align: center;\"><span style=\"font-size: 32px;\">晚来天欲雪，</span></p><p style=\"text-align: center;\"><span style=\"font-size: 32px;\">能饮一杯无？ </span><br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img src=\"/picture/get/2023-01-02_06-25-21-216c0cf2a4dd034da5829b4d8e02d3ee0a.jpg\" alt=\"\" data-href=\"\" style=\"\"></p>',3);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yingpin`
--

DROP TABLE IF EXISTS `yingpin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yingpin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` int(11) NOT NULL,
  `birth_year` int(11) NOT NULL,
  `education` int(11) NOT NULL,
  `school` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `skill` varchar(500) NOT NULL,
  `zhao_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yingpin`
--

LOCK TABLES `yingpin` WRITE;
/*!40000 ALTER TABLE `yingpin` DISABLE KEYS */;
INSERT INTO `yingpin` VALUES (1,'张三',1,1998,4,'常熟理工学院','手机123445','技能1，技能2',7);
/*!40000 ALTER TABLE `yingpin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhaopin`
--

DROP TABLE IF EXISTS `zhaopin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zhaopin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `describe` varchar(1000) NOT NULL,
  `address` varchar(100) NOT NULL,
  `education` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `text` varchar(500) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhaopin`
--

LOCK TABLES `zhaopin` WRITE;
/*!40000 ALTER TABLE `zhaopin` DISABLE KEYS */;
INSERT INTO `zhaopin` VALUES (1,'开发工程师','完成任务\n提交方案\n执行方案\n上线项目','苏州市',3,1,'很有精神。的确如此',1),(2,'1','1','1',2,1,'1',1),(3,'2','2','2',4,2,'2',1),(4,'3','3','3',3,3,'3',0),(5,'4','4','4',5,4,'4',0),(6,'5','5','5',0,5,'5',1),(7,'6','6','6',1,6,'6',1),(8,'7','7','7',3,7,'7',1),(9,'8','8','8',4,8,'8',1);
/*!40000 ALTER TABLE `zhaopin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'home_decoration'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-02 21:44:09
