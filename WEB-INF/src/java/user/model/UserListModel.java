package user.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import common.model.AbstractModel;
import common.service.ServiceFactory;
import user.entity.User;
import user.service.UserService;

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
