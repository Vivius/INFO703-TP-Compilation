package fr.usmb.m1isc.compilation.tp34;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    /**
     * Détecte l'ensemble des variables du programme.
     *
     * @param arbre Arbre
     */
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

    /**
     * Génère le code assembleur d'une façon récursive.
     * L'arbre passé en paramètre doit être la racine.
     *
     * @param arbre Arbre
     */
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
                case PLUS:
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");
                    code.add("add eax, ebx");
                    break;
                case MOINS:
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");
                    code.add("sub ebx, eax");
                    code.add("mov eax, ebx");
                    break;
                case MOD:
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");
                    code.add("mov ecx, eax");
                    code.add("div ecx, ebx");
                    code.add("mul ecx, ebx");
                    code.add("sub eax, ecx");
                    break;

                case SEMI:
                    makeCode(arbre.getGauche());
                    makeCode(arbre.getDroit());
                    break;
            }
        }
    }

    /**
     * Permet de générer l'ensemble du code assembleur.
     *
     * @return String
     */
    public String generateAsm() {
        vars.clear();
        code.clear();
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

    /**
     * Permet d'enregistrer le fichier assembleur résultant de l'arbre synthaxique.
     *
     * @param chemin Path
     * @throws IOException
     */
    public void saveAsmFile(Path chemin) throws IOException {
        Files.write(chemin, generateAsm().getBytes());
    }

    @Override
    public String toString() {
        return "GeneratorAsm{" +
                "arbre=" + arbre +
                ", vars=" + vars +
                ", code=" + code +
                '}';
    }
}
