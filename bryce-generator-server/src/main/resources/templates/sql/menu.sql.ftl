<#assign now = "now()">
<#if dbType=="SQLServer">
	<#assign now = "getDate()">
</#if>
<#assign uuid = "uuid()">
<#if dbType=="PostgreSQL">
    <#assign uuid = "gen_random_uuid()">
</#if>
-- 添加菜单
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '${tableComment!}', 'M', @parentId, 'icon-menu', '${moduleName}/${functionName}/index', null, 0, '1', '1', '${moduleName}:${functionName}:page', '0', '1', '1', '1', 'admin', ${now}, null, null, null);

-- 按钮父菜单ID
set @parentId = LAST_INSERT_ID();

-- 按钮
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '查询', 'B', '100', '', null, null, '0', '1', '1', '${moduleName}:${functionName}:info', '0', '1', '1', '1', 'admin', ${now}, null, null, null);
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '新增', 'B', '100', '',null, null, '0', '1', '1', '${moduleName}:${functionName}:add', '0', '2', '1', '1', 'admin', ${now}, null, null, null);
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '修改', 'B', '100', '', null, null, '0', '1', '1', '${moduleName}:${functionName}:update', '0', '3', '1', '1', 'admin', ${now}, null, null, null);
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '删除', 'B', '100', '', null, null, '0', '1', '1', '${moduleName}:${functionName}:delete', '0', '4', '1', '1', 'admin', ${now}, null, null, null);
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '导出', 'B', '100', '', null, null, '0', '1', '1', '${moduleName}:${functionName}:export', '0', '5', '1', '1', 'admin', ${now}, null, null, null);
insert into brc_sys_menu (id, menu_name, menu_type, parent_id, icon, url, query, is_frame, is_cache, visible, permission, delete_flag, sort, status, create_user_id, create_time, update_user_id, update_time, remark)
values (${uuid}, '导入', 'B', '100', '', null, null, '0', '1', '1', '${moduleName}:${functionName}:import', '0', '6', '1', '1', 'admin', ${now}, null, null, null);