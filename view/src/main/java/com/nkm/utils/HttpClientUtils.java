package com.nkm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientUtils {

    final static Logger logger = Logger.getLogger(HttpClientUtils.class);

    public static String httpGet(String url){

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            String result = "";
            if(statusCode >= 200 && statusCode < 300){
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
                return result;
            }
            logger.info("result : "+result);
        }catch (Exception e){
            logger.error("ERROR execute API : "+e.getMessage(), e);
        }
        return null;
    }

}
