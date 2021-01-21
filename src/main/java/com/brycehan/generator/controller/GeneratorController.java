package com.brycehan.generator.controller;

import com.brycehan.generator.entity.Table;
import com.brycehan.generator.service.GeneratorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping(value = "test")
    @ResponseBody
    public PageInfo<Table> test(){
        PageInfo<Table> page = PageHelper.startPage(1, 10)
                .doSelectPageInfo(() -> generatorService.findPage());
            return page;
    }
}
