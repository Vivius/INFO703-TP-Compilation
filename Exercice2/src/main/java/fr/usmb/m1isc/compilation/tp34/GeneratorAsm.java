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
    private int id;

    public GeneratorAsm(Arbre arbre) {
        this.arbre = arbre;
        this.vars = new ArrayList<>();
        this.code = new ArrayList<>();
    }

    private int generateNewId() {
        id++;
        return id;
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
                if(!vars.contains(var)) vars.add(var);
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

                // INPUT & OUTPUT
                case INPUT:
                    code.add("in eax");
                    break;
                case OUTPUT:
                    makeCode(arbre.getGauche());
                    code.add("out eax");
                    break;

                // Expressions
                case SEMI:
                    makeCode(arbre.getGauche());
                    makeCode(arbre.getDroit());
                    break;
                case LET :
                    makeCode(arbre.getDroit());
                    code.add("mov " + ((FeuilleString)arbre.getGauche()).getValeur() + ", eax");
                    break;

                // Opérateurs arithmétiques
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
                    makeCode(arbre.getDroit());
                    code.add("push eax");
                    makeCode(arbre.getGauche());
                    code.add("pop ebx");
                    code.add("mov ecx, eax");
                    code.add("div ecx, ebx");
                    code.add("mul ecx, ebx");
                    code.add("sub eax, ecx");
                    break;

                // Opérateurs booléens
                case IF:
                    int ifID = generateNewId();
                    // If FALSE
                    makeCode(arbre.getGauche());
                    code.add("jz ELSE_" + ifID);
                    // If TRUE
                    makeCode(arbre.getDroit().getGauche());
                    code.add("jmp END_IF_" + ifID);
                    // END
                    code.add("ELSE_" + ifID + ":");
                    makeCode(arbre.getDroit().getDroit());
                    code.add("END_IF_" + ifID + ":");
                    break;

                case AND:
                    int andID = generateNewId();
                    // FALSE CASE
                    makeCode(arbre.getGauche());
                    code.add("jz END_AND_" + andID);
                    // TRUE CASE
                    makeCode(arbre.getDroit());
                    // END
                    code.add("END_AND_" + andID+ ":");
                    break;

                case OR:
                    int orID = generateNewId();

                    // TRUE CASE
                    makeCode(arbre.getGauche());
                    code.add("jnz END_OR_" + orID);
                    // TRUE CASE
                    makeCode(arbre.getDroit());
                    // END
                    code.add("END_OR_" + orID + ":");
                    break;

                case EQUALS:
                    int eqID = generateNewId();

                    // Interprétation des expressions gauche et droite du EQUALS.
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");

                    // Exécution du EQUALS avec une soustraction.
                    code.add("sub eax, ebx");

                    // FALSE CASE
                    code.add("jnz FALSE_EQ_" + eqID);
                    // TRUE CASE
                    code.add("mov eax, 1");
                    code.add("jmp END_EQ" + eqID);
                    // ENDS
                    code.add("FALSE_EQ_" + eqID + ":");
                    code.add("mov eax, 0");
                    code.add("END_EQ" + eqID + ":");
                    break;

                case NOT:
                    int notID = generateNewId();

                    makeCode(arbre.getGauche());
                    // FALSE CASE
                    code.add("jz TRUE_NOT_" + notID);
                    code.add("mov eax, 0");
                    code.add("jmp END_NOT_" + notID);
                    // TRUE CASE
                    code.add("TRUE_NOT_" + notID + ":");
                    code.add("mov eax, 1");
                    // END
                    code.add("END_NOT_" + notID + ":");
                    break;

                case INF:
                    int infID = generateNewId();

                    // Interprétation des expressions gauche et droite du INF.
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");

                    // Exécution du INF avec une soustraction.
                    code.add("sub eax, ebx");
                    code.add("jle faux_gt_" + infID); // Si valDroite (eax) - valGauche (ebx) <= 0 on retourne faux.

                    // On lance l'exécution du code suivant si INF est vrai. On place 1 dans eax pour que le flag ZF reste à 0.
                    code.add("mov eax, 1");
                    code.add("jmp sortie_gt_" + infID);

                    // Code exécuté si le INF est faux. On place 0 dans eax pour activer le flag ZF.
                    code.add("faux_gt_" + infID + ":");
                    code.add("mov eax, 0");

                    // Code exécuté si le INF est vrai. Le code des noeuds parents sera généré...
                    code.add("sortie_gt_" + infID + ":");
                    break;

                case INF_EQUALS:
                    int infEqID = generateNewId();

                    // Interprétation des expressions gauche et droite du INF.
                    makeCode(arbre.getGauche());
                    code.add("push eax");
                    makeCode(arbre.getDroit());
                    code.add("pop ebx");

                    // Exécution du INF avec une soustraction.
                    code.add("sub eax, ebx");
                    code.add("jl faux_gteq_" + infEqID); // Si valDroite (eax) - valGauche (ebx) < 0 on retourne faux.

                    // On lance l'exécution du code suivant si INF est vrai. On place 1 dans eax pour que le flag ZF reste à 0.
                    code.add("mov eax, 1");
                    code.add("jmp sortie_gteq_" + infEqID);

                    // Code exécuté si le INF est faux. On place 0 dans eax pour activer le flag ZF.
                    code.add("faux_gteq_" + infEqID + ":");
                    code.add("mov eax, 0");

                    // Code exécuté si le INF est vrai. Le code des noeuds parents sera généré...
                    code.add("sortie_gteq_" + infEqID + ":");
                    break;

                // Boucles
                case WHILE:
                    int whileID = generateNewId();

                    // Début du while et génération du code correspondant à la condition (fils gauche)
                    code.add("debut_while_" + whileID + ":");
                    makeCode(arbre.getGauche());

                    // Ecriture du corps de la boucle. S'exécute tant que ZF est différent de 1.
                    code.add("jz sortie_while_" + whileID);
                    makeCode(arbre.getDroit());
                    code.add("jmp debut_while_" + whileID);

                    // Code exécuté après l'exécution de la boucle.
                    code.add("sortie_while_" + whileID + ":");
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
        id = 0;
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
        for(String line : code) {
            if(line.contains(":"))  asm += (line + "\n");
            else                    asm += ("\t" + line + "\n");
        }
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
