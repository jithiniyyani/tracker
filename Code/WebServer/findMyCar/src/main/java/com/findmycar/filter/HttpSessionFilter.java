package com.findmycar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

/**
 * Servlet Filter implementation class HttpSessionFilter
 */
public class HttpSessionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public HttpSessionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServeltResponse = (HttpServletResponse) response;
		String servletPath = httpServletRequest.getServletPath();
		if (servletPath.contains("login") || servletPath.contains("register") ||servletPath.contains("countries")) {
			chain.doFilter(request, response);
		} else {
			if (httpServletRequest.getSession(false) == null) {
				// we have no session
				httpServeltResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
