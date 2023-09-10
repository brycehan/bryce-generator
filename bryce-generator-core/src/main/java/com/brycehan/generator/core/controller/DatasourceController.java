package com.brycehan.generator.core.controller;

import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.ResponseResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.config.GenDatasource;
import com.brycehan.generator.core.convert.DatasourceConvert;
import com.brycehan.generator.core.convert.TableConvert;
import com.brycehan.generator.core.dto.DatasourceDto;
import com.brycehan.generator.core.dto.DatasourcePageDto;
import com.brycehan.generator.core.entity.Datasource;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.service.DatasourceService;
import com.brycehan.generator.core.util.DbUtils;
import com.brycehan.generator.core.util.TableUtils;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.brycehan.generator.core.vo.DatasourceVo;
import com.brycehan.generator.core.vo.TableVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 数据源管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Slf4j
@RequestMapping("/datasource")
@RestController
@RequiredArgsConstructor
public class DatasourceController {

    private final DatasourceService datasourceService;

    /**
     * 保存数据源
     *
     * @param datasourceDto 数据源Dto
     * @return 响应结果
     */
    @PostMapping
    public ResponseResult<Void> save(@Validated(value = SaveGroup.class) @RequestBody DatasourceDto datasourceDto) {
        this.datasourceService.save(datasourceDto);
        return ResponseResult.ok();
    }

    /**
     * 更新数据源
     *
     * @param datasourceDto 数据源Dto
     * @return 响应结果
     */
    @PutMapping
    public ResponseResult<Void> update(@Validated(value = UpdateGroup.class) @RequestBody DatasourceDto datasourceDto) {
        this.datasourceService.update(datasourceDto);
        return ResponseResult.ok();
    }

    /**
     * 删除数据源
     *
     * @param idsDto 数据源删除Dto
     * @return 响应结果
     */
    @DeleteMapping
    public ResponseResult<Void> delete(@Validated @RequestBody IdsDto idsDto) {
        // 批量删除
        this.datasourceService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据数据源 ID 查询数据源信息
     *
     * @param id 数据源ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<DatasourceVo> get(@PathVariable String id) {
        Datasource datasource = this.datasourceService.getById(id);
        return ResponseResult.ok(DatasourceConvert.INSTANCE.convert(datasource));
    }

    /**
     * 根据数据源 ID 测试数据源连接
     *
     * @param id 数据源ID
     * @return 响应结果
     */
    @GetMapping(path = "/test/{id}")
    public ResponseResult<String> test(@PathVariable String id) {
        Datasource datasource = this.datasourceService.getById(id);
        try {
            DbUtils.getConnection(new GenDatasource(datasource));
            return ResponseResult.ok("连接成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseResult.error("连接失败，请检查配置信息");
        }

    }

    /**
     * 列表查询
     *
     * @return 数据源列表
     */
    @GetMapping(path = "/list")
    public ResponseResult<List<DatasourceVo>> list() {
        List<Datasource> list = this.datasourceService.list();
        return ResponseResult.ok(DatasourceConvert.INSTANCE.convert(list));
    }

    /**
     * 根据数据源ID，获取全部数据表
     *
     * @param datasourceId 数据源ID
     * @return 表列表
     */
    @GetMapping(path = "/table/list/{datasourceId}")
    public ResponseResult<List<TableVo>> list(@PathVariable Long datasourceId) {
        // 获取数据源
        GenDatasource datasource = this.datasourceService.get(datasourceId);
        // 根据数据源，获取全部数据表
        List<Table> tableList = TableUtils.getTableList(datasource);
        return ResponseResult.ok(TableConvert.INSTANCE.convert(tableList));
    }

    /**
     * 分页查询
     *
     * @param datasourcePageDto 查询条件
     * @return 数据源分页列表
     */
    @PostMapping(path = "/page")
    public ResponseResult<PageResult<DatasourceVo>> page(@Validated @RequestBody DatasourcePageDto datasourcePageDto) {
        PageResult<DatasourceVo> page = this.datasourceService.page(datasourcePageDto);
        return ResponseResult.ok(page);
    }

}

