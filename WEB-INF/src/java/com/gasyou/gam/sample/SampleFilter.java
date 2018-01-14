package com.gasyou.gam.sample;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class SampleFilter implements Filter {

	public FilterConfig filterConfig = null;

	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, new GenericResponseWrapper((HttpServletResponse) response));
	}

	public void init(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}
}