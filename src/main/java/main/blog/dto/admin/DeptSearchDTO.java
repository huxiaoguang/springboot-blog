package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptSearchDTO extends PageDTO
{
    /**
     * 关键词
     */
    private String keywords;

    /**
     * 部门状态
     */
    private Integer status;
}
