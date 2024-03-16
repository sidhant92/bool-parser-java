package com.github.sidhant92.boolparser.domain.arithmetic;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.domain.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sidhant.aggarwal
 * @since 15/03/2024
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArithmeticLeafNode extends Node {
    private Object operand;

    private DataType dataType;

    @Override
    public NodeType getTokenType() {
        return NodeType.ARITHMETIC_LEAF;
    }
}
