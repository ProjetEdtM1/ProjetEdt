package fr.utln.projet.utilisateur;

public class Utilisateur {
    public String nom;
    public String prenom;
    public String telephone;

    public static class Builder{
        // parametres non optionnels
        private final String nom;
        private final String prenom;

        // parametre optionnel initialises a null
        private String telephone = "";

        // les parametres obligatoires entres directement dans le .Builder
        public Builder(String nom, String prenom) {
            this.nom = nom;
            this.prenom = prenom;
        }

        // les parametres optionnels entres par la suite
        public Builder telephone(String tel) {
            telephone = tel;
            return this;
        }

        // creation d'un utilisateur
        public Utilisateur build() {
            return new Utilisateur((this));
        }
    }

    // affectation des valeurs a l'utilisateur
    private Utilisateur(Builder builder) {
        nom = builder.nom;
        prenom = builder.prenom;
        telephone = builder.telephone;
    }
}
