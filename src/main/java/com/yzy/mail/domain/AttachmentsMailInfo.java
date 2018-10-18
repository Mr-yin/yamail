package com.yzy.mail.domain;

/**
 * 带有附件的实体封装
 */
public class AttachmentsMailInfo extends  BaseEmailInfo {
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


}
