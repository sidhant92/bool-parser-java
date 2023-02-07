package com.github.sidhant92.boolparser.parser.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.sidhant92.boolparser.domain.Token;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import io.vavr.control.Try;

public class BoolParser implements BoolExpressionParser {
    @Override
    public Try<Token> parseExpression(final String expression) {
        return Try.of(() -> getNode(expression));
    }

    private Token getNode(final String expression) {
        BooleanExpressionLexer filterLexer = new BooleanExpressionLexer(CharStreams.fromString(expression));
        CommonTokenStream commonTokenStream = new CommonTokenStream(filterLexer);
        BooleanExpressionBoolParser filterParser = new BooleanExpressionBoolParser(commonTokenStream);
        BooleanExpressionBoolParser.ParseContext filterContext = filterParser.parse();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        final BooleanFilterListener listener = new BooleanFilterListener();
        parseTreeWalker.walk(listener, filterContext);
        return listener.getNode();
    }
}
