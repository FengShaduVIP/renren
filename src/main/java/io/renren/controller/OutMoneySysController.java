package io.renren.controller;

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

import io.renren.entity.OutMoneySysEntity;
import io.renren.service.OutMoneySysService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author FengShadu
 * @email twpvip@gmail.com
 * @date 
 */
@Controller
@RequestMapping("outmoneysys")
public class OutMoneySysController {
	@Autowired
	private OutMoneySysService outMoneySysService;
	
	@RequestMapping("/outmoneysys.html")
	public String list(){
		return "outmoneysys/outmoneysys.html";
	}
	
	@RequestMapping("/outmoneysys_add.html")
	public String add(){
		return "outmoneysys/outmoneysys_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("outmoneysys:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<OutMoneySysEntity> outMoneySysList = outMoneySysService.queryList(map);
		int total = outMoneySysService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(outMoneySysList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("outmoneysys:info")
	public R info(@PathVariable("id") Integer id){
		OutMoneySysEntity outMoneySys = outMoneySysService.queryObject(id);
		
		return R.ok().put("outMoneySys", outMoneySys);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("outmoneysys:save")
	public R save(@RequestBody OutMoneySysEntity outMoneySys){
		outMoneySysService.save(outMoneySys);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("outmoneysys:update")
	public R update(@RequestBody OutMoneySysEntity outMoneySys){
		outMoneySysService.update(outMoneySys);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("outmoneysys:delete")
	public R delete(@RequestBody Integer[] ids){
		outMoneySysService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
