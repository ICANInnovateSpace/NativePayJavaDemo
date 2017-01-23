package com.ican.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mrzhou on 2017/1/19.
 */
public class WechatPayData {
    /*
    * 由于微信后台接收以及发送的数据是xml格式的数据，是键值对的形式
    * 所以用一个Map对象来存放需要发送到微信后台或者从微信后台接收的
    * 数据
    * */
    private Map<String,Object> data = new HashMap<>();

    /**
     * @return 返回一个Map类型的数据
     * */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 添加一条数据到Map集合中
     * @param key
     * @param value
     * */
    public void addSubData(String key,Object value){
        data.put(key,value);
    }

    /**
     * 判断是否存在某个键所对应的值
     * @param key
     * @return 是否存在的结果
     * */
    public boolean isExist(String key){
        if (data.get(key) == null)
            return false;
        return true;
    }

    /**
     *获取某个键的值
     * @param key
     * @return key对应的值
     */
    public Object getDataByKey(String key){
        return data.get(key);
    }
}
