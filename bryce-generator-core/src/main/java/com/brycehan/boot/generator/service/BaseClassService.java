package com.brycehan.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.boot.generator.entity.dto.BaseClassDto;
import com.brycehan.boot.generator.entity.dto.BaseClassPageDto;
import com.brycehan.boot.generator.entity.vo.BaseClassVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.po.BaseClass;
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
     * @param idsDto 基类删除Dto
     */
    void delete(IdsDto idsDto);

    /**
     * 基类分页查询信息
     *
     * @param baseClassPageDto 基类分页搜索条件
     * @return 分页信息
     */
    PageResult<BaseClassVo> page(@NotNull BaseClassPageDto baseClassPageDto);

}
