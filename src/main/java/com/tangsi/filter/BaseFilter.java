package com.tangsi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BaseFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreRequest = (HttpServletRequest) request;
		
		ServletContext application = httpreRequest.getSession().getServletContext();
		//设置根路径
		String base = application.getContextPath();
		
		request.setAttribute("base", base);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
