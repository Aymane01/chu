package org.chu.greve.web.actions;

import java.util.List;

import org.chu.greve.business.SpecialiteBusiness;
import org.chu.greve.models.Specialite;
import org.chu.greve.web.WebContext;

public class SpecialiteAction {
	private SpecialiteBusiness business;
	
	
	public SpecialiteAction() {
		// TODO Auto-generated constructor stub
	}


	public SpecialiteAction(SpecialiteBusiness business) {
		super();
		this.business = business;
	}


	public SpecialiteBusiness getBusiness() {
		return business;
	}


	public void setBusiness(SpecialiteBusiness business) {
		this.business = business;
	}
	
	public String addSpecialite(WebContext context) {
		Specialite spec = new Specialite(context.getParameter("intitulefr"), context.getParameter("intitulear"));
		int res = business.addSpecialite(spec);
		if(res == 1) {
			context.setMdel("model", business.selectAllSpecilite());
			return "specialite";
		}else {
			return "error";
		}
	}
	public String listSpec(WebContext context) {
		List<Specialite> specs = business.selectAllSpecilite();
		System.out.println(specs.get(24).getIntituleAr());
		System.out.println(specs.size());
		context.setMdel("model", specs);
		return "specialite";
	}
	public String removeSpec(WebContext context) {
		try {
			business.deleteSpecialite(context.getIntParameter("id"));
			context.setMdel("model", business.selectAllSpecilite());
			return "specialite";
		} catch (Exception e) {
			return "error";
		}
	}
	public String modifierSpec(WebContext context) {
		try {
			business.modifySpecialite(new Specialite(context.getIntParameter("id"), context.getParameter("intitulefr"), context.getParameter("intitulear")));
			context.setMdel("model", business.selectAllSpecilite());
			return "specialite";
		} catch (Exception e) {
			return "error";
		}
	}
}
