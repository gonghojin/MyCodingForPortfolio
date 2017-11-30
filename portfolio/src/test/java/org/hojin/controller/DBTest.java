package org.hojin.controller;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hojin.dao.BoardDao;
import org.hojin.dao.UserDao;
import org.hojin.model.Board;
import org.hojin.model.PageMaker;
import org.hojin.model.RequestPage;
import org.hojin.model.SearchPage;
import org.hojin.model.User;
import org.hojin.model.UserProfile;
import org.hojin.service.BoardService;
import org.hojin.service.UserProfileService;
import org.hojin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@WebAppConfiguration
public class DBTest {

	@Resource(name = "dataSource")
	private DataSource data;
	
	@Inject
	private BoardService service;
	
	@Inject
	private BoardDao dao;
	
	@Inject
	private SessionFactory session; 
	
	@Value("${portfolio.aws.region}")
	private String driverClass;
	/*@Test
	public void TestConnection() throws Exception{
		try(Connection conn = DataSourceUtils.getConnection(data)){
			System.out.println(conn);
		}
		
		System.out.println(session);
	}*/
	
	@Test
	public void testdao(){
	System.out.println(driverClass);	
	}
	
	
}
