package com.brycehan.boot.generator.controller;

import com.brycehan.boot.generator.config.GenDatasource;
import com.brycehan.boot.generator.entity.dto.DatasourceDto;
import com.brycehan.boot.generator.entity.dto.DatasourcePageDto;
import com.brycehan.boot.generator.common.util.TableUtils;
import com.brycehan.boot.generator.entity.vo.DatasourceVo;
import com.brycehan.boot.generator.entity.vo.TableVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.ResponseResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.convert.DatasourceConvert;
import com.brycehan.boot.generator.entity.convert.TableConvert;
import com.brycehan.boot.generator.entity.po.Datasource;
import com.brycehan.boot.generator.entity.po.Table;
import com.brycehan.boot.generator.service.DatasourceService;
import com.brycehan.boot.generator.common.util.DbUtils;
import com.brycehan.boot.generator.common.validator.SaveGroup;
import com.brycehan.boot.generator.common.validator.UpdateGroup;
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
@RequestMapping("/generator-ui/gen/datasource")
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
        datasourceService.save(datasourceDto);
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
        datasourceService.update(datasourceDto);
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
        datasourceService.delete(idsDto);
        return ResponseResult.ok();
    }

    /**
     * 根据数据源 ID 查询数据源信息
     *
     * @param id 数据源ID
     * @return 响应结果
     */
    @GetMapping(path = "/{id}")
    public ResponseResult<DatasourceVo> get(@PathVariable Long id) {
        Datasource datasource = datasourceService.getById(id);
        return ResponseResult.ok(DatasourceConvert.INSTANCE.convert(datasource));
    }

    /**
     * 根据数据源 ID 测试数据源连接
     *
     * @param id 数据源ID
     * @return 响应结果
     */
    @GetMapping(path = "/test/{id}")
    public ResponseResult<String> test(@PathVariable Long id) {
        Datasource datasource = datasourceService.getById(id);
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
        List<Datasource> list = datasourceService.list();
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
        GenDatasource datasource = datasourceService.get(datasourceId);
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
        PageResult<DatasourceVo> page = datasourceService.page(datasourcePageDto);
        return ResponseResult.ok(page);
    }

}

