package com.brycehan.generator.core.controller;

import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.ResponseResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.convert.BaseClassConvert;
import com.brycehan.generator.core.dto.BaseClassDto;
import com.brycehan.generator.core.dto.BaseClassPageDto;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.service.BaseClassService;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.brycehan.generator.core.vo.BaseClassVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 基类管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/baseClass")
@RestController
@RequiredArgsConstructor
public class BaseClassController {

    private final BaseClassService baseClassService;

    /**
     * 保存基类
     *
     * @param baseClassDto 基类Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody BaseClassDto baseClassDto) {
        this.baseClassService.save(baseClassDto);
        return ResponseResult.ok();
    }

    /**
     * 更新基类
     *
     * @param baseClassDto 基类Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody BaseClassDto baseClassDto) {
        this.baseClassService.update(baseClassDto);
        return ResponseResult.ok();
    }

    /**
     * 删除基类
     *
     * @param idsDto 基类删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        this.baseClassService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据基类 ID 查询基类信息
     *
     * @param id 基类ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<BaseClassVo> get(@PathVariable String id) {
        BaseClass baseClass = this.baseClassService.getById(id);
        return ResponseResult.ok(BaseClassConvert.INSTANCE.convert(baseClass));
    }

    /**
     * 列表查询
     *
     * @return 基类列表
     */
    @GetMapping(path = "/list")
    public ResponseResult<List<BaseClassVo>> list() {
        List<BaseClass> list = this.baseClassService.list();
//        LambdaQueryWrapper<BaseClass> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.between()
        return ResponseResult.ok(BaseClassConvert.INSTANCE.convert(list));
    }

    /**
     * 分页查询
     *
     * @param baseClassPageDto 查询条件
     * @return 基类分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<BaseClassVo>> page(@Validated @RequestBody BaseClassPageDto baseClassPageDto) {
        PageResult<BaseClassVo> page = this.baseClassService.page(baseClassPageDto);
        return ResponseResult.ok(page);
    }

}

