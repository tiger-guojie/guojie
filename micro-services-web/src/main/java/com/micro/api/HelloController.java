package com.micro.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.service.ResourcesService;
import com.micro.util.ShiroService;

@RestController 
@RequestMapping("/hello")  
public class HelloController {
	
	@Resource
    private ResourcesService resourcesService;
    @Resource
    private ShiroService shiroService;
	
    @GetMapping("/sts")
    public String helloworld(){
        return "使用spring-tool-suite插件";  
    }
    
    @GetMapping
    public String testException() throws Exception{
    	//int a = 5/0;
        //return "使用spring-tool-suite插件";  
    	throw new Exception();
    }
    
    @GetMapping("/loadTreeMenuTest")
    public Map<String,Object> loadTreeMenu(){
        Map<String,Object> map = new HashMap<>();
        Integer userid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        map.put("type",1);
        map.put("userid",userid);
        Map<String,Object> mapTree = resourcesService.loadUserTreeResources(map);
        return mapTree;
    }
}