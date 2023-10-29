package com.brycehan.generator.core.controller;

import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.ResponseResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.convert.FieldTypeConvert;
import com.brycehan.generator.core.dto.FieldTypeDto;
import com.brycehan.generator.core.dto.FieldTypePageDto;
import com.brycehan.generator.core.entity.FieldType;
import com.brycehan.generator.core.service.FieldTypeService;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.brycehan.generator.core.vo.FieldTypeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字段类型管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/generator-ui/gen/fieldType")
@RestController
@RequiredArgsConstructor
public class FieldTypeController {

    private final FieldTypeService fieldTypeService;

    /**
     * 保存字段类型
     *
     * @param fieldTypeDto 字段类型Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody FieldTypeDto fieldTypeDto) {
        this.fieldTypeService.save(fieldTypeDto);
        return ResponseResult.ok();
    }

    /**
     * 更新字段类型
     *
     * @param fieldTypeDto 字段类型Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody FieldTypeDto fieldTypeDto) {
        this.fieldTypeService.update(fieldTypeDto);
        return ResponseResult.ok();
    }

    /**
     * 删除字段类型
     *
     * @param idsDto 字段类型删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        this.fieldTypeService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据字段类型 ID 查询字段类型信息
     *
     * @param id 字段类型ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<FieldTypeVo> get(@PathVariable Long id) {
        FieldType fieldType = this.fieldTypeService.getById(id);
        return ResponseResult.ok(FieldTypeConvert.INSTANCE.convert(fieldType));
    }

    /**
     * 列表查询
     *
     * @return 字段类型列表
     */
    @GetMapping(path = "/list")
    public ResponseResult<List<String>> list() {
        List<String> list = this.fieldTypeService.getAttrTypeList();
        return ResponseResult.ok(list);
    }

    /**
     * 分页查询
     *
     * @param fieldTypePageDto 查询条件
     * @return 字段类型分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<FieldTypeVo>> page(@Validated @RequestBody FieldTypePageDto fieldTypePageDto) {
        PageResult<FieldTypeVo> page = this.fieldTypeService.page(fieldTypePageDto);
        return ResponseResult.ok(page);
    }

}

