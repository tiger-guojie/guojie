package com.micro.mapper;

import java.util.HashMap;
import java.util.List;

import com.micro.entity.Tmenu;
import com.micro.util.MyMapper;

public interface TmenuMapper extends MyMapper<Tmenu> {

    List<Tmenu> selectMenusByRoleId(Integer roleid);

    List<Tmenu> selectByParentIdAndRoleId(HashMap<String,Object> paraMap);
    
    List<Tmenu> queryAll();

}