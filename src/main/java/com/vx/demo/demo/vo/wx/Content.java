package com.vx.demo.demo.vo.wx;

/**
 * 模板单个属性封装
 */
public class Content {

    //消息内容
    private String value;

    //消息字体颜色
    private String color;

    public Content() {
    }

    public Content(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
