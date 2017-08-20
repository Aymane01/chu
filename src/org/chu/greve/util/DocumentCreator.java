package org.chu.greve.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.context.FacesContext;
import javax.xml.crypto.Data;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.chu.greve.models.Interne;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.primefaces.model.DefaultStreamedContent;

public class DocumentCreator {
	private String target;
	private DefaultStreamedContent file;
	private String[] head = {"ROYAUME DU MAROC"
			,"MINISTERE DE LA SANTE"
			,"CENTRE HOSPITALO-UNIVERSITAIRE HASSAN II "                                                  
			,"D.R.H.F.C"
			};
	  private String imgFile="C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\header.png";
	@PostConstruct
	  public void init() {
		file = new DefaultStreamedContent();
	}
	  public DocumentCreator() {
		target="C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\test.docx";
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/test.docx");
		file = new DefaultStreamedContent(stream, "doc/docx", "attest_salaire.docx");
	}
	public DocumentCreator(String target) {
		super();
		this.target = target;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	public DefaultStreamedContent getFile() {
		return file;
	}
	public void setFile(DefaultStreamedContent file) {
		this.file = file;
	}
	public int createAttestationSalaire(Interne interne, boolean unMois) {
		XWPFDocument doc= new XWPFDocument();
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
		 DateFormat format2 = new SimpleDateFormat("MMMM");
		 Date d = new Date();
		  // the body conterne
		  
		  //header
			  XWPFParagraph paragraph = doc.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  try {
				run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(450) , Units.toEMU(100));
			} catch (InvalidFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		  
		  paragraph = doc.createParagraph();
		  paragraph.setAlignment(ParagraphAlignment.CENTER);
		  run=paragraph.createRun();
		  run.addBreak();
		  run.setText("ATTESTATION :");
		  run.setBold(true);
		  run.setFontSize(20);
		  run.setFontFamily("Times New Roman");
		  run.setUnderline(UnderlinePatterns.DOUBLE);
		  run.addBreak();
		  run.addBreak();
		  run.addBreak();

		  paragraph = doc.createParagraph();
		  run=paragraph.createRun();  
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  if(unMois == true) {
			Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(interne.getDateRecru());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date  dateArr = new Date(dateAr.getYear(), dateAr.getMonth() -1, dateAr.getDay());
			  if(d.before(dateArr)) {
					run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la pr�sente que M(me) " + interne.getNomFr() + " (CIN N� : " +  interne.getCin() + " ), Interne dudit Centre, per�oit une indemnit� de fonction au taux mensuel de 3400,00 Dh."); 

				  }else {
					  run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la pr�sente que M(me) " +  interne.getNomFr()  + " (CIN N� : " +  interne.getCin() + " ), percevra par le Minist�re de la Sant� et lors de la r�gularisation de sa situation Administrative et p�cuniaire une indemnit� de fonction au taux mensuel net de 3400,00 Dh. "); 
				  }
			  
			 
		  }else if(unMois == false) {
			  Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(interne.getDateRecru());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				Date  dateArr = new Date(dateAr.getYear(), dateAr.getMonth() -1, dateAr.getDay());
			  if(d.before(dateArr)) {
				  Date d1 = new Date(d.getYear(), d.getMonth() , d.getDay());
				  Date d2 = new Date(d.getYear(), d.getMonth() -1 , d.getDay());
				  Date d3 = new Date(d.getYear(), d.getMonth() -2 , d.getDay());
				  run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la pr�sente que M(me) " 
				  +  interne.getNomFr()  + " (CIN N� : " +  interne.getCin() +
				  " ), Interne dudit Centre, a per�u  par le Minist�re de la Sant� au titre des mois de " +
				  format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3) +" de l�ann�e 2017 une indemnit� de fonction au taux mensuel de 3400,00 Dh."); 
				  System.out.println(format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3));
			  }else {
				  System.out.println("C'est un nouveau Employ�e pour avoir une attestation de 3 mois , Essayez 1 mois");
			  }
			  
			 }
		  run.setTextPosition(40);
		  run.addBreak();

		  paragraph = doc.createParagraph();
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);
		  run.setText("      La pr�sente attestation est d�livr�e � l�int�ress�(e) sur sa demande pour servir et valoir ce que de droit.");
		  
		  
		  paragraph = doc.createParagraph();
		  paragraph.setAlignment(ParagraphAlignment.RIGHT);
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);

		  run.setText(" F�s, le " + dateFormat.format(d));
		  
		  try {
			doc.write(new FileOutputStream("C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\test.docx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return 1;
	}
	public static void setTabStop(XWPFParagraph oParagraph, STTabJc.Enum oSTTabJc, BigInteger oPos) {
        CTP oCTP = oParagraph.getCTP();
        CTPPr oPPr = oCTP.getPPr();
        if (oPPr == null) {
            oPPr = oCTP.addNewPPr();
        }

        CTTabs oTabs = oPPr.getTabs();
        if (oTabs == null) {
            oTabs = oPPr.addNewTabs();
        }

        CTTabStop oTabStop = oTabs.addNewTab();
        oTabStop.setVal(oSTTabJc);
        oTabStop.setPos(oPos);
    }
}
