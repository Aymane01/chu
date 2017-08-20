package org.chu.greve.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="fileBean")
@RequestScoped
public class FileDownloadController {

	public StreamedContent getFile() {
		InputStream stream;
		try {
			stream = new FileInputStream(new File("/bdCHU.sql"));
			System.out.println(stream.toString());
			return new DefaultStreamedContent(stream);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
}
