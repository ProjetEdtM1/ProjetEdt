-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 02 Novembre 2018 à 21:18
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `hyper_planning`
--

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--


CREATE TABLE IF NOT EXISTS PROFESSEUR (
  idProfesseur serial,
  nomProfesseur varchar(15),
  prenomProfesseur varchar(50),
  login varchar(15),
  motDePasse varchar(15),
  PRIMARY KEY (idProfesseur)
);

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE IF NOT EXISTS MODULE (
  intituleModule varchar(11) NOT NULL,
  nbHeureTp integer DEFAULT NULL,
  nbHeureCm integer DEFAULT NULL,
  nbHeureTd integer DEFAULT NULL,
  PRIMARY KEY (intituleModule)
);


-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE IF NOT EXISTS MATERIEL (
  idMateriel serial,
  nomMateriel varchar(15) DEFAULT NULL,
  PRIMARY KEY (idMateriel)
);

-- --------------------------------------------------------

--
-- Structure de la table `emprunter`
--

CREATE TABLE IF NOT EXISTS EMPRUNTER (
  idMateriel integer references MATERIEL(idMateriel),
  idProfesseur integer references PROFESSEUR(idProfesseur),
  dateEmprunt date DEFAULT NULL,
  heureDebEmp date DEFAULT NULL,
  heureFinEmp date DEFAULT NULL,
  PRIMARY KEY(idMateriel, idProfesseur, dateEmprunt, heureDebEmp)
);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE IF NOT EXISTS SALLE (
  numeroSalle integer NOT NULL,
  PRIMARY KEY (numeroSalle)
);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE IF NOT EXISTS PROMOTION (
  intitulePromo varchar(11) NOT NULL,
  idProfesseur integer references PROFESSEUR(idProfesseur),
  PRIMARY KEY (intitulePromo)
);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE IF NOT EXISTS GROUPE (
  intituleGroupe varchar(10) NOT NULL,
  intitulePromo varchar(11) references PROMOTION(intitulePromo),
  PRIMARY KEY (intituleGroupe)
);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE IF NOT EXISTS ETUDIANT (
  numEtudiant varchar(10),
  nomEtudiant varchar(15),
  prenomEtudiant varchar(15),
  motDePasse varchar(15),
  intituleGroupe varchar(10) references GROUPE(intituleGroupe),
  PRIMARY KEY (numEtudiant)
);

-- --------------------------------------------------------

--
-- Structure de la table `reserver`
--

CREATE TABLE IF NOT EXISTS RESERVER (
  numeroSalle integer references SALLE(numeroSalle),
  idProfesseur integer references PROFESSEUR(idProfesseur),
  dateReservation date,
  heureDebRes date,
  heureFinRes date,
  PRIMARY KEY(numeroSalle, idProfesseur, dateReservation, heureDebRes)
);

-- --------------------------------------------------------

--
-- Structure de la table `suivre`
--

CREATE TABLE IF NOT EXISTS SUIVRE (
  numEtudiant varchar(10) references ETUDIANT(numEtudiant),
  idProfesseur integer references PROFESSEUR(idProfesseur),
  intituleModule varchar(11) references MODULE(intituleModule),
  dateCours date,
  heureDebCours date,
  heureFinCours date,
  numeroSalle integer,
  Primary KEY(numEtudiant, idProfesseur, intituleModule, dateCours, heureDebCours)
);

-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
