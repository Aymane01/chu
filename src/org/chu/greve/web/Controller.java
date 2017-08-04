package org.chu.greve.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.business.SpecialiteBusinessImpl;
import org.chu.greve.dao.SpecialiteDao;
import org.chu.greve.dao.SpecialiteDaoHibernate;
import org.chu.greve.util.HibernateUtil;
import org.chu.greve.web.actions.SpecialiteAction;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpecialiteAction action;
    private String prefix = "/views/";
    private String suffix = ".jsp";
       
	@Override
	public void init() throws ServletException {
		SpecialiteDao daoS = new SpecialiteDaoHibernate(HibernateUtil.getSessionFactory());
		SpecialiteBusiness business = new SpecialiteBusinessImpl(daoS);
		action = new SpecialiteAction(business);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		WebContext context = new WebContext(request);
		String view = "Error";
		
		if(uri.endsWith("/listeSpec")){
			view = action.listSpec(context);
		}else if(uri.endsWith("/addSpec")) {
			view = action.addSpecialite(context);
		}else if(uri.endsWith("/RemoveSpec")) {
			view = action.removeSpec(context);
		}else if(uri.endsWith("/ModifierSpec")) {
			view = action.removeSpec(context);
		}
		
	view = prefix + view + suffix ;
	request.getServletContext().getRequestDispatcher(view).forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
