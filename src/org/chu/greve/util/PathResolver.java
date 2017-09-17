package org.chu.greve.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

abstract public class PathResolver {

	public PathResolver() {
	}

	static public String getAbsolutePath(String path) {
		try {
			ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			System.out.println(context.getRealPath("/"));
			return context.getRealPath(path);
		} catch (Exception e) {
			System.out.println("Cannot resolve the specfied path : " + path);
			return null;
		}
	}
}
