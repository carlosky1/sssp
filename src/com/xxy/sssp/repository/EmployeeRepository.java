package com.xxy.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xxy.sssp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//ajax验证用户名可用性
	Employee getByLastName(String lastName);
}
