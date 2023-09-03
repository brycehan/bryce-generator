create database if not exists bryce_generator default charset utf8mb4;
use bryce_generator;

-- 1、数据源表
drop table if exists brc_gen_datasource;
create table brc_gen_datasource
(
    id          bigint       not null auto_increment comment 'ID',
    conn_name   varchar(100) not null comment '连接名称',
    db_type     varchar(50) comment '数据库类型',
    conn_url    varchar(200) comment '连接地址',
    username    varchar(100) comment '用户名',
    password    varchar(200) comment '密码',
    create_time datetime     default null comment '创建时间',
    primary key (id)
) engine InnoDB default charset utf8mb4 comment '数据源管理';

-- 2、字段类型表
drop table if exists brc_gen_field_type;
create table brc_gen_field_type
(
    id             bigint      not null auto_increment comment 'ID',
    column_type    varchar(50) not null comment '字段类型',
    attr_type varchar(50) not null comment '属性类型',
    package_name   varchar(200) comment '属性包名',
    remark         varchar(300) default null comment '备注',
    create_time    datetime     default null comment '创建时间',
    primary key (id),
    unique key (column_type)
) engine InnoDB default charset utf8mb4 comment '字段类型管理';

-- 初始化-字段类型表数据
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (1, 'bit', 'Boolean', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (2, 'tinyint', 'Boolean', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (3, 'smallint', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (4, 'mediumint', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (5, 'int', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (6, 'integer', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (7, 'NUMBER', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (8, 'BINARY_INTEGER', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (9, 'int2', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (10, 'int4', 'Integer', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (11, 'int8', 'Long', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (12, 'bigint', 'Long', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (13, 'float', 'Float', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (14, 'BINARY_FLOAT', 'Float', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (15, 'double', 'Double', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (16, 'BINARY_DOUBLE', 'Double', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (17, 'char', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (18, 'varchar', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (19, 'tinytext', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (20, 'text', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (21, 'mediumtext', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (22, 'longtext', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (23, 'VARCHAR2', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (24, 'NVARCHAR', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (25, 'NVARCHAR2', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (26, 'CLOB', 'String', null, null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (27, 'decimal', 'BigDecimal', 'java.math.BigDecimal', null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (28, 'numeric', 'BigDecimal', 'java.math.BigDecimal', null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (29, 'date', 'LocalDate', 'java.time.LocalDate', null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (30, 'datetime', 'LocalDateTime', 'java.time.LocalDateTime', null, now());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (31, 'timestamp', 'LocalDateTime', 'java.time.LocalDateTime', null, now());

-- 3、基类表
drop table if exists brc_gen_base_class;
create table brc_gen_base_class
(
    id           bigint       not null auto_increment comment 'ID',
    code   varchar(100) not null comment '基类编码',
    package_name varchar(200) not null comment '基类包名',
    fields       varchar(500) comment '基类字段（多个用英文逗号分隔）',
    remark       varchar(300) default null comment '备注',
    create_time  datetime     default null comment '创建时间',
    primary key (id)
) engine InnoDB default charset utf8mb4 comment '基类管理';

-- 初始化-基类表数据
INSERT INTO brc_gen_base_class (id, code, package_name, fields, remark, create_time) VALUES (1, 'BaseEntity', 'com.brycehan.boot.common.base.entity', 'id,version,deleted,created_user_id,created_time,updated_user_id,updated_time', '使用该基类，则需要表里有这些字段', now());

-- 4、代码生成表
drop table if exists brc_gen_table;
create table brc_gen_table
(
    id             bigint       not null auto_increment comment 'ID',
    table_name     varchar(200) not null comment '表名',
    class_name     varchar(200) not null comment '类名',
    table_comment  varchar(200) comment '说明',
    author         varchar(100) comment '作者',
    email          varchar(100) comment '邮箱',
    package_name   varchar(200) not null comment '项目包名',
    version        varchar(20)  not null comment '项目版本号',
    generator_type tinyint comment '生成方式（0：zip压缩包，1：自定义目录）',
    backend_path   varchar(300) comment '后端生成路径',
    frontend_path  varchar(300) comment '前端生成路径',
    module_name    varchar(100) comment '模块名',
    function_name  varchar(100) comment '功能名',
    form_layout    tinyint comment '表单布局（1：一列，2：两列）',
    datasource_id  bigint       not null comment '数据源ID',
    base_class_id   bigint       comment '基类ID',
    remark         varchar(300) default null comment '备注',
    create_time    datetime     default null comment '创建时间',
    primary key (id)
) engine InnoDB default charset utf8mb4 comment '代码生成表';

-- 5、代码生成表字段
drop table if exists brc_gen_table_field;
create table brc_gen_table_field
(
    id               bigint       not null auto_increment comment 'ID',
    table_id         bigint       not null comment '表ID',
    field_name       varchar(200) not null comment '字段名称',
    field_type       varchar(100) not null comment '字段类型',
    field_comment    varchar(200) comment '字段说明',
    attr_name       varchar(200) not null comment '属性名',
    attr_type       varchar(100) not null comment '属性类型',
    package_name     varchar(200) comment '属性包名',
    sort            int  default '0' comment '排序',
    auto_fill        varchar(20) comment '自动填充（DEFAULT, INSERT, UPDATE, INSERT_UPDATE）',
    primary_key      tinyint comment '主键（0：否，1：是）',
    character_maximum_length bigint comment '字符最大长度',
    base_field       tinyint comment '基类字段（0：否，1：是）',
    form_item        tinyint comment '表单项（0：否，1：是）',
    form_item_type   varchar(200) comment '表单项类型',
    form_dict        varchar(200) comment '表单字典类型',
    form_required    tinyint comment '表单必填（0：否，1：是）',
    form_validator   varchar(200) comment '表单校验器',
    grid_item        tinyint comment '列表项（0：否，1：是）',
    grid_sort tinyint comment '列表排序（0：否，1：是）',
    query_item       tinyint comment '查询项（0：否，1：是）',
    query_type       varchar(200) comment '查询方式',
    query_form_type  varchar(200) comment '查询表单类型',
    create_time      datetime default null comment '创建时间',
    primary key (id)
) engine InnoDB default charset utf8mb4 comment '代码生成表字段';

-- 6、项目名变更表
drop table if exists brc_gen_project_modify;
create table brc_gen_project_modify
(
    id                     bigint       not null auto_increment comment 'ID',
    project_name           varchar(100) not null comment '项目名',
    project_code           varchar(100) not null comment '项目标识',
    project_package        varchar(100) not null comment '项目包名',
    project_path           varchar(200) not null comment '项目路径',
    modify_project_name    varchar(100) comment '变更项目名',
    modify_project_code    varchar(100) comment '变更项目标识',
    modify_project_package varchar(100) comment '变更项目包名',
    exclusions             varchar(200) comment '排除文件',
    modify_suffix          varchar(200) comment '变更文件',
    modify_tmp_path       varchar(100) comment '变更临时路径',
    create_time            datetime default null comment '创建时间',
    primary key (id)
) engine InnoDB default charset utf8mb4 comment '项目名变更';

-- 初始化-项目名变更表数据
insert into brc_gen_project_modify (id,project_name,project_code,project_package,project_path,modify_project_name,modify_project_code,modify_project_package,exclusions,modify_suffix,modify_tmp_path,create_time) values (2,'bryce-boot','bryce-boot','com.brycehan.boot','/Users/brycehan/git/bryce-boot','bryce-boo','br-boo','com.br.boo','.git,.idea,target,logs','java,xml,yml,,factories,md,txt',NULL,now());
