DROP DATABASE IF EXISTS presence;
CREATE DATABASE presence;
USE presence;
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
CREATE TABLE Presence (
    numEleve INT(10) AUTO_INCREMENT NOT NULL,
    numFiche INT(10) NOT NULL,
    present BOOLEAN,
    PRIMARY KEY (numEleve, numFiche)
);
ALTER TABLE Eleve
ADD CONSTRAINT FK_Eleve_numClasse FOREIGN KEY (numClasse) REFERENCES Classe (numClasse);
ALTER TABLE Matiere
ADD CONSTRAINT FK_Matiere_numEnseignant FOREIGN KEY (numEnseignant) REFERENCES Enseignant (numEnseignant);
ALTER TABLE FichePresence
ADD CONSTRAINT FK_FichePresence_numEnseignant FOREIGN KEY (numEnseignant) REFERENCES Enseignant (numEnseignant);
ALTER TABLE Presence
ADD CONSTRAINT FK_Presence_numEleve FOREIGN KEY (numEleve) REFERENCES Eleve (numEleve);
ALTER TABLE Presence
ADD CONSTRAINT FK_Presence_numFiche FOREIGN KEY (numFiche) REFERENCES FichePresence (numFiche);