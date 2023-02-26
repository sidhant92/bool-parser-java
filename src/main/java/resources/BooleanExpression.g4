grammar BooleanExpression;

parse
 : expression EOF
 ;

expression
 : LPAREN expression RPAREN                                          #parentExpression
 | NOT expression                                                    #notExpression
 | left=expression op=comparator right=expression                    #comparatorExpression
 | left=expression op=binary right=expression                        #binaryExpression
 | types                                                             #typesExpression
 | field=WORD lower=numericTypes TO upper=numericTypes               #toExpression
 | field=WORD IN data=wordlist                                       #inExpression
 ;

comparator
 : GT | GE | LT | LE | EQ | NE
 ;


 wordlist
 : LPAREN WS* first=types WS* (',' WS* rest=types WS*)* RPAREN
 ;


 numericTypes
 : INTEGER | DECIMAL
 ;

 types
 : INTEGER | DECIMAL | APP_VERSION | bool | WORD |
 ;


binary
 : AND | OR
 ;

bool
 : TRUE | FALSE
 ;


IN           : 'IN' | 'in';
TO           : 'TO' | 'to';
AND          : 'AND' | 'and' | '&&';
OR           : 'OR' | 'or' | '||';
NOT          : 'NOT' | 'not';
TRUE         : 'TRUE' | 'true';
FALSE        : 'FALSE' | 'false';
NE           : '!=';
GT           : '>' ;
GE           : '>=' ;
LT           : '<' ;
LE           : '<=' ;
EQ           : '=' ;
LPAREN       : '(' ;
RPAREN       : ')' ;
DECIMAL      : [0-9]+ '.' [0-9]+;
APP_VERSION  : [0-9] ('.' INTEGER)+;
INTEGER      : [0-9]+;
WS           : [ \r\t\u000C\n]+ -> skip;
WORD         : (ALPHANUMERIC | '_' | '-' | '.' | SQ | DQ)+;
ALPHANUMERIC : [a-zA-Z0-9];
SQ           : '\''.*? '\'';
DQ           : '"'.*? '"';
