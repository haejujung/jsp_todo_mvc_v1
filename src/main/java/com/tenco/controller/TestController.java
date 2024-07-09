package com.tenco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;
import com.tenco.model.UserDTO;

@WebServlet("/test/*")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
   
    public TestController() {
    }
    

    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAOImpl();
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo();
		System.out.println("12121");
		switch (action) {
		case "/byId": 
			// http://localhost:8080/mvc/test/byId
			System.out.println("!1212");
//			userDAO.getUserById(1);
			userDAO.getUserByUsername("홍길동");
//			List<UserDTO> list = userDAO.getAllUsers();
//			if(list.isEmpty()) {
				
			
//				UserDTO.builder().password("999").email("h@naver.com").build();
//				int count = UserDAO.updateUser(dto,3);
//				System.out.println("count : " + count);
//				userDAO.updateUser(dto, 3);
				
			break;
			
			default:
				break;
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
