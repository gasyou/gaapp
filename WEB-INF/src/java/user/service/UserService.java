package user.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.DataAccessException;
import common.service.AbstractService;
import common.service.AlreadyUpdatedException;
import common.service.ServiceException;
import user.dao.MySQLUserDAO;
import user.dao.UserDAO;
import user.entity.User;

public class UserService extends AbstractService {

	/**
	 * ユーザー情報を取得する.
	 * @return ユーザー情報
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		Connection conn = null;
		try {

			conn = getConnection();
			UserDAO dao = new MySQLUserDAO(conn);

			users = dao.select();

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			close(conn);
		}

		return users;
	}

	/**
	 * ユーザー情報を取得する.
	 * @param userName ユーザー名
	 * @return ユーザー情報
	 */
	public List<User> getUsers(String userName) {
		List<User> users = new ArrayList<User>();

		Connection conn = null;
		try {

			conn = getConnection();
			UserDAO dao = new MySQLUserDAO(conn);

			users = dao.select(userName);

		} catch (DataAccessException e) {
			throw new ServiceException(e);
		} finally {
			close(conn);
		}

		return users;

	}

	public User getUser(int userId) {
		Connection conn = null;

		User user = null;

		try {

			conn = getConnection();
			UserDAO dao = new MySQLUserDAO(conn);

			user = dao.getUser(userId);

		} catch (DataAccessException e) {
			throw new ServiceException(e);
		} finally {
			close(conn);
		}

		return user;
	}

	/**
	 * ユーザー情報を登録する.
	 * @param user ユーザー情報
	 * @return 登録件数
	 */
	public int addUser(User user) {
		Connection conn = null;
		
		try {

			conn = getConnection();
			UserDAO dao = new MySQLUserDAO(conn);

			// データ更新実行.更新の場合は件数が返却される.
			int ret = dao.addUser(user);

			// AutoCommit ではないのでコミットする.
			conn.commit();

			return ret;

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			close(conn);
		}
	}

	/**
	 * ユーザー情報を更新する.
	 * @param user ユーザー情報
	 * @return 更新件数
	 */
	public int updateUser(User user) {
		Connection conn = null;
		
		try {

			conn = getConnection();
			UserDAO dao = new MySQLUserDAO(conn);

			// ロック取得
			int updCnt = dao.getLock(user.getId());

			int ret = 0;

			if (user.getUpdCnt() == updCnt) {
				// 更新回数が同じ場合は更新.
				ret = dao.updateUser(user);

				// AutoCommit ではないのでコミットする.
				conn.commit();
			} else {
				throw new AlreadyUpdatedException("User has been already updated.");
			}

			return ret;

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			close(conn);
		}
	}
}
