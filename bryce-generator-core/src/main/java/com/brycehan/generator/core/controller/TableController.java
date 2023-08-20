package com.brycehan.generator.core.controller;

import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.ResponseResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.convert.TableConvert;
import com.brycehan.generator.core.convert.TableFieldConvert;
import com.brycehan.generator.core.dto.TableDto;
import com.brycehan.generator.core.dto.TableFieldDto;
import com.brycehan.generator.core.dto.TablePageDto;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.entity.TableField;
import com.brycehan.generator.core.service.TableFieldService;
import com.brycehan.generator.core.service.TableService;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.brycehan.generator.core.vo.TableVo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 表管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/table")
@RestController
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    private final TableFieldService tableFieldService;

    /**
     * 保存表
     *
     * @param tableDto 表Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody TableDto tableDto) {
        this.tableService.save(tableDto);
        return ResponseResult.ok();
    }

    /**
     * 更新表
     *
     * @param tableDto 表Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody TableDto tableDto) {
        this.tableService.update(tableDto);
        return ResponseResult.ok();
    }

    /**
     * 删除表
     *
     * @param deleteDto 表删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody DeleteDto deleteDto) {
        // 批量删除
        this.tableService.delete(deleteDto);
        return ResponseResult.ok();
    }

    /**
     * 根据表 ID 查询表信息
     *
     * @param id 表ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<TableVo> get(@PathVariable Long id) {
        Table table = this.tableService.getById(id);
        // 获取表的字段列表
        List<TableField> fieldList = this.tableFieldService.getByTableId(id);
        table.setFieldList(fieldList);
        return ResponseResult.ok(TableConvert.INSTANCE.convert(table));
    }

    /**
     * 分页查询
     *
     * @param tablePageDto 查询条件
     * @return 表分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<TableVo>> page(@Validated @RequestBody TablePageDto tablePageDto) {
        PageResult<TableVo> page = this.tableService.page(tablePageDto);
        return ResponseResult.ok(page);
    }

    /**
     * 导入数据源中的表
     *
     * @param datasourceId 数据源ID
     * @return 响应结果
     */
    @PostMapping(path = "/import/{datasourceId}")
    public ResponseResult<Void> importTable(@PathVariable Long datasourceId,
                                            @RequestBody List<String> tableNameList) {
        if (!CollectionUtils.isEmpty(tableNameList)) {
            for (String tableName : tableNameList) {
                this.tableService.tableImport(datasourceId, tableName);
            }
        }
        return ResponseResult.ok();
    }

    /**
     * 同步表结构
     *
     * @param id 表ID
     * @return 响应结果
     */
    @GetMapping(path = "/sync/{id}")
    public ResponseResult<Void> sync(@PathVariable Long id) {
        this.tableService.sync(id);
        return ResponseResult.ok();
    }

    /**
     * 修改表字段数据
     *
     * @param tableId        表ID
     * @param tableFieldList 字段列表
     * @return 响应结果
     */
    @PutMapping(path = "/field/{tableId}")
    public ResponseResult<Void> updateTableFields(@PathVariable Long tableId, @RequestBody List<TableFieldDto> tableFieldList) {
        this.tableFieldService.updateTableField(tableId, TableFieldConvert.INSTANCE.convertDto(tableFieldList));
        return ResponseResult.ok();
    }

}

