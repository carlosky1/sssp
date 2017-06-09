package com.xxy.sssp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Table(name="SSSP_EMPLOYEES")
@Entity
public class Employee {
	private Integer id;
	private String lastName;
	private  String email;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	private Date CreateTime;
	private Department dept;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	
	@JoinColumn(name="DEPARTMENT_ID")
	//事务的边缘在service结束时，sesion关闭
	//@ManyToOne
	
	//如果使用懒加载，必须在web.xml中加入OpenEntityManagerInViewFilter
	@ManyToOne(fetch=FetchType.LAZY)
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
}
