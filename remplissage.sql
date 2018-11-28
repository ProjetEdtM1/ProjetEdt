insert into PROFESSEUR (nomProfesseur, prenomProfesseur, login, motDePasse)
   values ('Jean', 'TOTO', 'jtoto', 'unmdp');

insert into PROMOTION (intitulePromo, idProfesseur) values ('SI', 1);

insert into GROUPE (intituleGroupe, intitulePromo) values ('SI1A', 'SI');

insert into ETUDIANT (numEtudiant, nomEtudiant, prenomEtudiant, motDePasse, intituleGroupe)
  values ('210323', 'DUPONT', 'RENE', 'confiture', 'SI1A');

insert into SALLE (numeroSalle) values (1);

insert into RESERVER (numerosalle, idprofesseur, datereservation, heuredebres, etat)
  values (1, 1, curdate(), curtime(), 1);
insert into RESERVER (numerosalle, idprofesseur, datereservation, heuredebres, heureFinRes, etat)
  values (1,1, '2018-01-01', '10:00:00', '12:00:00', 0)
