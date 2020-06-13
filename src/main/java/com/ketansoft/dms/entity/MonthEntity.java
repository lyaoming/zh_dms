package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 13:36:25
 */
public class MonthEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer mId;

	private int yName;

	//
	private int mName;
	//

	private String mBgtime;
	//

	private String mEndtime;

	/**
	 * 设置：
	 */
	public void setMId(Integer mId) {
		this.mId = mId;
	}
	/**
	 * 获取：
	 */
	public Integer getMId() {
		return mId;
	}


	public int getYName() {
		return yName;
	}

	public void setYName(int yName) {
		this.yName = yName;
	}
	/**
	 * 设置：
	 */
	public void setMName(int mName) {
		this.mName = mName;
	}
	/**
	 * 获取：
	 */
	public int getMName() {
		return mName;
	}
	/**
	 * 设置：
	 */
	public void setMBgtime(String mBgtime) {
		this.mBgtime = mBgtime;
	}
	/**
	 * 获取：
	 */
	public String getMBgtime() {
		return mBgtime;
	}
	/**
	 * 设置：
	 */
	public void setMEndtime(String mEndtime) {
		this.mEndtime = mEndtime;
	}
	/**
	 * 获取：
	 */
	public String getMEndtime() {
		return mEndtime;
	}
}
