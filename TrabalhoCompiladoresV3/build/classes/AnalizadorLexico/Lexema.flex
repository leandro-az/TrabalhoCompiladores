package AnalizadorLexico;
import java_cup.runtime.Symbol;
import AnalizadorSintatico.sym;

%%

%cup
%public
%class ScannerJLexer
%line
%column
%type java_cup.runtime.Symbol


DO = "do" | "Do" | "DO"
END = "end" | "End" | "END"
WHILE = "while" | "While" | "WHILE"
REPEAT = "repeat" | "Repeat" | "REPEAT"
UNTIL = "until" | "Until" | "UNTIL"
IF = "if" | "If" | "IF"
THEN = "then" | "Then" | "THEN"
ELSEIF = "elseif" | "Elseif" | "ELSEIF"
ELSE = "else" | "Else" | "ELSE"
FOR = "for" | "For" | "FOR"
NIL = "nil" | "Nil" | "NILL"
FALSE = "false" | "False" | "FALSE"
TRUE = "true" | "True" | "TRUE"
IN = "in" | "In" | "IN"
LOCAL = "local" | "Local" | "LOCAL"
FUNCTION = "function" | "Function" | "FUNCTION"
RETURN = "return" | "Return" | "RETURN"
BREAK = "break" | "Break" | "BREAK" 
AND = "and" | "And" | "AND"
OR = "or" | "Or" | "OR"
NOT = "not" | "Not" | "NOT"
AP = "("
FP = ")"
AX = "{"
FX = "}"
AC = "["
FC = "]"
IGUAL = "="
TRESPT = "..."
UMPT = "."
SUM = "+" 
MEN = "-" | - 
MULT = "*"
DIV = "/"
PERCENT = "%" 
DPH = ".."
MENOR = "<"
MENORIGUAL = "<="
MAIOR = ">" 
MAIORIGUAL = ">=" 
IGUALIGUAL = "=="
APROXIGUAL = "∼="
UNIAO = "^"
VI = ","
PV = ";"
NOT = "NOT" 
TRALHA = "#"
COMENT = ("--")([a-zA-Z0-9_] ) ( [a-zA-Z0-9_] | "!" | " " | "@" | "#" | "$" | "&" | "?" | "^" |"~" | "¨"  | "§" | "£" | "¢" |"¬")* ([\r\n])
DP = ":"
STRING = ("\"")([a-zA-Z0-9_] ) ( [a-zA-Z0-9_] | "!" | "@" | "#" | "$" | "&" | "?" | "^" |"~" | "¨"  | "§" | "£" | "¢" |"¬")*("\"") | ([a-zA-Z0-9_] ) ( [a-zA-Z0-9_] | "!" | "@" | "#" | "$" | "&" | "?" | "^" |"~" | "¨"  | "§" | "£" | "¢" |"¬")*
NUMBER = ([0-9]+)(".")([0-9]+)| ([0-9]+)
NAME = ([a-zA-Z] | "_" )+
WHITE= ([ \t\r\n] | "\"" | " " )+
%{
public String lexema;
%}
%%

<YYINITIAL> {
{COMENT} {/*Ignore*/}
{WHITE} {/*Ignore*/}
{AP} {lexema=yytext(); return new Symbol(sym.AP, yychar, yyline, yytext());}
{FP} {lexema=yytext(); return new Symbol(sym.FP, yychar, yyline, yytext());}
{AX} {lexema=yytext(); return new Symbol(sym.AX, yychar, yyline, yytext());}
{FX} {lexema=yytext(); return new Symbol(sym.FX, yychar, yyline, yytext());}
{AC} {lexema=yytext(); return new Symbol(sym.AC, yychar, yyline, yytext());}
{FC} {lexema=yytext(); return new Symbol(sym.FC, yychar, yyline, yytext());}
{UNIAO} {lexema=yytext(); return new Symbol(sym.UNIAO, yychar, yyline, yytext());}
{IGUALIGUAL} {lexema=yytext(); return new Symbol(sym.IGUALIGUAL, yychar, yyline, yytext());}
{IGUAL} {lexema=yytext(); return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
{TRESPT} {lexema=yytext(); return new Symbol(sym.TRESPT, yychar, yyline, yytext());}
{UMPT} {lexema=yytext(); return new Symbol(sym.UMPT, yychar, yyline, yytext());}
{SUM} {lexema=yytext(); return new Symbol(sym.SUM, yychar, yyline, yytext());}
{MEN} {lexema=yytext(); return new Symbol(sym.MEN, yychar, yyline, yytext());}
{MULT} {lexema=yytext(); return new Symbol(sym.MULT, yychar, yyline, yytext());}
{DIV} {lexema=yytext(); return new Symbol(sym.DIV, yychar, yyline, yytext());}
{PERCENT} {lexema=yytext(); return new Symbol(sym.PERCENT, yychar, yyline, yytext());}
{DPH} {lexema=yytext(); return new Symbol(sym.DPH, yychar, yyline, yytext());}
{MENOR} {lexema=yytext(); return new Symbol(sym.MENOR, yychar, yyline, yytext());}
{MENORIGUAL} {lexema=yytext(); return new Symbol(sym.MENORIGUAL, yychar, yyline, yytext());}
{MAIOR} {lexema=yytext(); return new Symbol(sym.MAIOR, yychar, yyline, yytext());}
{TRALHA} {lexema=yytext(); return new Symbol(sym.TRALHA, yychar, yyline, yytext());}
{MAIORIGUAL} {lexema=yytext(); return new Symbol(sym.MAIORIGUAL, yychar, yyline, yytext());}
{APROXIGUAL} {lexema=yytext(); return new Symbol(sym.APROXIGUAL, yychar, yyline, yytext());}
{VI} {lexema=yytext(); return new Symbol(sym.VI, yychar, yyline, yytext());}
{PV} {lexema=yytext(); return new Symbol(sym.PV, yychar, yyline, yytext());}
{DP} {lexema=yytext(); return new Symbol(sym.DP, yychar, yyline, yytext());}
{DO} {lexema=yytext(); return new Symbol(sym.DO, yychar, yyline, yytext());}
{END} {lexema=yytext(); return new Symbol(sym.END, yychar, yyline, yytext());}
{WHILE} {lexema=yytext(); return new Symbol(sym.WHILE, yychar, yyline, yytext());}
{REPEAT} {lexema=yytext(); return new Symbol(sym.REPEAT, yychar, yyline, yytext());}
{UNTIL} {lexema=yytext(); return new Symbol(sym.UNTIL, yychar, yyline, yytext());}
{IF} {lexema=yytext(); return new Symbol(sym.IF, yychar, yyline, yytext());}
{THEN} {lexema=yytext(); return new Symbol(sym.THEN, yychar, yyline, yytext());}
{ELSEIF} {lexema=yytext(); return new Symbol(sym.ELSEIF, yychar, yyline, yytext());}
{ELSE} {lexema=yytext(); return new Symbol(sym.ELSE, yychar, yyline, yytext());}
{FOR} {lexema=yytext(); return new Symbol(sym.FOR, yychar, yyline, yytext());}
{NIL} {lexema=yytext(); return new Symbol(sym.NIL, yychar, yyline, yytext());}
{LOCAL} {lexema=yytext(); return new Symbol(sym.LOCAL, yychar, yyline, yytext());}
{FUNCTION} {lexema=yytext(); return new Symbol(sym.FUNCTION, yychar, yyline, yytext());}
{RETURN} {lexema=yytext(); return new Symbol(sym.RETURN, yychar, yyline, yytext());}
{BREAK} {lexema=yytext(); return new Symbol(sym.BREAK, yychar, yyline, yytext());}
{AND} {lexema=yytext(); return new Symbol(sym.AND, yychar, yyline, yytext());}
{OR} {lexema=yytext(); return new Symbol(sym.OR, yychar, yyline, yytext());}
{NOT} {lexema=yytext(); return new Symbol(sym.NOT, yychar, yyline, yytext());}
{IN} {lexema=yytext(); return new Symbol(sym.IN, yychar, yyline, yytext());}
{TRUE} {lexema=yytext(); return new Symbol(sym.TRUE, yychar, yyline, yytext());}
{FALSE} {lexema=yytext(); return new Symbol(sym.FALSE, yychar, yyline, yytext());}
{NUMBER} {lexema=yytext(); return new Symbol(sym.NUMBER, yychar, yyline, yytext());}
{NAME} {lexema=yytext(); return new Symbol(sym.NAME, yychar, yyline, yytext());}
{STRING} {lexema=yytext(); return new Symbol(sym.STRING, yychar, yyline, yytext());}
}

<<EOF>> { return new Symbol( sym.EOF , new String ("Fim de arquivo, elemento faltando")); }

. { throw new Error("Illegal character: "+yytext()+" at line "+(yyline+1)+", column "+(yycolumn+1) + " ERRO LEXICO"  ); }