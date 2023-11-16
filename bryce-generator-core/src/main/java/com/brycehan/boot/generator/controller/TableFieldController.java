package com.brycehan.boot.generator.controller;

import com.brycehan.boot.generator.dto.TableFieldDto;
import com.brycehan.boot.generator.dto.TableFieldPageDto;
import com.brycehan.boot.generator.entity.TableField;
import com.brycehan.boot.generator.service.TableFieldService;
import com.brycehan.boot.generator.vo.TableFieldVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.ResponseResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.convert.TableFieldConvert;
import com.brycehan.boot.generator.validator.group.SaveGroup;
import com.brycehan.boot.generator.validator.group.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 表字段管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/generator-ui/gen/tableField")
@RestController
@RequiredArgsConstructor
public class TableFieldController {

    private final TableFieldService tableFieldService;

    /**
     * 保存表字段
     *
     * @param tableFieldDto 表字段Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody TableFieldDto tableFieldDto) {
        this.tableFieldService.save(tableFieldDto);
        return ResponseResult.ok();
    }

    /**
     * 更新表字段
     *
     * @param tableFieldDto 表字段Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody TableFieldDto tableFieldDto) {
        this.tableFieldService.update(tableFieldDto);
        return ResponseResult.ok();
    }

    /**
     * 删除表字段
     *
     * @param idsDto 表字段删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        this.tableFieldService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据表字段 ID 查询表字段信息
     *
     * @param id 表字段ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<TableFieldVo> get(@PathVariable Long id) {
        TableField tableField = this.tableFieldService.getById(id);
        return ResponseResult.ok(TableFieldConvert.INSTANCE.convert(tableField));
    }

    /**
     * 列表查询
     *
     * @return 表字段列表
     */
    @PostMapping(path = "/list")
    public ResponseResult<List<TableFieldVo>> list() {
        List<TableField> list = this.tableFieldService.list();
        return ResponseResult.ok(TableFieldConvert.INSTANCE.convert(list));
    }

    /**
     * 分页查询
     *
     * @param tableFieldPageDto 查询条件
     * @return 表字段分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<TableFieldVo>> page(@Validated @RequestBody TableFieldPageDto tableFieldPageDto) {
        PageResult<TableFieldVo> page = this.tableFieldService.page(tableFieldPageDto);
        return ResponseResult.ok(page);
    }

}

