package com.brycehan.generator.service;

import com.brycehan.generator.entity.Table;
import com.brycehan.generator.mapper.GeneratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    public List<Table> findPage(){
        return generatorMapper.findPage();
    }
}
