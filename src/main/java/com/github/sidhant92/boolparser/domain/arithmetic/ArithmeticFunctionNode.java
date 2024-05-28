package com.github.sidhant92.boolparser.domain.arithmetic;

import java.util.List;
import com.github.sidhant92.boolparser.constant.FunctionType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.domain.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sidhant.aggarwal
 * @since 21/05/2024
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArithmeticFunctionNode extends ArithmeticBaseNode {
    private FunctionType functionType;


    private final List<Node> items;


    @Override
    public NodeType getTokenType() {
        return NodeType.ARITHMETIC_FUNCTION;
    }
}
