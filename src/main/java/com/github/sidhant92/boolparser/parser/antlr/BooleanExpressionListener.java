package com.github.sidhant92.boolparser.parser.antlr;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BooleanExpressionParser}.
 */
public interface BooleanExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(BooleanExpressionParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(BooleanExpressionParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(BooleanExpressionParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(BooleanExpressionParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typesExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTypesExpression(BooleanExpressionParser.TypesExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typesExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTypesExpression(BooleanExpressionParser.TypesExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(BooleanExpressionParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(BooleanExpressionParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterToExpression(BooleanExpressionParser.ToExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitToExpression(BooleanExpressionParser.ToExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(BooleanExpressionParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(BooleanExpressionParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparatorExpression(BooleanExpressionParser.ComparatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparatorExpression(BooleanExpressionParser.ComparatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExpression(BooleanExpressionParser.ParentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentExpression}
	 * labeled alternative in {@link BooleanExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExpression(BooleanExpressionParser.ParentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(BooleanExpressionParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(BooleanExpressionParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#wordlist}.
	 * @param ctx the parse tree
	 */
	void enterWordlist(BooleanExpressionParser.WordlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#wordlist}.
	 * @param ctx the parse tree
	 */
	void exitWordlist(BooleanExpressionParser.WordlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#numericTypes}.
	 * @param ctx the parse tree
	 */
	void enterNumericTypes(BooleanExpressionParser.NumericTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#numericTypes}.
	 * @param ctx the parse tree
	 */
	void exitNumericTypes(BooleanExpressionParser.NumericTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#types}.
	 * @param ctx the parse tree
	 */
	void enterTypes(BooleanExpressionParser.TypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#types}.
	 * @param ctx the parse tree
	 */
	void exitTypes(BooleanExpressionParser.TypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(BooleanExpressionParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(BooleanExpressionParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(BooleanExpressionParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(BooleanExpressionParser.BoolContext ctx);
}