package ${packageName}.${moduleName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.common.base.entity.PageResult;
import ${packageName}.${moduleName}.dto.DeleteDto;
import ${packageName}.${moduleName}.dto.${entityName}Dto;
import ${packageName}.${moduleName}.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.vo.${entityName}Vo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
* ${tableComment}服务
*
* @author ${author}
* @since ${date}
*/
@Validated
public interface SysPostService extends IService<${entityName}> {

/**
* 添加${tableComment}
*
* @param ${entityParam}Dto ${tableComment}Dto
*/
void save(${entityName}Dto ${entityParam}Dto);

/**
* 更新${tableComment}
*
* @param ${entityParam}Dto ${tableComment}Dto
*/
void update(${entityName}Dto ${entityParam}Dto);

/**
* 删除${tableComment}
*
* @param deleteDto ${tableComment}删除Dto
*/
void delete(DeleteDto deleteDto);

/**
* ${tableComment}分页查询信息
*
* @param ${entityParam}PageDto ${tableComment}分页搜索条件
* @return 分页信息
*/
PageResult
<${entityName}Vo> page(@NotNull ${entityPageDtoName} ${entityParam}PageDto);

	/**
	* ${tableComment}导出数据
	*
	* @param ${entityParam}PageDto ${tableComment}分页搜索条件
	* @return ${tableComment}列表
	*/
	void export(@NotNull ${entityPageDtoName} ${entityParam}PageDto);

	}
