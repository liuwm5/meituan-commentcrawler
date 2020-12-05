package com.gis.snake.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 暂时用于存放经筛选的ip
 */
public class IPList {
    private static List<String> ipBeanList = new ArrayList<String>();

    // 计数器,线程结束即+1, 用于判断所有副线程是否完成
    private static int count = 0;

    /**
     * 支持并发操作
     * @param hostName
     * @param port
     */
    public static synchronized void add(String hostName,Integer port) {
        ipBeanList.add(hostName+":"+port);
    }

    public static void clearList(){
        ipBeanList.clear();
        count = 0;
    }

    public static List<String> getIpBeanList() {
        return ipBeanList;
    }

    public static int getSize() {
        return ipBeanList.size();
    }


    public static synchronized void increase() {
        count++;
    }

    public static synchronized int getCount(){
        return count;
    }
}
