package org.chu.greve.models;


public class Interne extends Fonctionnaire{
	private String dateRecru;
	private String stage1;
	private String stage2;
	private String stage3;
	private String stage4;
	
	public Interne() {
		// TODO Auto-generated constructor stub
	}

	public Interne(String dateRecru, String stage1, String stage2, String stage3, String stage4) {
		super();
		this.dateRecru = dateRecru;
		this.stage1 = stage1;
		this.stage2 = stage2;
		this.stage3 = stage3;
		this.stage4 = stage4;
	}
	public Interne(String cin, String nomFr, String dateN, String sexe, String dateArr,String budget,String observ,String dateRecru,String stage1,String stage2,String stage3,String stage4) {
		this(dateRecru, stage1, stage2, stage3, stage4);
		super.setCin(cin);
		super.setNomFr(nomFr);
		super.setDateN(dateN);
		super.setSexe(sexe);
		super.setDateArrive(dateArr);
		super.setBudget(budget);
		super.setObservation(observ);
	}
	public Interne(String[] row) {
		setCin(row[3]);
		setNomFr(row[2]);
		setDateN(row[4]);
		setDateRecru(row[5]);
		setSexe(row[1]);
		setStage1(row[6]);
		setStage2(row[7]);
		setStage3(row[8]);
		setStage4(row[9]);
	}
	public Interne(String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN, String sexe,
			String dateArrive) {
		super(nomFr, prenomFr, nomCompletAr, cin, dateN, sexe, dateArrive);
		// TODO Auto-generated constructor stub
	}

	public String getDateRecru() {
		return dateRecru;
	}

	public void setDateRecru(String dateRecru) {
		this.dateRecru = dateRecru;
	}

	public String getStage1() {
		return stage1;
	}

	public void setStage1(String stage1) {
		this.stage1 = stage1;
	}

	public String getStage2() {
		return stage2;
	}

	public void setStage2(String stage2) {
		this.stage2 = stage2;
	}

	public String getStage3() {
		return stage3;
	}

	public void setStage3(String stage3) {
		this.stage3 = stage3;
	}

	public String getStage4() {
		return stage4;
	}

	public void setStage4(String stage4) {
		this.stage4 = stage4;
	}
	
	
}
