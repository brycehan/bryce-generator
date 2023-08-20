package ${packageName}.${moduleName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${packageName}.common.base.entity.PageResult;
import ${packageName}.common.base.http.HttpResponseStatusEnum;
import ${packageName}.common.base.id.IdGenerator;
import ${packageName}.common.exception.BusinessException;
import ${packageName}.common.service.impl.BaseServiceImpl;
import ${packageName}.common.util.ExcelUtils;
import ${packageName}.${moduleName}.convert.${convertName};
import ${packageName}.${moduleName}.dto.DeleteDto;
import ${packageName}.${moduleName}.dto.${entityName}Dto;
import ${packageName}.${moduleName}.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.mapper.SysPostMapper;
import ${packageName}.${moduleName}.service.SysPostService;
import ${packageName}.${moduleName}.vo.${entityName}Vo;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
* ${tableComment}服务实现类
*
* @author ${author}
* @since ${date}
*/
@Service
@RequiredArgsConstructor
public class SysPostServiceImpl extends BaseServiceImpl
<SysPostMapper, ${entityName}> implements SysPostService {

private final SysPostMapper ${mapperName};

@Override
public void save(${entityName}Dto ${entityParam}Dto) {
${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
${entityParam}.setId(IdGenerator.generate());
this.${mapperName}.insert(${entityParam});
}

@Override
public void update(${entityName}Dto ${entityParam}Dto) {
${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
this.${mapperName}.updateById(${entityParam});
}

@Override
@Transactional(rollbackFor = Exception.class)
public void delete(DeleteDto deleteDto) {
// 过滤空数据
List
<String> ids = deleteDto.getIds()
	.stream()
	.filter(StringUtils::isNotBlank)
	.toList();
	if(CollectionUtils.isNotEmpty(ids)){
	throw BusinessException.responseStatus(HttpResponseStatusEnum.HTTP_BAD_REQUEST);
	}
	removeBatchByIds(ids);
	}


	@Override
	public PageResult
	<${entityName}Vo> page(${entityPageDtoName} ${entityParam}PageDto) {


		IPage<${entityName}> page = this.${mapperName}.selectPage(getPage(${entityParam}PageDto), getWrapper(${entityParam}
		PageDto));

		return new PageResult<>(page.getTotal(), ${convertName}.INSTANCE.convert(page.getRecords()));
		}

		@Override
		public void export(${entityPageDtoName} ${entityParam}PageDto) {
		List<${entityName}> ${entityParam}s = this.${mapperName}.selectList(getWrapper(${entityParam}PageDto));
		List
		<${entityName}Vo> ${entityParam}Vos = ${convertName}.INSTANCE.convert(${entityParam}s);
			ExcelUtils.export(${entityName}Vo.class, "${tableComment}", "${tableComment}", ${entityParam}Vos);
			}

			/**
			* 封装查询条件
			*
			* @param ${entityParam}PageDto ${tableComment}分页dto
			* @return 查询条件Wrapper
			*/
			private Wrapper<${entityName}> getWrapper(${entityPageDtoName} ${entityParam}PageDto){
			LambdaQueryWrapper<${entityName}> wrapper = new LambdaQueryWrapper<>();
			wrapper.eq(Objects.nonNull(${entityParam}PageDto.getStatus()), ${entityName}::getStatus, ${entityParam}
			PageDto.getStatus());
			return wrapper;
			}

			}
