package com.ketansoft.dms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public class ExpensetableEntity {

    private Integer recordId;

    private Integer pId;



    //姓名
    @Excel(name="姓名")
    private String pName;
    //部室
    @Excel(name="部室")
    private String pDepartment;
    //宿舍地址（房号）
    @Excel(name="入住房屋")
    private String dorm;

    //年份
    @Excel(name="年份")
    private Integer  recordYear;

    //月份
    @Excel(name="月份")
    private Integer  recordMonth;

    private List costs;


    private String pCategroy;

    private String costItems;


    //登记入住时间
    @JSONField(format = "yyyy-MM-dd")
    private Date checkInTime;
    //预期到期时间
    @JSONField(format = "yyyy-MM-dd")
    private Date expectedDueTime;

    //退租时间
    @JSONField(format = "yyyy-MM-dd")
    private Date leaveTime;

    //ID
    public Integer getRecordId() {
        return recordId;
    }


    public void setRecordId(Integer recordId) {
        this.recordId =recordId;
    }
    /**
     * 设置：姓名
     */
    public void setPName(String pName) {
        this.pName = pName;
    }
    /**
     * 获取：姓名
     */
    public String getPName() {
        return pName;
    }
    /**
     * 设置：部室
     */
    public void setPDepartment(String pDepartment) {
        this.pDepartment = pDepartment;
    }
    /**
     * 获取：部室
     */
    public String getPDepartment() {
        return pDepartment;
    }
    /**
     * 设置：宿舍地址
     */
    public void setDorm(String dorm) {
        this.dorm = dorm;
    }
    /**
     * 获取：宿舍地址
     */
    public String getDorm() {
        return dorm;
    }

    //年份
    public Integer getRecordYear() {
        return recordYear;
    }
    public void setRecordYear(Integer recordYear) {
        this.recordYear = recordYear;
    }

    //月份
    public Integer getRecordMonth() {
        return  recordMonth;
    }

    public void setRecordMonth(Integer recordMonth) {
        this.recordMonth = recordMonth;
    }

    public List getCosts() {
        return costs;
    }

    public void setCosts(List costs) {
        this.costs = costs;
    }

    public String getpCategroy() {
        return pCategroy;
    }

    public void setpCategroy(String pCategroy) {
        this.pCategroy = pCategroy;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }


    /**
     * 设置：登记入住时间
     */
    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }
    /**
     * 获取：登记入住时间
     */
    public Date getCheckInTime() {
        return checkInTime;
    }
    /**
     * 设置：预期到期时间
     */
    public void setExpectedDueTime(Date expectedDueTime) {
        this.expectedDueTime = expectedDueTime;
    }
    /**
     * 获取：预期到期时间
     */
    public Date getExpectedDueTime() {
        return expectedDueTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getCostItems() {
        return costItems;
    }

    public void setCostItems(String costItems) {
        this.costItems = costItems;
    }


}
