package com.brycehan.generator.core.config.template;

import lombok.Data;

/**
 * 模板
 *
 * @author Bryce Han
 * @since 2023/5/8
 */
@Data
public class TemplateVo {

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 生成代码的路径
     */
    private String generatorPath;

}
