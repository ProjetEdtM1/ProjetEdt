insert into PROFESSEUR (nomProfesseur, prenomProfesseur, login, motDePasse)
   values ('Jean', 'TOTO', 'ptoto', 'unmdp');

insert into PROFESSEUR (nomProfesseur, prenomProfesseur, login, motDePasse)
   values ('Jean', 'TATA', 'ptata', 'unmdp');


insert into PROMOTION (intitulePromo, idProfesseur) values ('SI', 1);

insert into GROUPE (intituleGroupe, intitulePromo) values ('SI1A', 'SI');
insert into GROUPE (intituleGroupe, intitulePromo) values ('SI1', 'SI');
insert into GROUPE (intituleGroupe, intitulePromo) values ('SI2', 'SI');

insert into SALLE(NUMEROSALLE) values(1);
insert into SALLE(NUMEROSALLE) values(2);
insert into SALLE(NUMEROSALLE) values(3);

insert into ETUDIANT (numEtudiant, nomEtudiant, prenomEtudiant, motDePasse, intituleGroupe)
  values ('e210323', 'DUPONT', 'RENE', 'confiture', 'SI1A');
  values ('e2', 'CLAIN', 'Cyril', 'confiture', 'SI1A');


insert into RESERVER (numerosalle, idprofesseur, datereservation, heuredebres, etat)
  values (1, 1, curdate(), curtime(), 1);
insert into RESERVER (numerosalle, idprofesseur, datereservation, heuredebres, heureFinRes, etat)
  values (1,1, '2018-01-01', '10:00:00', '12:00:00', 0);

 insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Math',10,10,10);

  insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Histoire',10,10,10);

  insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Français',10,10,10);

  insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Math','2018-11-26','8:00:00','10:00:00',3);

   insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Français','2018-11-27','10:00:00','12:00:00',3);

   insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Histoire','2018-11-29','13:00:00','15:00:00',3);


 insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Français','2018-11-28','12:00:00','13:00:00',3);
