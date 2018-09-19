package com.micro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.model.UserRolePo;
import com.micro.model.UsersPo;
import com.micro.service.UserRoleService;
import com.micro.service.UserService;
import com.micro.util.PasswordHelper;

/**
 * Created by GJ on 2018/9/4.
 */
@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping
    public Map<String,Object> getAllUser(UsersPo user,
                                     @RequestParam(required = false, defaultValue = "1") Integer start,
                                     @RequestParam(required = false, defaultValue = "10") Integer length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<UsersPo> pageInfo = userService.getUserListWithPage(start, length);
        logger.info("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    /**
     * 保存用户角色
     * @param userRole 用户角色
     *  	  此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRolePo userRole){
        if(StringUtils.isEmpty(userRole.getUserid()))
            return "error";
        try {
            userRoleService.addUserRole(userRole);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(UsersPo user) {
    	UsersPo u = userService.selectByUsername(user.getUsername());
        if(u != null)
            return "error";
        try {
            user.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userService.addUser(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
      try{
          userService.deleteUserById(id);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

}
