package com.micro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.mapper.UsersPoMapper;
import com.micro.model.UsersPo;
import com.micro.model.UsersPoExample;
import com.micro.service.UserService;

/**
 * @author GJ
 * @since 2018-09-04
 **/
@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersPoMapper usersPoMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

	@Override
	public int addUser(UsersPo user) {
		return usersPoMapper.insert(user);
	}

	@Override
	public UsersPo getUserById(Integer id) {
		return  usersPoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUser(UsersPo user) {
		return usersPoMapper.updateByPrimaryKey(user);
	}

	@Override
	public int deleteUserById(Integer id) {
		return usersPoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<UsersPo> getUserListWithPage(Integer start, Integer length) {
		Integer page = start/length+1;
		PageHelper.startPage(page, length);
		UsersPoExample query=new UsersPoExample();
        try{
            List<UsersPo> list=usersPoMapper.selectByExample(query);
            return new PageInfo<UsersPo>(list);
        }
        catch (Exception e) {
            logger.error("UserServiceImpl.getUserListWithPage error",e);
        }
        return null;
	}
	
	/**
     * 无分页List
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UsersPo> getAllUserWithNoPage(){


        try{

            //序列化器，将key的值设置为字符串
            RedisSerializer redisSerializer=new StringRedisSerializer();
            redisTemplate.setKeySerializer(redisSerializer);

            //查缓存
            List<UsersPo> list=(List<UsersPo>)redisTemplate.opsForValue().get("allUsers");

            if(null==list){
                //双重检测 锁
                synchronized (this) {

                    List<UsersPo> list1 = (List<UsersPo>) redisTemplate.opsForValue().get("allUsers");
                    if (null == list1) {

                    	UsersPoExample query=new UsersPoExample();
                        list=usersPoMapper.selectByExample(query);
                        redisTemplate.opsForValue().set("allUsers", list);

                        System.out.println("从数据库中取数据");
                    }
                    else{
                        System.out.println("从缓存中取数据");
                    }
                }
            }
            else{
                System.out.println("从缓存中取数据");
            }
            return list;
        }
        catch (Exception e) {
            logger.error("UserServiceImpl.getAllUserWithNoPage error",e);
        }
        return null;
    }

	@Override
	public UsersPo selectByUsername(String username) {
		return usersPoMapper.selectByUsername(username);
	}

   
}
