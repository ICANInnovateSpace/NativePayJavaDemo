package com.ican.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mrzhou on 2017/1/20.
 */
public class HttpService {
    /**
     * 以POST的方式请求微信后台的数据
     * @param url 微信后台的url地址
     * @param xml 请求的xml格式数据
     * @return 微信后台返回的数据
     * */
    public static String post(String url, String xml){
        try {
            //指定微信查询订单的服务器域名url访问地址
            URL descUrl = new URL(url);
            try {
                //用url打开http连接
                HttpURLConnection urlConnection = (HttpURLConnection) descUrl.openConnection();
                //将包涵订单id的xml字符串数据转化成字节数组，方便字节流传输
                byte[] bytes = xml.getBytes();
                //设置可读写数据
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                //设置请求方式以及请求超时的时间
                urlConnection.setRequestMethod("POST");
                urlConnection.setConnectTimeout(66000);
                //设置请求的属性，包括字符集，请求内容的长度、类型，以及保持连接
                urlConnection.setRequestProperty("Charset","UTF-8");
                urlConnection.setRequestProperty("Content-Length",String.valueOf(bytes.length));
                urlConnection.setRequestProperty("ContentType","text/xml");
                urlConnection.setRequestProperty("Connection","Keep-Alive");
                //获取写入流，往服务器写包涵订单信息的xml数据
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(bytes,0,bytes.length);
                outputStream.close();
                //以下读取微信服务器端返回的xml数据
                String line = null;
                StringBuffer sb = new StringBuffer();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while((line = reader.readLine()) != null){
                    sb.append(line);
                }
                reader.close();
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
