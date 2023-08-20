package com.brycehan.generator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 代码生成方式枚举
 *
 * @author Bryce Han
 * @since 2023/5/9
 */
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum GeneratorTypeEnum {
    /**
     * zip压缩包下载
     */
    ZIP_DOWNLOAD(0),

    /**
     * 自定义目录
     */
    CUSTOM_DIRECTORY(1);

    private final Integer value;
}
