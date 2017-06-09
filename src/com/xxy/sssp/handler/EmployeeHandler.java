package com.xxy.sssp.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxy.sssp.entity.Employee;
import com.xxy.sssp.service.DepartmentService;
import com.xxy.sssp.service.EmployeeService;

@Controller
public class EmployeeHandler {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/empe")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNoStr,
			Map<String,Object> map){
		System.out.println("========我是测试============");
		int pageNo=1;
		try {
			//对pageNo的校验
			pageNo=Integer.parseInt(pageNoStr);
			
			if(pageNo<1){
				pageNo=1;
			}
			
		} catch (Exception e) {}
		
		Page<Employee> page=employeeService.getPage(pageNo, 6);
		map.put("page", page);
		
		return "emp/list";
	}
	
	
	@RequestMapping(value="/ees",method=RequestMethod.GET)
	public String input(Map<String,Object>map){
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "emp/input";
	}
	
	//@ResponseBody使用标记位0/1，可以不作任何处理
	@ResponseBody
	@RequestMapping(value="/ajaxValidateLastNames",method=RequestMethod.POST)
	public String validateLastName(@RequestParam(value="lastName",required=true)String lastName){
		Employee employee=employeeService.getByLastName(lastName);
		if(employee==null){
			return "0";
			//return "数据加载失败";
		}else{
		//return "数据加载成功";
			return "1";
		}
	}
	
	//重定向到显示所有员工的页面
	@RequestMapping(value="/ees",method=RequestMethod.POST)
	public String save(Employee employee){
		employeeService.save(employee);
		return "redirect:/empe";
	}
	
	//
	@RequestMapping(value="/ees/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id")Integer id,Map<String,Object>map){
		Employee employee= employeeService.get(id);
		map.put("employee", employee);
		map.put("departments", departmentService.getAll());
		return "emp/input";
	}
	
	//更新
	//都是mapping请求时，且value值相同，建议写成数组，url不同，所以需要重写一个
	@RequestMapping(value="/ees/{id}",method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeService.save(employee);
		return "redirect:/empe";
	}
	
	//没有写这个，createTime会被置空
	@ModelAttribute
	public void getEmployee(@RequestParam(value="",required=false)Integer id,
			Map<String,Object>map){
		if(id!=null){
			Employee employee=employeeService.get(id);
			employee.setDept(null);//这很重要，指向一个新的对象
			map.put("employee",employee);
		}
	}
	
	//
	@RequestMapping(value="/ees/{id}",method=RequestMethod.DELETE)
	public  String delete(@PathVariable("id")Integer id){
		employeeService.delete(id);
		return "redirect:/empe";
	}
	
}
