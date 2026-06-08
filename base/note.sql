DROP DATABASE IF EXISTS note;
CREATE DATABASE note;
USE note;
CREATE TABLE Eleve (
    numEleve INT(10) AUTO_INCREMENT NOT NULL,
    nomEleve VARCHAR(255),
    prenomEleve VARCHAR(255),
    numClasse INT(10),
    PRIMARY KEY (numEleve)
);
CREATE TABLE Matiere (
    numMatiere INT(10) AUTO_INCREMENT NOT NULL,
    libelleMatiere VARCHAR(255),
    coefficient INT(10),
    numEnseignant INT(10),
    PRIMARY KEY (numMatiere)
);
CREATE TABLE Enseignant (
    numEnseignant INT(10) AUTO_INCREMENT NOT NULL,
    nomEnseignant VARCHAR(255),
    prenomEnseignant VARCHAR(255),
    PRIMARY KEY (numEnseignant)
);
CREATE TABLE FichePresence (
    numFiche INT(10) AUTO_INCREMENT NOT NULL,
    dateFiche DATE,
    heureFiche TIME,
    numEnseignant INT(10),
    PRIMARY KEY (numFiche)
);
CREATE TABLE Classe (
    numClasse INT(10) AUTO_INCREMENT NOT NULL,
    libelleClasse VARCHAR(255),
    PRIMARY KEY (numClasse)
);
CREATE TABLE SessionExam (
    numEleve INT(10) AUTO_INCREMENT NOT NULL,
    numMatiere INT(10) NOT NULL,
    dateSession DATE,
    note FLOAT(15),
    PRIMARY KEY (numEleve, numMatiere)
);
ALTER TABLE Eleve
ADD CONSTRAINT FK_Eleve_numClasse FOREIGN KEY (numClasse) REFERENCES Classe (numClasse);
ALTER TABLE Matiere
ADD CONSTRAINT FK_Matiere_numEnseignant FOREIGN KEY (numEnseignant) REFERENCES Enseignant (numEnseignant);
ALTER TABLE FichePresence
ADD CONSTRAINT FK_FichePresence_numEnseignant FOREIGN KEY (numEnseignant) REFERENCES Enseignant (numEnseignant);
ALTER TABLE SessionExam
ADD CONSTRAINT FK_Session_numEleve FOREIGN KEY (numEleve) REFERENCES Eleve (numEleve);
ALTER TABLE SessionExam
ADD CONSTRAINT FK_Session_numMatiere FOREIGN KEY (numMatiere) REFERENCES Matiere (numMatiere);