package com.vx.demo.demo.config;

/**
 * @author Twilight
 * @desc
 * @createTime 2020-04-12-20:39
 */
public class DingConfig {

    /**
     * 钉钉appkey
     */
    public static final String dingAppkey = "dingvr4n5ztjixqlnzyi";
    /**
     * 钉钉appsecret
     */
    public static final String dingAppSecret = "11RBq6AN1MDwPblJwSvPJ9YOTLoQXYUdKbmTZz2sINGoW9_8amY5_pWBlNyzfEx6";
    /**
     * 钉钉agentId
     */
    public static final String dingAgentId = "707829281";
    /**
     * 钉钉access_token Url
     */
    public static final String dingGetAccessTokenUrl = "https://oapi.dingtalk.com/gettoken?appkey=" + dingAppkey + "&appsecret=" + dingAppSecret;
    /**
     * 钉钉getUserId url
     */
    public static final String dingGetUserByMoblieUrl = "https://oapi.dingtalk.com/user/get_by_mobile?access_token=";
    /**
     * 钉钉push to user Url
     */
    public static final String dingMsgPushToUser = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?access_token=";
}
