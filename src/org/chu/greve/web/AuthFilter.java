package org.chu.greve.web;
import java.io.IOException;
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

import org.chu.greve.util.Util;
import org.chu.greve.web.actions.LoginBean;
 
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
 
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            String reqURI = req.getRequestURI();
            if(reqURI.indexOf("/Compte.xhtml") >= 0) {
            	if(ses != null) {
            		String uname = (String)ses.getAttribute("username");
            		if(!uname.equals("admin")) {
                        res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml");  //Deconnexion
            		}
            	}
            }
            if ( reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null) || reqURI.contains("javax.faces.resource") ) {
            	
            	
            	chain.doFilter(request, response);
            	if(reqURI.indexOf("/logout.xhtml") >= 0) {
                	ses.invalidate();
                    res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml");  //Deconnexion
                }
            	
            }
            else   
                   res.sendRedirect(req.getContextPath() + "/faces/facelets/login.xhtml");  // Si Aucun utilisateur n'est connecté , rediger vers login
      }
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    } //doFilter
 
    @Override
    public void destroy() {
         
    }
}