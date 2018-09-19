package com.micro.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.micro.model.ResourcesPo;
import com.micro.model.ResourcesPoExample;

@Repository
public interface ResourcesPoMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	long countByExample(ResourcesPoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int deleteByExample(ResourcesPoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int insert(ResourcesPo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int insertSelective(ResourcesPo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	List<ResourcesPo> selectByExample(ResourcesPoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	ResourcesPo selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") ResourcesPo record, @Param("example") ResourcesPoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") ResourcesPo record, @Param("example") ResourcesPoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(ResourcesPo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table resources
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(ResourcesPo record);

	List<ResourcesPo> loadUserResources(Map<String, Object> map);

	List<ResourcesPo> queryResourcesListWithSelected(Integer rid);

	List<ResourcesPo> queryAll();
}