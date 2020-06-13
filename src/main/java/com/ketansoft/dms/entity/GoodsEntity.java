package com.ketansoft.dms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-18 19:58:44
 */
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//物品ID
	private Integer gId;
	//物品名称
	private String gName;
	//物品规格
	private String gSpecification;
	//物品类型
	private String gType;
	//物品数量
	private Integer gNum;
	//
	private Long gNumber;

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
	/**
	 * 设置：物品名称
	 */
	public void setGName(String gName) {
		this.gName = gName;
	}
	/**
	 * 获取：物品名称
	 */
	public String getGName() {
		return gName;
	}
	/**
	 * 设置：物品规格
	 */
	public void setGSpecification(String gSpecification) {
		this.gSpecification = gSpecification;
	}
	/**
	 * 获取：物品规格
	 */
	public String getGSpecification() {
		return gSpecification;
	}
	/**
	 * 设置：物品类型
	 */
	public void setGType(String gType) {
		this.gType = gType;
	}
	/**
	 * 获取：物品类型
	 */
	public String getGType() {
		return gType;
	}
	/**
	 * 设置：物品数量
	 */
	public void setGNum(Integer gNum) {
		this.gNum = gNum;
	}
	/**
	 * 获取：物品数量
	 */
	public Integer getGNum() {
		return gNum;
	}
	/**
	 * 设置：
	 */
	public void setGNumber(Long gNumber) {
		this.gNumber = gNumber;
	}
	/**
	 * 获取：
	 */
	public Long getGNumber() {
		return gNumber;
	}
}
