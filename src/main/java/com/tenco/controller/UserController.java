package com.tenco.controller;

import java.io.IOException;

import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;
import com.tenco.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 주소설계
// http://localhost:8080/mvc/user
// http://localhost:8080/mvc/user/xxx

//  uesr/* 세부까지 확장
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;   
    
    public UserController() {
    }
    
    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAOImpl();
    }

 // GET 방식으로 들어 올 떄
 // http://localhost:8080/mvc/user/signUp
 // http://localhost:8080/mvc/user/signIn
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		switch (action) {
		case "/signIn":
			// 로그인 페이지로 보내는 동작 처리
			request.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(request, response);
			break;
		case "/signUp":
			// 회원 가입 페이지로 보내는 동작 처리
			request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}
	
	// http://localhost:8080/mvc/views/todoForm.jsp
	

	// 로그인 기능 요청 (자원에 요청 -- GET 방식 예외적인 처리 보안)
	// POST 요청 시 - 로그인 기능 구현, 회원 가입 기능 구현
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		switch (action) {
		case "/signIn":
			
			break;
		case "/signUp":
			signUp(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	/*
	 * 회원 가입 기능
	 * @param request
	 * @param response
	 */
	
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인증 검사 필요 없는 기능
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		// 방어적 코드 작성 (username)
		if(username == null || username.trim().isEmpty()) {
			request.setAttribute("errorMessage", "사용자 이름을 입력하세요");
			request.getRequestDispatcher("/WEB-INF/signUp.jsp").forward(request, response);
			return;
		}
		
		// 방어적 코드 작성 (password)
		// 방어적 코드 작성 (email)
		
		UserDTO userDTO = UserDTO.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
		
		int resultRowCount = userDAO.addUser(userDTO);
		System.out.println("rresultRowCount : " + resultRowCount);
		
		if(resultRowCount == 1) {
			response.sendRedirect("http://localhost:8080/mvc/user/signIn");
		} else { 
			response.sendRedirect("http://localhost:8080/mvc/user/signIn");
		}
		
	}
		
	}


