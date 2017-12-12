package fr.usmb.m1isc.compilation.tp34;


public class Arbre {
    protected Type type;
    protected Arbre gauche, droit;

    // CONSTRUCTEURS

    /**
     * Constuit un arbre avec une racine ayant le type t donné.
     *
     * @param t Type
     */
    public Arbre(Type t) {
        type = t;
    }

    /**
     * Permet de constuire un arbre directement avec ses fils droit et gauche.
     *
     * @param t Type
     * @param g Arbre
     * @param d Arbre
     */
    public Arbre(Type t, Arbre g, Arbre d) {
        this(t);
        gauche = g;
        droit = d;
    }

    // ACCESSEURS

    public Type getType() {
        return type;
    }

    public Arbre getGauche() {
        return gauche;
    }

    public Arbre getDroit() {
        return droit;
    }

    @Override
    public String toString() {
        String type = this.type.toString();

        if (gauche != null) {
            if (droit != null) {
                return ("(" + type + " " + gauche.toString() + droit.toString() + ")");
            } else {
                return ("(" + type + " " + gauche.toString() + ")");
            }
        } else if (droit != null) {
            return ("(" + type + " . " + droit.toString() + ")");
        }else {
            return (type + " ");
        }
    }
}

