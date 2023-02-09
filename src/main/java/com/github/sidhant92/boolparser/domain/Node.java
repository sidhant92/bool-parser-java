package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.NodeType;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public abstract class Node {
    public abstract NodeType getTokenType();
}
