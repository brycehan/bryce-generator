package com.brycehan.boot.generator.controller;

import cn.hutool.core.util.StrUtil;
import com.brycehan.boot.generator.entity.convert.TableConvert;
import com.brycehan.boot.generator.entity.convert.TableFieldConvert;
import com.brycehan.boot.generator.entity.dto.TableDto;
import com.brycehan.boot.generator.entity.dto.TableFieldDto;
import com.brycehan.boot.generator.entity.dto.TablePageDto;
import com.brycehan.boot.generator.entity.po.TableField;
import com.brycehan.boot.generator.service.TableFieldService;
import com.brycehan.boot.generator.service.TableService;
import com.brycehan.boot.generator.entity.vo.TableVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.ResponseResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.po.Table;
import com.brycehan.boot.generator.common.validator.SaveGroup;
import com.brycehan.boot.generator.common.validator.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


/**
 * 表管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/generator-ui/gen/table")
@RestController
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    private final TableFieldService tableFieldService;

    // 定义表名的正则表达式
    private static final String TABLE_NAME_PATTERN = "^[a-zA-Z_][a-zA-Z0-9_]{0,63}$";

    /**
     * 保存表
     *
     * @param tableDto 表Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody TableDto tableDto) {
        tableService.save(tableDto);
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
        tableService.update(tableDto);
        return ResponseResult.ok();
    }

    /**
     * 删除表
     *
     * @param idsDto 表删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        tableService.delete(idsDto);
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
        Table table = tableService.getById(id);
        // 获取表的字段列表
        List<TableField> fieldList = tableFieldService.getByTableId(id);
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
        PageResult<TableVo> page = tableService.page(tablePageDto);
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
        // 无效表名过滤
        tableNameList.removeIf(tableName -> StrUtil.isBlank(tableName) || !Pattern.matches(TABLE_NAME_PATTERN, tableName));

        if (!CollectionUtils.isEmpty(tableNameList)) {
            for (String tableName : tableNameList) {
                tableService.tableImport(datasourceId, tableName);
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
        tableService.sync(id);
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
        tableFieldService.updateTableField(tableId, TableFieldConvert.INSTANCE.convertDto(tableFieldList));
        return ResponseResult.ok();
    }

}

