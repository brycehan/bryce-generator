package com.brycehan.generator.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.generator.core.entity.BaseClass;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基类管理Mapper接口
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Mapper
public interface BaseClassMapper extends BaseMapper<BaseClass> {

}
