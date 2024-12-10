//School of Informatics Xiamen University, GPL-3.0 license
package com.xmu.exp7_mongo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 状态DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDto {
    /**
     * 状态码
     */
    private Byte code;
    /**
     * 状态名称
     */
    private String name;
}
