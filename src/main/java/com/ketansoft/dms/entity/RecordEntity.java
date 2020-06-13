package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 17:27:52
 */
public class RecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer recordId;
	//
	private Integer pId;
	//
	private String pName;
	//
	private String dorm;
	//
	private String pCategroy;
	//
	private String pDepartment;
	//
	private String costItems;
	//
	private int recordMonth;
	//
	private int recordYear;

	private List costs;

	private int status;



	/**
	 * 设置：
	 */
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取：
	 */
	public Integer getRecordId() {
		return recordId;
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
	public void setPName(String pName) {
		this.pName = pName;
	}
	/**
	 * 获取：
	 */
	public String getPName() {
		return pName;
	}
	/**
	 * 设置：
	 */
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	/**
	 * 获取：
	 */
	public String getDorm() {
		return dorm;
	}
	/**
	 * 设置：
	 */
	public void setPCategroy(String pCategroy) {
		this.pCategroy = pCategroy;
	}
	/**
	 * 获取：
	 */
	public String getPCategroy() {
		return pCategroy;
	}
	/**
	 * 设置：
	 */
	public void setPDepartment(String pDepartment) {
		this.pDepartment = pDepartment;
	}
	/**
	 * 获取：
	 */
	public String getPDepartment() {
		return pDepartment;
	}
	/**
	 * 设置：
	 */
	public void setCostItems(String costItems) {
		this.costItems = costItems;
	}
	/**
	 * 获取：
	 */
	public String getCostItems() {
		return costItems;
	}
	/**
	 * 设置：
	 */
	public void setRecordMonth(int recordMonth) {
		this.recordMonth = recordMonth;
	}
	/**
	 * 获取：
	 */
	public int getRecordMonth() {
		return recordMonth;
	}
	/**
	 * 设置：
	 */
	public void setRecordYear(int recordYear) {
		this.recordYear = recordYear;
	}
	/**
	 * 获取：
	 */
	public int getRecordYear() {
		return recordYear;
	}

	public List getCosts() {
		return costs;
	}

	public void setCosts(List costs) {
		this.costs = costs;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
