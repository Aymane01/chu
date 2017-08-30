package org.chu.greve.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
import org.chu.greve.business.InterneBusiness;
import org.chu.greve.business.InterneBusinessImp;
import org.chu.greve.business.ResidentBusiness;
import org.chu.greve.business.ResidentBusinessImpl;
import org.chu.greve.dao.InterneDaoHibernate;
import org.chu.greve.dao.ResidentDaoImpl;
import org.chu.greve.models.Interne;
import org.chu.greve.models.Resident;
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
	private InterneBusiness interneB;
	private ResidentBusiness residentB;
	private List<Interne> internes;
	private List<Resident> residents;
	private String target;
	private DefaultStreamedContent file;
	private InputStream stream;
	private boolean unMois;
	private String query;
	private String[] head = {"ROYAUME DU MAROC"
			,"MINISTERE DE LA SANTE"
			,"CENTRE HOSPITALO-UNIVERSITAIRE HASSAN II "                                                  
			,"D.R.H.F.C"
			};
	  private String File="C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\header.png";
	@PostConstruct
	  public void init() {
		interneB = new InterneBusinessImp(new InterneDaoHibernate(HibernateUtil.getSessionFactory()));
		residentB = new ResidentBusinessImpl(new ResidentDaoImpl(HibernateUtil.getSessionFactory()));
		

		internes = interneB.selectAllInterne();
		residents = residentB.listResident();
	}
	  public DocumentCreator() {
		target="C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\test.docx";
		try {
			stream = new FileInputStream(target);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public boolean isUnMois() {
		return unMois;
	}
	public void setUnMois(boolean unMois) {
		this.unMois = unMois;
	}
	public DefaultStreamedContent getFile() {
		return file;
	}
	public void setFile(DefaultStreamedContent file) {
		this.file = file;
	}
	public int createAttestationSalaireInterne(Interne interne, boolean unMois) {
		System.out.println(interne.getDateRecru());
		file = new DefaultStreamedContent(stream, "doc/docx", "attest_salaire_" + interne.getNomFr() + ".docx");
		XWPFDocument doc= new XWPFDocument();
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
		 DateFormat format2 = new SimpleDateFormat("MMMM");
		 Date d = new Date();
		  // the body conterne
		  
		  //header
			  XWPFParagraph paragraph = doc.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  try {
				run.addPicture(new FileInputStream(File), XWPFDocument.PICTURE_TYPE_JPEG, File, Units.toEMU(450) , Units.toEMU(100));
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
				dateAr = dateFormat2.parse(interne.getDateRecru().replace("-", "/"));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date  dateArr = new Date(dateAr.getYear(), dateAr.getMonth() +1, dateAr.getDay());
			System.out.println("Date d'aujourd'hui : " + d.toGMTString());
			System.out.println("Date de recrutement : " + dateArr.toGMTString());
			  if(d.after(dateArr)) {
				  	System.out.println("after");
					run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) " + interne.getNomFr() + " " + interne.getPrenomFr() +   " (CIN N° : " +  interne.getCin() + " ), Interne dudit Centre, perçoit une indemnité de fonction au taux mensuel de 3400,00 Dh."); 

				  }else {
					  System.out.println("before");
					  run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) " +  interne.getNomFr()  + " (CIN N° : " +  interne.getCin() + " ), percevra par le Ministère de la Santé et lors de la régularisation de sa situation Administrative et pécuniaire une indemnité de fonction au taux mensuel net de 3400,00 Dh. "); 
				  }
			  
			 
		  }else if(unMois == false) {
			  Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(interne.getDateRecru().replace("-", "/"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				Date  dateArr = new Date();
				dateArr.setYear(dateAr.getYear());
				dateArr.setMonth(dateAr.getMonth() + 1);
				dateArr.setDate(dateAr.getDay());
				
				System.out.println("Date d'aujourd'hui : " + d.toGMTString());
				System.out.println("Date de recrutement : " + dateArr.toGMTString());
			  if(d.after(dateArr)) {
				  Date d1 = new Date(d.getYear(), d.getMonth() , d.getDay());
				  Date d2 = new Date(d.getYear(), d.getMonth() -1 , d.getDay());
				  Date d3 = new Date(d.getYear(), d.getMonth() -2 , d.getDay());
				  run.setText("      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) " 
				  +  interne.getNomFr()  + " (CIN N° : " +  interne.getCin() +
				  " ), Interne dudit Centre, a perçu  par le Ministère de la Santé au titre des mois de " +
				  format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3) +" de l’année 2017 une indemnité de fonction au taux mensuel de 3400,00 Dh."); 
				  System.out.println(format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3));
			  }else {
				  System.out.println("C'est un nouveau Employée pour avoir une attestation de 3 mois , Essayez 1 mois");
			  }
			  
			 }
		  run.setTextPosition(40);
		  run.addBreak();

		  paragraph = doc.createParagraph();
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);
		  run.setText("      La présente attestation est délivrée à l’intéressé(e) sur sa demande pour servir et valoir ce que de droit.");
		  
		  
		  paragraph = doc.createParagraph();
		  paragraph.setAlignment(ParagraphAlignment.RIGHT);
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);

		  run.setText(" Fès, le " + dateFormat.format(d));
		  
		  try {
			doc.write(new FileOutputStream("C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\test.docx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return 1;
	}
	
	public int createAttestationSalaireResident(Resident resident, boolean unMois) {
		file = new DefaultStreamedContent(stream, "doc/docx", "attest_salaire_" + resident.getNomFr() + ".docx");
		XWPFDocument doc= new XWPFDocument();
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
		 DateFormat format2 = new SimpleDateFormat("MMMM");
		 Date d = new Date();
		  // the body conterne
		  
		  //header
			  XWPFParagraph paragraph = doc.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  try {
				run.addPicture(new FileInputStream(File), XWPFDocument.PICTURE_TYPE_JPEG, File, Units.toEMU(450) , Units.toEMU(100));
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
				dateAr = dateFormat2.parse(resident.getDateArrive().replace("-", "/"));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date  dateArr = new Date(dateAr.getYear(), dateAr.getMonth() +1, dateAr.getDay());
			System.out.println("Date d'aujourd'hui : " + d.toGMTString());
			System.out.println("Date de recrutement : " + dateArr.toGMTString());
			  if(d.after(dateArr)) {
				  	System.out.println("after");
					run.setText("             Le Directeur du Centre Hospitalo-universitaire Hassan II, atteste par la présente, que M(me) le Dr. " 
				  	+ resident.getPrenomFr() + " " + resident.getNomFr() +" (CIN N° : " + resident.getCin() + "), Médecin Résident(e) " +
				  	resident.getStatus() +  " en formation audit Centre "  +
				  	", perçoit une indemnité de fonction au taux mensuel de 3500.00 Dh."); 

				  }else {
					  System.out.println("before");
					  run.setText("			Le Directeur du Centre Hospitalo-universitaire Hassan II de Fès, atteste par la présente que M(me) le Dr. " + resident.getPrenomFr() + " " + resident.getNomFr() + "      (CIN N°: " + resident.getCin() +") Médecin Résident(e) " + resident.getStatus() +" en formation au Centre Hospitalo-universitaire Hassan II de Fès, percevra par ledit Centre et lors de la régularisation de sa situation administrative et pécuniaire une indemnité de fonction mensuelle d’un montant de 3.500,00 DHS."); 
				  }
			  
			 
		  }else if(unMois == false) {
			  Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(resident.getDateArrive().replace("-", "/"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				Date  dateArr = new Date();
				dateArr.setYear(dateAr.getYear());
				dateArr.setMonth(dateAr.getMonth() + 1);
				dateArr.setDate(dateAr.getDay());
				
				System.out.println("Date d'aujourd'hui : " + d.toGMTString());
				System.out.println("Date de recrutement : " + dateArr.toGMTString());
			  if(d.after(dateArr)) {
				  Date d1 = new Date(d.getYear(), d.getMonth() , d.getDay());
				  Date d2 = new Date(d.getYear(), d.getMonth() -1 , d.getDay());
				  Date d3 = new Date(d.getYear(), d.getMonth() -2 , d.getDay());
				  run.setText("		 Le Directeur du Centre Hospitalo-universitaire Hassan II, atteste par la présente, que M(me) le Dr. " + resident.getNomFr() + " " + resident.getPrenomFr() + " (CIN N° : " + resident.getCin() +"), Médecin Résident(e) " + resident.getStatus() +" en formation audit Centre, a perçu au titre des mois de " + format2.format(d1) +" , " + format2.format(d2) +" et " + format2.format(d3) +" de l’année 2017 une indemnité de fonction au taux mensuel de 3500.00 Dh.");
				
			  }else {
				  System.out.println("C'est un nouveau Employée pour avoir une attestation de 3 mois , Essayez 1 mois");
			  }
			  
			 }
		  run.setTextPosition(40);
		  run.addBreak();

		  paragraph = doc.createParagraph();
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);
		  run.setText("      La présente attestation est délivrée à l’intéressé(e) sur sa demande pour servir et valoir ce que de droit.");
		  
		  
		  paragraph = doc.createParagraph();
		  paragraph.setAlignment(ParagraphAlignment.RIGHT);
		  run=paragraph.createRun();
		  run.setFontSize(18);
		  run.setFontFamily("Times New Roman");
		  run.setTextPosition(40);

		  run.setText(" Fès, le " + dateFormat.format(d));
		  
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
	public List<String> getPropositions(String query) {
		List<String> props = new Vector<>();
		
		for (Resident resident : residents) {
			
			if(resident.getNomFr().contains(query) || resident.getPrenomFr().contains(query)) {

				props.add(resident.getNomFr() + " " + resident.getPrenomFr());
			}
			
		}
		for (Interne interne : internes) {
			if(interne.getNomFr().contains(query) || interne.getPrenomFr().contains(query)) {

				props.add(interne.getNomFr() + " " + interne.getPrenomFr());
			}
		}
		
		return props;
		
	}
	public void createAttestationSalaire(Object fonctionnaire,boolean unMois) {
		if(fonctionnaire instanceof Interne) {
			System.out.println("dans l'instance interne");
			createAttestationSalaireInterne((Interne) fonctionnaire, unMois);
			
		}else if(fonctionnaire instanceof Resident) {
			createAttestationSalaireResident((Resident) fonctionnaire, unMois);
		}else {
			System.out.println("no instance");
		}
	}
	public Object getFonctionnaire(String name) {
		System.out.println(name);
		for (Resident resident : residents) {
			String chaine = resident.getNomFr() + " " + resident.getPrenomFr();
			if(chaine.equals(name)) {
				return resident;
			}
		}
		for (Interne interne : internes) {
			String chaine = interne.getNomFr() + " " + interne.getPrenomFr();
			if(chaine.equals(name)) {
				return interne;
			}
		}
		
		return null;
		
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
