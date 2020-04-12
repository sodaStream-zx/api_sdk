package com.vx.demo.demo.vo.wx;

/**
 * 模板封装
 */
public class TemplateMessage {

    //模板消息ID
    private String template_id;

    //详情跳转页面
    private String url;

    //模板数据封装实体
    private Data data;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}