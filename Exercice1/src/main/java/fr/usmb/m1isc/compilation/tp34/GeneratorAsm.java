package fr.usmb.m1isc.compilation.tp34;

import java.util.ArrayList;
import java.util.List;

public class GeneratorAsm {
    private Arbre arbre;
    private List<String> vars;
    private List<String> code;

    public GeneratorAsm(Arbre arbre) {
        this.arbre = arbre;
        this.vars = new ArrayList<>();
        this.code = new ArrayList<>();
    }

    private void detectVars(Arbre arbre) {
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

    private void makeCode(Arbre arbre) {
        if(arbre != null) {
            switch (arbre.getType())
            {
                // Feuilles
                case ENTIER :
                    code.add("mov eax, " + ((FeuilleInteger)arbre).getValeur());
                    break;
                case IDENT:
                    code.add("mov eax, " + ((FeuilleString)arbre).getValeur());
                    break;

                // Expressions
                case LET :
                    makeCode(arbre.getDroit());
                    code.add("mov " + ((FeuilleString)arbre.getGauche()).getValeur() + ", eax");
                    break;
                case MUL:
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");
                    code.add("mul eax, ebx");
                    break;
                case DIV:
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");
                    code.add("div ebx, eax");
                    code.add("mov eax, ebx");
                    break;
                case SEMI:
                    makeCode(arbre.getGauche());
                    makeCode(arbre.getDroit());
                    break;
            }
        }
    }

    public String generateAsm() {
        detectVars(arbre);
        makeCode(arbre);
        String asm = "";

        // Génération des variables
        asm += "DATA SEGMENT\n";
        for(String var : vars)  asm += ("\t" + var + " DD\n");
        asm += "DATA ENDS\n";

        // Génération du code assembleur
        asm += "CODE SEGMENT\n";
        for(String line : code)  asm += ("\t" + line + "\n");
        asm += "CODE ENDS\n";

        return asm;
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
