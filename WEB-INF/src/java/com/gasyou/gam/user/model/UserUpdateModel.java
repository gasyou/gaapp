package com.gasyou.gam.user.model;

import org.apache.commons.codec.digest.DigestUtils;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.user.entity.User;
import com.gasyou.gam.user.service.UserService;

public class UserUpdateModel extends AbstractModel {

	@Override
	public String action() {

		// Model の実行モードを記憶する.
		String mode = getMode();
		req.setAttribute("mode", mode);

		if ("add".equals(mode) || mode.equals("update.update")) {

			String passrowd1 = req.getParameter("password1");
			String passrowd2 = req.getParameter("password2");

			String password = "dummy";

			// パスワードが一致しない場合はエラーとする.
			if (!passrowd1.equals(passrowd2)) {
				// TODO 実装
			} else {
				// パスワードはハッシュ化して登録する.
				// Apache Commons Codec 利用
				password = DigestUtils.md5Hex(passrowd1);
			}

			User user = new User();
			if (!"add".equals(mode)) {
				user.setId(Integer.parseInt(req.getParameter("id")));
			}
			user.setLoginId(req.getParameter("loginId"));
			user.setPassword(password);
			user.setName(req.getParameter("name"));
			user.setUpdCnt(getParameter("updCnt", 1));

			UserService service = ServiceFactory.getService(UserService.class);

			int ret = 0;
			if ("add".equals(mode)) {
				ret = service.addUser(user);
			} else if ("update.update".equals(mode)) {
				ret = service.updateUser(user);
			}

			if (ret == 0) {
				// TODO 登録できなかった場合はエラー
			}

			this.req.setAttribute("returnUrl", this.req.getContextPath() + "/UserList.x");

			return "success";

		} else if (mode.equals("update")) {
//			String userId = this.req.getParameter("userId");
//
//			UserService service = ServiceFactory.getService(UserService.class);
//			User user = service.getUser(Integer.parseInt(userId));
//			req.setAttribute("user", user);

			this.req.setAttribute("mode", "update.confirm");

			req.setAttribute("id", this.req.getParameter("id"));
			req.setAttribute("loginId", this.req.getParameter("loginId"));
			req.setAttribute("password1", this.req.getParameter("password1"));
			req.setAttribute("password2", this.req.getParameter("password2"));
			req.setAttribute("name", this.req.getParameter("name"));
			req.setAttribute("updCnt", this.req.getParameter("updCnt"));

			return "confirm_success";
		}

		return null;
	}
}
