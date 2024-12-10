package com.brycehan.boot.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brycehan.boot.generator.config.GenDatasource;
import com.brycehan.boot.generator.entity.dto.DatasourceDto;
import com.brycehan.boot.generator.entity.dto.DatasourcePageDto;
import com.brycehan.boot.generator.entity.vo.DatasourceVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.convert.DatasourceConvert;
import com.brycehan.boot.generator.entity.po.Datasource;
import com.brycehan.boot.generator.mapper.DatasourceMapper;
import com.brycehan.boot.generator.service.DatasourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


/**
 * 数据源管理服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, Datasource> implements DatasourceService {

    private final DatasourceMapper datasourceMapper;

    private final DataSource dataSource;

    @Override
    public void save(DatasourceDto datasourceDto) {
        Datasource datasource = DatasourceConvert.INSTANCE.convert(datasourceDto);
        this.datasourceMapper.insert(datasource);
    }

    @Override
    public void update(DatasourceDto datasourceDto) {
        Datasource datasource = DatasourceConvert.INSTANCE.convert(datasourceDto);
        this.datasourceMapper.updateById(datasource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(IdsDto idsDto) {
        // 过滤空数据
        List<Long> ids = idsDto.getIds()
                .stream()
                .filter(Objects::nonNull)
                .toList();
        if (CollectionUtils.isEmpty(ids)) {
            throw new RuntimeException("参数无效");
        }
        // 删除
        removeByIds(ids);
    }

    @Override
    public GenDatasource get(Long datasourceId) {
        // 初始化配置
        GenDatasource datasource = null;
        // 默认数据源
        if (datasourceId.intValue() == 0) {
            try {
                datasource = new GenDatasource(this.dataSource.getConnection());
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            datasource = new GenDatasource(this.datasourceMapper.selectById(datasourceId));
        }
        return datasource;
    }

    @Override
    public PageResult<DatasourceVo> page(DatasourcePageDto datasourcePageDto) {

        IPage<Datasource> page = this.datasourceMapper.selectPage(datasourcePageDto.toPage(), getWrapper(datasourcePageDto));

        return new PageResult<>(page.getTotal(), DatasourceConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param datasourcePageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<Datasource> getWrapper(DatasourcePageDto datasourcePageDto) {
        LambdaQueryWrapper<Datasource> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(datasourcePageDto.getConnName()), Datasource::getConnName, datasourcePageDto.getConnName());
        wrapper.like(StrUtil.isNotBlank(datasourcePageDto.getDbType()), Datasource::getDbType, datasourcePageDto.getDbType());
        return wrapper;
    }

}
