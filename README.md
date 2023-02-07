# bool-parser-java
A Boolean Expression Parser for Java

The library can help parse complex and nested boolean expressions.
The expressions are in SQL-like syntax, where you can use boolean operators and parentheses to combine individual expressions.

An expression can be as simple as `name = Sidhant`.
A Complex expression is formed by combining these small expressions by logical operators and giving precedence using parenthesis

### Examples
#### Textual Equality

Format: `${attributeName} = ${value}`

Example: `name = john`

#### Numeric Comparisons

Format: `${attributeName} ${operator} ${value}`

Example: `price > 12.99`

The ${value} must be numeric. Supported operators are `<`, `<=`, `=`, `!=`, `>=` and `>`, with the same semantics as in virtually all programming languages.

#### Numeric Range

Format: `${attributeName} ${lowerBound} TO ${upperBound}`

Example: `price 5.99 TO 100`

`${lowerBound}` and `${upperBound}` must be numeric. Both are inclusive.

#### Boolean operators

Example:

`price < 10 AND (category:Book OR NOT category:Ebook)`

Individual filters can be combined via boolean operators. The following operators are supported:

* `OR`: must match any of the combined conditions (disjunction)
* `AND`: must match all of the combined conditions (conjunction)
* `NOT`: negate a filter

Parentheses, `(` and `)`, can be used for grouping.

#### Usage Notes
* Phrases that includes quotes, like `content = "It's a wonderful day"`
* Phrases that includes quotes, like `attribute = 'She said "Hello World"'`

## Usage
POM
```xml
    <dependencies>
        <dependency>
            <groupId>com.github.sidhant92</groupId>
            <artifactId>bool-parser-java</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
```
Gradle
```
dependencies {
	implementation "com.github.sidhant92:bool-parser-java:1.0.0"
}
```


Code
```
final BoolParser boolParser = new BoolParser();
final Try<Token> tokenOptional = boolParser.parseExpression("name = test");
```

### Token Types Post Parsing
####
StringToken
```
private final String field;

private final String value;
```

####
NumericToken
```
private final String field;

private final Object value;

private final Operator operator;

private final DataType dataType;
```

####
NumericRangeToken
```
private final String field;

private final Object fromValue;

private final Object toValue;

private final DataType fromDataType;

private final DataType toDataType;
```

####
BooleanToken
```
private Token left;

private Token right;

private LogicalOperationType operator;
```

####
UnaryToken
```
private final DataType dataType;

private final Object value;
```

####
InToken
```
private final String field;

private final List<Pair<DataType, Object>> items;
```


[For a complete list of examples please check out the test file](src/test/java/com/github/sidhant92/boolparser/application/BooleanExpressionEvaluatorTest.java)