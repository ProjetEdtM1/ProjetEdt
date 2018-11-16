package fr.utln.projet.utilisateur;

/**
 * Nom de classe : Etudiant
 *
 * Description   : Classe Etudiant qui comporte les données de celui-ci
 *
 * Version       : 1.0
 *
 * Date          : 16/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

import java.util.Objects;

public class Professeur {

    public String idprofesseur;
    private String Nom;
    private String Prenom;
    private String mdp;
    private String Login;
    private String telephone;

    public Professeur() {

    }


        public String getTelephone() {
        return telephone;
    }

        public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

        public String getMdp() {
        return mdp;
    }

        public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Professeur(String nom, String prenom, String groupe, String idprofesseur, String mdp, String login, String telephone) {
        this.mdp = mdp;
        this.Nom = nom;
        this.Prenom = prenom;
        this.idprofesseur = idprofesseur;
        this.Login = login;
        this.telephone = telephone;
    }
        public String getNom() {
        return Nom;
    }

    public String getIdprofesseur() {
        return idprofesseur;
    }

    public void setIdprofesseur(String idprofesseur) {
        this.idprofesseur = idprofesseur;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

        public String getPrenom() {
        return Prenom;
    }

        public void setPrenom(String prenom) {
        Prenom = prenom;
    }



        @Override
        public String toString() {
        return
                "Nom='" + Nom + '\'' +
                        ", Prenom='" + Prenom + '\'' +
                        ", numID='" + idprofesseur;
    }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professeur professeur = (Professeur) o;
        return Objects.equals(idprofesseur, professeur.idprofesseur);
    }

        @Override
        public int hashCode() {
        return Objects.hash(idprofesseur);
    }
    }
