package com.brycehan.generator.core.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectUtils {
    ///var/folders/bz/bs8k_3jn4mv4g0lhr5dgtd9r0000gn/T/generator/1692170759685/br-boot
    ///var/folders/bz/bs8k_3jn4mv4g0lhr5dgtd9r0000gn/T/generator/1692170810076/br-boot

    public static final String SPLIT = ",";

    /**
     * 生成临时路径
     *
     * @param names 项目名称
     * @return 项目临时路径
     */
    public static String getTmpDirPath(String... names) {
        StringBuilder tmpPath = new StringBuilder(FileUtil.getTmpDirPath());
        tmpPath.append("generator");
        tmpPath.append(File.separator);
        tmpPath.append(System.currentTimeMillis());

        for (String name : names) {
            tmpPath.append(File.separator).append(name);
        }

        return tmpPath.toString();
    }

    public static void copyDirectory(File srcRoot, File destRoot, List<String> exclusions, Map<String, String> replaceRules) throws IOException {
        String destPath = destRoot.getPath().replaceAll("\\\\", "/");
        destRoot = new File(replaceData(destPath, replaceRules));

        // 获取排除后的源文件
        File[] srcFiles = CollectionUtils.isEmpty(exclusions) ?
                srcRoot.listFiles() : srcRoot.listFiles(file -> !exclusions.contains(file.getName()));

        if (srcFiles == null) {
            throw new IOException("没有需要拷贝的文件 ".concat(srcRoot.getPath()));
        }
        for (File srcFile : srcFiles) {
            String fileName = srcFile.getName();
            if (srcFile.isFile()) {
                fileName = replaceData(fileName, replaceRules);
            }
            File destFile = new File(destRoot, fileName);
            if (srcFile.isDirectory()) {
                copyDirectory(srcFile, destFile, exclusions, replaceRules);
            } else {
                FileUtil.copyFile(srcFile, destFile);
            }
        }
    }

    /**
     * 替换数据
     *
     * @param str        待替换的字符串
     * @param replaceMap 替换的kv集合
     * @return 返回替换后的数据
     */
    private static String replaceData(String str, Map<String, String> replaceMap) {
        for (String key : replaceMap.keySet()) {
            str = str.replaceAll(key, replaceMap.get(key));
        }
        return str;
    }

    /**
     * 内容格式化
     *
     * @param rootFile     文件根目录
     * @param suffix       需要格式化的文件后缀
     * @param replaceRules 替换规则
     */
    public static void contentFormat(File rootFile, List<String> suffix, Map<String, String> replaceRules) {
        List<File> destList = FileUtil.loopFiles(rootFile, file -> suffix.contains(FileNameUtil.getSuffix(file)));
        for (File dest : destList) {
            List<String> lines = FileUtil.readUtf8Lines(dest);
            List<String> newList = new ArrayList<>();

            for (String line : lines) {
                newList.add(replaceData(line, replaceRules));
            }
            FileUtil.writeUtf8Lines(newList, dest);
        }
    }
}
