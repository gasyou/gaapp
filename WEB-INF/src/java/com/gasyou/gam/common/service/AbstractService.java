package com.gasyou.gam.common.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gasyou.gam.common.DataAccessException;

public abstract class AbstractService {

	/** Service 設定 */
	private ServiceConfig serviceConfig = null;

	/** Connection */
	private Connection conn = null;

	/**
	 * Service を初期化する.
	 * @param serviceConfig Service 設定
	 */
	void initialize(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
		this.subInitialize(serviceConfig);
	}

	/**
	 * Service を初期化する(サブクラス処理).
	 * @param serviceConfig Service 設定
	 */
	protected void subInitialize(ServiceConfig serviceConfig) {}

	/**
	 * Service 設定を取得する.
	 * @return Service 設定
	 */
	protected ServiceConfig getServiceConfig() {
		return this.serviceConfig;
	}

	/**
	 * データベースのコネクションを取得する.
	 * 複数実行を考慮して同期処理にしておく.
	 * @return コネクション
	 */
	protected synchronized Connection getConnection() {
		try {

			if (this.conn != null) {
				return conn;
			}

			ServiceConfig conf = ServiceConfig.getConfig();

			// JDBCドライバのロード
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection c = DriverManager.getConnection(
			  conf.get(ServiceConfig.DB_URL)
			, conf.get(ServiceConfig.DB_USER)
			, conf.get(ServiceConfig.DB_PASSWORD));

			c.setAutoCommit(false);

			this.conn = c;
			return this.conn;
//			return DriverManager.getConnection(
//					  "jdbc:mysql://localhost:3306/test"
//					, "root"
//					, "admin");

		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ServiceException(e);
		}
	}


	/**
	 * データベースのコネクションをクローズする.
	 * @throws ServiceException 例外が発生した場合
	 */
	protected void closeConnection() throws ServiceException {

		try {
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (SQLException e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * データベースのコネクションをクローズする.
	 * @throws DataAccessException
	 */
	protected void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
}
