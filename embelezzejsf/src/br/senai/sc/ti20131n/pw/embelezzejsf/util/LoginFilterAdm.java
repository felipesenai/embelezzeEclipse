package br.senai.sc.ti20131n.pw.embelezzejsf.util;

import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.senai.sc.ti20131n.pw.embelezzejsf.mb.LoginAdmMb;

@WebFilter(urlPatterns="/admin/*")
public class LoginFilterAdm implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		LoginAdmMb loginAdmMb = (LoginAdmMb) httpServletRequest.getSession().getAttribute("loginAdmMb");
		
		if(loginAdmMb == null || !loginAdmMb.estaLogado()) {
			((HttpServletResponse)servletResponse).sendRedirect(httpServletRequest.getContextPath().concat("/login.xhtml"));
		}
			
		filterchain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	
}
