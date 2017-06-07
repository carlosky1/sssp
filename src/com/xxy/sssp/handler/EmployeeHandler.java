package com.xxy.sssp.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xxy.sssp.entity.Employee;
import com.xxy.sssp.service.EmployeeService;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/empe")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
			Map<String,Object> map){
		System.out.println("========================");
		int pageNo=1;
		try {
			//对pageNo的校验
			pageNo=Integer.parseInt(pageNoStr);
			
			if(pageNo<1){
				pageNo=1;
			}
			
		} catch (Exception e) {}
		
		Page<Employee> page=employeeService.getPage(pageNo, 5);
		map.put("page", page);
		
		return "emp/list";
	}
}
