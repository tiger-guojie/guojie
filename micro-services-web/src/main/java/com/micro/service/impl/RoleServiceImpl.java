package com.micro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.mapper.RolePoMapper;
import com.micro.model.RolePo;
import com.micro.model.RolePoExample;
import com.micro.service.RoleService;

/**
 * @author GJ
 * @since 2018-09-04
 **/
@Service
public class RoleServiceImpl implements RoleService{

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RolePoMapper rolePoMapper;

	@Override
	public int addRole(RolePo role) {
		return rolePoMapper.insert(role);
	}

	@Override
	public RolePo getRoleById(Integer id) {
		return rolePoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateRole(RolePo role) {
		return rolePoMapper.updateByPrimaryKey(role);
	}

	@Override
	public int deleteRoleById(Integer id) {
		return rolePoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<RolePo> getRoleListWithPage(Integer start, Integer length) {
		Integer page = start/length+1;
		PageHelper.startPage(page, length);
		RolePoExample query=new RolePoExample();
        try{
            List<RolePo> list=rolePoMapper.selectByExample(query);
            return new PageInfo<RolePo>(list);
        }
        catch (Exception e) {
            logger.error("RoleServiceImpl.getUserListWithPage error",e);
        }
        return null;
	}

	@Override
	public List<RolePo> queryRoleListWithSelected(Integer uid) {
		return rolePoMapper.queryRoleListWithSelected(uid);
	}
   
}
