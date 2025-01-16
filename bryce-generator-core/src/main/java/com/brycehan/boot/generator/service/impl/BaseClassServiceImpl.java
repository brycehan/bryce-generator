package com.brycehan.boot.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brycehan.boot.generator.entity.dto.BaseClassDto;
import com.brycehan.boot.generator.entity.dto.BaseClassPageDto;
import com.brycehan.boot.generator.entity.vo.BaseClassVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.convert.BaseClassConvert;
import com.brycehan.boot.generator.entity.po.BaseClass;
import com.brycehan.boot.generator.mapper.BaseClassMapper;
import com.brycehan.boot.generator.service.BaseClassService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.util.StrUtil;
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
public class BaseClassServiceImpl extends ServiceImpl<BaseClassMapper, BaseClass> implements BaseClassService {

    private final BaseClassMapper baseClassMapper;

    @Override
    public void save(BaseClassDto baseClassDto) {
        BaseClass baseClass = BaseClassConvert.INSTANCE.convert(baseClassDto);
        baseClassMapper.insert(baseClass);
    }

    @Override
    public void update(BaseClassDto baseClassDto) {
        BaseClass baseClass = BaseClassConvert.INSTANCE.convert(baseClassDto);
        baseClassMapper.updateById(baseClass);
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

        IPage<BaseClass> page = baseClassMapper.selectPage(baseClassPageDto.toPage(), getWrapper(baseClassPageDto));

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
        wrapper.like(StrUtil.isNotBlank(baseClassPageDto.getCode()), BaseClass::getCode, baseClassPageDto.getCode());
        return wrapper;
    }

}
