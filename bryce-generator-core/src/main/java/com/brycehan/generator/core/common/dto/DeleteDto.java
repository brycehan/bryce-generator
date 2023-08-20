package com.brycehan.generator.core.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 删除数据传输对象
 *
 * @author Bryce Han
 * @since 2022/10/31
 */
@Data
public class DeleteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * IDs
     */
    @Size(min = 1, max = 10000)
    @NotNull
    private List<String> ids;

}
