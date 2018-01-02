package com.gasyou.gam.common.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.model.ForwardInfo;
import com.gasyou.gam.common.model.LoginInfo;
import com.gasyou.gam.common.model.ModelConfig;
import com.gasyou.gam.common.model.ModelConfigFactory;
import com.gasyou.gam.common.model.ModelInfo;
import com.gasyou.gam.common.service.ServiceConfig;

public class ModelServlet extends AbstractServlet {

	private ModelConfig modelConfig = null;

	protected Properties props = null;

	@Override
	public void init() throws ServletException {
		String property = getInitParameter("property");

		InputStream is = null;
		try {
			// クラスパスからプロパティを読み込む
			is = ModelServlet.class.getResourceAsStream("/" + property);
			props = new Properties();
			props.load(is);
			is.close();
		} catch (IOException e) {
			throw new ServletException(e);
		}

		//----------------------------------
		// Model の初期設定
		//----------------------------------
		// モデルの設定を読み込む
		String modelConfigPath = props.getProperty("model-config");
		this.modelConfig = new ModelConfigFactory(modelConfigPath).create();

		//----------------------------------
		// Service の初期設定
		//----------------------------------
		Map<String, String> sParams = new HashMap<String, String>();
		sParams.put(ServiceConfig.DB_URL, props.getProperty(ServiceConfig.DB_URL));
		sParams.put(ServiceConfig.DB_USER, props.getProperty(ServiceConfig.DB_USER));
		sParams.put(ServiceConfig.DB_PASSWORD, props.getProperty(ServiceConfig.DB_PASSWORD));

		sParams.put(ServiceConfig.GA_APPLICATION_NAME, props.getProperty(ServiceConfig.GA_APPLICATION_NAME));
		sParams.put(ServiceConfig.GA_SERVICE_ACCOUNT_EMAIL, props.getProperty(ServiceConfig.GA_SERVICE_ACCOUNT_EMAIL));
		sParams.put(ServiceConfig.GA_KEY_FILE_LOCATION, props.getProperty(ServiceConfig.GA_KEY_FILE_LOCATION));

		ServiceConfig.initialize(sParams);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			//*****************************
			// 認可処理
			//*****************************
			boolean loginValid = Boolean.valueOf(props.getProperty("login.valid", "false"));
			String loginURL = props.getProperty("login.url");
			// Path Component
			String requestURI = req.getRequestURI();
			// Query String
			String queryString = req.getQueryString();

			if (loginValid && !requestURI.startsWith(loginURL)) {
				// ログイン画面以外は認可処理を行う
				HttpSession sesson = req.getSession(true);
				LoginInfo loginInfo = (LoginInfo) sesson.getAttribute(LoginInfo.SESSION_KEY);
				if (loginInfo == null) {
					// 未ログインのため、ログイン画面へリダイレクトする.戻りURLをつけておく

					// ログイン成功後の戻り先を指定しておく（パラメータ値になるのでURLエンコードしておく）
					String rt = requestURI;
					if (StringUtils.isNotBlank(queryString)) {
						rt += "?" + queryString;
					}
					resp.sendRedirect(loginURL + "?" + "rt=" + new URLCodec("UTF-8").encode(rt, "UTF-8"));
					return;
				}
			}

			String servletPath = req.getServletPath();
			String modelName = servletPath.replaceAll("^/(.+)\\.x$", "$1");

			ModelInfo modelInfo = modelConfig.getModelInfo(modelName);

			if (modelInfo == null) {
				throw new ServletException("Not supported Model : " + servletPath);
			}

			String className = modelInfo.getClassName();

			// クラス名からインスタンスを生成する(リフレクション)
			AbstractModel absModel = (AbstractModel) Class.forName(className).newInstance();
			absModel.setModelInfo(modelInfo);
			absModel.setRqequest(req);
			absModel.setResponse(resp);

			ForwardInfo forwardInfo = absModel.execute();

			if (forwardInfo.isRedirect()) {
				// リダイレクトの場合
				resp.sendRedirect(forwardInfo.getForwardPath());
			} else {
				// 内部フォワードの場合
				setForward(req, resp, forwardInfo.getForwardPath());
			}

		} catch (ServletException e) {
			throw e;
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
