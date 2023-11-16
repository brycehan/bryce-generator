package com.brycehan.boot.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brycehan.boot.generator.dto.BaseClassDto;
import com.brycehan.boot.generator.dto.BaseClassPageDto;
import com.brycehan.boot.generator.vo.BaseClassVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.common.service.impl.BaseServiceImpl;
import com.brycehan.boot.generator.convert.BaseClassConvert;
import com.brycehan.boot.generator.entity.BaseClass;
import com.brycehan.boot.generator.mapper.BaseClassMapper;
import com.brycehan.boot.generator.service.BaseClassService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 基类管理服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Service
@RequiredArgsConstructor
public class BaseClassServiceImpl extends BaseServiceImpl<BaseClassMapper, BaseClass> implements BaseClassService {

    private final BaseClassMapper baseClassMapper;

    @Override
    public void save(BaseClassDto baseClassDto) {
        BaseClass baseClass = BaseClassConvert.INSTANCE.convert(baseClassDto);
        this.baseClassMapper.insert(baseClass);
    }

    @Override
    public void update(BaseClassDto baseClassDto) {
        BaseClass baseClass = BaseClassConvert.INSTANCE.convert(baseClassDto);
        this.baseClassMapper.updateById(baseClass);
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
    public PageResult<BaseClassVo> page(@NotNull BaseClassPageDto baseClassPageDto) {

        IPage<BaseClass> page = this.baseClassMapper.selectPage(getPage(baseClassPageDto), getWrapper(baseClassPageDto));

        return new PageResult<>(page.getTotal(), BaseClassConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param baseClassPageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<BaseClass> getWrapper(BaseClassPageDto baseClassPageDto) {
        LambdaQueryWrapper<BaseClass> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(baseClassPageDto.getCode()), BaseClass::getCode, baseClassPageDto.getCode());
        return wrapper;
    }

}
