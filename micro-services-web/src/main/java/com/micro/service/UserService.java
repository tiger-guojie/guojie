package com.micro.service;

import com.github.pagehelper.PageInfo;
import com.micro.model.UsersPo;

/**
 * Created by GJ on 2018/9/4.
 */
public interface UserService {
	/**
	  * 添加
     * @param user
     * @return
     */
	public int addUser(UsersPo user);
    /**
      * 通过主键获取
     * @param id
     * @return
     */
	public UsersPo getUserById(Integer id);
    /**
       * 修改
     * @param user
     * @return
     */
	public int updateUser(UsersPo user);
    /**
     * 删除
     * @param id
     * @return
     */
	public int deleteUserById(Integer id);
	
	/**
        * 分页-list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<UsersPo> getUserListWithPage( Integer start, Integer length);
    
    public UsersPo selectByUsername(String username);
	

}
