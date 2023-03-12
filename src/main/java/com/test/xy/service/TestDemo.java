package com.test.xy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class TestDemo {
    //初始化httpclient
    CloseableHttpClient httpclient = HttpClients.createDefault();
    public  void testGet() throws IOException, InterruptedException {
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/api/v1/product/list/");
        CloseableHttpResponse response=null;
        try {
            response  = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取响应头
        Header[] allHeaders = response.getAllHeaders();
        for(Header a:allHeaders){

            System.out.println(Arrays.toString(a.getElements()));
        }
        //获取响应体
        HttpEntity entity = response.getEntity();
//        InputStream content = entity.getContent();
//        byte[] bytes=new byte [300];
//        content.read(bytes);
//        System.out.println(new String(bytes,"utf-8"));
//        获取响应体
        String result2 = EntityUtils.toString(entity);
        System.out.println(result2);
        httpclient.close();
    }
    public String loginPost() throws IOException {
        HttpPost httpPost=new HttpPost("http://127.0.0.1:8080/api/v1/user/login");
        httpPost.setHeader("content-type","application/json");

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mail","794666918@qq.com");
        jsonObject.put("pwd","123456");
        StringEntity stringEntity = new StringEntity(jsonObject.toString());
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        JSONObject jsonObject1 = (JSONObject) JSON.parse(result);
        String data = jsonObject1.getString("data");
        System.out.println(data);
        return data;
    }
    public void productInfo(String token ){
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/api/v1/user/info");
        httpGet.setHeader("token",token);
        CloseableHttpResponse response=null;
        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result=null;
        try {
             result = EntityUtils.toString(entity);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }


}
