package com.spring.baseSetting.utility;

import javax.servlet.*;
import java.io.IOException;

public class EncodeFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		arg0.setCharacterEncoding("UTF-8");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
}//class end
