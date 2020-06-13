package com.ketansoft.dms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public class DormtableEntity {
    private Integer dId;
@Excel(name="宿舍号")
    private String dAddress;
@Excel(name="宿舍类型")
    private Integer dType;
@Excel(name="宿舍规格")
    private Integer dSpecification;
@Excel(name="现住人数")
    private Integer dNum;
@Excel(name="可住人数")
    private Integer dAllnum;
@Excel(name ="空房数")
    private Integer dNull;

    @Excel(name="入住率")
    private Double occupancyRate;

    @Excel(name="使用部门")
    private String useUnit;

    @Excel(name="使用权管理")
    private String useAdmin;



    /**
     * 设置：宿舍ID
     */
    public void setDId(Integer dId) {
        this.dId = dId;
    }
    /**
     * 获取：宿舍ID
     */
    public Integer getDId() {
        return dId;
    }
    /**
     * 设置：宿舍地址
     */
    public void setDAddress(String dAddress) {
        this.dAddress = dAddress;
    }
    /**
     * 获取：宿舍地址
     */
    public String getDAddress() {
        return dAddress;
    }
    /**
     * 设置：类型（1、合并 0、拆分）
     */
    public void setDType(Integer dType) {
        this.dType = dType;
    }
    /**
     * 获取：类型（1、合并 0、拆分）
     */
    public Integer getDType() {
        return dType;
    }
    /**
     * 设置：规格（1、 2房一厅 2、3房一厅）
     */
    public void setDSpecification(Integer dSpecification) {
        this.dSpecification = dSpecification;
    }
    /**
     * 获取：规格（1、 2房一厅 2、3房一厅）
     */
    public Integer getDSpecification() {
        return dSpecification;
    }
    /**
     * 设置：现住户数
     */
    public void setDNum(Integer dNum) {
        this.dNum = dNum;
    }
    /**
     * 获取：现住户数
     */
    public Integer getDNum() {
        return dNum;
    }
    /**
     * 设置：总住户数
     */
    public void setDAllnum(Integer dAllnum) {
        this.dAllnum = dAllnum;
    }
    /**
     * 获取：总住户数
     */
    public Integer getDAllnum() {
        return dAllnum;
    }

    public Integer getdNull() {
        return dNull;
    }

    public void setdNull(Integer dNull) {
        this.dNull = dNull;
    }

    public Double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(Double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public String getUseUnit() {
        return useUnit;
    }

    public void setUseUnit(String useUnit) {
        this.useUnit = useUnit;
    }

    public String getUseAdmin() {
        return useAdmin;
    }

    public void setUseAdmin(String useAdmin) {
        this.useAdmin = useAdmin;
    }
}
