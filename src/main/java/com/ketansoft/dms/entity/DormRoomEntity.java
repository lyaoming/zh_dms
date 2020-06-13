package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-30 13:44:30
 */
public class DormRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer rId;
	//
	private Integer parentId;
	//
	private String roomName;
	//
	private Integer pId;
	//
	private Double roomAera;


	private int status;

	/**
	 * 设置：
	 */
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	/**
	 * 获取：
	 */
	public Integer getrId() {
		return rId;
	}
	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * 获取：
	 */
	public String getRoomName() {
		return roomName;
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
	public void setRoomAera(Double roomAera) {
		this.roomAera = roomAera;
	}
	/**
	 * 获取：
	 */
	public Double getRoomAera() {
		return roomAera;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
