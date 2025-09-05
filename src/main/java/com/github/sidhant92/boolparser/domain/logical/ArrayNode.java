package com.github.sidhant92.boolparser.domain.logical;

import java.util.List;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArrayNode extends Node {
    private final String field;

    private final Operator operator;

    private final List<Node> items;
    @Override
    public NodeType getTokenType() {
        return NodeType.ARRAY;
    }
}
