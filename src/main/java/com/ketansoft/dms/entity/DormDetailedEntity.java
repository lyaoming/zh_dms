package com.ketansoft.dms.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.List;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public class DormDetailedEntity {
    private Integer dId;

    private Integer rId;

    private Integer pId;

    @Excel(name="房屋号")
    private String dAddress;

    @Excel(name="房屋类型")
    private Integer dType;

    @Excel(name="使用单位")
    private String useUnit;

    @Excel(name="使用权管理")
    private String useAdmin;

    @Excel(name="房间")
    private String roomName;

    @Excel(name="是否入住")
    private int yesNo;

    @Excel(name="姓名")
    private String pName;

    @Excel(name="人员类型")
    private String pCategroy;


    @Excel(name="部室")
    private String pDepartment;

    @Excel(name="性别")
    private Integer pSex;

    @Excel(name="手机")
    private String pPhone;

    @Excel(name="房屋配置")
    private List<DgrelationshipEntity> home;



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

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public int getYesNo() {
        return yesNo;
    }

    public void setYesNo(int yesNo) {
        this.yesNo = yesNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpCategroy() {
        return pCategroy;
    }

    public void setpCategroy(String pCategroy) {
        this.pCategroy = pCategroy;
    }


    public String getpDepartment() {
        return pDepartment;
    }

    public void setpDepartment(String pDepartment) {
        this.pDepartment = pDepartment;
    }

    public Integer getpSex() {
        return pSex;
    }

    public void setpSex(Integer pSex) {
        this.pSex = pSex;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public List<DgrelationshipEntity> getHome() {
        return home;
    }

    public void setHome(List<DgrelationshipEntity> home) {
        this.home = home;
    }
}
