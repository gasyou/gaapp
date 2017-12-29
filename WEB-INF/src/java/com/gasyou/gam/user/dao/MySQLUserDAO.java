package com.gasyou.gam.user.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gasyou.gam.common.DataAccessException;
import com.gasyou.gam.user.entity.User;

public class MySQLUserDAO implements UserDAO {

	private Connection conn = null;

	public MySQLUserDAO(Connection conn) {
		this.conn = conn;
	}

	public List<User> select() throws DataAccessException {
		return select((String) null);
	}

	@Override
	public List<User> select(String userName) throws DataAccessException {
		List<User> users = new ArrayList<User>();

        try {

        	StringBuffer sql = new StringBuffer();
        	sql.append("select * from user");

        	// ユーザー名の指定がある場合
        	if (StringUtils.isNotEmpty(userName)) {
        		// TODO 部分一致のためINDEXが使われない
        		sql.append(" where name like ?");
        	}
        	sql.append(" order by id");

        	PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        	// ユーザー名の指定がある場合
        	if (StringUtils.isNotEmpty(userName)) {
        		// TODO 検索文言が%_含まれる場合にうまく検索できない
        		pstmt.setString(1, "%" + userName + "%");
        	}

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	users.add(createUser(rs));
            }
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

        return users;
	}

	@Override
	public User getUser(int userId) throws DataAccessException {

		User user = null;

        try {

        	StringBuffer sql = new StringBuffer();
        	sql.append("select * from user where id = ?");

        	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        	pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	user = createUser(rs);
            }
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

        return user;
	}

	@Override
	public int addUser(User user) throws DataAccessException {

        int ret = 0;

        try {

        	StringBuffer sql = new StringBuffer();
        	sql.append("INSERT INTO USER(id, login_id, password, name, upd_cnt) VALUE (?, ?, ?, ?, ?)");

        	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        	pstmt.setInt(1, user.getId());
        	pstmt.setString(2, user.getLoginId());
        	pstmt.setString(3, user.getPassword());
        	pstmt.setString(4, user.getName());
        	pstmt.setInt(5, 1);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

        return ret;
	}

	@Override
	public int getLock(int userId) throws DataAccessException {

        try {

        	String sql = "select * from user where id = ? for update";

        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	return rs.getInt("upd_cnt");
            }
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

		return 0;
	}

	@Override
	public int updateUser(User user) throws DataAccessException {
        int ret = 0;

        try {

        	StringBuffer sql = new StringBuffer();
        	sql.append(" UPDATE USER SET ");
        	sql.append("   login_id = ? ");
        	sql.append(" , password = ? ");
        	sql.append(" , name = ? ");
        	sql.append(" , upd_cnt = upd_cnt + 1 ");
        	sql.append(" WHERE id = ? ");

        	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        	pstmt.setString(1, user.getLoginId());
        	pstmt.setString(2, user.getPassword());
        	pstmt.setString(3, user.getName());
        	pstmt.setInt(4, user.getId());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

        return ret;
	}

	private User createUser(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getObject("id", Integer.class));
		user.setLoginId(rs.getObject("login_id", String.class));
		user.setPassword(rs.getObject("password", String.class));
		user.setName(rs.getObject("name", String.class));
		user.setUpdCnt(rs.getObject("upd_cnt", Integer.class));
		return user;
	}

	@Override
	public User getLoginUser(String loginId) throws DataAccessException {
		User user = null;

        try {

        	StringBuffer sql = new StringBuffer();
        	sql.append("select * from user where login_id = ?");

        	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        	pstmt.setString(1, loginId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	user = createUser(rs);
            }
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }

        return user;
	}
}