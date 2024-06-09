package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvaluatedNode {
    private final DataType dataType;

    private final Object value;
}
