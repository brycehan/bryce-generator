package com.brycehan.generator.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.common.service.impl.BaseServiceImpl;
import com.brycehan.generator.core.convert.BaseClassConvert;
import com.brycehan.generator.core.dto.BaseClassDto;
import com.brycehan.generator.core.dto.BaseClassPageDto;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.mapper.BaseClassMapper;
import com.brycehan.generator.core.service.BaseClassService;
import com.brycehan.generator.core.vo.BaseClassVo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    public void delete(DeleteDto deleteDto) {
        // 过滤空数据
        List<String> ids = deleteDto.getIds()
                .stream()
                .filter(StringUtils::isNotBlank)
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
