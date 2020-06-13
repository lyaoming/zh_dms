package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 09:25:50
 */
public class ClassionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//人员类型
	private String classion;
	//添加时间
	private Date addTime;
	//操作人员
	private String addName;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：人员类型
	 */
	public void setClassion(String classion) {
		this.classion = classion;
	}
	/**
	 * 获取：人员类型
	 */
	public String getClassion() {
		return classion;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：操作人员
	 */
	public void setAddName(String addName) {
		this.addName = addName;
	}
	/**
	 * 获取：操作人员
	 */
	public String getAddName() {
		return addName;
	}
}
