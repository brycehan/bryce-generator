<#assign now = "now()">
<#if dbType=="SQLServer">
  <#assign now = "getDate()">
</#if>
<#assign nextId = "(select max(id)+1 from brc_sys_menu)">
<#if dbType=="MySQL">
  <#assign nextId = "(select maxId from (select max(id)+1 as maxId from brc_sys_menu) as t)">
</#if>
<#assign menuName = "${(tableComment!?length > 2)?string(tableComment?remove_beginning('系统'), tableComment!)}">
-- 添加菜单
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}管理', 'M', 2, '${moduleName}/${hyphenName}/index', '${moduleName}:${hyphenName}:page', 'icon-menu', 0, 0, null, 1, 1, null, 1, ${now}, 1, ${now});

-- 按钮父菜单ID
set @parentId := (select max(id) from brc_sys_menu);

-- 按钮
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}新增', 'B', @parentId,null, '${moduleName}:${hyphenName}:save', null, 0, 1, null, 1, 1, null, 1, ${now}, 1, ${now});
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}修改', 'B', @parentId, null, '${moduleName}:${hyphenName}:update', null, 0, 2, null, 1, 1, null, 1, ${now}, 1, ${now});
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}删除', 'B', @parentId, null, '${moduleName}:${hyphenName}:delete', null, 0, 3, null, 1, 1, null, 1, ${now}, 1, ${now});
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}详情', 'B', @parentId, null, '${moduleName}:${hyphenName}:info', null, 0, 4, null, 1, 1, null, 1, ${now}, 1, ${now});
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}导出', 'B', @parentId, null, '${moduleName}:${hyphenName}:export', null, 0, 5, null, 1, 1, null, 1, ${now}, 1, ${now});
INSERT INTO brc_sys_menu (id, name, type, parent_id, url, authority, icon, open_style, sort, remark, visible, status, deleted, created_user_id, created_time, updated_user_id, updated_time) VALUES (${nextId}, '${menuName}导入', 'B', @parentId, null, '${moduleName}:${hyphenName}:import', null, 0, 6, null, 1, 1, null, 1, ${now}, 1, ${now});