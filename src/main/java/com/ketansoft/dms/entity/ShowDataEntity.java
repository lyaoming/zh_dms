package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 17:20:48
 */
public class ShowDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer entryNum;
	//
	private Integer retreateNum;
	//
	private Integer warningNum;

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
	 * 设置：
	 */
	public void setEntryNum(Integer entryNum) {
		this.entryNum = entryNum;
	}
	/**
	 * 获取：
	 */
	public Integer getEntryNum() {
		return entryNum;
	}
	/**
	 * 设置：
	 */
	public void setRetreateNum(Integer retreateNum) {
		this.retreateNum = retreateNum;
	}
	/**
	 * 获取：
	 */
	public Integer getRetreateNum() {
		return retreateNum;
	}
	/**
	 * 设置：
	 */
	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}
	/**
	 * 获取：
	 */
	public Integer getWarningNum() {
		return warningNum;
	}
}
