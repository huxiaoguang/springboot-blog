package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeSearchDTO extends PageDTO
{
    /**默认选中*/
    private List<Integer> checked = new ArrayList<>();

    /**节点名称*/
    private String field = "menuId";
}
