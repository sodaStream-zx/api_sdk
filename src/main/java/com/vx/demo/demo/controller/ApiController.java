package com.vx.demo.demo.controller;

import com.alibaba.fastjson.JSON;
import com.vx.demo.demo.config.WxConfig;
import com.vx.demo.demo.service.dingding.DingDingService;
import com.vx.demo.demo.service.wx.WxPushService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author TJ2020-6
 * @desc
 * @createTime 2020-04-10-11:06
 */
@RestController
@RequestMapping(value = "/weixin")
public class ApiController {

    @PostMapping(value = "/sendMsg")
    public String sendMsg(String msg, String openIds, String tempId, String params, String context) {
        if (StringUtils.isEmpty(openIds)) {
            return "openId 为空";
        }
        //解析参数
        Map<String, String> paramMap = null;
        if (!StringUtils.isEmpty(params)) {
            paramMap = Stream.of(params.split(";"))
                    .collect(Collectors.toMap(s -> s.split("=")[0], o -> o.split("=")[1]));
        }
        List<String> openidList = Stream.of(openIds.split(",")).
                filter(s -> !StringUtils.isEmpty(s)).
                collect(Collectors.toList());
        //组装openId
        openidList.add("oL2jGs06-SS0AI3GeCVj6djSR79E");
        WxPushService.sendWeChatMessage(openidList, 2, tempId, paramMap, context);
        return "ok";
    }

    //获取access_token
    @GetMapping(value = "/getToken")
    public String getToken() {
        return WxPushService.getAccessToken(WxConfig.appAccessTokenPath, WxConfig.appId, WxConfig.appSecrect);
    }

    @PostMapping(value = "/getTemplates")
    public String getTemplates() {
        WxPushService.getTemplateIds();
        return "ok";
    }

    //获取access_token
    @GetMapping(value = "/getDingToken")
    public String getDingToken() throws Exception {
        return DingDingService.getAccessToken();
    }

    //根据手机号获取 userId
    @GetMapping(value = "/getdingUserIdByMoblie")
    public String getUserId(String mobile) throws Exception {
        return DingDingService.getUserIdByMoblie("15282648585");
    }

    //根据手机号获取 userId
    @GetMapping(value = "/push")
    public Long pushJson(String json) throws Exception {
        json = "{\"msg\":{\"msgtype\":\"text\",\"text\":{\"content\":\"这是一条测试消息\"}},\"userid_list\":\"manager2642\"}";
        Map<String, Object> reqParams = (Map<String, Object>) JSON.parse(json);
        return DingDingService.pushMsgToUser(reqParams);
    }
}
