package com.micro.model;

import java.io.Serializable;
import java.util.List;

public class ResourcesPo  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3711855004385918130L;

	/** 
    * 
    * DB Column Name: id
    */
    private Integer id;

    /** 
    * 资源名称
    * DB Column Name: name
    */
    private String name;

    /** 
    * 资源url
    * DB Column Name: resUrl
    */
    private String resurl;

    /** 
    * 资源类型   1:菜单    2：按钮
    * DB Column Name: type
    */
    private Integer type;

    /** 
    * 父资源
    * DB Column Name: parentId
    */
    private Integer parentid;

    /** 
    * 排序
    * DB Column Name: sort
    */
    private Integer sort;
    
    private String checked;//是否选中
    
    // 子菜单
    private List<ResourcesPo> children;

    public List<ResourcesPo> getChildren() {
		return children;
	}

	public void setChildren(List<ResourcesPo> children) {
		this.children = children;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.id
     *
     * @return the value of resources.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.id
     *
     * @param id the value for resources.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.name
     *
     * @return the value of resources.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.name
     *
     * @param name the value for resources.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.resUrl
     *
     * @return the value of resources.resUrl
     *
     * @mbg.generated
     */
    public String getResurl() {
        return resurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.resUrl
     *
     * @param resurl the value for resources.resUrl
     *
     * @mbg.generated
     */
    public void setResurl(String resurl) {
        this.resurl = resurl == null ? null : resurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.type
     *
     * @return the value of resources.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.type
     *
     * @param type the value for resources.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.parentId
     *
     * @return the value of resources.parentId
     *
     * @mbg.generated
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.parentId
     *
     * @param parentid the value for resources.parentId
     *
     * @mbg.generated
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resources.sort
     *
     * @return the value of resources.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resources.sort
     *
     * @param sort the value for resources.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}