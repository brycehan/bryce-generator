package com.brycehan.boot.generator.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页结果数据
 *
 * @author Bryce Han
 * @since 2023/4/10
 */
@Data
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private long total;
}
