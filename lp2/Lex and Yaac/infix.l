%{ 
#include "y.tab.h" 
%} 
%% 
[0-9]+ { yylval.dval=atoi(yytext); return NUMBER;} 
[0-9]*"."[0-9]+ { yylval.dval=atof(yytext); return NUMBER;} 
[a-zA-Z] { return LETTER; } 
"+" { return PLUS;} 
"-" { return MINUS;} 
"*" { return MULTIPLY;} 
"/" { return DIVIDE;} 
"(" { return OPEN;} 
")" { return CLOSE;} 
"\n" { return ENTER;} 
"$" { return 0;} 
