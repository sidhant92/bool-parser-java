# bool-parser-java

A Boolean Expression Parser for Java that helps parse and evaluate complex boolean and arithmetic expressions.

## Table of Contents

- [Overview](#overview)
- [Installation](#installation)
- [Basic Usage](#basic-usage)
- [Language Syntax Reference](#language-syntax-reference)
  - [Data Types](#data-types)
  - [Field References](#field-references)
  - [Operators](#operators)
  - [Expression Types](#expression-types)
  - [Functions](#functions)
  - [Advanced Usage](#advanced-usage)
- [Applications](#applications)
  - [Boolean Expression Evaluator](#boolean-expression-evaluator)
  - [Arithmetic Expression Evaluator](#arithmetic-expression-evaluator)
- [Node Types Post Parsing](#node-types-post-parsing)

## Overview

This library helps parse and evaluate complex and nested boolean expressions. The expressions use a SQL-like syntax, where you can use boolean operators and parentheses to combine individual expressions.

An expression can be as simple as `name = 'Sidhant'` or as complex as `(age >= 18 AND age <= 65) AND (status = 'active' OR status = 'pending')`.

## Installation

### Maven
```xml
<dependencies>
    <dependency>
        <groupId>com.github.sidhant92</groupId>
        <artifactId>bool-parser-java</artifactId>
        <version>2.0.0</version>
    </dependency>
</dependencies>
```

### Gradle
```
dependencies {
    implementation "com.github.sidhant92:bool-parser-java:2.0.0"
}
```

## Basic Usage

```java
final BoolParser boolParser = new BoolParser();
final Try<Node> nodeOptional = boolParser.parseExpression("name = 'test'");
```

There are two implementations for the parser:
- `BoolParser`: Standard implementation
- `CachedBoolParser`: Takes input for the max cache size to improve performance

## Language Syntax Reference

### Data Types

The language supports the following data types:

1. **String**: Text values enclosed in single (`'`) or double (`"`) quotes. Examples: `'hello'`, `"world"`
2. **Integer**: Whole numbers without decimal points. Examples: `42`, `-10`
3. **Long**: Large whole numbers. Example: `1611473334114`
4. **Decimal**: Numbers with decimal points (stored as BigDecimal). Examples: `3.14`, `-2.5`
5. **Boolean**: `true` or `false` (case-insensitive)
6. **Version**: Semantic version numbers. Examples: `1.0.6`, `2.3.1.5`
7. **Date**: Date values in YYYY-MM-DD format. Examples: `2023-12-25`, `2024-01-15`
8. **DateTime**: Date and time values in YYYY-MM-DD HH:MM:SS format. Examples: `2023-12-25 14:30:00`, `2024-01-15 09:15:30`
9. **Null**: Represented as `null`

### Field References

Fields reference values in the data context:

1. **Simple fields**: Direct reference to a field. Example: `age`
2. **Nested fields**: Access nested objects using dot notation. Example: `person.details.age`
3. **Default field**: When a default field is specified, operators can be used without explicitly naming the field. Example: `>= 18` (with "age" as default field)

### Operators

#### Comparison Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `=` | Equal to | `name = 'John'` |
| `!=` | Not equal to | `status != 'pending'` |
| `>` | Greater than | `age > 18` |
| `>=` | Greater than or equal to | `price >= 9.99` |
| `<` | Less than | `temperature < 32` |
| `<=` | Less than or equal to | `quantity <= 100` |

#### Logical Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `AND` or `&&` | Logical AND | `age > 18 AND status = 'active'` |
| `OR` or `\|\|` | Logical OR | `category = 'book' OR category = 'ebook'` |
| `NOT` or `not` | Logical NOT | `NOT (price < 10)` |

#### Arithmetic Operators

| Operator | Description | Example |
|----------|-------------|---------|
| `+` | Addition | `price + tax` |
| `-` | Subtraction | `total - discount` |
| `*` | Multiplication | `quantity * price` |
| `/` | Division | `total / count` |
| `%` | Modulus | `id % 10` |
| `^` | Exponent | `2 ^ 3` |

### Expression Types

#### Comparison Expressions

Compare field values against literals or other fields:

```
age > 21
name = 'Alice'
price < maxPrice
```

#### Logical Expressions

Combine expressions with logical operators:

```
(age > 21) AND (status = 'active')
category = 'book' OR category = 'ebook'
NOT (price > 100)
```

#### Range Expressions

Test if a value falls within a range (both bounds are inclusive):

```
age 18 TO 65
price 9.99 TO 49.99
```

#### Membership Expressions

Test if a value is in a set:

```
status IN ('pending', 'processing', 'shipped')
category NOT IN ('restricted', 'discontinued')
```

#### Array Operations

Test if an array contains specific values:

```
tags CONTAINS_ANY ('sale', 'new')
permissions CONTAINS_ALL ('read', 'write')
```

### Functions

#### Arithmetic Functions

| Function | Description | Example |
|----------|-------------|---------|
| `MIN` | Minimum value | `MIN(1, 2, 3)` or `MIN(numbers)` |
| `MAX` | Maximum value | `MAX(1, 2, 3)` or `MAX(numbers)` |
| `SUM` | Sum of values | `SUM(1, 2, 3)` or `SUM(numbers)` |
| `AVG` | Average of values | `AVG(1, 2, 3)` or `AVG(numbers)` |
| `MEAN` | Mean of values | `MEAN(1, 2, 3)` or `MEAN(numbers)` |
| `MEDIAN` | Median of values | `MEDIAN(1, 2, 3)` or `MEDIAN(numbers)` |
| `MODE` | Mode of values | `MODE(1, 1, 2, 3)` or `MODE(numbers)` |
| `LEN` | Length of string or array | `LEN('hello')` or `LEN(items)` |
| `INT` | Convert to integer | `INT(2.7)` or `INT(value)` |
| `DAYS_ELAPSED` | Number of days elapsed since a date | `DAYS_ELAPSED(2023-01-01)` or `DAYS_ELAPSED(startDate)` |

Functions can be used in both boolean and arithmetic contexts:

```
age > MIN(requiredAge, recommendedAge)
LEN(name) > 3
```

### Advanced Usage

#### Combining Expressions

Complex conditions can be created by combining expressions:

```
(age >= 18 AND age <= 65) AND (status = 'active' OR status = 'pending')
```

#### Arithmetic in Boolean Expressions

Arithmetic expressions can be used within boolean expressions:

```
price > (basePrice + tax)
age > (minimumAge + 5)
```

#### Function Nesting

Functions can be nested:

```
MAX(1, 2, MIN(5, 7, 87))
```

#### Null Checks

Check if a field is null or not:

```
lastLogin = null
email != null
```

#### Working with Arrays

Arrays can be used with specific operators and functions:

```
// Check if the 'roles' array contains any of the specified values
roles CONTAINS_ANY ('admin', 'editor')

// Check if the 'permissions' array contains all of the specified values
permissions CONTAINS_ALL ('read', 'write', 'delete')

// Get the length of an array
LEN(items) > 0
```

#### Working with Dates

Date and DateTime values can be used in comparisons and with the DAYS_ELAPSED function:

```
// Date comparisons
startDate > 2023-01-01
endDate <= 2024-12-31

// DateTime comparisons
lastLogin >= 2023-06-15 09:30:00
createdAt < 2024-01-01 00:00:00

// Using DAYS_ELAPSED function
DAYS_ELAPSED(startDate) > 30
DAYS_ELAPSED(2023-01-01) < 365
```

#### String Handling

- Strings must be enclosed in single or double quotes
- Quotes within strings can be handled:
  - `content = "It's a wonderful day"`
  - `attribute = 'She said "Hello World"'`

#### Case Sensitivity

Operators and function names are case-insensitive:

```
// These are equivalent
name = 'John' AND age > 21
NAME = 'John' and AGE > 21
```

However, field names and string values are case-sensitive.

## Applications

### Boolean Expression Evaluator

The library can be used to evaluate boolean expressions against a data context.

Usage examples:

#### Simple Numerical Comparison
```java
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("age", 26);
final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("age >= 27", data);
assertTrue(resultOptional.isSuccess());
assertFalse(resultOptional.get());
```

#### Boolean Comparison
```java
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND age = 25", data);
assertTrue(resultOptional.isSuccess());
assertTrue(resultOptional.get());
```

#### Nested Boolean Comparison
```java
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("age", 25);
data.put("name", "sid");
data.put("num", 45);
final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("name = 'sid' AND (age = 25 OR num = 44)", data);
assertTrue(resultOptional.isSuccess());
assertTrue(resultOptional.get());
```

#### App Version Comparison
```java
final BooleanExpressionEvaluator booleanExpressionEvaluator = new BooleanExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("app_version", "1.5.9");
final Try<Boolean> resultOptional = booleanExpressionEvaluator.evaluate("app_version < 1.5.10", data);
assertTrue(resultOptional.isSuccess());
assertTrue(resultOptional.get());
```

The return type is `Try<Boolean>`. Failure means that parsing has failed and any fallback can be used.

[For a complete list of examples please check out the test file](src/test/java/com/github/sidhant92/boolparser/application/BooleanExpressionEvaluatorTest.java)

### Arithmetic Expression Evaluator

The library can also evaluate arithmetic expressions, supporting both literals and variables from the data context.

#### Simple Addition Operation
```java
final ArithmeticExpressionEvaluator evaluator = new ArithmeticExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("a", 10);
final Try<Object> resultOptional = evaluator.evaluate("a + 5", data);
assertTrue(resultOptional.isSuccess());
assertEquals(resultOptional.get(), 15);
```

#### Complex Arithmetic Operation
```java
final ArithmeticExpressionEvaluator evaluator = new ArithmeticExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("a", 10);
final Try<Object> resultOptional = evaluator.evaluate("((5 * 2) + a) * 2 + (1 + 3 * (a / 2))", data);
assertTrue(resultOptional.isSuccess());
assertEquals(resultOptional.get(), 56);
```

#### Function Usage
```java
final ArithmeticExpressionEvaluator evaluator = new ArithmeticExpressionEvaluator(new BoolParser());
final Map<String, Object> data = new HashMap<>();
data.put("a", 10);
final Try<Object> resultOptional = evaluator.evaluate("min(1, 2, 3)", data);
assertTrue(resultOptional.isSuccess());
assertEquals(resultOptional.get(), 1);
```

[For a complete list of examples please check out the test file](src/test/java/com/github/sidhant92/boolparser/application/ArithmeticExpressionEvaluatorTest.java)

## Node Types Post Parsing

The parser produces different node types based on the expression:

### ComparisonNode
```java
private final Node left;
private final Node right;
private final Operator operator;
private final DataType dataType;
```

### NumericRangeNode
```java
private final String field;
private final Object fromValue;
private final Object toValue;
private final DataType fromDataType;
private final DataType toDataType;
```

### BooleanNode
```java
private Node left;
private Node right;
private LogicalOperationType operator;
```

### UnaryNode
```java
private final DataType dataType;
private final Object value;
```

### FieldNode
```java
private final String field;
```

### InNode
```java
private final String field;
private final List<Node> items;
```
