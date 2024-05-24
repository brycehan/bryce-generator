package com.brycehan.boot.generator.controller;

import com.brycehan.boot.generator.entity.dto.BaseClassDto;
import com.brycehan.boot.generator.entity.dto.BaseClassPageDto;
import com.brycehan.boot.generator.entity.vo.BaseClassVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.ResponseResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.convert.BaseClassConvert;
import com.brycehan.boot.generator.entity.po.BaseClass;
import com.brycehan.boot.generator.service.BaseClassService;
import com.brycehan.boot.generator.common.validator.SaveGroup;
import com.brycehan.boot.generator.common.validator.UpdateGroup;
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
@RequestMapping("/generator-ui/gen/baseClass")
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
    public ResponseResult<BaseClassVo> get(@PathVariable Long id) {
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

