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
