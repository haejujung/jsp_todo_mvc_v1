package com.tenco.model;

import java.util.List;


public interface UserDAO {

	// CRUD
	int addUser(UserDTO userDTO);
	UserDTO getUserById(int id);
	UserDTO getUserByUsername(String username);
	List<UserDTO> getAllUsers();
	// ,,, WHERE id = 1;
	int updateUser(UserDTO user, int principalId); // 권한 (마이 정보 나만) - 인증(세션ID)
	int deleteUser();
	

}
