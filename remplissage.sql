insert into PROFESSEUR (nomProfesseur, prenomProfesseur, login, motDePasse)
   values ('Jean', 'TOTO', 'jtoto', 'unmdp');

insert into PROMOTION (intitulePromo, idProfesseur) values ('SI', 1);

insert into GROUPE (intituleGroupe, intitulePromo) values ('SI1A', 'SI');

insert into ETUDIANT (numEtudiant, nomEtudiant, prenomEtudiant, motDePasse, intituleGroupe)
  values ('210323', 'DUPONT', 'RENE', 'confiture', 'SI1A');
