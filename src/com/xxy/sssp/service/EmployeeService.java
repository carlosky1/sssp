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
	
	//只读方法
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo, int pageSize){
		//页码从0开始
		//pageable是一个接口，用他的实现类PageRequest
		PageRequest pageable=new PageRequest(pageNo-1, pageSize);
		return employeeRepository.findAll(pageable);
	
	}	
	
	
	 @Transactional(readOnly=true)	
	public Employee getByLastName(String lastName){
		return employeeRepository.getByLastName(lastName);
	 }
	 
	 @Transactional
	 public void save(Employee employee){
		 //设置创建时间
		 employee.setCreateTime(new Date());
		 employeeRepository.saveAndFlush(employee);
	 } 
	
}
