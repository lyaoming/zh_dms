package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-22 16:38:25
 */
public class DgrelationshipEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer gdId;
	//宿舍ID
	private Integer dId;
	//物品ID
	private Integer gId;

	private Integer[] gIds;



	private String dAddress;

	private String gName;

	private Integer dSpecification;

	private String gSpecification;

	private String gType;

	private Long gNumber;

	private Integer gdNumber;


	private Integer rId;

	private String roomName;



	/**
	 * 设置：
	 */
	public void setGdId(Integer gdId) {
		this.gdId = gdId;
	}
	/**
	 * 获取：
	 */
	public Integer getGdId() {
		return gdId;
	}
	/**
	 * 设置：宿舍ID
	 */
	public void setDId(Integer dId) {
		this.dId = dId;
	}
	/**
	 * 获取：宿舍ID
	 */
	public Integer getDId() {
		return dId;
	}
	/**
	 * 设置：物品ID
	 */
	public void setGId(Integer gId) {
		this.gId = gId;
	}
	/**
	 * 获取：物品ID
	 */
	public Integer getGId() {
		return gId;
	}

	public Integer[] getgIds() {
		return gIds;
	}

	public void setgIds(Integer[] gIds) {
		this.gIds = gIds;
	}

	//宿舍地址
	public String getDAddress() {
		return dAddress;
	}

	public void setDAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	//物品名称
	public String getGName() {
		return gName;
	}

	public void setGName(String gName) {
		this.gName = gName;
	}

	//宿舍规格
	public Integer getDSpecification() {
		return dSpecification;
	}

	public void setDSpecification(Integer dTSpecification) {
		this.dSpecification = dTSpecification;
	}

	//物品规格
	public String getGSpecification() {
		return gSpecification;
	}

	public void setGSpecification(String gSpecification) {
		this.gSpecification = gSpecification;
	}

	//物品类型
	public String getGType() {
		return gType;
	}

	public void setGType(String gType) {
		this.gType = gType;
	}

	//物品编号
	public Long getgNumber() {
		return gNumber;
	}

	public void setgNumber(Long gNumber) {
		this.gNumber = gNumber;
	}

	public Integer getGdNumber() {
		return gdNumber;
	}

	public void setGdNumber(Integer gdNumber) {
		this.gdNumber = gdNumber;
	}

    //房间Id
	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}
	//房间名
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


}
