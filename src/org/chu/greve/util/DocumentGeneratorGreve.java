package org.chu.greve.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.codec.language.bm.Rule.RPattern;
import org.apache.poi.sl.usermodel.TextParagraph.FontAlign;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.chu.greve.models.Fonctionnaire;
import org.chu.greve.models.Greviste;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

public class DocumentGeneratorGreve {
	private XWPFDocument document;
	Map<Integer, String> day;

	public DocumentGeneratorGreve() {
		day = new HashMap<>();
		day.put(1, " يوم واحد ");
		day.put(2, " يومين ");
		day.put(3, " 3 أيام ");
		day.put(4, " 4 أيام ");
		day.put(5, " 5 أيام ");
		day.put(6, " 6 أيام ");
		day.put(7, " 7 أيام ");
		day.put(8, " 8 أيام ");
		day.put(9, " 9 أيام ");
		day.put(11, " 11 يوما ");
		day.put(12, "  12 يوما ");
		day.put(13, " 13  يوما ");
		day.put(14, " 14  يوما ");
		day.put(15, " 15  يوما ");
		day.put(16, "  16 يوما ");
		day.put(17, " 17  يوما ");
		day.put(18, " 18  يوما ");
		day.put(19, " 19  يوما ");
		day.put(20, " 20  يوما ");
		day.put(21, " 21  يوما ");
		day.put(22, " 22  يوما ");
		day.put(23, " 23  يوما ");
		day.put(24, " 24  يوما ");
	}

	public int generateRetenueSalaire(Greviste g, String prefix) {
		Fonctionnaire f = g.getGreviste();
		try {
			document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = paragraph.createRun();
			FileInputStream img = new FileInputStream(new File(prefix + "resources/img/headerR.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/headerR.png", Units.toEMU(450),
					Units.toEMU(80));
			// PPRR et CIN
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Calibri (Corps)", 14);
			run.setText("رقم ب و  " + f.getCin() + " : ");
			run.addBreak();
			run.setText("رقم التأجير  " + f.getPpr() + " : ");
			// TITRE
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Calibri (Corps)", 18);
			run.addBreak();
			run.setText("أمر بالإقتطاع من الأجرة بسبب عدم ممارسة العمل");
			// Message
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Calibri (Corps)", 14);
			run.addBreak();
			run.setText("إن مدير المركز الإستشفائي الجامعي الحسن الثاني بفاس");
			// Contenu IMG
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			run = paragraph.createRun();
			img = new FileInputStream(new File(prefix + "resources/img/contenuR.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/contenuR.png", Units.toEMU(472),
					Units.toEMU(210));
			// Centenu Footer
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, false, "Calibri (Corps)", 14);
			run.setText("ونظرا أن ");
			run = paragraph.createRun();
			configRun(run, true, "Calibri (Corps)", 14);
			String[] translation = translateSexR(f.getSexe());
			run.setText(translation[0] + " " + f.getNomCompletAr() + " ");
			run = paragraph.createRun();
			configRun(run, false, "Calibri (Corps)", 14);
			String[] dayOrDays = dayOrDays(g);
			run.setText(translation[1] + dayOrDays[0]);
			// Days
			run = paragraph.createRun();
			configRun(run, true, "Calibri", 13);
			run.setText(dayOrDays[1]);
			run.addBreak();
			// Order
			run = paragraph.createRun();
			configRun(run, true, "Calibri", 14);
			run.setText("يأمر ب : ");
			run.addBreak();
			// Order Message
			run = createRun(paragraph, false);
			run.setText("     مباشرة الإقتطاع من الأجرة الشهرية  ");
			run = createRun(paragraph, true);
			run.setText(translation[2] + " " + f.getNomCompletAr() + " , " + f.getGrade().getIntituleAr());
			run = createRun(paragraph, false);
			run.setText(" السلم ");
			run = createRun(paragraph, true);
			run.setText(" " + f.getEchelle().toString() + " ");
			run = createRun(paragraph, false);
			run.setText(" الرتبة ");
			run = createRun(paragraph, true);
			run.setText(" " + f.getEchelon().toString() + " ");
			run = createRun(paragraph, false);
			run.setText(" الرقم الإستدلالي ");
			run = createRun(paragraph, true);
			run.setText(" " + f.getIndice().toString() + " ");
			run = createRun(paragraph, false);
			run.setText(translation[3] + " بالمركز الاستشفائي الجامعي الحسن الثاني بفاس عن مدة " + translation[4]
					+ " المحددة في ");
			run = createRun(paragraph, true);
			run.setText(day.get(g.getJours().size()) + ".");
			// Date et Signature
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			changeTextOrientation(paragraph);
			run = createRun(paragraph, true);
			String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			run.setText("  حرر بفاس في             " + date + "  : ");
			run.addBreak();
			run.setText("إمضاء                               ");
			run.addBreak();
			run.setText("السيد مدير المركز الاستشفائي الجامعي الحسن الثاني ");
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			changeTextOrientation(paragraph);
			run = createRun(paragraph, true);
			run.setText(" -  نسخة موجهة إلى" + translation[5] + "بالأمر.");
			document.write(new FileOutputStream(new File(prefix + "resources/RetenueSalaire.docx")));
			document.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	private XWPFRun createRun(XWPFParagraph p, boolean i) {
		XWPFRun r = p.createRun();
		configRun(r, i, "Calibri", 14);
		return r;
	}

	private String[] dayOrDays(Greviste g) {
		String[] t = new String[2];
		if (g.getJours().size() > 1) {
			t[0] = " أيام ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("  " + MyDateConverter.reverseDay(g.getJours().get(0)));
			for (int i = 1; i < g.getJours().size(); i++) {
				buffer.append(" و " + MyDateConverter.reverseDay(g.getJours().get(i)));
			}
			buffer.append("  ");
			t[1] = buffer.toString();
		} else {
			t[0] = "  يوم  ";
			t[1] = MyDateConverter.reverseDay(g.getJours().get(0)) + " : ";

		}
		return t;
	}

	private String[] translateSexR(String sex) {
		String[] t = new String[6];
		if (sex.equalsIgnoreCase("masculin")) {
			t[0] = " السيد";
			t[1] = " لم يمارس عمله ";
			t[2] = " للسيد ";
			t[3] = " و الذي يعمل ";
			t[4] = " تغيبه ";
			t[5] = " المعني ";

		} else {
			t[0] = " السيدة";
			t[1] = " لم تمارس عملها ";
			t[2] = " للسيدة ";
			t[3] = " و التي تعمل ";
			t[4] = " تغيبها ";
			t[5] = " المعنية ";
		}
		return t;
	}

	private void changeTextOrientation(XWPFParagraph paragraph) {
		CTP ctp = paragraph.getCTP();
		CTPPr ctppr = ctp.getPPr();
		if (ctppr == null) {
			ctppr = ctp.addNewPPr();
		}
		ctppr.addNewBidi().setVal(STOnOff.ON);
	}

	private void configRun(XWPFRun run, boolean bold, String fontFamily, int fontSize) {
		run.setBold(bold);
		run.setFontFamily(fontFamily);
		run.setFontSize(fontSize);
	}
}
