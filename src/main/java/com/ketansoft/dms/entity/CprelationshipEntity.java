package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-28 09:04:22
 */
public class CprelationshipEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer flag;

	private Integer rId;
	//
	private Integer pId;
	//
	private Integer cId;
	//
	private Double value;

	private String cName;

	private Integer status;


	@JSONField(format = "yyyy-MM-dd")
	private Date bgTime;


	@JSONField(format = "yyyy-MM-dd")
	private Date endTime;


	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 设置：
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：
	 */
	public Integer getPId() {
		return pId;
	}
	/**
	 * 设置：
	 */
	public void setCId(Integer cId) {
		this.cId = cId;
	}
	/**
	 * 获取：
	 */
	public Integer getCId() {
		return cId;
	}
	/**
	 * 设置：
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	/**
	 * 获取：
	 */
	public Double getValue() {
		return value;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBgTime() {
		return bgTime;
	}

	public void setBgTime(Date bgTime) {
		this.bgTime = bgTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


}
