package com.tenco.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	
//	private static DataSource dataSource;
//	
//	// 정적 초기화 블록
//	static {
//		// TODO - 삭제 예정
//		System.out.println("1111111정적 초기화 블1록11111111");
//		try {
//			// initial 객체를 생성하여 jndi api 기술을 통해 존재하는 리소스를 찾는 방법
//			InitialContext ctx = new InitialContext();
//			// env 뒤 context.xml 의 name 값 
//			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
//			
//			
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//
//	
//	public static Connection getConnection() throws SQLException {
//		 	
//		return dataSource.getConnection();
//		
//	}
//	
	
}
