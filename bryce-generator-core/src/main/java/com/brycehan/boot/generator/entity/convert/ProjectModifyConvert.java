package com.brycehan.boot.generator.entity.convert;

import com.brycehan.boot.generator.entity.dto.ProjectModifyDto;
import com.brycehan.boot.generator.entity.vo.ProjectModifyVo;
import com.brycehan.boot.generator.entity.po.ProjectModify;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 项目名变更转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface ProjectModifyConvert {

    ProjectModifyConvert INSTANCE = Mappers.getMapper(ProjectModifyConvert.class);

    ProjectModify convert(ProjectModifyDto projectModifyDto);

    ProjectModifyVo convert(ProjectModify projectModify);

    List<ProjectModifyVo> convert(List<ProjectModify> projectModifyList);

}
