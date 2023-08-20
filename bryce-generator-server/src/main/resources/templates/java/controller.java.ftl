package ${packageName}.${moduleName}.controller;

import ${packageName}.common.base.entity.PageResult;
import ${packageName}.common.base.http.ResponseResult;
import ${packageName}.common.validator.group.AddGroup;
import ${packageName}.common.validator.group.UpdateGroup;
import ${packageName}.${moduleName}.convert.${convertName};
import ${packageName}.${moduleName}.dto.DeleteDto;
import ${packageName}.${moduleName}.dto.${entityName}Dto;
import ${packageName}.${moduleName}.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.service.SysPostService;
import ${packageName}.${moduleName}.vo.${entityName}Vo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* ${tableComment}API
*
* @author ${author}
* @since ${date}
*/
@Tag(name = "${entityParam}", description = "${tableComment}API")
@RequestMapping("/${moduleName}/${functionName}")
@RestController
@RequiredArgsConstructor
public class ${controllerName} {

private final ${serviceName} ${serviceParam};

/**
* 保存${tableComment}
*
* @param ${entityParam}Dto ${tableComment}Dto
* @return 响应结果
*/
@Operation(summary = "保存${tableComment}")
@Secured("${moduleName}:${functionName}:add")
@PostMapping
public ResponseResult
<Void> add(@Parameter(description = "${tableComment}", required = true)
	@Validated(value = AddGroup.class) @RequestBody ${entityName}Dto ${entityParam}Dto) {
	this.${serviceParam}.save(${entityParam}Dto);
	return ResponseResult.ok();
	}

	/**
	* 更新${tableComment}
	*
	* @param ${entityParam}Dto ${tableComment}Dto
	* @return 响应结果
	*/
	@Operation(summary = "更新${tableComment}")
	@Secured("${moduleName}:${functionName}:update")
	@PutMapping
	public ResponseResult
	<Void> update(@Parameter(description = "${tableComment}实体", required = true) @Validated(value = UpdateGroup.class)
		@RequestBody ${entityName}Dto ${entityParam}Dto) {
		this.${serviceParam}.update(${entityParam}Dto);
		return ResponseResult.ok();
		}

		/**
		* 删除${tableComment}
		*
		* @param deleteDto ${tableComment}删除Dto
		* @return 响应结果
		*/
		@Operation(summary = "删除${tableComment}")
		@Secured("${moduleName}:${functionName}:delete")
		@DeleteMapping
		public ResponseResult
		<Void> delete(@Parameter(description = "${tableComment}Dto", required = true)
			@Validated @RequestBody DeleteDto deleteDto) {
			this.${serviceParam}.delete(deleteDto);
			return ResponseResult.ok();
			}


			/**
			* 根据${tableComment}ID查询${tableComment}信息
			*
			* @param id ${tableComment}ID
			* @return 响应结果
			*/
			@Operation(summary = "根据${tableComment}ID查询${tableComment}详情")
			@Secured("${moduleName}:${functionName}:info")
			@GetMapping(path = "/{id}")
			public ResponseResult
			<${entityName}Vo> get(@Parameter(description = "${tableComment}ID", required = true)
				@PathParam String id) {
          ${entityName} ${entityParam} = this.${serviceParam}.getById(id);
				return ResponseResult.ok(${convertName}.INSTANCE.convert(${entityParam}));
				}

				/**
				* 分页查询
				*
				* @param ${entityParam}PageDto 查询条件
				* @return ${tableComment}分页列表
				*/
				@Operation(summary = "分页查询")
				@Secured("${moduleName}:${functionName}:page")
				@PostMapping(path = "/page")
				public ResponseResult
				<PageResult
				<${entityName}Vo>> page(@Parameter(description = "查询信息", required = true)
					@Validated @RequestBody ${entityPageDtoName} ${entityParam}PageDto) {
					PageResult
					<${entityName}Vo> page = this.${serviceParam}.page(${entityParam}PageDto);
						return ResponseResult.ok(page);
						}


						/**
						* ${tableComment}导出数据
						*
						* @param ${entityParam}PageDto 查询条件
						*/
						@Operation(summary = "${tableComment}导出")
						@Secured("${moduleName}:${functionName}:export")
						@PostMapping(path = "/export")
						public void export(@Parameter(description = "查询信息", required = true)
						@Validated @RequestBody ${entityPageDtoName} ${entityParam}PageDto) {
						this.${serviceParam}.export(${entityParam}PageDto);
						}

						}

