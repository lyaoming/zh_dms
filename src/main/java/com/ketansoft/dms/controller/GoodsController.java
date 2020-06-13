package com.ketansoft.dms.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.entity.GoodsEntity;
import com.ketansoft.dms.service.GoodsService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-18 19:58:44
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/goods.html")
	public String list(){
		return "goods/goods.html";
	}
	
	@RequestMapping("/goods_add.html")
	public String add(){
		return "goods/goods_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("goods:list")
	public R list( String gkeyword,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword",gkeyword);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<GoodsEntity> goodsList = goodsService.queryList(map);
		int total = goodsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(goodsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{gId}")
	@RequiresPermissions("goods:info")
	public R info(@PathVariable("gId") Integer gId){
		GoodsEntity goods = goodsService.queryObject(gId);
		
		return R.ok().put("goods", goods);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("goods:save")
	public R save(@RequestBody GoodsEntity goods){
		goodsService.save(goods);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("goods:update")
	public R update(@RequestBody GoodsEntity goods){
		goodsService.update(goods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("goods:delete")
	public R delete(@RequestBody Integer[] gIds){
		goodsService.deleteBatch(gIds);
		
		return R.ok();
	}
	
}
