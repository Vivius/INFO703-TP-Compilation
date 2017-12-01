package fr.usmb.m1isc.compilation.tp34;

import java_cup.runtime.Symbol;

import javax.net.ssl.SSLContext;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception  {
		 LexicalAnalyzer yy;
		 if (args.length > 0)
		        yy = new LexicalAnalyzer(new FileReader(args[0])) ;
		    else
		        yy = new LexicalAnalyzer(new InputStreamReader(System.in)) ;
		@SuppressWarnings("deprecation")
		parser p = new parser (yy);
		Symbol s = p.parse( );
		Arbre arbre = (Arbre)s.value;
		System.out.println(arbre);

		GeneratorAsm asm = new GeneratorAsm(arbre);
		//asm.detectVars(arbre);
		asm.makeCode(arbre);
		System.out.println(asm.getCode());
	}
}
