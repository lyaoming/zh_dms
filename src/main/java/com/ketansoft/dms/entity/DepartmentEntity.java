package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 19:08:07
 */
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer dpmId;
	//
	private String dpmName;
	//
	private Date addTime;
	//
	private String addName;

	/**
	 * 设置：
	 */
	public void setDpmId(Integer dpmId) {
		this.dpmId = dpmId;
	}
	/**
	 * 获取：
	 */
	public Integer getDpmId() {
		return dpmId;
	}
	/**
	 * 设置：
	 */
	public void setDpmName(String dpmName) {
		this.dpmName = dpmName;
	}
	/**
	 * 获取：
	 */
	public String getDpmName() {
		return dpmName;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：
	 */
	public void setAddName(String addName) {
		this.addName = addName;
	}
	/**
	 * 获取：
	 */
	public String getAddName() {
		return addName;
	}
}
