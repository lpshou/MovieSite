package com.movie.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginOut extends ActionSupport {

	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
}