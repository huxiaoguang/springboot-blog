package main.blog.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MenuSearchDTO extends PageDTO
{
    private String keywords;
}
