package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 13:40:46
 */
public class DormEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//宿舍ID
	private Integer dId;
	//使用权ID
	private Integer uId;

	//使用单位
	private Integer tId;

	//
	private Integer parentId;

	//上级地址
	private String dSuperiorAddress;


	//宿舍地址（房号）
	private String dAddress;

	//面积
	private Double dArea;
	//规格（1、 2房一厅 2、3房一厅）
	private Integer dSpecification;
	//类型（1、合并 0、拆分）
	private Integer dType;
	//更改前的类型
	private Integer bfdType;

	//
	private Integer dNum;
	//
	private Integer dAllnum;


	private Integer orderNum;

	//姓名
	private String pName;
	//部室
	private String pDepartment;
	//性别（1、男 2、女）
	private Integer pSex;

	private String pPhone;
	//人员类型（1、交流干部 2、借调人员 3、新员工）
	private Integer pCategroy;
	//使用单位
	private String useUnit;

	//使用权
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
	/*
	 *获取：使用权ID
	 */
	public Integer getuId() {
		return uId;
	}
	/*
	 *设置：使用权ID
	 */
	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
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
	 * 设置：面积
	 */
	public void setDArea(Double dArea) {
		this.dArea = dArea;
	}
	/**
	 * 获取：面积
	 */
	public Double getDArea() {
		return dArea;
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

	public Integer getBfdType() {
		return bfdType;
	}

	public void setBfdType(Integer bfdType) {
		this.bfdType = bfdType;
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
	/**
	 * 设置：上级地址
	 */
	public String getdSuperiorAddress() {
		return dSuperiorAddress;
	}
	/**
	 * 获取：上级地址
	 */
	public void setdSuperiorAddress(String dSuperiorAddress) {
		this.dSuperiorAddress = dSuperiorAddress;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
	 * 设置：性别（1、男 2、女）
	 */
	public void setPSex(Integer pSex) {
		this.pSex = pSex;
	}
	/**
	 * 获取：性别（1、男 2、女）
	 */
	public Integer getPSex() {
		return pSex;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPPhone(String pPhone) {
		this.pPhone = pPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPPhone() {
		return pPhone;
	}
	/**
	 * 设置：人员类型（1、交流干部 2、借调人员 3、新员工）
	 */
	public void setPCategroy(Integer pCategroy) {
		this.pCategroy = pCategroy;
	}
	/**
	 * 获取：人员类型（1、交流干部 2、借调人员 3、新员工）
	 */
	public Integer getPCategroy() {
		return pCategroy;
	}



	public String getUseAdmin() {
		return useAdmin;
	}

	public void setUseAdmin(String useAdmin) {
		this.useAdmin = useAdmin;
	}

	public String getUseUnit() {
		return useUnit;
	}

	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}

}
