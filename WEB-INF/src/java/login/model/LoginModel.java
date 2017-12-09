package login.model;

import javax.servlet.http.HttpSession;

import common.model.AbstractModel;
import common.model.LoginInfo;
import common.service.ServiceFactory;
import login.service.LoginService;

public class LoginModel extends AbstractModel {

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
