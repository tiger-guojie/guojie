package com.micro.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.micro.model.RolePo;

/**
 * Created by GJ on 2018/9/4.
 */
public interface RoleService {
	/**
	  * 添加
     * @param Role
     * @return
     */
	public int addRole(RolePo role);
    /**
      * 通过主键获取
     * @param id
     * @return
     */
	public RolePo getRoleById(Integer id);
    /**
       * 修改
     * @param Role
     * @return
     */
	public int updateRole(RolePo role);
    /**
     * 删除
     * @param id
     * @return
     */
	public int deleteRoleById(Integer id);
	
	/**
        * 分页-list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<RolePo> getRoleListWithPage( Integer start, Integer length);
    
    public List<RolePo> queryRoleListWithSelected(Integer uid);
    

}
