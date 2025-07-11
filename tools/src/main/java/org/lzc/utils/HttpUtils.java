package org.lzc.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class HttpUtils {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    static {
        REST_TEMPLATE.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    /**
     * 发送POST JSON请求
     *
     * @param url  请求地址
     * @param data 请求数据
     */
    public static JSONObject post(String url, Object data) {
        return post(url, JSONObject.parseObject(JSONObject.toJSONString(data)), JSONObject.class);
    }


    /**
     * 发送POST JSON请求
     *
     * @param url         请求地址
     * @param data        请求数据
     * @param returnClass 返回类型
     */
    public static <T> T post(String url, Object data, Class<T> returnClass) {
        log.info("发送HTTP post请求 url: {},data: {}", url, data);
        String resultStr = REST_TEMPLATE.postForObject(url, JSONObject.parseObject(JSONObject.toJSONString(data)), String.class);
        log.info("HTTP POST请求返回数据: {}", resultStr);
        return JSON.parseObject(resultStr, returnClass);
    }

    /**
     * 发送POST JSON请求(携带鉴权)
     *
     * @param url            请求地址
     * @param data           请求数据
     * @param requestHeaders 请求头
     * @param returnClass    返回类型
     */
    public static <T> T post(String url, Object data, HttpHeaders requestHeaders, Class<T> returnClass) {
        log.info("发送HTTP post请求 url: {},data: {},header:{}", url, JSONObject.toJSONString(data), requestHeaders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(JSONObject.parseObject(JSONObject.toJSONString(data)), requestHeaders);
        String resultStr = REST_TEMPLATE.postForObject(url, requestEntity, String.class);
        log.info("HTTP POST请求返回数据: {}", resultStr);
        return JSON.parseObject(resultStr, returnClass);
    }

    /**
     * 发送POST JSON请求(携带鉴权)
     *
     * @param url            请求地址
     * @param data           请求数据
     * @param requestHeaders 请求头
     */
    public static String post(String url, Object data, HttpHeaders requestHeaders) {
//        log.info("发送HTTP post请求 url: {},data: {},header:{}", url, JSONObject.toJSONString(data), requestHeaders);


        HttpEntity<Object> requestEntity = new HttpEntity<>(JSONObject.parseObject(JSONObject.toJSONString(data)), requestHeaders);
        String resultStr = REST_TEMPLATE.postForObject(url, requestEntity, String.class);
        log.info("HTTP POST请求返回数据: {}", resultStr);
        return resultStr;
    }

    /**
     * 发送GET请求
     */
    public static String get(String url) {
        log.info("发送HTTP GET请求 url: {}", url);
        String resultStr = REST_TEMPLATE.getForObject(URI.create(url), String.class);
        log.info("HTTP GET请求返回数据: {}", resultStr);
        return resultStr;
    }


    public static String get(String url, Object obj, HttpHeaders requestHeaders) {
        log.info("发送HTTP get请求 url: {},data: {}", url, obj);
        HttpEntity<Object> requestEntity = new HttpEntity<>(obj, requestHeaders);
        ResponseEntity<String> exchange = REST_TEMPLATE.exchange(url, HttpMethod.GET, requestEntity, String.class);
        log.info("HTTP GET请求返回数据: {}", exchange);
        return exchange.getBody();
    }

    public static Map getMap(String url) {
        log.info("发送HTTP get请求 url: {}", url);
        ResponseEntity<Map> userResp = REST_TEMPLATE.getForEntity(url, Map.class);
        Map userBody = userResp.getBody();
        log.info("HTTP GET请求返回数据: {}", userBody);
        return userBody;
    }


    /**
     * 发送post表单请求
     * <p>
     * 上传文件示例 {@code data.put("file", new FileSystemResource()));}
     */
    public static <T> T postFormData(String url, MultiValueMap<String, Object> data, Class<T> returnClass) {
        log.info("发送HTTP POST 表单请求 url: {} ,data: {}", url, data);
        String resultStr = REST_TEMPLATE.postForObject(url, data, String.class);
        log.info("HTTP POST表单请求返回数据: {}", resultStr);
        return JSON.parseObject(resultStr, returnClass);
    }

    /**
     * 发送post xml请求
     */
    public static String postXml(String url, String xmlString) {
        log.info("发送HTTP POST XML 请求 url: {} ,data: {}", url, xmlString);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        String resultStr = REST_TEMPLATE.postForObject(url, new HttpEntity<>(xmlString, httpHeaders), String.class);
        log.info("HTTP POST XML返回数据: {}", resultStr);
        return resultStr;
    }


    /**
     * 发送GET请求，获取文件byte[]
     */
    public static ResponseEntity<byte[]> getToFile(String url) {
        log.info("发送HTTP GET getToFile请求 url: {}", url);
        ResponseEntity<byte[]> forEntity = REST_TEMPLATE.getForEntity(URI.create(url), byte[].class);
        log.info("HTTP GET getToFile 请求返回数据: {}", forEntity);
        return forEntity;
    }

}
