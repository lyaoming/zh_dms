package com.ketansoft.dms.entity;

public class Query {
    //房屋名称
    private String dorm;

    //是否交押金
    private Integer deposit;

    //人员名字
    private String pName;

    //入住时间
    private String sbgTime;

    //截止时间
    private String sendTime;

    private Integer limit;

    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getSbgTime() {
        return sbgTime;
    }

    public void setSbgTime(String sbgTime) {
        this.sbgTime = sbgTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }


}
