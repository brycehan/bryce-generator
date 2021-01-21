package com.brycehan.generator.mapper;

import com.brycehan.generator.entity.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneratorMapper {

    List<Table> findPage();
}
