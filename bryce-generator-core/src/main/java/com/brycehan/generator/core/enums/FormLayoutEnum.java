package com.brycehan.generator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 表单布局枚举
 *
 * @author Bryce Han
 * @since 2023/5/9
 */
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum FormLayoutEnum {
    ONE(1),
    TWO(2);

    private final Integer value;
}
