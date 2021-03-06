package com.gasyou.gam.menu.model;

import javax.servlet.http.HttpSession;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.model.LoginInfo;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.login.service.LoginService;

public class MenuModel extends AbstractModel {

	@Override
	public String action() {

		// Model の実行モードを記憶する.
		String mode = getMode();
		req.setAttribute("mode", mode);

		// 戻りURLを設定しておく
		String returnURL = req.getParameter("rt");
		req.setAttribute("rt", returnURL);

		if ("login".equals(mode)) {

			final String loginId = getParameter("loginId", "");
			final String password = getParameter("password", "");

			// ログイン処理
			LoginService service = ServiceFactory.getService(LoginService.class);
			boolean ok = service.login(loginId, password);

			if (ok) {
				// 認証OKの場合、ログイン情報をセッションに保存し元画面へリダイレクトする.
				HttpSession session = this.req.getSession(true);
				session.setAttribute(LoginInfo.SESSION_KEY, new LoginInfo(loginId));

				setRedirect(returnURL);
				return null;
			} else {
				// TODO 認証エラー時の処理
			}
		}

		return "success";
	}
}
