package fr.utln.projet.utilisateur;
/*
 * Nom de classe : Materiel
 *
 * Description   : Classe Materiel  representant le Materiel reservable
 *
 * Version       : 1.0
 *
 * Date          : 24/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

import java.util.Objects;

public class Materiel {
    private Integer idMateriel;
    private String nomMateriel;

    public Materiel() {
    }

    public Materiel(Integer idMateriel, String nomMateriel) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public Integer getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(Integer idMateriel) {
        this.idMateriel = idMateriel;
    }

    @Override
    public String toString() {
        return "numero Materiel : " + idMateriel +
                ", nom Materiel : '" + nomMateriel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materiel)) return false;
        Materiel materiel = (Materiel) o;
        return Objects.equals(getIdMateriel(), materiel.getIdMateriel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMateriel());
    }
}
