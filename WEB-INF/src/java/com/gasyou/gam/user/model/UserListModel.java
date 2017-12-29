package com.gasyou.gam.user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.user.entity.User;
import com.gasyou.gam.user.service.UserService;

public class UserListModel extends AbstractModel {

	@Override
	public String action() {

		String userName = this.req.getParameter("userName");

		UserService service = ServiceFactory.getService(UserService.class);
		List<User> users = null;

		if (StringUtils.isNotEmpty(userName)) {
			users = service.getUsers(userName);
		} else {
			users = service.getUsers();
		}

		req.setAttribute("userList", users);
		req.setAttribute("userName", userName);

		return "success";
	}
}
