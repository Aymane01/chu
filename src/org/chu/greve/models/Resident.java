package org.chu.greve.models;

import org.chu.greve.models.Fonctionnaire;

public class Resident extends Fonctionnaire {
	private String status;
	private String type;

	public Resident() {
		super();
	}

	public Resident(String status, String type, int ppr, String nomFr, String prenomFr, String nomCompletAr, String cin,
			String dateN, String sexe, String nationalite, String dateArrive, String budget, String observation,
			int echelle, int echelon, int indice, Corps corps, Grade grade, Cadre cadre, Service service,
			Specialite specialite) {
		super(ppr, nomFr, prenomFr, nomCompletAr, cin, dateN, sexe, nationalite, dateArrive, budget, observation,
				echelle, echelon, indice, corps, grade, cadre, service, specialite);
		this.status = status;
		this.type = type;
	}
	public Resident(String[] row) {
		//setIndice(Integer.parseInt(row[0]));
		setSexe(row[1]);
		setDateN(row[2]);
		setStatus(row[3]);
		setNomFr(row[4]);
		if(row[5] != "") {
			System.out.println(row[5]);
			setPpr(Integer.parseInt(row[5]));
		}
		
		setCin(row[6]);
		setDateArrive(row[10]);
		

		setDateArrive(row[10]);
		setObservation(row[14]);
		
		Grade g = new Grade();g.setIdG(1);
		setGrade(g);
		Cadre c = new Cadre();c.setId(1);
		setCadre(c);
		Corps cr = new Corps();cr.setId(1);
		setCorps(cr);
		Specialite sp = new Specialite();sp.setIdS(1);
		setSpecialite(sp);
		Service s = new Service();s.setIdSe(1);
		setService(s);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}