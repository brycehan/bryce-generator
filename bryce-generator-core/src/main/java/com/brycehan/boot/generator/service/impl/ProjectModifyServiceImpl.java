package com.brycehan.boot.generator.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.common.util.ProjectUtils;
import com.brycehan.boot.generator.entity.convert.ProjectModifyConvert;
import com.brycehan.boot.generator.entity.dto.ProjectModifyDto;
import com.brycehan.boot.generator.entity.dto.ProjectModifyPageDto;
import com.brycehan.boot.generator.entity.po.ProjectModify;
import com.brycehan.boot.generator.entity.vo.ProjectModifyVo;
import com.brycehan.boot.generator.mapper.ProjectModifyMapper;
import com.brycehan.boot.generator.service.ProjectModifyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 项目名变更服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Service
@RequiredArgsConstructor
public class ProjectModifyServiceImpl extends ServiceImpl<ProjectModifyMapper, ProjectModify> implements ProjectModifyService {

    private final ProjectModifyMapper projectModifyMapper;

    @Override
    public void save(ProjectModifyDto projectModifyDto) {
        ProjectModify projectModify = ProjectModifyConvert.INSTANCE.convert(projectModifyDto);
        projectModifyMapper.insert(projectModify);
    }

    @Override
    public void update(ProjectModifyDto projectModifyDto) {
        ProjectModify projectModify = ProjectModifyConvert.INSTANCE.convert(projectModifyDto);
        projectModifyMapper.updateById(projectModify);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(IdsDto idsDto) {
        // 过滤空数据
        List<Long> ids = idsDto.getIds()
                .stream()
                .filter(Objects::nonNull)
                .toList();
        if (CollectionUtils.isEmpty(ids)) {
            throw new RuntimeException("参数无效");
        }
        // 删除
        removeByIds(ids);
    }

    @Override
    public PageResult<ProjectModifyVo> page(@NotNull ProjectModifyPageDto projectModifyPageDto) {

        IPage<ProjectModify> page = projectModifyMapper.selectPage(projectModifyPageDto.toPage(), getWrapper(projectModifyPageDto));

        return new PageResult<>(ProjectModifyConvert.INSTANCE.convert(page.getRecords()), page.getTotal());
    }

    /**
     * 封装查询条件
     *
     * @param projectModifyPageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<ProjectModify> getWrapper(ProjectModifyPageDto projectModifyPageDto) {
        LambdaQueryWrapper<ProjectModify> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(projectModifyPageDto.getProjectName()), ProjectModify::getProjectName, projectModifyPageDto.getProjectName());
        return wrapper;
    }

    @Override
    public byte[] download(ProjectModify project) throws IOException {
        // 原项目根路径
        File srcRoot = new File(project.getProjectPath());

        // 临时项目根路径
        File destRoot = new File(ProjectUtils.getTmpDirPath(project.getModifyProjectCode()));

        // 排除的文件
        List<String> exclusions = StrUtil.split(project.getExclusions(), ProjectUtils.SPLIT);

        // 获取替换规则
        Map<String, String> replaceRules = getReplaceRules(project);

        // 拷贝项目到新路径，并替换路径和文件名
        ProjectUtils.copyDirectory(srcRoot, destRoot, exclusions, replaceRules);

        // 需要替换的文件后缀
        List<String> suffix = StrUtil.split(project.getModifySuffix(), ProjectUtils.SPLIT);

        // 替换文件内容数据
        ProjectUtils.contentFormat(destRoot, suffix, replaceRules);

        // 生成zip文件
        File zipFile = ZipUtil.zip(destRoot);
        byte[] data = FileUtil.readBytes(zipFile);

        // 清空文件
        FileUtil.clean(destRoot.getParentFile().getParentFile());

        return data;
    }

    /**
     * 获取替换规则
     *
     * @param project 项目
     * @return 项目替换规则
     */
    private Map<String, String> getReplaceRules(ProjectModify project) {
        Map<String, String> rules = new LinkedHashMap<>();

        // 项目路径替换
        String srcPath = "src/main/java/".concat(project.getProjectPackage().replaceAll("\\.", "/"));
        String destPath = "src/main/java/".concat(project.getModifyProjectPackage().replaceAll("\\.", "/"));
        rules.put(srcPath, destPath);

        // 项目包名替换
        rules.put(project.getProjectPackage(), project.getModifyProjectPackage());
        // 项目标识替换
        rules.put(project.getProjectCode(), project.getModifyProjectCode());
        if (StrUtil.isNotBlank(project.getProjectCode()) && StrUtil.isNotBlank(project.getModifyProjectCode())) {
            // 项目标识前缀替换
            rules.put(project.getProjectCode().split("-")[0], project.getModifyProjectCode().split("-")[0]);
            // 数据库标识替换
            rules.put(project.getProjectCode().replaceAll("-", "_"), project.getModifyProjectCode().replaceAll("-", "_"));
        }
        // 项目标识缩写替换
        rules.put(project.getProjectCodeAbbreviate(), project.getModifyProjectCodeAbbreviate());
        // 项目名称替换
        rules.put(project.getProjectName(), project.getModifyProjectName());

        return rules;
    }
}
