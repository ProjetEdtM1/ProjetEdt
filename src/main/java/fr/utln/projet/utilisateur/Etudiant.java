package fr.utln.projet.utilisateur;

/*
 * Nom de classe : Etudiant
 *
 * Description   : Classe Etudiant qui comporte les données de celui-ci
 *
 * Version       : 1.3
 *
 * Date          : 08/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

import java.util.Objects;

public class Etudiant {
    private String Nom;
    private String Prenom;
    private String Groupe;
    public String Numetud;
    private String mdp;
    private String telephone;

    public Etudiant() {

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

    public Etudiant(String nom, String prenom, String groupe, String numetud, String mdp, String telephone) {
        this.mdp = mdp;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Groupe = groupe;
        this.Numetud = numetud;
        this.telephone = telephone;
    }
    public String getNom() {
        return Nom;
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

    public String getGroupe() {
        return Groupe;
    }

    public void setGroupe(String groupe) {
        Groupe = groupe;
    }

    public String getNumetud() {
        return Numetud;
    }

    public void setNumetud(String numetud) {
        Numetud = numetud;
    }

    @Override
    public String toString() {
        return
                "Nom='" + Nom + '\'' +
                        ", Prenom='" + Prenom + '\'' +
                        ", Groupe='" + Groupe + '\'' +
                        ", Numetud='" + Numetud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(Numetud, etudiant.Numetud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Numetud);
    }
}