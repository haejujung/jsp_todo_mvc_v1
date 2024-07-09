package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.ha.LoadBalancedAutoCommitInterceptor;

public class UserDAOImpl implements UserDAO {

	private DataSource dataSource;

	public UserDAOImpl() {
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int addUser(UserDTO userDTO) {
		int resultCount = 0;
		String sql = " INSERT INTO users(username,password,email) values (?, ?, ?) ";

		try (Connection conn = dataSource.getConnection()) {
			
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, userDTO.getUsername());
				pstmt.setString(2, userDTO.getPassword());
				pstmt.setString(3, userDTO.getEmail());
				resultCount = pstmt.executeUpdate();
				conn.commit();
				
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			System.out.println("resultCount : " + resultCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultCount;

	}
	
/*
 * select 에서는 일단 트랜잭션 처리르 하지말자
 * 하지만 팬텀리드현상에서는 옳은방법이다
 * 
 */

	@Override
	public UserDTO getUserById(int id) {

		String sql = " select * from users where id = ? " ;
		UserDTO userDTO = null;
		
		try(Connection conn = dataSource.getConnection()) {
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));
					 
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("UserDTO : " + userDTO.toString());
		return userDTO;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		String sql = " select * from users where username = ? " ;
		UserDTO userDTO = null;
		
		try(Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, username);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));

				}
				
			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("UserDTO : " + userDTO.toString());
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		String sql = " select * from users " ;
		// 자료구조를 사용할 떄 일단 생성하자
		List<UserDTO> list = new ArrayList<>();
		
		
		try(Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					UserDTO userDTO = new UserDTO();
					userDTO.setId(rs.getInt("id"));
					userDTO.setUsername(rs.getString("username"));
					userDTO.setPassword(rs.getString("password"));
					userDTO.setEmail(rs.getString("email"));
					userDTO.setCreatedAt(rs.getString("created_at"));
					list.add(userDTO);
					
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("UserList All : " + list.toString());
		
		return list;
	}

		

	@Override
	public int updateUser(UserDTO user, int principalId) {
		int rowCount = 0;
		String sql = " update users set password = 'aaaa', email = 'asd@naver.com' where id = ? ";
		try (Connection conn = dataSource.getConnection()){
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getEmail());
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return 0;
	}
	@Override
	public int deleteUser() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public int deleteUser() {
//		int rowCount = 0;
//		String sql = " DELETE FROM users where id = ? ";
//		try (Connection conn = dataSource.getConnection()){
//			conn.setAutoCommit(false);
//			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
//				pstmt.setInt(1, id);
//				rowCount
//				pstmt.executeUpdate();
//				conn.commit();
//			} catch (Exception e) {
//				conn.rollback();
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		return 0;
//	}

}
