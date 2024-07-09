package com.tenco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 * 
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class UserDTO {

	private int id;
	private String username;
	private String password;
	private String email;
	private String createdAt;

}
