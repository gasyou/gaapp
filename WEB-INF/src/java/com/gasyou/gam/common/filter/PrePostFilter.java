package com.gasyou.gam.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Request をラップするオブジェクトを生成する.<br>
 * web.xml に定義せず、アノテーションにて定義する（この場合、Filter の順番は制御できない模様）.
 */
@WebFilter(filterName = "PrePostFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "encoding", value = "utf-8") })
public class PrePostFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(PrePostFilter.class);

	private FilterConfig filterConfig = null;

	/** encoding */
	private String encoding = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {

		chain.doFilter(request, response);

	}

	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;

		// アノテーションで指定しているため、encoding というパラメーターが取得可能
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void destroy() {
		filterConfig = null;
	}

	/**
	 * ResponseWrapper.
	 * 現在サンプルのため処理なし.
	 */
	private class GenericResponseWrapper extends HttpServletResponseWrapper {

		public GenericResponseWrapper(HttpServletResponse response) {
			super(response);
		}
	}
}