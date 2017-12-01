package fr.usmb.m1isc.compilation.tp34;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneratorAsm {
    private Arbre arbre;
    private String asm;
    private List<String> vars;
    private List<String> code;

    public GeneratorAsm(Arbre arbre) {
        this.arbre = arbre;
        this.asm = new String();
        this.vars = new ArrayList<>();
        this.code = new ArrayList<>();
    }

    public void detectVars(Arbre arbre) {
        if(arbre != null) {
            detectVars(arbre.getGauche());
            detectVars(arbre.getDroit());
            if(arbre.getType().equals(Type.LET)) {
                String var = ((FeuilleString)arbre.getGauche()).getValeur();
                //System.out.println(var);
                vars.add(var);
            }
        }
    }

    public void makeCode(Arbre arbre) {
        if(arbre != null) {
            makeCode(arbre.getGauche());

            switch (arbre.getType())
            {
                case ENTIER :
                    code.add("mov eax, " + ((FeuilleInteger)arbre).getValeur());
                    break;
                case LET :
                    code.add("mov " + ((FeuilleString)arbre.getGauche()).getValeur() + ", eax");
                    break;
                case MUL:
                    if(arbre.getType().equals(Type.IDENT)) {
                        String valFilsGauche = ((FeuilleString)arbre.getGauche()).getValeur();
                        String valFilsDroit = ((FeuilleString)arbre.getGauche()).getValeur();
                        code.add("mov eax, " + valFilsGauche);
                        code.add("push eax");
                        code.add("mov eax, " + valFilsDroit);
                        code.add("pop ebx");
                        code.add("mul eax, ebx");
                    } else {
                        int valFilsGauche = ((FeuilleInteger)arbre.getGauche()).getValeur();
                        int valFilsDroit = ((FeuilleInteger)arbre.getGauche()).getValeur();
                        code.add("mov eax, " + valFilsGauche);
                        code.add("push eax");
                        code.add("mov eax, " + valFilsDroit);
                        code.add("pop ebx");
                        code.add("mul eax, ebx");
                    }
                    break;

            }

            makeCode(arbre.getDroit());
        }
    }

    public void generateAsm()
    {
        // Do stuff
    }

    // Accesseurs


    public Arbre getArbre() {
        return arbre;
    }

    public List<String> getVars() {
        return vars;
    }

    public List<String> getCode() {
        return code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
