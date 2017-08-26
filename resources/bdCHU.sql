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

CREATE TABLE EMPLOYE (
cin  VARCHAR(100) PRIMARY KEY,
idSe INT FOREIGN KEY REFERENCES Service(idSe),
nomCompletFr VARCHAR(100),
nomCompletAr VARCHAR(100),
dateN VARCHAR(100),
sexe VARCHAR(100),
dateArr VARCHAR(100),
idG INT FOREIGN KEY REFERENCES Grade(idG),
idCr INT FOREIGN KEY REFERENCES Corps(idCr),
idCa INT FOREIGN KEY REFERENCES Cadre(idCa),
budget VARCHAR(100),
observation VARCHAR(100),
echelle INT,
echelon INT,
indice INT
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