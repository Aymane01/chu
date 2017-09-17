CREATE DATABASE chu
go

CREATE TABLE Cadre (
idCa INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100)
) 
go

CREATE TABLE Corps (
idCr INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100)
) 
go

CREATE TABLE Grade (
idG INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100)
)
go

CREATE TABLE Specialite (
idS INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100)
) 
go

CREATE TABLE Hopital (
idH INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100)
) 
go

CREATE TABLE Service (
idSe INT PRIMARY KEY,
intituleAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
intituleFr VARCHAR(100),
fk_idHopital INT,

CONSTRAINT  fk_hopital FOREIGN KEY (fk_idHopital) REFERENCES Hopital(idH)
) 
go

CREATE TABLE Resident(
cin VARCHAR(10) PRIMARY KEY,
ppr INT,
nomFr VARCHAR(50),
prenomFr VARCHAR(50),
nomCompletAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
dateN DATE,
sexe VARCHAR(10),
nationalite VARCHAR(50),
dateArrive DATE,
budget VARCHAR (50),
observation VARCHAR (100),
echelle INT,
echelon INT,
indice INT,
idCorps INT,
idGrade INT,
idCadre INT,
idService INT,
idSpecialite INT,
status VARCHAR (100),
typeR VARCHAR (100),

	 
CONSTRAINT  fk_corps FOREIGN KEY (idCorps) REFERENCES Corps(idCr),
CONSTRAINT  fk_grade FOREIGN KEY (idGrade) REFERENCES Grade(idG),
CONSTRAINT  fk_cadre FOREIGN KEY (idCadre) REFERENCES Cadre(idCa),
CONSTRAINT  fk_service FOREIGN KEY (idService) REFERENCES Service(idSe),
CONSTRAINT  fk_specialite FOREIGN KEY (idSpecialite) REFERENCES Specialite(idS)
)
go

CREATE TABLE Professeur(
cin VARCHAR(10) PRIMARY KEY,
ppr INT,
nomFr VARCHAR(50),
prenomFr VARCHAR(50),
nomCompletAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
dateN DATE,
sexe VARCHAR(10),
nationalite VARCHAR(50),
dateArrive DATE,
budget VARCHAR (50),
observation VARCHAR (100),
echelle INT,
echelon INT,
indice INT,
idCorps INT,
idGrade INT,
idCadre INT,
idService INT,
idSpecialite INT,
isChef VARCHAR(3),
dateNomination DATE,
dateIntegration DATE
	 
CONSTRAINT  fk_corps_p FOREIGN KEY (idCorps) REFERENCES Corps(idCr),
CONSTRAINT  fk_grade_p FOREIGN KEY (idGrade) REFERENCES Grade(idG),
CONSTRAINT  fk_cadre_p FOREIGN KEY (idCadre) REFERENCES Cadre(idCa),
CONSTRAINT  fk_service_p FOREIGN KEY (idService) REFERENCES Service(idSe),
CONSTRAINT  fk_specialite_p FOREIGN KEY (idSpecialite) REFERENCES Specialite(idS)
)
go

CREATE TABLE Employe(
cin VARCHAR(10) PRIMARY KEY,
ppr INT,
nomFr VARCHAR(50),
prenomFr VARCHAR(50),
nomCompletAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
dateN DATE,
sexe VARCHAR(10),
nationalite VARCHAR(50),
dateArrive DATE,
budget VARCHAR (50),
observation VARCHAR (100),
echelle INT,
echelon INT,
indice INT,
idCorps INT FOREIGN KEY  REFERENCES Corps(idCr),
idGrade INT FOREIGN KEY  REFERENCES Grade(idG),
idCadre INT FOREIGN KEY  REFERENCES Cadre(idCa),
idService INT FOREIGN KEY  REFERENCES Service(idSe),
idSpecialite INT FOREIGN KEY  REFERENCES Specialite(idS),
status VARCHAR (100),
typeR VARCHAR (100)


)
go

CREATE TABLE Interne(
cin VARCHAR(10) PRIMARY KEY,
ppr INT,
nomFr VARCHAR(50),
prenomFr VARCHAR(50),
nomCompletAr VARCHAR(100) COLLATE Arabic_100_CS_AS_KS_WS_SC,
dateN DATE,
sexe VARCHAR(10),
nationalite VARCHAR(50),
dateArrive DATE,
budget VARCHAR (50),
observation VARCHAR (100),
echelle INT,
echelon INT,
indice INT,
idService INT FOREIGN KEY  REFERENCES Service(idSe),
status VARCHAR (100),
typeR VARCHAR (100),
DATERECRU DATE,
stage1 VARCHAR(255),
stage2 VARCHAR(255),
stage3 VARCHAR(255),
stage4 VARCHAR(255)
)
go

CREATE TABLE Greve(
idG INT PRIMARY KEY,
dateDebut DATE,
dateFin DATE
)
go

CREATE TABLE Greviste(
id INT PRIMARY KEY,
idGreve INT,
idGreviste VARCHAR(10),
idServiceGreviste INT,

CONSTRAINT  fk_greve FOREIGN KEY (idGreve) REFERENCES Greve(idG),
CONSTRAINT fk_service_greviste FOREIGN KEY (idServiceGreviste) REFERENCES Service(idSe)
)
go

CREATE TABLE jourGreve(
jour DATE,
idGreviste INT,

CONSTRAINT fk_greviste FOREIGN KEY (idGreviste) REFERENCES Greviste(id)
)
go

CREATE TABLE Compte (
id INT PRIMARY KEY,
username VARCHAR(100),
password VARCHAR(100)
)
go

