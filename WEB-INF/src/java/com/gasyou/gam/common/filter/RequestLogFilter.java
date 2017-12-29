package com.gasyou.gam.common.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestLogFilter implements javax.servlet.Filter {

	private static final Logger logger = LogManager.getLogger(RequestLogFilter.class);

	private FilterConfig filterConfig;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {

		logger.info("start", "");

		chain.doFilter(request, response);

		logger.info("finish", "");
	}

	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		filterConfig = null;
	}
}