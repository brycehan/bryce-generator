package com.brycehan.generator.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Table implements Serializable {
    private String name;
    private String engine;
    private String comment;
    private LocalDateTime createTime;
}
