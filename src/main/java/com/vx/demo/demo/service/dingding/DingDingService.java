package com.vx.demo.demo.service.dingding;

import com.alibaba.fastjson.JSON;
import com.vx.demo.demo.config.DingConfig;
import com.vx.demo.demo.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * @author Twilight
 * @desc 钉钉接口开发
 * @createTime 2020-04-12-20:34
 */
public class DingDingService {

    private static final Logger log = LoggerFactory.getLogger(DingDingService.class);

    /**
     * 获取access_token
     */
    public static String getAccessToken() throws Exception {
        String jsonResult = HttpRequestUtil.httpGet(DingConfig.dingGetAccessTokenUrl);
        Map<String, Object> stringStringMap = resultCheck(jsonResult);
        return (String) stringStringMap.get("access_token");
    }

    /**
     * 根据手机号获取userId
     */
    public static String getUserIdByMoblie(String mobile) throws Exception {
        String accessToken = getAccessToken();
        String requestUrl = DingConfig.dingGetUserByMoblieUrl + accessToken + "&mobile=" + mobile;
        String jsonStr = HttpRequestUtil.httpGet(requestUrl);
        Map<String, Object> stringStringMap = resultCheck(jsonStr);
        return (String) stringStringMap.get("userid");
    }

    /**
     * 钉钉推送消息
     */
    public static Long pushMsgToUser(Map<String, Object> requestParam) throws Exception {
        //添加必要参数
        requestParam.put("agent_id", DingConfig.dingAgentId);
        String accessToken = getAccessToken();
        String requestUrl = DingConfig.dingMsgPushToUser + accessToken;

        /*---------------------- 推送消息  -----------------------*/
        String jsonStr = HttpRequestUtil.httpPost(JSON.toJSONString(requestParam), requestUrl);
        Map<String, Object> stringStringMap = resultCheck(jsonStr);
        return (Long) stringStringMap.get("task_id");
    }

    /**
     * 结果检查
     */
    private static Map<String, Object> resultCheck(String jsonResult) throws Exception {
        if (StringUtils.isEmpty(jsonResult)) {
            log.error("[钉钉: 获取 access_token结果数据为空]");
            throw new Exception("结果数据为空");
        }
        Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(jsonResult);
        Integer errCode = (Integer) resultMap.get("errcode");
        if (0 != errCode) {
            throw new Exception("[钉钉：接口调用异常--" + resultMap.get("errmsg"));
        }
        return resultMap;
    }
}
