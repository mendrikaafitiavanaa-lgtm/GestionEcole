DROP DATABASE IF EXISTS note;
CREATE DATABASE note;
USE note;
CREATE TABLE Eleve (
    numEleve INT(10) NOT NULL,
	numero  INT(10),
    nomEleve VARCHAR(255),
    prenomEleve VARCHAR(255),
    numClasse VARCHAR(10),
    PRIMARY KEY (numEleve)
);
CREATE TABLE Matiere (
    numMatiere INT(10) NOT NULL,
    libelleMatiere VARCHAR(255),
    coefficient INT(10),
    numEnseignant INT(10),
    PRIMARY KEY (numMatiere)
);
CREATE TABLE Enseignant (
    numEnseignant INT(10)  NOT NULL,
    nomEnseignant VARCHAR(255),
    prenomEnseignant VARCHAR(255),
    PRIMARY KEY (numEnseignant)
);
CREATE TABLE FichePresence (
    numFiche INT(10)  NOT NULL,
    dateFiche DATE,
    heureFiche TIME,
    numEnseignant INT(10),
    PRIMARY KEY (numFiche)
);
CREATE TABLE Classe (
    numClasse VARCHAR(10)  NOT NULL,
    libelleClasse VARCHAR(255),
    PRIMARY KEY (numClasse)
);
CREATE TABLE SessionExam (
    numEleve INT(10)  NOT NULL,
    numMatiere INT(10) NOT NULL,
    dateSession DATE,
    note FLOAT(15),
    PRIMARY KEY (numEleve, numMatiere)
);
CREATE TABLE Presence (
    numEleve INT(10)  NOT NULL,
    numFiche INT(10) NOT NULL,
    present varchar(1) DEFAULT '0',
    PRIMARY KEY (numEleve, numFiche)
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
ALTER TABLE FichePresence
ADD CONSTRAINT FK_FichePresence_numEnseignant FOREIGN KEY (numEnseignant) REFERENCES Enseignant (numEnseignant);
ALTER TABLE Presence
ADD CONSTRAINT FK_Presence_numEleve FOREIGN KEY (numEleve) REFERENCES Eleve (numEleve);
ALTER TABLE Presence
ADD CONSTRAINT FK_Presence_numFiche FOREIGN KEY (numFiche) REFERENCES FichePresence (numFiche);

INSERT INTO `classe` (`numClasse`, `libelleClasse`) VALUES
('1', 'SECONDE'),
('2', 'PREMIERE L'),
('3', 'TERMINALE A');
INSERT INTO `eleve` (`numEleve`, `numero`, `nomEleve`, `prenomEleve`, `numClasse`) VALUES
(1, 100, 'RALY', 'SON', '1'),
(2, 101, 'RASOA', 'PAUL', '1'),
(3, 300, 'RABY', 'LAHY', '2'),
(4, 301, 'RAVAO', 'BE', '2');
INSERT INTO `enseignant` (`numEnseignant`, `nomEnseignant`, `prenomEnseignant`) VALUES
(1, 'RAKOTO', 'BE'),
(2, 'RABE', 'KOTO');
INSERT INTO `fichepresence` (`numFiche`, `dateFiche`, `heureFiche`, `numEnseignant`) VALUES
(1, '2026-02-17', '08:27:58', 1);
INSERT INTO `fichepresence` (`numFiche`, `dateFiche`, `heureFiche`, `numEnseignant`) VALUES
(2, '2026-02-17', '08:27:58', 2)