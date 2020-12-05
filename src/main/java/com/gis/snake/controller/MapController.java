package com.gis.snake.controller;

import com.gis.snake.crawler.MeituanCrawler;
import com.gis.snake.mapper.MapMapper;
import com.gis.snake.pojo.IPList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 美团评论爬虫 - 控制层
 */
@RestController
@RequestMapping("/map")
public class MapController {

    ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
    @Autowired
    private MapMapper mapMapper;
    @Autowired
    MeituanCrawler meituanCrawler;

    @RequestMapping("/meituanScenicReviewInfoCrawler1")
    public void meituanScenicReviewInfoCrawler1(@RequestParam(value = "meituanId", required = true) String meituanId) {
        //先清空ip池
        IPList.clearList();
        //校验id
        meituanCrawler.verifyIP();
        //获取有效的ip
        String hostName = IPList.getIpBeanList().get(0).split(":")[0];
        Integer port = Integer.parseInt(IPList.getIpBeanList().get(0).split(":")[1]);
        if (hashMap.contains(meituanId)) {
            System.out.println("正在处理meituanId:" + meituanId);
            return;
        }
        hashMap.put(meituanId, meituanId);
        //爬虫爬取美团评论
        meituanCrawler.ScenicReviewInfoCrawler_ProxyIPWithLowSpeed(meituanId, hostName, port);
        hashMap.remove(meituanId);
        System.out.println("入库结束");
    }

}
