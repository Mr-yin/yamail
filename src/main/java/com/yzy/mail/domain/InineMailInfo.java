package com.yzy.mail.domain;

/**
 * 带有图片的实体封装
 */
public class InineMailInfo extends  BaseEmailInfo {
    private String srcpath;
    private String cid;

    public String getSrcpath() {
        return srcpath;
    }

    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }



}
