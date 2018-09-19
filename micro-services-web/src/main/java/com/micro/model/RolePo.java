package com.micro.model;

import java.io.Serializable;

public class RolePo  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4118747600612049044L;

	/** 
    * 
    * DB Column Name: id
    */
    private Integer id;

    /** 
    * 
    * DB Column Name: roleDesc
    */
    private String roledesc;
    
    private Integer selected;

    public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.roleDesc
     *
     * @return the value of role.roleDesc
     *
     * @mbg.generated
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.roleDesc
     *
     * @param roledesc the value for role.roleDesc
     *
     * @mbg.generated
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }
}