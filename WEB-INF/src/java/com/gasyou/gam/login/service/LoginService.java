package com.gasyou.gam.login.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import com.gasyou.gam.common.DataAccessException;
import com.gasyou.gam.common.service.AbstractService;
import com.gasyou.gam.common.service.ServiceException;
import com.gasyou.gam.user.dao.MySQLUserDAO;
import com.gasyou.gam.user.dao.UserDAO;
import com.gasyou.gam.user.entity.User;

public class LoginService extends AbstractService {

	/**
	 * ログイン ID とパスワードが登録されたユーザーと一致するかを確認する.
	 * @param loginId ログイン ID
	 * @param password パスワード
	 * @return ログイン成否
	 */
	public boolean login(String loginId, String password) {

		Connection conn = getConnection();

		try {
			UserDAO dao = new MySQLUserDAO(conn);

			// ログインIDからユーザーを取得する
			User user = dao.getLoginUser(loginId);
			if (user != null) {

				// DBのパスワードはハッシュ化されているためハッシュ化して比較する
				String hashPwDB = user.getPassword();
				String hashPassword = DigestUtils.md5Hex(password);
				return hashPassword.equals(hashPwDB);
			}
		} catch (DataAccessException e) {
			throw new ServiceException(e);
		} finally {
			closeConnection();
		}

		return false;
	}
}
