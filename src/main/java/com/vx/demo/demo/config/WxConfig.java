package com.vx.demo.demo.config;

/**
 * @author TJ2020-6
 * @desc
 * @createTime 2020-04-10-11:38
 */
public class WxConfig {
    //获取的 appid（微信公众号号）
    public static final String appId = "wx8babc1090b1a2b6f";


    //获取的 appsecret（微信公众号）
    public static final String appSecrect = "e1c6fd34606e232048aa19ff857cce01";

    //发送文本消息接口https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=
    public static final String textMessagePath = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";


    //发送模板消息的接口地址：https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=
    public static final String templateMessagePath = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";


    //获取微信服务号的access_token接口地址:https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    public static final String appAccessTokenPath = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";


    //获取微信服务号消息模板id列表
    public static final String getTemplateIdsPath = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=";
    //模板ID
    public static final String template_id = "zz00cHBfHKQi4O93CzMWjH3ZPxZ2BR3Q6m7Rx2iidsI";
    public static final String template_id2 = "BrMPizfWgJfjnF3zy4xGCDpzG1zhMtZ6nEfs2uhbn5o";

}
