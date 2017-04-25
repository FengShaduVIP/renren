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

import io.renren.entity.AccountSysEntity;
import io.renren.service.AccountSysService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author FengShadu
 * @email twpvip@gmail.com
 * @date 2017-04-25 21:49:38
 */
@Controller
@RequestMapping("accountsys")
public class AccountSysController {
	@Autowired
	private AccountSysService accountSysService;
	
	@RequestMapping("/accountsys.html")
	public String list(){
		return "accountsys/accountsys.html";
	}
	
	@RequestMapping("/accountsys_add.html")
	public String add(){
		return "accountsys/accountsys_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("accountsys:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<AccountSysEntity> accountSysList = accountSysService.queryList(map);
		int total = accountSysService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(accountSysList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("accountsys:info")
	public R info(@PathVariable("id") Integer id){
		AccountSysEntity accountSys = accountSysService.queryObject(id);
		
		return R.ok().put("accountSys", accountSys);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("accountsys:save")
	public R save(@RequestBody AccountSysEntity accountSys){
		accountSysService.save(accountSys);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("accountsys:update")
	public R update(@RequestBody AccountSysEntity accountSys){
		accountSysService.update(accountSys);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("accountsys:delete")
	public R delete(@RequestBody Integer[] ids){
		accountSysService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
