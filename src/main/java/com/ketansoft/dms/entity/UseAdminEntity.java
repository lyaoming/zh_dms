package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 09:48:57
 */
public class UseAdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer uId;
	//
	private String useAdmin;
	//
	private Date addTime;
	//
	private String addName;

	/**
	 * 设置：
	 */
	public void setUId(Integer uId) {
		this.uId = uId;
	}
	/**
	 * 获取：
	 */
	public Integer getUId() {
		return uId;
	}
	/**
	 * 设置：
	 */
	public void setUseAdmin(String useAdmin) {
		this.useAdmin = useAdmin;
	}
	/**
	 * 获取：
	 */
	public String getUseAdmin() {
		return useAdmin;
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
