package com.brycehan.generator.core.controller;

import cn.hutool.core.io.IoUtil;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.ResponseResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.convert.ProjectModifyConvert;
import com.brycehan.generator.core.dto.ProjectModifyDto;
import com.brycehan.generator.core.dto.ProjectModifyPageDto;
import com.brycehan.generator.core.entity.ProjectModify;
import com.brycehan.generator.core.service.ProjectModifyService;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.brycehan.generator.core.vo.ProjectModifyVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 项目名变更管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/generator-ui/gen/projectModify")
@RestController
@RequiredArgsConstructor
public class ProjectModifyController {

    private final ProjectModifyService projectModifyService;

    /**
     * 保存项目名变更
     *
     * @param projectModifyDto 项目名变更Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody ProjectModifyDto projectModifyDto) {
        this.projectModifyService.save(projectModifyDto);
        return ResponseResult.ok();
    }

    /**
     * 更新项目名变更
     *
     * @param projectModifyDto 项目名变更Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody ProjectModifyDto projectModifyDto) {
        this.projectModifyService.update(projectModifyDto);
        return ResponseResult.ok();
    }

    /**
     * 删除项目名变更
     *
     * @param idsDto 项目名变更删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        this.projectModifyService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据项目名变更 ID 查询项目名变更信息
     *
     * @param id 项目名变更ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<ProjectModifyVo> get(@PathVariable Long id) {
        ProjectModify projectModify = this.projectModifyService.getById(id);
        return ResponseResult.ok(ProjectModifyConvert.INSTANCE.convert(projectModify));
    }

    /**
     * 列表查询
     *
     * @return 项目名变更列表
     */
    @PostMapping(path = "/list")
    public ResponseResult<List<ProjectModifyVo>> list() {
        List<ProjectModify> list = this.projectModifyService.list();
        return ResponseResult.ok(ProjectModifyConvert.INSTANCE.convert(list));
    }

    /**
     * 分页查询
     *
     * @param projectModifyPageDto 查询条件
     * @return 项目名变更分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<ProjectModifyVo>> page(@Validated @RequestBody ProjectModifyPageDto projectModifyPageDto) {
        PageResult<ProjectModifyVo> page = this.projectModifyService.page(projectModifyPageDto);
        return ResponseResult.ok(page);
    }

    /**
     * 源码下载
     *
     * @param id       项目ID
     * @param response http响应体
     */
    @GetMapping(path = "/download/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ProjectModify projectModify = projectModifyService.getById(id);
        byte[] data = projectModifyService.download(projectModify);
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''"
                .concat(projectModify.getModifyProjectName())
                .concat(".zip"));
        response.addHeader("Content-Length", String.valueOf(data.length));
        response.setContentType("application/octet-stream;charset=UTF-8");
        IoUtil.write(response.getOutputStream(), false, data);
    }
}

