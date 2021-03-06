package org.chu.greve.web;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.html" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			System.out.println("here 2");

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);

			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/Compte.xhtml") >= 0) {
				if (ses != null) {
					String uname = (String) ses.getAttribute("username");
					if (!uname.equals("admin")) {
						res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml"); // Deconnexion
					}
				}
			}
			if (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
					|| reqURI.contains("javax.faces.resource")) {

				chain.doFilter(request, response);
				if (reqURI.indexOf("/logout.xhtml") >= 0) {
					ses.invalidate();
					res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml"); // Deconnexion
				}

			} else
				res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml"); // Si
																						// Aucun
																						// utilisateur
																						// n'est
																						// connect�
																						// ,
																						// rediger
																						// vers
																						// login
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	} // doFilter

	@Override
	public void destroy() {

	}
}