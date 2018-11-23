package fr.utln.projet.utilisateur;

/*
 * Nom de classe : Salle
 *
 * Description   : Classe Salle  representant la salle où le cour se passe
 *
 * Version       : 1.0
 *
 * Date          : 23/11/2018
 *
 * Copyright     : CLAIN Cyril
 */

import java.util.Objects;

public class Salle {
    private  Integer numerosalle;

    public Salle(Integer numerosalle) {
        this.numerosalle = numerosalle;
    }

    public Salle() {
    }

    public Integer getNumerosalle() {
        return numerosalle;
    }

    public void setNumerosalle(Integer numerosalle) {
        this.numerosalle = numerosalle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salle)) return false;
        Salle salle = (Salle) o;
        return Objects.equals(getNumerosalle(), salle.getNumerosalle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerosalle());
    }

    @Override
    public String toString() {
        return "Salle{" +
                "Salle :" + numerosalle +
                '}';
    }

}
