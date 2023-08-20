<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.brycehan.generator.core.mapper.BaseClassMapper">

	<!-- 通用查询映射结果 -->
	<resultMap id="BaseResultMap" type="com.brycehan.generator.core.entity.BaseClass">
		<id column="id" property="id"/>
		<result column="code" property="code"/>
		<result column="package_name" property="packageName"/>
		<result column="fields" property="fields"/>
		<result column="sort" property="sort"/>
		<result column="create_time" property="createTime"/>
		<result column="remark" property="remark"/>
		<result column="status" property="status"/>
	</resultMap>

	<!-- 通用查询结果列 -->
	<sql id="Base_Column_List">
		id, code, package_name, fields, sort, remark, create_time,status
	</sql>

</mapper>
