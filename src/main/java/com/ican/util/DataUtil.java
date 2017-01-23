package com.ican.util;

import com.ican.lib.WechatPayData;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mrzhou on 2017/1/16.
 */
public class DataUtil {
    /**
     * 把Map类型的数据转换成xml格式的字符串
     * @param data
     * @return 返回xml格式的字符串
     * */
    public static String dataToXml(WechatPayData data){
        //用StringBuffer存放转换后的数据
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        //获取数据
        Map<String, Object> mapData = data.getData();
        //获取key的集合
        Set<String> keySet = mapData.keySet();
        //获取key集合的迭代器
        Iterator<String> iterator = keySet.iterator();
        //迭代取出key对应的value，并添加到StringBuffer中
        while(iterator.hasNext()){
            String keyString = iterator.next();
            String valueString = (String) mapData.get(keyString);
            sb.append("<" + keyString + ">" + valueString + "</" + keyString + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 将xml格式数据字符串转换成WechatPayData类型存放
     * @param xml
     * @return 返回一个WechatPayData
     * */
    public static WechatPayData xmlToData(String xml){
        //将xml字符串转换成文档document类型
        Document document = null;
        //新建WechatData对象来存放转换后的数据
        WechatPayData data = new WechatPayData();
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根结点
        Element rootElement = document.getRootElement();
        //获取根结点<xml>包含的所有结点
        List<Element> elements = rootElement.elements();
        //遍历所有结点，并取出每个结点的名字和对应的值,添加到data中
        for (Element element:elements) {
            String name = element.getName();
            String text = element.getText();
            data.addSubData(name,text);
        }
        return data;
    }
}
