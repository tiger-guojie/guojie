package com.micro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micro.mapper.UserRolePoMapper;
import com.micro.model.UserRolePo;
import com.micro.model.UserRolePoExample;
import com.micro.model.UserRolePoExample.Criteria;
import com.micro.service.UserRoleService;

/**
 * Created by GJ on 2018/9/6.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
    private UserRolePoMapper userRolePoMapper;
    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addUserRole(UserRolePo userRole) {
        //删除
    	UserRolePoExample userRolePoExample=new UserRolePoExample();
    	Criteria criteria = userRolePoExample.createCriteria();
        criteria.andUseridEqualTo(userRole.getUserid());
        userRolePoMapper.deleteByExample(userRolePoExample);
        //添加
        String[] roleids = userRole.getRoleid().split(",");
        for (String roleId : roleids) {
        	UserRolePo u = new UserRolePo();
            u.setUserid(userRole.getUserid());
            u.setRoleid(roleId);
            userRolePoMapper.insert(u);
        }

    }
}
