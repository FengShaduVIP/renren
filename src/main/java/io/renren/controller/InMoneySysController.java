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

import io.renren.entity.InMoneySysEntity;
import io.renren.service.InMoneySysService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author FengShadu
 * @email twpvip@gmail.com
 * @date 2017-04-25 21:39:41
 */
@Controller
@RequestMapping("inmoneysys")
public class InMoneySysController {
	@Autowired
	private InMoneySysService inMoneySysService;
	
	@RequestMapping("/inmoneysys.html")
	public String list(){
		return "inmoneysys/inmoneysys.html";
	}
	
	@RequestMapping("/inmoneysys_add.html")
	public String add(){
		return "inmoneysys/inmoneysys_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("inmoneysys:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<InMoneySysEntity> inMoneySysList = inMoneySysService.queryList(map);
		int total = inMoneySysService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(inMoneySysList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("inmoneysys:info")
	public R info(@PathVariable("id") Integer id){
		InMoneySysEntity inMoneySys = inMoneySysService.queryObject(id);
		
		return R.ok().put("inMoneySys", inMoneySys);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("inmoneysys:save")
	public R save(@RequestBody InMoneySysEntity inMoneySys){
		inMoneySysService.save(inMoneySys);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("inmoneysys:update")
	public R update(@RequestBody InMoneySysEntity inMoneySys){
		inMoneySysService.update(inMoneySys);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("inmoneysys:delete")
	public R delete(@RequestBody Integer[] ids){
		inMoneySysService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
