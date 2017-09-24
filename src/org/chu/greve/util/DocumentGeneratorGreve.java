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
	private Map<Integer, String> day;

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
		day.put(31, " يوما  (1) واحدا ");
		day.put(32, " يومان ");
		day.put(33, " تلاتة  (3) أيام ");
		day.put(34, "  اربعة  (4)  أيام  ");
		day.put(35, " خمسة (5) أيام ");
		day.put(36, " ستة  (6) أيام ");
		day.put(37, " سبعة (7) أيام ");
		day.put(38, " ثمانية (8) أيام ");
		day.put(39, " تسعة (9) أيام ");
		day.put(40, " عشرة (10) أيام ");
		day.put(41, " احدى عشر (11) يوم ");
		day.put(42, " اتنا عشر (12) يوم ");
		day.put(43, " ثلاثة عشر (13) يوم ");
		day.put(44, " اربعة عشر (14) يوم ");
		day.put(45, " خمسة عشر  (15) يوم ");

	}

	public int generateAvertissement(Greviste g, String prefix, Date DemandeExplication, Date DateReponse) {
		Fonctionnaire f = g.getGreviste();
		try {
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = paragraph.createRun();
			FileInputStream img = new FileInputStream(new File(prefix + "resources/img/headerE.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/headerE.png", Units.toEMU(450),
					Units.toEMU(80));
			// PPRR et CIN
			String[] translation = translateSexR(f.getSexe());
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Times New Roman (Titres CS)", 16);
			run.setText("مقـــــــــــــــــرر");
			// à gauche
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setFontSize(14);
			changeTextOrientation(paragraph);
			run.setText("رقم التأجير  " + f.getPpr() + " : ");
			// Contenu IMG
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			run = paragraph.createRun();
			img = new FileInputStream(new File(prefix + "resources/img/ContenuAvertissement.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/ContenuAvertissement.png",
					Units.toEMU(472), Units.toEMU(210));

			// Contenu R
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = paragraph.createRun();
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			configRun(run, false, "Times New Roman (Titres CS)", 12);
			run.setText("- بناءا على طلب الاستفسار الموجه إلى السيدة " + f.getNomCompletAr() + " بتاريخ "
					+ format.format(DemandeExplication) + " ");
			run.addBreak();
			run.setText("- بناءا على جواب المعنية بالأمر على طلب الإستفسار بتاريخ " + format.format(DateReponse) + ".");
			run.addBreak();
			run.setText("- بناءا  على رسالة الإخبار بالعقوبة التأديبية . ");
			// Decision

			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			run = paragraph.createRun();
			changeTextOrientation(paragraph);
			configRun(run, true, "Times New Roman (Titres CS)", 16);
			run.setText("يقــــــرر مايــــلي  : ");
			// Text
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = paragraph.createRun();
			changeTextOrientation(paragraph);
			configRun(run, false, "Times New Roman (Titres CS)", 12);
			run.setText("مادة فريدة: تصدر عقوبة الإنذار في حق السيدة " + f.getNomCompletAr() + " "
					+ f.getGrade().getIntituleAr() + " والتي  تعمل ب" + g.getService().getHopital().getIntituleAr()
					+ "، و ذلك مع تسجيله في ملفها الإداري. ");
			// Decision

			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = paragraph.createRun();
			changeTextOrientation(paragraph);
			configRun(run, true, "Times New Roman (Titres CS)", 16);
			run.setText("نظائر:");
			// Decision
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = paragraph.createRun();

			changeTextOrientation(paragraph);
			configRun(run, false, "Times New Roman (Titres CS)", 12);
			run.setText("- السيد الكاتب العام بالمركز الإستشفائي  الجامعي الحسن الثاني؛ ");
			run.addBreak();
			run.setText("- السيد مدير مستشفى الأنكولوجيا؛ ");
			run.addBreak();
			run.setText("- المعني بالأمر؛ ");
			run.addBreak();
			run.setText("- الملف. ");
			run.addBreak();
			// Bas
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();

			run.setFontSize(12);
			changeTextOrientation(paragraph);
			Date d = new Date();

			run.setText("فاس في ." + format.format(d) + " : ");
			run.addBreak();
			run.setText("                مدير المركز ");
			run.addBreak();
			run.setText(
					"                                                            الاستشفائي الجامعي  الحسن  الثاني   ");
			document.write(new FileOutputStream(new File(prefix + "resources/Avertissement.docx")));
			document.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int generatePunition(Greviste g, String prefix, Date reunion, int decision) {
		Fonctionnaire f = g.getGreviste();
		try {
			document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = paragraph.createRun();
			FileInputStream img = new FileInputStream(new File(prefix + "resources/img/headerE.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/headerE.png", Units.toEMU(450),
					Units.toEMU(70));
			// PPRR et CIN
			String[] translation = translateSexR(f.getSexe());
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Times New Roman (Titres CS)", 16);
			run.setText("من السيد مدير المركز الإستشفائي الجامعي الحسن الثاني");
			run.addBreak();
			run.setText("إلى");
			run.addBreak();
			run.addBreak();
			run.setText(" " + translation[0] + " : ");
			run.setText(f.getNomCompletAr());
			run.addBreak();
			run.setText(f.getGrade().getIntituleAr());
			run.addBreak();
			run.setText(g.getService().getHopital().getIntituleAr());
			// à gauche
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setFontSize(14);
			changeTextOrientation(paragraph);
			run.setText("ت إ س إ             ");
			// SUJET
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Times New Roman (Titres CS)", 14);
			run.setText("الموضوع:عقوبة تأديبية.");
			run.addBreak();
			// Contenu R
			String[] days = dayOrDays(g);
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, false, "Times New Roman (Titres CS)", 14);
			run.setText("           سلام تام بوجود مولانا الإمام.");
			run.addBreak();
			String duree;
			System.out.println(reunion);
			run.setText(
					"        و بعد، يؤسفني أن أخبرك بأنني قررت الموافقة على اقتراح لجنة البحث التمهيدي المنعقدة بتاريخ "
							+ format.format(reunion)
							+ " و التي اقترحت في حقك العقوبة التأديبية التالية: الطرد المؤقت من العمل لمدة  "
							+ day.get(decision + 30)
							+ "مع الحرمان من المرتب خلال هذه المدة باستثناء التعويضات العائلية، تطبيقا لمقتضيات المادة 24 من المرسوم رقم 2.03.535 بمثابة النظام الأساسي الخاص بمستشفى المراكز الإستشفائية و يتعين على السيد مدير مستشفى الأم و الطفل أن يرجعك إلى عملك فور انتهاء مدة الطرد المذكورة و موافاة قسم الموارد البشرية و التكوين و التعاون بمخضر التوقف و استئناف العمل.");
			run.addBreak();
			run.setText(
					"	و بالمناسبة أطلب منك التحلي بروج المسؤولية و الالتزام بواجباتك المهنية و أحذرك من العودة إلى ارتكاب الأفعال التي نسبت إليك و إلا سأكون مضطرا لإتخاد عقوبة أقسى في حقك طبقا للمقتضيات القانونية و التنظيمية الجاري بها العمل. ");
			run.addBreak();
			run.setText(
					"	وسأوافيك، في وقت لاحق، بنسخة من المقرر المجسد للعقوبة الصادرة في حقك فور التأشير عليه من طرف السيد الخازن المكلف بالأداء المنتدب لدى المركز الإستشفائي الحسن الثاني.");
			// Bas
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setFontSize(14);
			changeTextOrientation(paragraph);
			Date d = new Date();

			run.setText("فاس في ." + format.format(d) + " : ");
			document.write(new FileOutputStream(new File(prefix + "resources/Punition.docx")));
			document.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int generateArretTravail(Greviste g, String prefix) {
		Fonctionnaire f = g.getGreviste();
		try {
			document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = paragraph.createRun();
			FileInputStream img = new FileInputStream(new File(prefix + "resources/img/headerE.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/headerE.png", Units.toEMU(450),
					Units.toEMU(70));
			// PPRR et CIN
			String[] translation = translateSexR(f.getSexe());
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Times New Roman (Titres CS)", 16);
			run.setText("من السيد مدير المركز الإستشفائي الجامعي الحسن الثاني");
			run.addBreak();
			run.setText("إلى");
			run.addBreak();
			run.addBreak();
			run.setText(" " + translation[0] + " ׃ " + f.getNomCompletAr());
			run.addBreak();
			run.setText("رقم التأجير  " + f.getPpr() + " : ");
			run.addBreak();
			run.setText(g.getService().getIntituleAr());
			System.out.println(g.getService().getIntituleAr());
			run.addBreak();
			run.setText(g.getService().getHopital().getIntituleAr());
			// à gauche
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			run.setFontSize(14);
			changeTextOrientation(paragraph);
			run.setText("تحت إشراف السلم الإداري");
			// SUJET
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Times New Roman (Titres CS)", 14);
			run.setText("الموضوع   ׃  التوقيف الاحتياطي عن العمل.");
			run.addBreak();
			// Contenu R
			String[] days = dayOrDays(g);
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, false, "Times New Roman (Titres CS)", 14);
			run.setText("           سلام تام بوجود مولانا الإمام.");
			run.addBreak();
			run.setText(
					"          و بعد، يؤسفني أن أخبرك بأنني قررت توقيفك احتياطيا عن العمل مع توقيف الراتب باستثناء التعويضات العائلية، عملا بمقتضيات الفصل 32 من المرسوم  رقم2.03.535 ب 28  يونيو2003  بمثابة النظام الأساسي الخاص لمستخدمي المراكز الإستشفائية و ذلك بسبب عدم حضورك "
							+ days[0] + " " + days[1]
							+ " لمقر العمل من أجل تقديم العلاجات الضرورية لمرضى السرطان رغم أنك مسجلة بجدول الحراسة لنفس اليوم بمصلحة تعد ضمن مصالح تابعة لمستشفى مصنف قانونا كمؤسسة إستشفائية مستعجلة مما يعتبر هفوة خطيرة لعدم تقديم المساعدة و العلاجات لمرضى مصنفين في خانة الخطر.");
			run.addBreak();
			// Bas
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			paragraph.setSpacingBetween(1.5);
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, false, "Times New Roman (Titres CS)", 14);
			run.setText(
					" لذا يتعين عليك التوقف عن العمل فور توصلك بهذه الرسالة في انتظار مثولك أمام لجنة البحث التمهيدي وإن اقتضى الحال المجلس التأديبي.");
			run.addBreak();
			run.setText(
					" 		وعلى السيد مدير مستشفى الأنكولوجيا التعجيل بتبليغ مديرية المركز بمحضر التوقف عن العمل يحمل آخر عنوان شخصي لك، مشفوعا بنسخة من هذه الرسالة تحمل على هامشها عبارة توصلت بها يومه متبوعة بتوقيعك و بتاريخ التوقيع مكتوبة بخط يدك أصلي. ");
			run.addBreak();
			document.write(new FileOutputStream(new File(prefix + "resources/ArretTravail.docx")));
			document.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int generateDemandeExplication(Greviste g, String prefix, Date dateR, String heure) {
		String date = new SimpleDateFormat("dd/MM/yyyy").format(dateR);
		Fonctionnaire f = g.getGreviste();
		try {
			document = new XWPFDocument();
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = paragraph.createRun();
			FileInputStream img = new FileInputStream(new File(prefix + "resources/img/headerE.png"));
			run.addPicture(img, XWPFDocument.PICTURE_TYPE_PNG, prefix + "resources/img/headerE.png", Units.toEMU(450),
					Units.toEMU(70));
			changeTextOrientation(paragraph);
			run = paragraph.createRun();
			configRun(run, true, "Calibri", 16);
			run.addBreak();
			run.addBreak();
			run.setText("من مدير المركز الإستشفائي الجامعي الحسن الثاني");
			run.addBreak();
			run.setText("إلى");
			String[] translation = translateSexR(f.getSexe());
			run.addBreak();
			run.setText(translation[0] + " : " + f.getNomCompletAr());
			run.addBreak();
			run.setText(" رقم التأجير  " + f.getPpr() + " : ");
			run.addBreak();
			run.setText(f.getService().getIntituleAr());
			run.addBreak();
			run.setText(f.getService().getHopital().getIntituleAr());
			paragraph = document.createParagraph();
			changeTextOrientation(paragraph);
			paragraph.setAlignment(ParagraphAlignment.RIGHT);
			run = paragraph.createRun();
			configRun(run, true, "Calibri", 16);
			run.setText("ت.إ.س.إ");
			paragraph = document.createParagraph();
			changeTextOrientation(paragraph);
			paragraph.setAlignment(ParagraphAlignment.LEFT);
			run = createRun(paragraph, true, 16);
			run.setText("الموضوع : ");
			run = createRun(paragraph, false, 16);
			run.setText("طلب استفسار");
			run.addBreak();
			run = createRun(paragraph, true, 16);
			run.setText("المرجع : ");
			run = createRun(paragraph, false, 16);
			run.setText(" تقرير السيد طراف عماد حارس عام بمستشفى الإختصاصات بتاريخ  " + date + " : ");
			run.addBreak();
			run.addBreak();
			run.setText("        سلام تام بوجود مولانا الإمام،");
			paragraph = document.createParagraph();
			changeTextOrientation(paragraph);
			paragraph.setAlignment(ParagraphAlignment.BOTH);
			run = createRun(paragraph, false, 16);
			run.setText("      وبعد، لقد بلغ إلى علمي و استنادا إلى المرجع أعلاه، أنه يوم  " + date + "  على الساعة "
					+ heure
					+ " لم تمثتل لأمر السيد المدير العام للمركز الإستشفائي الجامعي الحسن الثاني لفحص أحد المرضى ب"
					+ f.getService().getIntituleAr()
					+ "  بدون أي مبرر أو عذر مقبول و بالتالي فإن موقفك هذا يعد عصيانا و رفضا لتقديم المساعدة و العلاج لمريض  في حالة خطيرة و كذا إخلالا بمقتضيات العقد المبرم بينك و بين المركز الإستشفائي الجامعي الحسن الثاني.");

			run.addBreak();
			run.addBreak();
			run.setText(
					"      لذا، و حتى يتسنى لنا عن دراية، اتخاذ الإجراءات اللازمة، أطلب منك ،و في ظرف 48 ساعة من توصلك بهذا الطلب، موافاتي و تحت إشراف السلم الإداري، بجوابك حول ما نسب إليك.");
			document.write(new FileOutputStream(new File(prefix + "resources/DemandeExplication.docx")));
			document.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
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

	private XWPFRun createRun(XWPFParagraph p, boolean i, int fontSize) {
		XWPFRun r = p.createRun();
		configRun(r, i, "Calibri", fontSize);
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
		String[] t = new String[7];
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
