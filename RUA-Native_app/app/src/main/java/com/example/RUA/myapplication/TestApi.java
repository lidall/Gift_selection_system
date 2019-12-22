
package com.example.RUA.myapplication;


import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Lida
 */


public class TestApi {

    static HttpURLConnection connection;
    /*
     * Your Access Key ID, as taken from the Your Account page.
     */
    private static final String ACCESS_KEY_ID = "your_access_key_ID";

    /*
     * Your Secret Key corresponding to the above ID, as taken from the
     * Your Account page.
     */
    private static final String SECRET_KEY = "your_secret_key";

    /*
     * Use the end-point according to the region you are interested in.
     */
    private static final String ENDPOINT = "webservices.amazon.co.uk";

    public static void callAPI(String SearchIndex, String KeyWords) {
        /*
         * Set up the signed requests helper.
         */
        SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, ACCESS_KEY_ID, SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl = null;

        Map<String, String> params = new HashMap<String, String>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", ACCESS_KEY_ID);
        params.put("AssociateTag", "your_associated_tag");
        params.put("SearchIndex", SearchIndex);
        params.put("ResponseGroup", "Medium");
        params.put("Keywords", KeyWords);
        params.put("ItemPage","1,2,3");

        requestUrl = helper.sign(params);

     //   System.out.println("Signed URL: \"" + requestUrl + "\"");
        
        String result = requestGet(requestUrl);
        try{
        XmlUtil.strChangeXML(result);
        ProcessXML.usefulXMLProcess();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        
    }



    private static String requestGet(String requestUrl) {
        BufferedReader read=null;//读取访问结果
        String result = null;
        try {

            // 新建一个URL对象
            URL url = new URL(requestUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            // 获取所有响应头字段
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                result = convertStreamToString(urlConn.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(result==null){
            System.out.println("WAIT WAIT RUARUARUA");
        }
        return result;
    }

    public static String convertStreamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }



    
}
