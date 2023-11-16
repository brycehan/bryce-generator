package com.brycehan.boot.generator.util;

import cn.hutool.core.util.StrUtil;
import com.brycehan.boot.generator.config.PlatformType;
import com.brycehan.boot.generator.entity.BaseClass;
import com.brycehan.boot.generator.entity.Table;

/**
 * 表处理工具类
 *
 * @author Bryce Han
 * @since 2023/11/16
 */
public class TableProcessUtils {

    /**
     * 处理 boot 和 cloud 版本的后端生成代码路径
     *
     * @param table 表
     */
    public static void processBackendPath(Table table) {
        String backendPath = table.getBackendPath();
        String platformName = StrUtil.subAfter(table.getPackageName(), ".", true);
        if(PlatformType.boot.name().equals(platformName)) {
            table.setBackendPath(backendPath.replaceFirst(PlatformType.cloud.name(),PlatformType.boot.name()));
        } else {
            table.setBackendPath(backendPath.replaceFirst(PlatformType.boot.name(),PlatformType.cloud.name()));
        }
    }

    /**
     * 处理 boot 和 cloud 版本的基类包名
     *
     * @param table 表
     * @param baseClass 基类
     */
    public static void processBaseEntityPackageName(Table table, BaseClass baseClass) {
        String packageName = baseClass.getPackageName();

        String basePackageName = StrUtil.subBefore(table.getPackageName(), ".", true);
        String platformName = StrUtil.subAfter(table.getPackageName(), ".", true);

        if(PlatformType.boot.name().equals(platformName)) {
            packageName = packageName.replace(basePackageName + "." + PlatformType.cloud.name(),
                    basePackageName + "." + PlatformType.boot.name());
        } else {
            packageName = packageName.replace(basePackageName + "." + PlatformType.boot.name(),
                    basePackageName + "." + PlatformType.cloud.name());
        }

        baseClass.setPackageName(packageName);
    }

}
