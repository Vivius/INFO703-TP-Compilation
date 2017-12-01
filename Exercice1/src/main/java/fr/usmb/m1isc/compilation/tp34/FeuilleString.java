package fr.usmb.m1isc.compilation.tp34;

public class FeuilleString extends Arbre{
    private String valeur;

    public FeuilleString(String valeur, Type t) {
        super(t);
        this.estFeuille = true;
        this.valeur = valeur;
    }

    public FeuilleString(String valeur, Type t, Arbre g, Arbre d) {
        super(t, g, d);
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return "#" + valeur + " ";
    }
}
