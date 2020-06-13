package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 10:08:23
 */
public class PersonnelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//人员ID
	private Integer pId;
	//姓名
	private String pName;
	//部室
	private String pDepartment;
	//性别（1、男 2、女）
	private Integer pSex;
	//入行时间
	@JSONField(format = "yyyy-MM-dd")
	private Date initiationTime;
	//联系电话
	private String pPhone;
	//人员类型（1、交流干部 2、借调人员 3、新员工）
	private Integer pCategroy;
	//身份证号码
	private String pNumber;

	private  String roomName;

	private String classion;


	/**
	 * 设置：人员ID
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：人员ID
	 */
	public Integer getPId() {
		return pId;
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
	 * 设置：入行时间
	 */
	public void setInitiationTime(Date initiationTime) {
		this.initiationTime = initiationTime;
	}
	/**
	 * 获取：入行时间
	 */
	public Date getInitiationTime() {
		return initiationTime;
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
	/**
	 * 设置：身份证号码
	 */
	public void setPNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getPNumber() {
		return pNumber;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getClassion() {
		return classion;
	}

	public void setClassion(String classion) {
		this.classion = classion;
	}
}
