package main.blog.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageDTO
{
    /**
     * 页码
     */
    @JsonProperty("page")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @JsonProperty("size")
    private Integer limit = 15;
}
