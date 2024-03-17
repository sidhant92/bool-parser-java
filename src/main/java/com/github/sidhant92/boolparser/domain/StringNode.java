package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.NodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sidhant.aggarwal
 * @since 16/03/2024
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StringNode extends Node {
    private final String field;

    @Override
    public NodeType getTokenType() {
        return NodeType.STRING;
    }
}
