package com.micro.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.micro.model.ResourcesPo;

/**
 * Created by GJ on 2018/9/4.
 */
public interface ResourcesService {
	/**
	  * 添加
     * @param Resources
     * @return
     */
	public int addResources(ResourcesPo resources);
    /**
      * 通过主键获取
     * @param id
     * @return
     */
	public ResourcesPo getResourcesById(Integer id);
    /**
       * 修改
     * @param Resources
     * @return
     */
	public int updateResources(ResourcesPo resources);
    /**
     * 删除
     * @param id
     * @return
     */
	public int deleteResourcesById(Integer id);
	
	/**
        * 分页-list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ResourcesPo> getResourcesListWithPage( Integer start, Integer length);
    
    public List<ResourcesPo> loadUserResources(Map<String,Object> map);
    
    public Map<String,Object> loadUserTreeResources(Map<String,Object> map);

    public List<ResourcesPo> queryResourcesListWithSelected(Integer rid);
	
    public List<ResourcesPo> queryAll();
}
