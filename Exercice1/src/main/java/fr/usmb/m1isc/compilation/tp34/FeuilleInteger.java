package fr.usmb.m1isc.compilation.tp34;

public class FeuilleInteger extends Arbre{
    private int valeur;

    public FeuilleInteger(int valeur, Type t) {
        super(t);
        this.estFeuille = true;
        this.valeur = valeur;
    }

    public FeuilleInteger(int valeur, Type t, Arbre g, Arbre d) {
        super(t, g, d);
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return "#" + valeur + " ";
    }
}
