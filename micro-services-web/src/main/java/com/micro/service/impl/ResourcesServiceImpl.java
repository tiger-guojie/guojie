package com.micro.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.mapper.ResourcesPoMapper;
import com.micro.model.ResourcesPo;
import com.micro.model.ResourcesPoExample;
import com.micro.service.ResourcesService;

/**
 * @author GJ
 * @since 2018-09-04
 **/
@Service
public class ResourcesServiceImpl implements ResourcesService {

	private static final Logger logger = LoggerFactory.getLogger(ResourcesServiceImpl.class);
	@Autowired
	private ResourcesPoMapper resourcesPoMapper;

	@Override
	public int addResources(ResourcesPo resources) {
		return resourcesPoMapper.insert(resources);
	}

	@Override
	public ResourcesPo getResourcesById(Integer id) {
		return resourcesPoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateResources(ResourcesPo resources) {
		return resourcesPoMapper.updateByPrimaryKey(resources);
	}

	@Override
	public int deleteResourcesById(Integer id) {
		return resourcesPoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<ResourcesPo> getResourcesListWithPage(Integer start, Integer length) {
		Integer page = start / length + 1;
		PageHelper.startPage(page, length);
		ResourcesPoExample query = new ResourcesPoExample();
		try {
			List<ResourcesPo> list = resourcesPoMapper.selectByExample(query);
			return new PageInfo<ResourcesPo>(list);
		} catch (Exception e) {
			logger.error("ResourcesServiceImpl.getUserListWithPage error", e);
		}
		return null;
	}

	@Override
	public List<ResourcesPo> loadUserResources(Map<String, Object> map) {
		return resourcesPoMapper.loadUserResources(map);
	}

	@Override
	public List<ResourcesPo> queryResourcesListWithSelected(Integer rid) {
		return resourcesPoMapper.queryResourcesListWithSelected(rid);
	}

	@Override
	public List<ResourcesPo> queryAll() {
		return resourcesPoMapper.queryAll();
	}

	@Override
	public Map<String, Object> loadUserTreeResources(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 用户所属所有菜单
		List<ResourcesPo> allResources = resourcesPoMapper.loadUserResources(map);
		// 定义根节点
		List<ResourcesPo> rootResources = new ArrayList<ResourcesPo>();
		for (ResourcesPo nav : allResources) {
			if ("1".equals(nav.getParentid().toString())) {// 父节点是0的，为根节点。
				rootResources.add(nav);
			}
		}
		/* 根据ResourcesPo类的order排序 */
		// Collections.sort(rootResources, order());
		// 为根菜单设置子菜单，getClild是递归调用的
		for (ResourcesPo nav : rootResources) {
			/* 获取根节点下的所有子节点 使用getChild方法 */
			List<ResourcesPo> childList = getChild(nav.getId().toString(), allResources);
			nav.setChildren(childList);// 给根节点设置子节点
		}
		/**
		 * 输出构建好的菜单数据。
		 * 
		 */
		data.put("success", "true");
		data.put("list", rootResources);
		return data;
	}
	
	/**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<ResourcesPo> getChild(String id,List<ResourcesPo> allResources){
        //子菜单
        List<ResourcesPo> childList = new ArrayList<ResourcesPo>();
        for (ResourcesPo nav : allResources) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(id.equals(nav.getParentid().toString())){
                childList.add(nav);
            }
        }
        //递归
        for (ResourcesPo nav : childList) {
            nav.setChildren(getChild(nav.getId().toString(), allResources));
        }
       // Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<ResourcesPo>();
        }
        return childList;
    }

}
