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
* For nested keys in data map you can use the dot notation, like `person.age`

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
final Try<Node> nodeOptional = boolParser.parseExpression("name = test");
```

### Node Types Post Parsing
####
NumericNode
```
private final String field;

private final Object value;

private final Operator operator;

private final DataType dataType;
```

####
NumericRangeNode
```
private final String field;

private final Object fromValue;

private final Object toValue;

private final DataType fromDataType;

private final DataType toDataType;
```

####
BooleanNode
```
private Node left;

private Node right;

private LogicalOperationType operator;
```

####
UnaryNode
```
private final DataType dataType;

private final Object value;
```

####
InNode
```
private final String field;

private final List<Pair<DataType, Object>> items;
```


## Applications

### Boolean Expression Evaluator

The library can be used to evaluate a boolean expression.

The following Data Types are supported:
1. String
2. Integer
3. Long
4. Decimal
5. Boolean
6. Semantic Version

Usage examples:

Simple Numerical Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 26);
final Try<Boolean> result = booleanExpressionEvaluator.evaluate("age >= 27", data);
assertTrue(booleanOptional.isPresent());
assertFalse(booleanOptional.get());
```
Boolean Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
final Try<Boolean> result = booleanExpressionEvaluator.evaluate("name = sid AND age = 25", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```
Nested Boolean Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
data.put("num", 45);
final Try<Boolean> result = booleanExpressionEvaluator.evaluate("name:sid AND (age = 25 OR num = 44)", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```
App Version Comparison
```
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator();
final Map<String, Object> data = new HashMap<>();
data.put("app_version", "1.5.9");
final Try<Boolean> result = booleanExpressionEvaluator.evaluate("app_version < 1.5.10", data);
assertTrue(booleanOptional.isPresent());
assertTrue(booleanOptional.get());
```

The return type is `Try<Boolean>`. Failure means that parsing has failed and any fallback can be used.


[For a complete list of examples please check out the test file](src/test/java/com/github/sidhant92/boolparser/application/BooleanExpressionEvaluatorTest.java)