grammar BooleanExpression;

parse
 : expression EOF
 ;

expression
 : LPAREN expression RPAREN                                          #parentExpression
 | NOT expression                                                    #notExpression
 | left=expression op=comparator right=expression                    #comparatorExpression
 | op=SUBTRACT exp=expression                                        #unaryArithmeticExpression
 | left=expression op= EXPONENT right=expression                     #arithmeticExpression
 | left=expression op= DIVIDE right=expression                       #arithmeticExpression
 | left=expression op= MULTIPLY right=expression                     #arithmeticExpression
 | left=expression op= MODULUS right=expression                      #arithmeticExpression
 | left=expression op= ADD right=expression                          #arithmeticExpression
 | left=expression op= SUBTRACT right=expression                     #arithmeticExpression
 | left=arithmeticFunction data=wordlist                             #arithmeticFunctionExpression
 | left=expression op=binary right=expression                        #binaryExpression
 | types                                                             #typesExpression
 | (field=FIELD) lower=numericTypes TO upper=numericTypes            #toExpression
 | (field=FIELD) (not=NOT)? IN data=wordlist                         #inExpression
 | (field=FIELD) op=arrayOperators data=wordlist                     #arrayExpression
 ;

comparator
 : GT | GE | LT | LE | EQ | NE
 ;

arithmeticOperator
 : MULTIPLY
 | DIVIDE
 | ADD
 | SUBTRACT
 | MODULUS
 | EXPONENT
 ;

arithmeticFunction
  : MIN
  | MAX
  | SUM
  | AVG
  | MEAN
  | MODE
  | LEN
  | MEDIAN
  | INT
  ;

 wordlist
 : LPAREN WS* first=expression WS* (',' WS* rest=expression WS*)* RPAREN
 ;

 arrayOperators
  : CONTAINS_ANY | CONTAINS_ALL
  ;


 numericTypes
 : INTEGER | DECIMAL
 ;

 types
 : INTEGER | DECIMAL | APP_VERSION | bool | WORD | FIELD |
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
CONTAINS_ALL : 'CONTAINS_ALL' | 'contains_all';
CONTAINS_ANY : 'CONTAINS_ANY' | 'contains_any';
MIN          : 'MIN' | 'min';
MAX          : 'MAX' | 'max';
AVG          : 'AVG' | 'avg';
SUM          : 'SUM' | 'sum';
MEAN         : 'MEAN' | 'mean';
MODE         : 'MODE' | 'mode';
MEDIAN       : 'MEDIAN' | 'median';
LEN          : 'LEN' | 'len';
INT          : 'INT' | 'int';
ADD          : '+';
SUBTRACT     : '-' ;
MULTIPLY     : '*' ;
DIVIDE       : '/' ;
MODULUS      : '%' ;
EXPONENT     : '^' ;
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
WORD         : SQSTR | DQSTR;
SQSTR        : '\'' .*? '\'';
DQSTR        : '"' .*? '"';
FIELD        : (ALPHANUMERIC | '_' | '.')+;
ALPHANUMERIC : [a-zA-Z0-9];
SQ           : '\''.*? '\'';
DQ           : '"'.*? '"';
