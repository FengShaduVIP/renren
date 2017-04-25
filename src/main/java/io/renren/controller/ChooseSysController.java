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

import io.renren.entity.ChooseSysEntity;
import io.renren.service.ChooseSysService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author FengShadu
 * @email twpvip@gmail.com
 * @date 2017-04-25 22:17:04
 */
@Controller
@RequestMapping("choosesys")
public class ChooseSysController {
	@Autowired
	private ChooseSysService chooseSysService;
	
	@RequestMapping("/choosesys.html")
	public String list(){
		return "choosesys/choosesys.html";
	}
	
	@RequestMapping("/choosesys_add.html")
	public String add(){
		return "choosesys/choosesys_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("choosesys:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ChooseSysEntity> chooseSysList = chooseSysService.queryList(map);
		int total = chooseSysService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(chooseSysList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("choosesys:info")
	public R info(@PathVariable("id") Integer id){
		ChooseSysEntity chooseSys = chooseSysService.queryObject(id);
		
		return R.ok().put("chooseSys", chooseSys);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("choosesys:save")
	public R save(@RequestBody ChooseSysEntity chooseSys){
		chooseSysService.save(chooseSys);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("choosesys:update")
	public R update(@RequestBody ChooseSysEntity chooseSys){
		chooseSysService.update(chooseSys);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("choosesys:delete")
	public R delete(@RequestBody Integer[] ids){
		chooseSysService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
