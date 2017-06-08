package com.xxy.sssp.test;


import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.ejb.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xxy.sssp.entity.Department;
import com.xxy.sssp.repository.DepartmentRepository;

public class sspTest {

	private ApplicationContext ctx=null;
	
	private DepartmentRepository departmentRepository;
	private EntityManagerFactory entityManagerFactory;
	
	{
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		departmentRepository=ctx.getBean(DepartmentRepository.class);
		entityManagerFactory=ctx.getBean(EntityManagerFactory.class);
	}
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource=ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testRepositorySecondLevelCache(){
		//List<Department> departments=departmentRepository.findAll();
		//departments=departmentRepository.findAll();
		
		//spring与jpa整合，使用二级缓存。只需要定义一个方法，定义jpql，用@QuertHints进行设置[把HINT_CACHEABLE设置为true]
		List<Department> departments=departmentRepository.getAll();
		departments=departmentRepository.getAll();
	}

	//jpa二级缓存 
	@Test
	public void testJpaSecondLevelCache(){
		String jpql="FROM Department d";
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery(jpql);
		//List<Department> departments=query.getResultList();
		List<Department> departments=query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
	
		entityManager.close();
		//默认情况下没有二级缓存，本案例会显示两条SQL语句
		 entityManager=entityManagerFactory.createEntityManager();
		 query=entityManager.createQuery(jpql);
		 //departments=query.getResultList();
		 departments=query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();//使用二级缓存，仅显示一条sql语句
		 entityManager.close();
	}
	
}
