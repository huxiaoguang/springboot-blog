package main.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessStatus
{
    SUCCESS(  1, "成功"),
    FAIL( 2, "失败");

    private final Integer code;
    private final String info;
}
