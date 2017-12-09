package user.dao;
import java.util.List;

import common.DataAccessException;
import user.entity.User;

public interface UserDAO {
	public List<User> select() throws DataAccessException;
	public List<User> select(String userName) throws DataAccessException;
	public User getUser(int userId) throws DataAccessException;
	public int addUser(User user) throws DataAccessException;
	public int getLock(int userId) throws DataAccessException;
	public int updateUser(User user) throws DataAccessException;

	/**
	 * ログイン ID から ユーザー情報を取得する.
	 * @param loginId ログイン ID
	 * @return ユーザー情報
	 * @throws DataAccessException データアクセスに失敗した場合
	 */
	public User getLoginUser(String loginId) throws DataAccessException;
}
