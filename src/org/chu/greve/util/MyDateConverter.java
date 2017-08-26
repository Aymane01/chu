package org.chu.greve.util;

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

}
