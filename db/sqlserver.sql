/*
    -- 创建库
    create database if not exists bryce_generator default charset utf8mb4;
    use bryce_generator;
    -- 删除表
    drop table if exists brc_gen_datasource;
    drop table if exists brc_gen_field_type;
    drop table if exists brc_gen_base_class;
    drop table if exists brc_gen_table;
    drop table if exists brc_gen_table_field;
    drop table if exists brc_gen_project_modify;
 */

-- 1、数据源表
create table brc_gen_datasource
(
    id          bigint          identity(1, 1) not null,
    conn_name   varchar(100)    not null,
    db_type     varchar(50),
    conn_url    varchar(200),
    username    varchar(100),
    password    varchar(200),
    create_time datetime,
    primary key (id)
);

-- 2、字段类型表
create table brc_gen_field_type
(
    id           bigint         identity(1, 1) not null,
    column_type  varchar(50)    not null,
    attr_type    varchar(50)    not null,
    package_name varchar(200),
    remark       varchar(300),
    create_time  datetime,
    primary key (id)
);

create unique index uk_brc_gen_column_type on brc_gen_field_type(column_type);

-- 初始化-字段类型表数据
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (1, 'bit', 'Boolean', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (2, 'tinyint', 'Boolean', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (3, 'smallint', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (4, 'mediumint', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (5, 'int', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (6, 'integer', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (7, 'NUMBER', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (8, 'BINARY_INTEGER', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (9, 'int2', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (10, 'int4', 'Integer', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (11, 'int8', 'Long', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (12, 'bigint', 'Long', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (13, 'float', 'Float', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (14, 'BINARY_FLOAT', 'Float', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (15, 'double', 'Double', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (16, 'BINARY_DOUBLE', 'Double', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (17, 'char', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (18, 'varchar', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (19, 'tinytext', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (20, 'text', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (21, 'mediumtext', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (22, 'longtext', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (23, 'VARCHAR2', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (24, 'NVARCHAR', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (25, 'NVARCHAR2', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (26, 'CLOB', 'String', null, null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (27, 'decimal', 'BigDecimal', 'java.math.BigDecimal', null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (28, 'numeric', 'BigDecimal', 'java.math.BigDecimal', null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (29, 'date', 'LocalDate', 'java.time.LocalDate', null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (30, 'datetime', 'LocalDateTime', 'java.time.LocalDateTime', null, getdate());
insert into brc_gen_field_type (id, column_type, attr_type, package_name, remark, create_time) values (31, 'timestamp', 'LocalDateTime', 'java.time.LocalDateTime', null, getdate());

-- 3、基类表
create table brc_gen_base_class
(
    id           bigint         identity(1, 1) not null,
    code         varchar(100)   not null,
    package_name varchar(200)   not null,
    fields       varchar(500),
    remark       varchar(300),
    create_time  datetime,
    primary key (id)
);

-- 初始化-基类表数据
insert into brc_gen_base_class (id,code,package_name,fields,remark,create_time) values (1,'BaseEntity','com.brycehan.boot.common.base.entity','id,create_user_id,create_time,update_user_id,update_time','使用该基类，则需要表里有这些字段', getdate());

-- 4、代码生成表
create table brc_gen_table
(
    id             bigint       identity(1, 1) not null,
    table_name     varchar(200) not null,
    class_name     varchar(200) not null,
    table_comment  varchar(200),
    author         varchar(100),
    email          varchar(100),
    package_name   varchar(200) not null,
    version        varchar(20),
    generator_type int,
    backend_path   varchar(300),
    frontend_path  varchar(300),
    module_name    varchar(100),
    function_name  varchar(100),
    form_layout    int,
    datasource_id  bigint       not null,
    base_class_id   bigint,
    remark         varchar(300),
    create_time    datetime,
    primary key (id)
);

create unique index uk_brc_gen_table_name on brc_gen_table(table_name);

-- 5、代码生成表字段
create table brc_gen_table_field
(
    id              bigint          identity(1, 1) not null,
    table_id        bigint          not null,
    field_name      varchar(200)    not null,
    field_type      varchar(100)    not null,
    field_comment   varchar(200),
    attr_name       varchar(200)    not null,
    attr_type       varchar(100)    not null,
    package_name    varchar(200),
    sort            number(10, 0),
    auto_fill       varchar(20),
    primary_key     int,
    character_maximum_length bigint,
    base_field      int,
    form_item       int,
    form_item_type  varchar(200),
    form_dict       varchar(200),
    form_required   int,
    form_validator  varchar(200),
    grid_item       int,
    grid_sort       int,
    query_item      int,
    query_type      varchar(200),
    query_form_type varchar(200),
    create_time     datetime,
    primary key (id)
);

-- 6、项目名变更表
create table brc_gen_project_modify
(
    id                      bigint          identity(1, 1) not null,
    project_name            varchar(100)    not null,
    project_code            varchar(100)    not null,
    project_package         varchar(100)    not null,
    project_path            varchar(200)    not null,
    modify_project_name     varchar(100),
    modify_project_code     varchar(100),
    modify_project_package  varchar(100),
    exclusions              varchar(200),
    modify_suffix           varchar(200),
    modify_tmp_path         varchar(100),
    create_time            datetime,
    primary key (id)
);

-- 初始化-项目名变更表数据
insert into brc_gen_project_modify (id,project_name,project_code,project_package,project_path,modify_project_name,modify_project_code,modify_project_package,exclusions,modify_suffix,modify_tmp_path,create_time) values (2,'bryce-boot','bryce-boot','com.brycehan.boot','/Users/brycehan/git/bryce-boot','bryce-boo','br-boo','com.br.boo','.git,.idea,target,logs','java,xml,yml,,factories,md,txt',NULL,getdate());
