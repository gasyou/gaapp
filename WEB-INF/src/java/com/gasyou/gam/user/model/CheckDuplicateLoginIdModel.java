package com.gasyou.gam.user.model;

import org.apache.commons.lang3.StringUtils;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.user.entity.User;
import com.gasyou.gam.user.service.UserService;

public class CheckDuplicateLoginIdModel extends AbstractModel {

	@Override
	public String action() {

		User user = null;

		String loginId = this.req.getParameter("loginId");

		if (StringUtils.isNotBlank(loginId)) {
			UserService service = ServiceFactory.getService(UserService.class);
			user = service.getUserByLoginId(loginId);
		}

		req.setAttribute("loginId", loginId);
		req.setAttribute("user", user);

		return "success";
	}
}
