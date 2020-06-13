package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 15:26:38
 */
public class UseUnitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//使用单位ID
	private Integer tId;
	//使用单位
	private String useUnit;
	//新增时间
	private Date addTime;
	//操作人员
	private String addName;

	/**
	 * 设置：使用单位ID
	 */
	public void setTId(Integer tId) {
		this.tId = tId;
	}
	/**
	 * 获取：使用单位ID
	 */
	public Integer getTId() {
		return tId;
	}
	/**
	 * 设置：使用单位
	 */
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	/**
	 * 获取：使用单位
	 */
	public String getUseUnit() {
		return useUnit;
	}
	/**
	 * 设置：新增时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：新增时间
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
