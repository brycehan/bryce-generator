package com.brycehan.generator.core.convert;

import com.brycehan.generator.core.dto.ProjectModifyDto;
import com.brycehan.generator.core.entity.ProjectModify;
import com.brycehan.generator.core.vo.ProjectModifyVo;
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
