package com.vx.demo.demo.service.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vx.demo.demo.config.WxConfig;
import com.vx.demo.demo.utils.HttpRequestUtil;
import com.vx.demo.demo.vo.wx.Content;
import com.vx.demo.demo.vo.wx.TemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TJ2020-6
 * @desc
 * @createTime 2020-04-10-11:06
 */
public class WxPushService {

    private static Logger log = LoggerFactory.getLogger(WxPushService.class);

    /**
     * access_token 每2小时失效，需要定时刷新
     * 获取access_token
     */
    public static String getAccessToken(String accessTokenUrl, String appId, String appSecret) {
        //组装参数
        String getRequest = accessTokenUrl + WxConfig.appId + "&secret=" + WxConfig.appSecrect;
        String result = HttpRequestUtil.httpGet(getRequest);

        //解析结果
        Map<String, String> access_tokenMap = (Map<String, String>) JSON.parse(result);
        log.error("access_tokenMapxx:" + access_tokenMap.toString());
        String access_token = access_tokenMap.get("access_token");
        log.error("access_tokenxx:" + access_token);
        return access_token;

    }


    /**
     * 获取模板id列表
     */
    public static void getTemplateIds() {
        String accessToken = getAccessToken(WxConfig.appAccessTokenPath, WxConfig.appId, WxConfig.appSecrect);
        //组装参数
        String getRequest = WxConfig.getTemplateIdsPath + accessToken;
        String result = HttpRequestUtil.httpGet(getRequest);
        parseResultData((Map<String, List<Map<String, String>>>) JSON.parse(result));
    }

    //解析模板
    private static void parseResultData(Map<String, List<Map<String, String>>> parse) {
        List<Map<String, String>> templateList = parse.get("template_list");
        List<TemplateVo> collect = templateList.stream().map(TemplateVo::new).collect(Collectors.toList());
        /*---------------------- 存入数据库  -----------------------*/
        //todo
        collect.forEach(System.out::println);
    }


    /**
     * 根据不同推送消息
     */
    public static void sendWeChatMessage(List<String> openIds,
                                         Integer msgType,
                                         String tempId,
                                         Map<String, String> params, String context) {
        String accessToken = "";
        //判断accesstoken 是否有效，无效则重新获取
        boolean validToken = true;
        if (validToken) {
            accessToken = getAccessToken(WxConfig.appAccessTokenPath, WxConfig.appId, WxConfig.appSecrect);
        }

        /*----------------------  选择推送消息类型 -----------------------*/
        if (msgType == 1) {
            sendTestMsg(openIds, context, accessToken);
        } else {
            sendTemplateMsg(openIds, accessToken, tempId, params);
        }

    }

    /**
     * 发送模板消息
     */
    private static void sendTemplateMsg(List<String> openIds,
                                        String accessToken, String tempId,
                                        Map<String, String> params) {

        //模板消息格式
        JSONObject jsonObject = new JSONObject();
        //动态根据不同id发送不同模板
//        jsonObject.put("template_id", WxConfig.template_id2);
        jsonObject.put("template_id", tempId);

        HashMap<String, Content> contentHashMap = new HashMap<>();
        params.forEach((s, s2) -> {
            contentHashMap.put(s, new Content(s2, "#173177"));
        });
//        data.setPay(new Content("1000.00", "#173177"));
//        data.setAddress(new Content(context, "#173177"));
//        data.setTime(new Content("2020-04-03 18:46:07", "#173177"));
//        data.setRemark(new Content("亲，欢迎下次光临'", "#173177"));

        jsonObject.put("data", contentHashMap);
        //开始推送模板消息
        openIds.forEach(openId -> {
            jsonObject.put("touser", openId);
            String result = HttpRequestUtil.httpPost(jsonObject.toJSONString(), WxConfig.templateMessagePath + accessToken);
            log.error("result 推送结果：" + result);
        });
    }

    /**
     * 发送文本消息
     */
    private static void sendTestMsg(List<String> openIds, String context, String accessToken) {
        //组装文本消息元素据
        JSONObject sengParams = new JSONObject();
        sengParams.put("msgtype", "text");

        //组装文本消息内容
        JSONObject textData = new JSONObject();
        textData.put("content", context);

        //组装消息实体
        sengParams.put("text", textData);

        //开始推送文本消息
        openIds.forEach(openId -> {
            sengParams.put("touser", openId);
            String result = HttpRequestUtil.httpPost(sengParams.toJSONString(), WxConfig.textMessagePath + accessToken);
            log.error("result 推送结果：" + result);
        });
    }
}
