package com.micro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.model.RolePo;
import com.micro.model.RoleResourcesPo;
import com.micro.service.RoleResourcesService;
import com.micro.service.RoleService;

/**
 * Created by GJ on 2018/9/5.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
	@Resource
	private RoleService roleService;
	@Resource
	private RoleResourcesService roleResourcesService;

	@RequestMapping
	public Map<String, Object> getAll(RolePo role, @RequestParam(required = false, defaultValue = "1") Integer start, @RequestParam(required = false, defaultValue = "10") Integer length) {

		Map<String, Object> map = new HashMap<>();
		PageInfo<RolePo> pageInfo = roleService.getRoleListWithPage(start, length);
		map.put("recordsTotal", pageInfo.getTotal());
		map.put("recordsFiltered", pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}

	@RequestMapping("/rolesWithSelected")
	public List<RolePo> rolesWithSelected(Integer uid) {
		return roleService.queryRoleListWithSelected(uid);
	}

	// 分配角色
	@RequestMapping("/saveRoleResources")
	public String saveRoleResources(RoleResourcesPo roleResources) {
		if (StringUtils.isEmpty(roleResources.getRoleid()))
			return "error";
		try {
			roleResourcesService.addRoleResources(roleResources);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "/add")
	public String add(RolePo role) {
		try {
			roleService.addRole(role);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@RequestMapping(value = "/delete")
	public String delete(Integer id) {
		try {
			roleService.deleteRoleById(id);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

}
