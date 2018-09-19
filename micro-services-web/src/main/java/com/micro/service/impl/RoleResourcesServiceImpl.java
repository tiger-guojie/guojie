package com.micro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.micro.mapper.RoleResourcesPoMapper;
import com.micro.model.RoleResourcesPo;
import com.micro.model.RoleResourcesPoExample;
import com.micro.model.RoleResourcesPoExample.Criteria;
import com.micro.service.RoleResourcesService;

/**
 * @author GJ
 * @since 2018-09-04
 **/
@Service
public class RoleResourcesServiceImpl implements RoleResourcesService{

    @Autowired
    private RoleResourcesPoMapper roleResourcesPoMapper;

	@Override
	public void addRoleResources(RoleResourcesPo roleResources) {
		 //删除
		RoleResourcesPoExample roleResourcesPoExample =new RoleResourcesPoExample();
		Criteria criteria = roleResourcesPoExample.createCriteria();
        criteria.andRoleidEqualTo(roleResources.getRoleid());
        roleResourcesPoMapper.deleteByExample(roleResourcesPoExample);
        //添加
        if(!StringUtils.isEmpty(roleResources.getResourcesid())){
            String[] resourcesArr = roleResources.getResourcesid().split(",");
            for(String resourcesId:resourcesArr ){
            	RoleResourcesPo r = new RoleResourcesPo();
                r.setRoleid(roleResources.getRoleid());
                r.setResourcesid(resourcesId);
                roleResourcesPoMapper.insert(r);
            }
        }
	}
	
}
