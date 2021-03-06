package com.micro.mapper;

import com.micro.model.UserRolePo;
import com.micro.model.UserRolePoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolePoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    long countByExample(UserRolePoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    int deleteByExample(UserRolePoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    int insert(UserRolePo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    int insertSelective(UserRolePo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    List<UserRolePo> selectByExample(UserRolePoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") UserRolePo record, @Param("example") UserRolePoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") UserRolePo record, @Param("example") UserRolePoExample example);
}