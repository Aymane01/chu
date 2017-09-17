package org.chu.greve.util;

import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class MyDateConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent c, String s) {
		return s;
	}

	public String getAsString(FacesContext context, UIComponent c, Object s) {
		return (String) s;
	}

	static String reverseDay(String date) {
		String[] d = date.split("-");
		return d[2] + "/" + d[1] + "/" + d[0];
	}
}
