package user.model;

import common.model.AbstractModel;
import common.service.ServiceFactory;
import user.entity.User;
import user.service.UserService;

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
