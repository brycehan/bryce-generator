package com.brycehan.generator.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.dto.ProjectModifyDto;
import com.brycehan.generator.core.dto.ProjectModifyPageDto;
import com.brycehan.generator.core.entity.ProjectModify;
import com.brycehan.generator.core.vo.ProjectModifyVo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;

/**
 * 项目名变更服务类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface ProjectModifyService extends IService<ProjectModify> {


    /**
     * 添加项目名变更
     *
     * @param projectModifyDto 项目名变更Dto
     */
    void save(ProjectModifyDto projectModifyDto);

    /**
     * 更新项目名变更
     *
     * @param projectModifyDto 项目名变更Dto
     */
    void update(ProjectModifyDto projectModifyDto);

    /**
     * 删除项目名变更
     *
     * @param deleteDto 项目名变更删除Dto
     */
    void delete(DeleteDto deleteDto);

    /**
     * 项目名变更分页查询信息
     *
     * @param projectModifyPageDto 项目名变更分页搜索条件
     * @return 分页信息
     */
    PageResult<ProjectModifyVo> page(@NotNull ProjectModifyPageDto projectModifyPageDto);

    /**
     * 源码下载
     *
     * @param projectModify
     * @return 字节数组数据
     */
    byte[] download(ProjectModify projectModify) throws IOException;
}
