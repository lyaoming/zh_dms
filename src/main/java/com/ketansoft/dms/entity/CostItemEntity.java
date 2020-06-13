package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 19:49:39
 */
public class CostItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer cId;
	//收费项
	private String cName;

	private Double value;



	/**
	 * 设置：ID
	 */
	public void setCId(Integer cId) {
		this.cId = cId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getCId() {
		return cId;
	}
	/**
	 * 设置：收费项
	 */
	public void setCName(String cName) {
		this.cName = cName;
	}
	/**
	 * 获取：收费项
	 */
	public String getCName() {
		return cName;
	}
	/**
	 * 设置：添加时间
	 */

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
