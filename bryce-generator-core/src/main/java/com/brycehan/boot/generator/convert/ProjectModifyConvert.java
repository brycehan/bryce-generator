package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.ProjectModifyDto;
import com.brycehan.boot.generator.vo.ProjectModifyVo;
import com.brycehan.boot.generator.entity.ProjectModify;
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
