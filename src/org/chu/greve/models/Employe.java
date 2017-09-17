package org.chu.greve.models;

public class Employe extends Fonctionnaire {

	public Employe() {
		super();
		setGrade(new Grade());
		setSpecialite(new Specialite());
	}

	public Employe(Integer ppr, String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN,
			String sexe, String dateArrive) {
		super(ppr, nomFr, prenomFr, nomCompletAr, cin, dateN, sexe, dateArrive);

		// TODO Auto-generated constructor stub
	}

	public Employe(String nomFr, String prenomFr, String nomCompletAr, String cin, String dateN, String sexe,
			String dateArrive, Integer echelle, Integer echelon, Integer indice) {
		super(nomFr, prenomFr, nomCompletAr, cin, dateN, sexe, dateArrive, echelle, echelon, indice);

		// TODO Auto-generated constructor stub
	}
	public Employe(String[] rows) {
		setNomFr(rows[2] + " " + rows[3]);
		setNomCompletAr(rows[4]);
		
		if(rows[5] != "") {
			setPpr(Integer.parseInt(rows[5]));
		}
		
		setCin(rows[6]);
		if(!rows[7].contains("/") ) {

			setDateN("01/01/" + rows[7]);
		}else {
			setDateN(rows[7]);
		}
		setSexe(rows[8]);
		setNationalite(rows[10]);
		
		if(rows[19] != "") {
			setEchelle(Integer.parseInt(rows[19]));
		}
		if(rows[20] != "") {
			setEchelon(Integer.parseInt(rows[20]));
		}
		if(rows[23] != "") {
			setIndice(Integer.parseInt(rows[23]));
		}
		setDateArrive(rows[34]);
		setBudget(rows[36]);
		setObservation(rows[35]);
		setGrade(new Grade(rows[13]));
		setCadre(new Cadre(rows[15]));
		setSpecialite(new Specialite(rows[31]));
		setCorps(new Corps(rows[14]));
		
		
		Service s = new Service();s.setIdSe(1);
		setService(s);
	}

}
