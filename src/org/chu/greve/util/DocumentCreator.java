package org.chu.greve.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultStreamedContent;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.chu.greve.models.Interne;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

public class DocumentCreator {
	private String target;
	private DefaultStreamedContent file;
	private InputStream stream;
	private boolean unMois;
	private String[] head = { "ROYAUME DU MAROC", "MINISTERE DE LA SANTE", "CENTRE HOSPITALO-UNIVERSITAIRE HASSAN II ",
			"D.R.H.F.C" };
	private String imgFile = "C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\header.png";

	@PostConstruct
	public void init() {
	}

	public DocumentCreator() {
		target = "C:\\Utilisateur\\mk-15\\workspace JEE\\CHU\\CHU\\resources\\test.docx";
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

	public int createAttestationSalaire(Interne interne, boolean unMois) {
		file = new DefaultStreamedContent(stream, "doc/docx", "attest_salaire_" + interne.getNomFr() + ".docx");
		XWPFDocument doc = new XWPFDocument();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
		DateFormat format2 = new SimpleDateFormat("MMMM");
		Date d = new Date();
		// the body conterne

		// header
		XWPFParagraph paragraph = doc.createParagraph();
		XWPFRun run = paragraph.createRun();
		try {
			run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(450),
					Units.toEMU(100));
		} catch (InvalidFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
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
		run = paragraph.createRun();
		run.setFontSize(18);
		run.setFontFamily("Times New Roman");
		if (unMois == true) {
			Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(interne.getDateRecru());

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Date dateArr = new Date(dateAr.getYear(), dateAr.getMonth() + 1, dateAr.getDay());
			System.out.println("Date d'aujourd'hui : " + d.toGMTString());
			System.out.println("Date de recrutement : " + dateArr.toGMTString());
			if (d.after(dateArr)) {
				run.setText(
						"      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) "
								+ interne.getNomFr() + " (CIN N° : " + interne.getCin()
								+ " ), Interne dudit Centre, perçoit une indemnité de fonction au taux mensuel de 3400,00 Dh.");

			} else {
				run.setText(
						"      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) "
								+ interne.getNomFr() + " (CIN N° : " + interne.getCin()
								+ " ), percevra par le Ministère de la Santé et lors de la régularisation de sa situation Administrative et pécuniaire une indemnité de fonction au taux mensuel net de 3400,00 Dh. ");
			}

		} else if (unMois == false) {
			Date dateAr = null;
			try {
				dateAr = dateFormat2.parse(interne.getDateRecru());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date dateArr = new Date();
			dateArr.setYear(dateAr.getYear());
			dateArr.setMonth(dateAr.getMonth() + 1);
			dateArr.setDate(dateAr.getDay());

			if (d.after(dateArr)) {
				Date d1 = new Date(d.getYear(), d.getMonth(), d.getDay());
				Date d2 = new Date(d.getYear(), d.getMonth() - 1, d.getDay());
				Date d3 = new Date(d.getYear(), d.getMonth() - 2, d.getDay());
				run.setText(
						"      Le Directeur du Centre Hospitalo-universitaire Hassan II  atteste par la présente que M(me) "
								+ interne.getNomFr() + " (CIN N° : " + interne.getCin()
								+ " ), Interne dudit Centre, a perçu  par le Ministère de la Santé au titre des mois de "
								+ format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3)
								+ " de l’année 2017 une indemnité de fonction au taux mensuel de 3400,00 Dh.");
				System.out.println(format2.format(d1) + ", " + format2.format(d2) + " et " + format2.format(d3));
			} else {
				System.out.println("C'est un nouveau Employée pour avoir une attestation de 3 mois , Essayez 1 mois");
			}

		}
		run.setTextPosition(40);
		run.addBreak();

		paragraph = doc.createParagraph();
		run = paragraph.createRun();
		run.setFontSize(18);
		run.setFontFamily("Times New Roman");
		run.setTextPosition(40);
		run.setText(
				"      La présente attestation est délivrée à l’intéressé(e) sur sa demande pour servir et valoir ce que de droit.");

		paragraph = doc.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.RIGHT);
		run = paragraph.createRun();
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
}
