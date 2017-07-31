package org.chu.greve.web;

import javax.servlet.http.HttpServletRequest;

public class WebContext {
	private HttpServletRequest request ;
	public WebContext(HttpServletRequest request) {
		super();
		this.request = request;
	}
	public void setMdel(String key,Object model) {
		request.setAttribute(key, model);
	}
	public String getParameter(String name) {
		return request.getParameter(name);
	}
	public int getIntParameter(String name) {
		String param = request.getParameter(name);
		try {
			return Integer.parseInt(param);
		} catch (Exception e) {
			return 0;
		}
	}
	public double getDoubleParameter(String name) {
		String param = request.getParameter(name);
		try {
			return Double.parseDouble(param);
		} catch (Exception e) {
			return  0;
		}
	}
}
