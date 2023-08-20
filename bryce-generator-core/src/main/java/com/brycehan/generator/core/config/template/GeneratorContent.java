package com.brycehan.generator.core.config.template;

import lombok.Data;

import java.util.List;

/**
 * 代码生成信息
 *
 * @author Bryce Han
 * @since 2023/5/8
 */
@Data
public class GeneratorContent {

    /**
     * 项目
     */
    private ProjectVo project;

    /**
     * 开发者
     */
    private DeveloperVo developer;

    /**
     * 模板
     */
    private List<TemplateVo> templates;

}
