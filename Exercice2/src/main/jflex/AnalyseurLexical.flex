/* --------------------------Section de Code Utilisateur---------------------*/
package fr.usmb.m1isc.compilation.tp34;
import java_cup.runtime.Symbol;

%%

/* -----------------Section des Declarations et Options----------------------*/
// nom de la class a generer
%class LexicalAnalyzer
%unicode
%line   // numerotation des lignes
%column // numerotation caracteres par ligne

// utilisation avec CUP 
%cup

// code a ajouter dans la classe produite
%{

%}

/* definitions regulieres */

variable    = [a-zA-Z]+
chiffre 	= [0-9]
espace 		= \s+|\t+
mod 		= "mod"|"MOD"
commentaire = \/\*.*\*\/|\/\/.*
let         = "LET"|"let"
if          = "IF"|"if"
then        = "THEN"|"then"
else        = "ELSE"|"else"
do          = "DO"|"do"
while       = "WHILE"|"while"
not         = "NOT"|"not"
and         = "AND"|"and"
or          = "OR"|"or"
input       = "INPUT"|"input"
cst         = "CST"|"cst"
nil         = "NIL"|"nil"
outout      = "OUTPUT"|"output"

%% 
/* ------------------------Section des Regles Lexicales----------------------*/

/* regles */

{let}	        { return new Symbol(sym.LET, yyline, yycolumn) ;}
{if}            { return new Symbol(sym.IF, yyline, yycolumn) ;}
{then}          { return new Symbol(sym.THEN, yyline, yycolumn) ;}
{else}          { return new Symbol(sym.ELSE, yyline, yycolumn) ;}
{do}            { return new Symbol(sym.DO, yyline, yycolumn) ; }
{while}         { return new Symbol(sym.WHILE, yyline, yycolumn) ;}
{not}           { return new Symbol(sym.NOT, yyline, yycolumn) ;}
{and}           { return new Symbol(sym.AND, yyline, yycolumn) ;}
{or}            { return new Symbol(sym.OR, yyline, yycolumn) ;}
{cst}           { return new Symbol(sym.CST, yyline, yycolumn, new String(yytext())) ;}
{input}         { return new Symbol(sym.INPUT, yyline, yycolumn) ;}
{outout}        { return new Symbol(sym.OUTPUT, yyline, yycolumn) ;}
{nil}           { return new Symbol(sym.NIL, yyline, yycolumn) ;}
"<"             { return new Symbol(sym.INF, yyline, yycolumn) ;}
"<="            { return new Symbol(sym.INF_EQUALS, yyline, yycolumn) ;}
"("			    { return new Symbol(sym.PAR_G, yyline, yycolumn) ;}
")"			    { return new Symbol(sym.PAR_D, yyline, yycolumn) ;}
"+"			    { return new Symbol(sym.PLUS, yyline, yycolumn) ;}
"-"			    { return new Symbol(sym.MOINS, yyline, yycolumn) ;}
"/"			    { return new Symbol(sym.DIV, yyline, yycolumn) ;}
{mod}		    { return new Symbol(sym.MOD, yyline, yycolumn) ;}
"*"			    { return new Symbol(sym.MUL, yyline, yycolumn) ;}
";"			    { return new Symbol(sym.SEMI, yyline, yycolumn) ;}
"="			    { return new Symbol(sym.EQUALS, yyline, yycolumn) ;}
{chiffre}+	    { return new Symbol(sym.ENTIER, yyline, yycolumn, new Integer(yytext())) ;}
{variable}	    { return new Symbol(sym.IDENT, yyline, yycolumn, new String(yytext())) ;}
{espace} 	    { /*espace : pas d'action*/ }
{commentaire}   { /*commentaire : pas d'action */ }
.			    { return new Symbol(sym.ERROR, yyline, yycolumn) ;}