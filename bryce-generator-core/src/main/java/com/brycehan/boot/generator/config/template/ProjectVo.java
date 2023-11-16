package com.brycehan.boot.generator.config.template;

import lombok.Data;

/**
 * 项目
 *
 * @author Bryce Han
 * @since 2023/5/8
 */
@Data
public class ProjectVo {

    /**
     * 项目包名
     */
    private String packageName;

    /**
     * 项目版本号
     */
    private String version;

    /**
     * 后端生成代码路径
     */
    private String backendPath;

    /**
     * 前端生成代码路径
     */
    private String frontendPath;

}
