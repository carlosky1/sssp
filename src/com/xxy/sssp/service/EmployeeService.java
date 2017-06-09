package com.xxy.sssp.service;

import java.util.Date;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxy.sssp.entity.Employee;
import com.xxy.sssp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//事务--只读
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo, int pageSize){
		//页码从0开始
		//pageable是一个接口，用他的实现类PageRequest
		PageRequest pageable=new PageRequest(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	
	}	
	
	//ajax验证用户可用性
	 @Transactional(readOnly=true)	
	public Employee getByLastName(String lastName){
		return employeeRepository.getByLastName(lastName);
	 }
	 
	 //完成添加操作，重定向
	 @Transactional
	 public void save(Employee employee){
		 //设置创建时间
		 employee.setCreateTime(new Date());
		 employeeRepository.saveAndFlush(employee);
	 } 
	 
	 //完成修改操作
	//springmvc表单回显原理：实际上表单的回显是由springmvc标签完成的，在handler方法中，向request中添加一个属性，
	 //键：springmvc form:form标签，modelAttribute属性值；值：包含回显信息的一个bean对象
	 @Transactional(readOnly=true)
	 public Employee get(Integer id){
		 return employeeRepository.findOne(id);
	 }
}
