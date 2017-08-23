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