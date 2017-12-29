package com.gasyou.gam.user.model;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.user.entity.User;
import com.gasyou.gam.user.service.UserService;

public class UserModel extends AbstractModel {

	@Override
	public String action() {

		// Model の実行モードを記憶する.
		String mode = getMode();
		req.setAttribute("mode", mode);

		if ("add".equals(mode)) {
			// Nothing to do.
		} else {
			String userId = this.req.getParameter("userId");

			UserService service = ServiceFactory.getService(UserService.class);
			User user = service.getUser(Integer.parseInt(userId));
			req.setAttribute("user", user);

			req.setAttribute("id", user.getId());
			req.setAttribute("loginId", user.getLoginId());
			req.setAttribute("name", user.getName());
			req.setAttribute("updCnt", user.getUpdCnt());
		}

		return "success";
	}
}
