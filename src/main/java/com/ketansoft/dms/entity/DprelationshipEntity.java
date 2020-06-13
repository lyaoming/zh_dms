package com.ketansoft.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;


import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-19 16:41:52
 */
public class DprelationshipEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//入住关系ID
	private Integer pdId;
	//人员ID
	private Integer pId;
	//宿舍ID
	private Integer dId;
	//房间Id
	private Integer rId;

	//房间名
	private String roomName;

	//收费项
	private Integer[] costs;

	//登记入住时间
	@JSONField(format = "yyyy-MM-dd")
	private Date checkInTime;
	//预期到期时间
	@JSONField(format = "yyyy-MM-dd")
	private Date expectedDueTime;
	//是否已交押金
	private Integer deposit;
	//金额
	private Double depositMoney;
	//入住状态（1、在住 2、退租）
	private Integer status;

	private String pName;

	private String cName;

	private String dAddress;

	//判断所选的是不是宿舍号
	private Integer flag;


	//人员宿舍调动更改原来宿舍人数
	private Integer beforeDid;

	//信息修改宿舍人员更换
	private Integer bfpId;

	//修改房间
	private Integer bfrId;


	//退租
	private Integer rentreate;

	//退租时间
	@JSONField(format = "yyyy-MM-dd")
	private Date leaveTime;

	private List<String >costList;


	/**
	 * 设置：入住关系ID
	 */
	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}
	/**
	 * 获取：入住关系ID
	 */
	public Integer getPdId() {
		return pdId;
	}
	/**
	 * 设置：人员ID
	 */
	public void setPId(Integer pId) {
		this.pId = pId;
	}
	/**
	 * 获取：人员ID
	 */
	public Integer getPId() {
		return pId;
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

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}
	/**
	 * 设置：收费类型
	 */
	public void setCosts(Integer[] costs) {
		this.costs = costs;
	}
	/**
	 * 获取：收费类型
	 */
	public Integer[] getCosts() {
		return costs;
	}
	/**
	 * 设置：登记入住时间
	 */
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	/**
	 * 获取：登记入住时间
	 */
	public Date getCheckInTime() {
		return checkInTime;
	}
	/**
	 * 设置：预期到期时间
	 */
	public void setExpectedDueTime(Date expectedDueTime) {
		this.expectedDueTime = expectedDueTime;
	}
	/**
	 * 获取：预期到期时间
	 */
	public Date getExpectedDueTime() {
		return expectedDueTime;
	}
	/**
	 * 设置：是否已交押金
	 */
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	/**
	 * 获取：是否已交押金
	 */
	public Integer getDeposit() {
		return deposit;
	}
	/**
	 * 设置：金额
	 */
	public void setDepositMoney(Double depositMoney) {
		this.depositMoney = depositMoney;
	}
	/**
	 * 获取：金额
	 */
	public Double getDepositMoney() {
		return depositMoney;
	}
	/**
	 * 设置：入住状态（1、在住 2、退租）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：入住状态（1、在住 2、退租）
	 */
	public Integer getStatus() {
		return status;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getBeforeDid() {
		return beforeDid;
	}

	public void setBeforeDid(Integer beforeDid) {
		this.beforeDid = beforeDid;
	}


	public Integer getRentreate() {
		return rentreate;
	}

	public void setRentreate(Integer rentreate) {
		this.rentreate = rentreate;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Integer getBfpId() {
		return bfpId;
	}

	public void setBfpId(Integer bfpId) {
		this.bfpId = bfpId;
	}

	public Integer getBfrId() {
		return bfrId;
	}

	public void setBfrId(Integer bfrId) {
		this.bfrId = bfrId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<String> getCostList() {
		return costList;
	}

	public void setCostList(List<String> costList) {
		this.costList = costList;
	}

}
