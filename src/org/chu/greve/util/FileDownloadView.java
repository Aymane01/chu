package org.chu.greve.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.sun.faces.context.ExternalContextFactoryImpl;
 

public class FileDownloadView {
     
    private StreamedContent file;
    @PostConstruct
     public  void init() {
    	
     }
    public FileDownloadView() {        
    	InputStream stream=null;
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream();
    	 System.out.println(stream == null ? "Null" : "notNull");
    	
        file = new DefaultStreamedContent(stream, "image/png", "downloaded.png");
        if(file==null) {
        	System.out.println("null");
        }
    }
 
    public StreamedContent getFile() {
        return file;
    }
}