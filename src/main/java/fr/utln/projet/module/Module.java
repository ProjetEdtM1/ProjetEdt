package fr.utln.projet.module;

public class Module {
    public String intitule;
    public int nbHeureCm;
    public int nbHeureTd;
    public int nbHeureTP;

    public static class Builder {
        private final String intitule;

        private int nbHeureCm = 0;
        private int nbHeureTd = 0;
        private int nbHeureTp = 0;

        public Builder(String intitule) {
            this.intitule = intitule;
        }

        public Builder nbHeureCm(int nbHeureCm) {
            this.nbHeureCm = nbHeureCm;
            return this;
        }

        public Builder nbHeureTd(int nbHeureTd) {
            this.nbHeureTd = nbHeureTd;
            return this;
        }

        public Builder nbHeureTp(int nbHeureTp) {
            this.nbHeureTp = nbHeureTp;
            return this;
        }

        public Module build() {
            return new Module(this);
        }
    }

    private Module(Builder builder) {
        intitule = builder.intitule;
        nbHeureCm = builder.nbHeureCm;
        nbHeureTd = builder.nbHeureTd;
        nbHeureTP = builder.nbHeureTp;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getNbHeureCm() {
        return nbHeureCm;
    }

    public int getNbHeureTd() {
        return nbHeureTd;
    }

    public int getNbHeureTP() {
        return nbHeureTP;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setNbHeureCm(int nbHeureCm) {
        this.nbHeureCm = nbHeureCm;
    }

    public void setNbHeureTd(int nbHeureTd) {
        this.nbHeureTd = nbHeureTd;
    }

    public void setNbHeureTP(int nbHeureTP) {
        this.nbHeureTP = nbHeureTP;
    }

    @Override
    public String toString(){
        return "Intitulé " + getIntitule() + ", NbHCm " + getNbHeureCm() + ", NbHTd " + getNbHeureTd() +
                ", NbHTp " + getNbHeureTP();
    }


}
