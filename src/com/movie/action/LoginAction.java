package com.movie.action;

import java.util.Map;

import com.movie.dao.userDAO;
import com.movie.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String email;
	private String psw;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		User user = userDAO.getUserByEmail(email);
		if(user != null)
		{
			Map session=ActionContext.getContext().getSession();
			if(session.containsKey("user"))
				session.remove("user");
			session.put("user", user);
			if(session.containsKey("userID"))
				session.remove("userID");
			session.put("userID", user.getId());
			return SUCCESS;
		}
		else return ERROR;
	}
}