package com.vx.demo.demo.vo.wx;

import java.util.Map;

/**
 * @author TJ2020-6
 * @desc
 * @createTime 2020-04-10-15:07
 */
public class TemplateVo {
    private String templateId;// 模板id
    private String title;    //模板标题
    private String primaryIndustry;//	模板所属行业的一级行业
    private String deputyIndustry;//	模板所属行业的二级行业
    private String content;//   模板内容
    private String example;//	模板示例

    public TemplateVo() {
    }

    public TemplateVo(Map<String, String> map) {
        this.deputyIndustry = map.get("deputy_industry");
        this.primaryIndustry = map.get("primary_industry");
        this.content = map.get("content");
        this.example = map.get("example");
        this.title = map.get("title");
    }

    @Override
    public String toString() {
        return "TemplateVo{" +
                "templateId='" + templateId + '\'' +
                ", title='" + title + '\'' +
                ", primaryIndustry='" + primaryIndustry + '\'' +
                ", deputyIndustry='" + deputyIndustry + '\'' +
                ", content='" + content + '\'' +
                ", example='" + example + '\'' +
                '}';
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryIndustry() {
        return primaryIndustry;
    }

    public void setPrimaryIndustry(String primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    public String getDeputyIndustry() {
        return deputyIndustry;
    }

    public void setDeputyIndustry(String deputyIndustry) {
        this.deputyIndustry = deputyIndustry;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
