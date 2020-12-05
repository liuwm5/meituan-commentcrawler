# Java版本的美团评论爬虫

近期需要做一些景点数据的分析学习,需要一些数据,通过爬虫的方式抓取了部分景点数据,这里是爬取美团评论的一些过程和代码.

抓取的数据

![image-20201206005236603](https://piggo-lwm.oss-cn-beijing.aliyuncs.com/img/image-20201206005236603.png)



取数的核心代码地址 [Snake8859/meituan-commentcrawler](https://github.com/Snake8859/meituan-commentcrawler.git),在这个代码的基础上进行了调整.

> 原地址的Issues 提到 
>
> 网页端评论的数据到2018年就停止更新了, 要拿完整的数据应该去爬小程序,但是自己爬下来还是有2020年的数据.如果对数据时效性不是很高,也可以拿数据练手.

因为里面的IP代理池是从文件里读取的,所以改了一部分内容,还有调整了写入数据库的顺序

> 采用ip代理池+降速方式，避免防止反爬虫。
>
> ip代理池：采用多线程方式，校验ip列表内有效ip，用于防止爬取频率过多，ip被封。
>
> 降速：降低爬取速度，减轻访问压力。

核心类：MeituanCrawler.java

## mysql的表结构

```sql
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

需要引入mysql数据库,版本5.7.

## IP代理池

IP代理池使用了一个开源的python版本的数据

https://github.com/Python3WebSpider/ProxyPool.git

1. 需要引入redis
2. 按照ProxyPool 启动 redis,和py脚本. 存入到redis中的数据格式是zset格式.

![image-20201206003208604](https://piggo-lwm.oss-cn-beijing.aliyuncs.com/img/image-20201206003208604.png)





在配置java的RedisTemplate访问配置时

注意设置 `template.setValueSerializer(new StringRedisSerializer());`

```java
		//  需要设置为string,代理IP池存入是非json格式.
        template.setValueSerializer(new StringRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
```



## 总结

1. 需要引入IP代理池,启动 redis, PorxyPool(python 启动).
2. 启动mysql数据库.
3. 启动 meituan-commentcrawler springboot 项目.
4. post请求触发爬虫启动.



代码地址为: https://github.com/liuwm5/meituan-commentcrawler.git