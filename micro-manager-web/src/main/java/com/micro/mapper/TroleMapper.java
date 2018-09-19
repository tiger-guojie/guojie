package com.micro.mapper;

import java.util.List;

import com.micro.entity.Trole;
import com.micro.util.MyMapper;

public interface TroleMapper extends MyMapper<Trole> {

    List<Trole> selectRolesByUserId(Integer userid);

}