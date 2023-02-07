package com.github.sidhant92.boolparser.parser.antlr;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BooleanExpressionBoolParser}.
 */
public interface BooleanExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(BooleanExpressionBoolParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(BooleanExpressionBoolParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(BooleanExpressionBoolParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(BooleanExpressionBoolParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typesExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTypesExpression(BooleanExpressionBoolParser.TypesExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typesExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTypesExpression(BooleanExpressionBoolParser.TypesExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(BooleanExpressionBoolParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(BooleanExpressionBoolParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterToExpression(BooleanExpressionBoolParser.ToExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitToExpression(BooleanExpressionBoolParser.ToExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(BooleanExpressionBoolParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(BooleanExpressionBoolParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparatorExpression(BooleanExpressionBoolParser.ComparatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparatorExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparatorExpression(BooleanExpressionBoolParser.ComparatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExpression(BooleanExpressionBoolParser.ParentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentExpression}
	 * labeled alternative in {@link BooleanExpressionBoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExpression(BooleanExpressionBoolParser.ParentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(BooleanExpressionBoolParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(BooleanExpressionBoolParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#wordlist}.
	 * @param ctx the parse tree
	 */
	void enterWordlist(BooleanExpressionBoolParser.WordlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#wordlist}.
	 * @param ctx the parse tree
	 */
	void exitWordlist(BooleanExpressionBoolParser.WordlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#numericTypes}.
	 * @param ctx the parse tree
	 */
	void enterNumericTypes(BooleanExpressionBoolParser.NumericTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#numericTypes}.
	 * @param ctx the parse tree
	 */
	void exitNumericTypes(BooleanExpressionBoolParser.NumericTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#types}.
	 * @param ctx the parse tree
	 */
	void enterTypes(BooleanExpressionBoolParser.TypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#types}.
	 * @param ctx the parse tree
	 */
	void exitTypes(BooleanExpressionBoolParser.TypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(BooleanExpressionBoolParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(BooleanExpressionBoolParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanExpressionBoolParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(BooleanExpressionBoolParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanExpressionBoolParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(BooleanExpressionBoolParser.BoolContext ctx);
}