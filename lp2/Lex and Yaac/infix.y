%{ 
#include<stdio.h> 
#include<math.h> 
%} 
%union { 
double dval; 
char symbol; 
} 
%token<dval>NUMBER 
%token<symbol>LETTER 
%token PLUS MINUS MULTIPLY DIVIDE OPEN CLOSE ENTER 
%left PLUS MINUS 
%left DIVIDE MULTIPLY 
%nonassoc UMINUS 
%type<dval>E 
%% 
print: E ENTER { printf("\n\v VALID INFIX EXP.......\n"); exit (0); } 
; 
E:E PLUS E 
| 
E MINUS E 
| 
E MULTIPLY E 
| 
E DIVIDE E 
| 
MINUS E %prec UMINUS {$$=-$2;} 
| 
OPEN E CLOSE { $$=$2;} 
| 
NUMBER { $$=$1; } 
| 
LETTER {$$=$1;} 
; 
%% 
int main() 
{ 
printf("\n Enter infix expression: "); 
yyparse(); 
return 0; 
}
void yyerror( char *msg) 
{ 
printf("\n INVALID INFIX EXPRESSION.....: ");
} 
int yywrap(){return(1);}
