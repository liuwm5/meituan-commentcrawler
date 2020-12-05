# 美团评论爬虫

Java版本的美团评论爬虫

取数的核心代码地址 [Snake8859/meituan-commentcrawler](https://github.com/Snake8859/meituan-commentcrawler.git)
因为里面的IP代理池是从文件里读取的,所以改了一部分内容,还有调整了写入数据库的顺序

> 采用ip代理池+降速方式，避免防止反爬虫。
>
> ip代理池：采用多线程方式，校验ip列表内有效ip，用于防止爬取频率过多，ip被封。
>
> 降速：降低爬取速度，减轻访问压力。

核心类：MeituanCrawler.java

mysql的表结构
```
CREATE TABLE `tb_scenic_review_info` (
  `reviewId` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `authorProfileUrl` varchar(255) DEFAULT NULL,
  `picInfo` varchar(2048) DEFAULT NULL,
  `review` varchar(2048) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `srcName` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `dt_create` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

```

