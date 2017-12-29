package com.gasyou.gam.common.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractModel {

	/** Model 情報 */
	private ModelInfo modelInfo = null;
	/** HttpServletRequest */
	protected HttpServletRequest req = null;
	/** HttpServletResponse */
	protected HttpServletResponse resp = null;
	/** リダイレクト先 */
	protected String redirect = null;

	/**
	 * Model を実行する.
	 * @return フォワード情報
	 */
	public ForwardInfo execute() throws Exception {

		String forward = this.action();

		if (this.redirect != null) {
			// モデルでリダイレクトが指定されている場合
			return new ForwardInfo(this.redirect, true);
		} else {
			return modelInfo.getForward(forward);
		}
	}

	/**
	 * Model を動作させる.
	 * @throws Exception
	 * @retursdazn フォワード先
	 */
	public abstract String action() throws Exception;

	/**
	 * Model 情報を設定する.
	 * @param modelInfo Model 情報
	 */
	public void setModelInfo(ModelInfo modelInfo) {
		this.modelInfo = modelInfo;
	}

	/**
	 * Request を設定する.
	 * @param req HTTPServletRequest
	 */
	public void setRqequest(HttpServletRequest req) {
		this.req = req;
	}

	/**
	 * Response を設定する.
	 * @param resp HTTPServletResponse
	 */
	public void setResponse(HttpServletResponse resp) {
		this.resp = resp;
	}

	/**
	 * リダイレクト先を設定する.
	 * リダイレクト先が設定されている場合、フォワードより優先される.
	 * @param redirect リダイレクト先
	 */
	protected void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	/**
	 * Model の実行モードを取得します.
	 * @return 実行モード
	 */
	protected String getMode() {
		return this.req.getParameter("mode");
	}

	protected <T> T getParameter(String param, T defaultValue) {
		String value = this.req.getParameter(param);

		T returnValue = defaultValue;
		if (!StringUtils.isBlank(value)) {
			if (defaultValue instanceof Integer) {
				returnValue = (T) Integer.valueOf(value);
			} else if (defaultValue instanceof String) {
				returnValue = (T) value;
			} else if (defaultValue instanceof Boolean) {
				returnValue = (T) Boolean.valueOf(value);
			}
		}

		return returnValue;
	}
}
