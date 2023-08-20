package com.brycehan.generator.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.dto.BaseClassDto;
import com.brycehan.generator.core.dto.BaseClassPageDto;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.vo.BaseClassVo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * 基类管理服务
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface BaseClassService extends IService<BaseClass> {

    /**
     * 添加基类
     *
     * @param baseClassDto 基类Dto
     */
    void save(BaseClassDto baseClassDto);

    /**
     * 更新基类
     *
     * @param baseClassDto 基类Dto
     */
    void update(BaseClassDto baseClassDto);

    /**
     * 删除基类
     *
     * @param deleteDto 基类删除Dto
     */
    void delete(DeleteDto deleteDto);

    /**
     * 基类分页查询信息
     *
     * @param baseClassPageDto 基类分页搜索条件
     * @return 分页信息
     */
    PageResult<BaseClassVo> page(@NotNull BaseClassPageDto baseClassPageDto);

}
