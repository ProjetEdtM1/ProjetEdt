insert into PROFESSEUR (nomProfesseur, prenomProfesseur, login, motDePasse)
   values ('Jean', 'TOTO', 'jtoto', 'unmdp');

insert into PROMOTION (intitulePromo, idProfesseur) values ('SI', 1);

insert into GROUPE (intituleGroupe, intitulePromo) values ('SI1A', 'SI');

insert into SALLE(NUMEROSALLE) values(1);
insert into SALLE(NUMEROSALLE) values(2);
insert into SALLE(NUMEROSALLE) values(3);

insert into ETUDIANT (numEtudiant, nomEtudiant, prenomEtudiant, motDePasse, intituleGroupe)
  values ('210323', 'DUPONT', 'RENE', 'confiture', 'SI1A');

 insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Math',10,10,10);
 
  insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Histoire',10,10,10);
 
  insert into MODULE (INTITULEMODULE,NBHEURECM,NBHEURETD,NBHEURETP)
 values('Français',10,10,10);
 
  insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Math','2018-11-26','8:00:00','10:00:00',3)
 
   insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Français','2018-11-27','10:00:00','12:00:00',3)
 
   insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Histoire','2018-11-29','13:00:00','15:00:00',3)
 
 
 insert into SUIVRE(INTITULEGROUPE,IDPROFESSEUR,INTITULEMODULE,DATECOURS,HEUREDEBCOURS,HEUREFINCOURS,NUMEROSALLE)
 values('SI1A',1,'Français','2018-11-28','12:00:00','13:00:00',3)